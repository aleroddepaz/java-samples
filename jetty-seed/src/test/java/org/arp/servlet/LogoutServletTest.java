package org.arp.servlet;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.Test;

public class LogoutServletTest extends BaseServletTest {

    private LogoutServlet servlet = new LogoutServlet();

    @Test
    public void testDoPost() throws ServletException, IOException {
        servlet.doPost(request, response);

        verify(response).sendRedirect("/login");
    }

    @Test
    public void testDoPostInvalidate() throws ServletException, IOException {
        HttpSession session = spy(HttpSession.class);
        when(request.getSession(false)).thenReturn(session);

        servlet.doPost(request, response);

        verify(session).invalidate();
    }

}
