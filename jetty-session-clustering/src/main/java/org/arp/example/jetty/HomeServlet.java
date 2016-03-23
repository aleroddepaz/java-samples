package org.arp.example.jetty;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		renderHomePage(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String attribute = request.getParameter("sessionAttribute");
		final String value = request.getParameter("sessionValue");
		if(attribute != null && value != null) {
			log("Adding '" + attribute + "=" + value + "' in session (Port " + request.getLocalPort() + ")");
			HttpSession session = request.getSession();
			session.setAttribute(attribute, value);
		}
		renderHomePage(request, response);
	}

	private void renderHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("time", new Date());
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}
	
}
