package org.arp.resources;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.arp.domain.Movie;

public class MovieResourceImpl implements MovieResource {

    private AtomicLong counter = new AtomicLong();
    private List<Movie> movies = new LinkedList<>();

    @Override
    public List<Movie> findAll() {
        return Collections.unmodifiableList(movies);
    }

    @Override
    public Movie create(Movie movie) {
        movie.setId(counter.incrementAndGet());
        movies.add(movie);
        return movie;
    }

}
