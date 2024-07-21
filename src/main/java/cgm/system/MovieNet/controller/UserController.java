package cgm.system.MovieNet.controller;

import cgm.system.MovieNet.entity.Genre;
import cgm.system.MovieNet.entity.Movie;
import cgm.system.MovieNet.entity.Review;
import cgm.system.MovieNet.repository.GenreRepository;
import cgm.system.MovieNet.repository.MovieRepository;
import cgm.system.MovieNet.repository.ReviewRepository;
import cgm.system.MovieNet.service.MovieService;
import cgm.system.MovieNet.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GenreRepository genreRepository;
    @GetMapping("/home")
    public String userHome(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            Model model){
        Page<Movie> moviePage = movieService.findPaginated(page, size);
        model.addAttribute("moviePage", moviePage);
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        return "/user/userHome";
    }

    @GetMapping("/movie/{id}")
    public String showMovieDetails(@PathVariable("id") Long id, Model model) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie id: " + id));
        List<Review> reviews = reviewRepository.findByMovie(movie);
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        model.addAttribute("newReview", new Review());

        return "movie-details"; // Thymeleaf template name
    }

    @GetMapping("/search")
    public String searchMovie(@RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "genreId", required = false) Long genreId,  @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "20") int size,Model model) {

        PageRequest pageRequest = PageRequest.of(page, size);

       /* Page<Movie> moviePage = */
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        Page<Movie> moviePage;
        List<Movie> movies;
       /* if (genreId == null) {
            moviePage =  movieService.findPaginated(page, size); // Adjust this method based on your service layer
        } else {


        }*/

        if ((title == null || title.isEmpty()) && genreId == null) {
            moviePage = movieService.findPaginated(page, size);
        } else if (genreId == null) {
            moviePage = movieService.searchByTitle(title, pageRequest);
        } else if (title == null || title.isEmpty()) {
            moviePage = movieService.searchByGenre(genreId, pageRequest);
        } else {
            movies = movieService.searchMovies(title, genreId);

            moviePage = movieService.setPage(movies, pageRequest);; // Adjust as per your actual search method
            model.addAttribute("movies_genres", movies);
        }

        model.addAttribute("ttl", title); // Add title to model for Thymeleaf
        model.addAttribute("gId", genreId);

        model.addAttribute("moviePage", moviePage);

        return "/user/userHome"; // Thymeleaf template name
    }




    @PostMapping("/reviews/{id}")
    public String addReview(@ModelAttribute("newReview") Review newReview,@PathVariable("id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Movie movieB = movieService.getMovieById(id);
        newReview.setMovie(movieB);

        if (movieB == null || newReview == null || newReview.getMovie() == null ) {
            // Handle the case where movie is not properly set
            // Redirect or return an error message
            return "redirect:/user/home"; // Example redirection
        }
        // Save the review with username
        reviewService.saveReview(newReview,username);
        return "redirect:/user/movie/" + id;
    }

}
