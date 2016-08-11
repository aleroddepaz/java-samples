package org.arp.repositories;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.arp.domain.Movie;

public class MoviesRepositoryImpl implements MoviesRepository {

    private AtomicLong counter = new AtomicLong();
    private List<Movie> movies = new LinkedList<>();
    
    @Override
    public Movie create(Movie movie) {
        movie.setId(counter.incrementAndGet());
        movies.add(movie);
        return movie;
    }

    @Override
    public List<Movie> findAll() {
        return Collections.unmodifiableList(movies);
    }


}
