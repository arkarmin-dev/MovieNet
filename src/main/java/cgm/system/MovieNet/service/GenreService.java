package cgm.system.MovieNet.service;

import cgm.system.MovieNet.entity.Genre;

import java.util.List;

public interface GenreService {
    public List<Genre> findAllGenres();
    public List<Genre> findGenresByIds(List<Long> ids);
}
