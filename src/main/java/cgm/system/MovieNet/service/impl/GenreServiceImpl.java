package cgm.system.MovieNet.service.impl;

import cgm.system.MovieNet.entity.Genre;
import cgm.system.MovieNet.repository.GenreRepository;
import cgm.system.MovieNet.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public List<Genre> findGenresByIds(List<Long> ids) {
        return genreRepository.findAllById(ids);
    }
}
