package cgm.system.MovieNet.service.impl;

import cgm.system.MovieNet.entity.Movie;
import cgm.system.MovieNet.repository.MovieRepository;
import cgm.system.MovieNet.repository.ReviewRepository;
import cgm.system.MovieNet.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServieImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Page<Movie> findPaginated(int page, int size) {
        return movieRepository.findAllActiveMovies(PageRequest.of(page, size));
    }

    @Override
    public Movie getMovieById(Long movieId) {

        return movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found")); // Handle not found case as per your application logic
    }

    public List<Movie> searchMovies(String title, Long genreId) {
        return movieRepository.searchMovies(title, genreId);
    }

    public Page<Movie> setPage(List<Movie> movies, Pageable pageable) {

        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > movies.size() ? movies.size() : (start + pageable.getPageSize());
        Page<Movie> moviePage = new PageImpl<>(movies.subList(start, end), pageable, movies.size());

        return moviePage;
    }

    @Override
    public Page<Movie> searchByTitle(String title, Pageable pageable) {
        return movieRepository.findByTitleContainingIgnoreCaseAndDeletedFalse(title, pageable);
    }

    @Override
    public Page<Movie> searchByGenre(Long genreId, Pageable pageable) {
        return movieRepository.findByGenres_GenreIdAndDeletedFalse(genreId, pageable);
    }

    @Transactional
    public void deleteMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setDeleted(true);
        movieRepository.save(movie);


    }
}
