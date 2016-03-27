package org.arp.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "", "/index" })
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static final String VIEW_NAME = "/WEB-INF/views/index.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        req.setAttribute("date", df.format(new Date()));
        req.getRequestDispatcher(VIEW_NAME).forward(req, res);
    }

}
