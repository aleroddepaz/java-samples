package org.arp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arp.services.TaskService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TaskService taskService;

    @Inject
    public HomeServlet(TaskService taskService) {
        this.taskService = taskService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("tasks", taskService.findAll());
        req.getRequestDispatcher("/WEB-INF/views/index.jspx").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String text = req.getParameter("task");
        try {
            taskService.create(text);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        doGet(req, res);
    }

}
