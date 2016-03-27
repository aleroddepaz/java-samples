package org.arp.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arp.services.ServiceException;
import org.arp.services.UsersService;

@WebServlet(value = { "", "/index" }, loadOnStartup = 1)
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    UsersService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        renderHomePage(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        try {
            service.addUser(username);
        } catch (ServiceException e) {
            req.setAttribute("errors", Arrays.asList(e.getMessage()));
        }
        renderHomePage(req, res);
    }

    private void renderHomePage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("users", service.findAllUsers());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, res);
    }

}
