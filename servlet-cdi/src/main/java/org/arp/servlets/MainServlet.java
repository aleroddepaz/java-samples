package org.arp.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arp.domain.User;
import org.arp.services.UsersService;

@WebServlet(value = { "/", "/index" }, loadOnStartup = 1)
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsersService service;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		renderHomePage(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		User newUser = service.addUser(username);
		request.setAttribute("newUser", newUser);
		renderHomePage(request, response);
	}

	private void renderHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("users", service.findAllUsers());
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

}
