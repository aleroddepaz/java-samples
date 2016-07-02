package org.arp.example;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class EchoServletTest {

	private EchoServlet servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher dispatcher;

	@Before
	public void setUp() {
		servlet = new EchoServlet();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(any(String.class))).thenReturn(dispatcher);
	}

	@Test
	public void testDoGet() throws ServletException, IOException {
		servlet.doGet(request, response);

		verify(request).getRequestDispatcher(any(String.class));
	}

}
