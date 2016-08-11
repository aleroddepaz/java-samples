package org.arp.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.arp.resources.MovieResourceImpl;

@ApplicationPath("api")
public class MovieApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new MovieResourceImpl());
        return singletons;
    }
    
}
