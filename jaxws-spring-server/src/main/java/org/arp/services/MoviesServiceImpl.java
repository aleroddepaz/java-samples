package org.arp.services;

import java.util.List;

import javax.jws.WebService;

import org.arp.domain.Movie;
import org.arp.repositories.MoviesRepository;

@WebService(portName = "MoviesPort", serviceName = "MoviesService", targetNamespace = "http://example.arp.org/services/movies", endpointInterface = "org.arp.services.MoviesService")
public class MoviesServiceImpl implements MoviesService {

    private final MoviesRepository repository;

    public MoviesServiceImpl(MoviesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        return repository.create(movie);
    }

    @Override
    public List<Movie> listMovies() {
        return repository.findAll();
    }

}
