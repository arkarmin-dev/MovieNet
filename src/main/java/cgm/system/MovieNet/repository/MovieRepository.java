package cgm.system.MovieNet.repository;

import cgm.system.MovieNet.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE " +
            "(:title IS NULL OR m.title LIKE %:title%) AND " +
            "(:genreId IS NULL OR g.genreId = :genreId)")
    List<Movie> searchMovies(@Param("title") String title, @Param("genreId") Long genreId);

    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Method to search movies by genre ID
    Page<Movie> findByGenres_GenreId(Long genreId, Pageable pageable);
}

