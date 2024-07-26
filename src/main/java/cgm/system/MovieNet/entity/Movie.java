package cgm.system.MovieNet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_title")
    private String title;

    @Column(name = "releaseDate")
    private Integer releaseDate;

    @Column(name = "imdb_rating")
    private Double imdb_rating;

    @Column(name = "plot",columnDefinition = "TEXT")
    private String plot;

    @Column(name = "director")
    private String director;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Movie_Genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    public Movie(String title, Integer releaseDate, String plot,Double imdb_rating, String director, String posterUrl, String fileUrl) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.plot = plot;
        this.imdb_rating = imdb_rating;
        this.director = director;
        this.posterUrl = posterUrl;
        this.fileUrl = fileUrl;
    }

    // getters and setters
}

