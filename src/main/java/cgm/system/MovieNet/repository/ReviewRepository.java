package cgm.system.MovieNet.repository;

import cgm.system.MovieNet.entity.Movie;
import cgm.system.MovieNet.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovie(Movie movie);
}

