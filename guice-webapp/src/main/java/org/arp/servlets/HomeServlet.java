package org.arp.servlets;

import static org.apache.commons.lang3.StringUtils.trimToNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("tasks", taskService.findAll());
        req.getRequestDispatcher("/WEB-INF/views/index.jspx").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String text = trimToNull(req.getParameter("task"));
        try {
            taskService.create(text);
        } catch (ConstraintViolationException cve) {
            List<String> messages = cve.getConstraintViolations().stream().map(cv -> cv.getMessage()).collect(Collectors.toList());
            req.setAttribute("errors", messages);
        } catch (Exception e) {
            req.setAttribute("errors", Arrays.asList(e.getMessage()));
        }
        doGet(req, res);
    }

}