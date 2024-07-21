package cgm.system.MovieNet.controller;

import cgm.system.MovieNet.entity.Genre;
import cgm.system.MovieNet.entity.Movie;
import cgm.system.MovieNet.entity.Review;
import cgm.system.MovieNet.repository.GenreRepository;
import cgm.system.MovieNet.repository.MovieRepository;
import cgm.system.MovieNet.repository.ReviewRepository;
import cgm.system.MovieNet.service.GenreService;
import cgm.system.MovieNet.service.MovieService;
import cgm.system.MovieNet.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
/*
    @GetMapping("/home")
    public String adminHome() {
        return "/user/userHome";
    }*/


    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GenreService genreService;

    private final Path fileStorageLocation;

    public AdminController(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Autowired
    private GenreRepository genreRepository;
    @GetMapping("/home")
    public String userHome(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            Model model){
        Page<Movie> moviePage = movieService.findPaginated(page, size);
        model.addAttribute("adm_moviePage", moviePage);
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("adm_genres", genres);

        return "/admin/adminHome";
    }



    @GetMapping("/search")
    public String searchMovie(@RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "genreId", required = false) Long genreId,  @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "20") int size,Model model) {

        PageRequest pageRequest = PageRequest.of(page, size);

        /* Page<Movie> moviePage = */
    List<Genre> genres = genreRepository.findAll();
        model.addAttribute("adm_genres", genres);

    Page<Movie> moviePage;
    List<Movie> movies;


        if ((title == null || title.isEmpty()) && genreId == null) {
        moviePage = movieService.findPaginated(page, size);
    } else if (genreId == null) {
        moviePage = movieService.searchByTitle(title, pageRequest);
    } else if (title == null || title.isEmpty()) {
        moviePage = movieService.searchByGenre(genreId, pageRequest);
    } else {
        movies = movieService.searchMovies(title, genreId);

        moviePage = movieService.setPage(movies, pageRequest);; // Adjust as per your actual search method
        model.addAttribute("adm_movies_genres", movies);
    }

        model.addAttribute("adm_ttl", title); // Add title to model for Thymeleaf
        model.addAttribute("adm_gId", genreId);

        model.addAttribute("adm_moviePage", moviePage);

        return "/admin/adminHome"; // Thymeleaf template name
}

        @GetMapping("/movie/{id}")
        public String showMovieDetails(@PathVariable("id") Long id, Model model, @RequestParam(value = "genreId", required = false) Long genreId) {
            Movie movie = movieRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid movie id: " + id));
            List<Review> reviews = reviewRepository.findByMovie(movie);
            model.addAttribute("adm_movie", movie);
            model.addAttribute("adm_reviews", reviews);
            List<Genre> genres = genreRepository.findAll();
            model.addAttribute("adm_genres", genres);
            model.addAttribute("adm_gId", genreId);
            /* model.addAttribute("newReview", new Review());*/

            return "admin-movie-details"; // Thymeleaf template name
        }

        @PostMapping("/updatePoster/{id}")
        public String updatePoster(@PathVariable("id") Long movieId, @RequestParam("posterFile") MultipartFile posterFile) {
            Movie movie = movieService.getMovieById(movieId);
            if (!posterFile.isEmpty()) {
                String posterUrl = savePosterFile(posterFile);
                movie.setPosterUrl(posterUrl);
                movieRepository.save(movie);
            }
            return "redirect:/admin/movie/" + movieId;
        }

    @PostMapping("/updateMovie/{id}")
    public String updateMovie(@ModelAttribute Movie movie, @PathVariable Long id,Model model,@RequestParam("genreId") List<Long> genreIds) {
        List<Genre> genres = genreService.findGenresByIds(genreIds);
        Movie existingMovie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie ID:" + id));


        existingMovie.setTitle(movie.getTitle());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        existingMovie.setImdb_rating(movie.getImdb_rating());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setPlot(movie.getPlot());
        existingMovie.setGenres(genres);

        movieRepository.save(existingMovie);
        model.addAttribute("adm_gId", genreIds);

        return "redirect:/admin/movie/" + id; // Redirect to the updated movie's details page
    }

        private String savePosterFile(MultipartFile file) {
            // Logic to save the file and return the URL
            // ...
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());

            try {
                if (fileName.contains("..")) {
                    throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
                }
                if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")) {
                    throw new RuntimeException("Invalid file type. Only JPG and PNG are allowed.");
                }

                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                return "/img/poster/"+fileName;

            } catch (IOException ex) {
                throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
            }

        }

    @PostMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable("id") Long movieId) {
        movieService.deleteMovieById(movieId);

        return "redirect:/admin/home";
    }

    @GetMapping("/addMovie")
    public String showAddMovieForm(Model model) {
        List<Genre> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        return "/admin/admin-add-movie"; // make sure this matches your actual HTML file name
    }


    @PostMapping("/addNewMovie")
    public String addMovie(@RequestParam("title") String title,
                           @RequestParam("releaseDate") Integer releaseDate,
                           @RequestParam("imdb_rating") Double imdbRating,
                           @RequestParam("plot") String plot,
                           @RequestParam("director") String director,
                           @RequestParam("poster") MultipartFile poster,
                           @RequestParam("video") MultipartFile video,
                           @RequestParam("genreIds") List<Long> genreIds) {

        // Save poster and video files
        String posterUrl = savePosterFile(poster);
        String videoUrl = saveFile(video);

        // Create new movie object
        Movie movie = new Movie(title, releaseDate, plot, imdbRating, director, posterUrl, videoUrl);

        // Add genres to the movie
        List<Genre> genres = genreService.findGenresByIds(genreIds);
        movie.setGenres(genres);

        // Save movie
        movieRepository.save(movie);

        return "redirect:/admin/home";
    }

    private String saveFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }


            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return "/img/poster/"+fileName;

        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }


}
