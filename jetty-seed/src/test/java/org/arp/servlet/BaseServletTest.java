package org.arp.servlet;

import static org.mockito.Mockito.spy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;

/**
 * Base class for initializing HTTP request and response spies.
 */
public abstract class BaseServletTest {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @Before
    public void setUp() {
        request = spy(HttpServletRequest.class);
        response = spy(HttpServletResponse.class);
    }
}
