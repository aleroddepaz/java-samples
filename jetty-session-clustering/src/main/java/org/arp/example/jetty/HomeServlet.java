package org.arp.example.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final String VIEW_NAME = "/WEB-INF/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW_NAME).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String attribute = req.getParameter("sessionAttribute");
        final String value = req.getParameter("sessionValue");
        if (attribute != null && value != null) {
            log("Adding '" + attribute + "=" + value + "' in session (Port " + req.getLocalPort() + ")");
            HttpSession session = req.getSession();
            session.setAttribute(attribute, value);
        }
        req.getRequestDispatcher(VIEW_NAME).forward(req, res);
    }
}