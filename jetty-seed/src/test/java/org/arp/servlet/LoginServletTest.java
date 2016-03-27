package org.arp.servlet;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.Test;

public class LoginServletTest extends BaseServletTest {

    private LoginServlet servlet = new LoginServlet();

    @Test
    public void testDoGet() throws ServletException, IOException {
        RequestDispatcher dispatcher = spy(RequestDispatcher.class);
        when(request.getRequestDispatcher(LoginServlet.VIEW_NAME)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(dispatcher).forward(eq(request), eq(response));
    }

}
