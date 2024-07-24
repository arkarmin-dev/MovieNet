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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        public String updatePoster(@PathVariable("id") Long movieId, @RequestParam("posterFile") MultipartFile posterFile, Model model, RedirectAttributes redirectAttributes) {
            Movie movie = movieService.getMovieById(movieId);
            if (!posterFile.isEmpty()) {
                // Validate file type
                String contentType = posterFile.getContentType();
                if (!isImageFile(contentType)) {
                    redirectAttributes.addFlashAttribute("error", "Invalid file type. Only JPG and PNG are allowed.");
                    /*model.addAttribute("error", "Invalid file type. Only JPG and PNG are allowed.");*/
                    return "redirect:/admin/movie/" + movieId;
                }

                // Validate file size (limit to 2MB for example)
                if (posterFile.getSize() > 2 * 1024 * 1024) {
                    redirectAttributes.addFlashAttribute("error", "File size too large. The maximum allowed size is 2MB.");
                   /* model.addAttribute("error", "File size too large. The maximum allowed size is 2MB.");*/
                    return "redirect:/admin/movie/" + movieId;
                }
                String posterUrl = movieService.savePosterFile(posterFile);
                movie.setPosterUrl(posterUrl);
                movieRepository.save(movie);
                redirectAttributes.addFlashAttribute("success", "Poster updated successfully!");
            }
            return "redirect:/admin/movie/" + movieId;
        }
    private boolean isImageFile(String contentType) {
        return contentType.equals("image/jpeg") || contentType.equals("image/png");
    }
    private boolean isVideoFile(String contentType) {
        return contentType.startsWith("video/");
    }

    @PostMapping("/updateMovie/{id}")
    public String updateMovie(@ModelAttribute Movie movie, @PathVariable Long id,Model model,RedirectAttributes redirectAttributes,@RequestParam("genreId") List<Long> genreIds) {
        List<Genre> genres = genreService.findGenresByIds(genreIds);
        Movie existingMovie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie ID:" + id));


        existingMovie.setTitle(movie.getTitle());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        existingMovie.setImdb_rating(movie.getImdb_rating());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setPlot(movie.getPlot());
        existingMovie.setGenres(genres);

        movieRepository.save(existingMovie);
        redirectAttributes.addFlashAttribute("success", "Movie updated successfully!");
        model.addAttribute("adm_gId", genreIds);

        return "redirect:/admin/movie/" + id; // Redirect to the updated movie's details page
    }



    @PostMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable("id") Long movieId,RedirectAttributes redirectAttributes) {
        movieService.deleteMovieById(movieId);
        redirectAttributes.addFlashAttribute("success", "Movie deleted successfully!");
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
                           @RequestParam("releaseYear") Integer releaseYear,
                           @RequestParam("imdb_rating") Double imdbRating,
                           @RequestParam("plot") String plot,
                           @RequestParam("director") String director,
                           @RequestParam("poster") MultipartFile poster,
                           @RequestParam("video") MultipartFile video,
                           @RequestParam("genreIds") List<Long> genreIds,
                           RedirectAttributes redirectAttributes) {
        if (title.isEmpty() || plot.isEmpty() || director.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Title, plot, and director are required fields.");
            return "redirect:/admin/addMovie";
        }

        if (releaseYear < 1888 || releaseYear > 2100) {
            redirectAttributes.addFlashAttribute("error", "Please enter a valid release year.");
            return "redirect:/admin/addMovie";
        }

        if (imdbRating < 0 || imdbRating > 10) {
            redirectAttributes.addFlashAttribute("error", "Please enter a valid IMDB rating (0-10).");
            return "redirect:/admin/addMovie";
        }

        if (poster.isEmpty() || !isImageFile(poster.getContentType())) {
            redirectAttributes.addFlashAttribute("error", "Please upload a valid poster file (JPG or PNG).");
            return "redirect:/admin/addMovie";
        }

        if (video.isEmpty() || !isVideoFile(video.getContentType())) {
            redirectAttributes.addFlashAttribute("error", "Please upload a valid video file.");
            return "redirect:/admin/addMovie";
        }

        movieService.addMovie(title, releaseYear, imdbRating, plot, director, poster, video, genreIds);
        redirectAttributes.addFlashAttribute("success", "Movie added successfully!");
        return "redirect:/admin/home";
    }



}
