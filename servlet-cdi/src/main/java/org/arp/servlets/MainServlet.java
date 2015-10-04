package org.arp.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arp.services.ServiceException;
import org.arp.services.UsersService;

@WebServlet(value = { "/index" }, loadOnStartup = 1)
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
		try {
			service.addUser(username);
		} catch(ServiceException e) {
			setErrors(request, e.getMessage());
		}
		renderHomePage(request, response);
	}
	
	private void setErrors(HttpServletRequest request, String... errors) {
		request.setAttribute("errors", errors);
	}

	private void renderHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("users", service.findAllUsers());
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
	}

}
