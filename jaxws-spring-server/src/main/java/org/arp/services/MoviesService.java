package org.arp.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.arp.domain.Movie;

@WebService(targetNamespace = "http://example.arp.org/services/movies")
public interface MoviesService {

    @WebMethod
    Movie createMovie(@WebParam(name = "movie") Movie movie);

    @WebMethod
    List<Movie> listMovies();

}
