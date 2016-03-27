package org.arp.servlet;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.Test;

public class HomeServletTest extends BaseServletTest {

    private HomeServlet servlet = new HomeServlet();

    @Test
    public void testDoGet() throws ServletException, IOException {
        RequestDispatcher dispatcher = spy(RequestDispatcher.class);
        when(request.getRequestDispatcher(HomeServlet.VIEW_NAME)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("date"), any());
        verify(dispatcher).forward(eq(request), eq(response));
    }

}
