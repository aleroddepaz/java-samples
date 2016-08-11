package org.arp.repositories;

import java.util.List;

import org.arp.domain.Movie;

public interface MoviesRepository {
    
    Movie create(Movie movie);
    
    List<Movie> findAll();

}
