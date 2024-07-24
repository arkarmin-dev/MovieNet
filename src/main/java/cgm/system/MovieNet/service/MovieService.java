package cgm.system.MovieNet.service;

import cgm.system.MovieNet.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    public Page<Movie> findPaginated(int page, int size);
    public Movie getMovieById(Long movieId);

    public List<Movie> searchMovies(String title, Long genreId);
    public Page<Movie> setPage(List<Movie> movies, Pageable pageable);

    Page<Movie> searchByTitle(String title, Pageable pageable);

    Page<Movie> searchByGenre(Long genreId, Pageable pageable);

    public void deleteMovieById(Long movieId);

    void addMovie(String title, Integer releaseDate, Double imdbRating, String plot,
                  String director, MultipartFile poster, MultipartFile video, List<Long> genreIds);

    public  String savePosterFile(MultipartFile file);

}
