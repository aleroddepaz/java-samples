package org.arp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.arp.domain.Movie;
import org.arp.resources.MovieResource;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

@WebServlet(urlPatterns = { "/" }, loadOnStartup = 1)
public class MoviesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/api/");
    private final MovieResource resource = WebResourceFactory.newResource(MovieResource.class, target);

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        renderPage(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setYear(2016);

        Movie newMovie = resource.create(movie);
        req.setAttribute("newMovie", newMovie);

        renderPage(req, res);
    }

    private void renderPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Movie> movies = resource.findAll();
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, res);
    }

}
