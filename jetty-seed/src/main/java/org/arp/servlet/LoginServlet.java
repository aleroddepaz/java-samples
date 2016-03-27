package org.arp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static final String VIEW_NAME = "/WEB-INF/views/login.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        boolean authError = req.getParameter("error") != null;
        req.setAttribute("loginError", authError);
        req.getRequestDispatcher(VIEW_NAME).forward(req, res);
    }

}
