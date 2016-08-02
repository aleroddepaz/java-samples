package org.arp.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.Map;

import javax.xml.ws.Holder;

import org.junit.Before;
import org.junit.Test;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.tempuri.ArrayOfPasoParada;
import org.tempuri.DinamicaSoap;

public class HomeControllerTest {

    private HomeController controller;
    private DinamicaSoap port;

    @Before
    public void setUp() {
        port = mock(DinamicaSoap.class);
        controller = new HomeController(port);
    }

    @Test
    public void testGet() {
        ModelAndView modelAndView = controller.get();

        assertEquals(HomeController.HOME_VIEW, modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("now"));
    }

    @Test
    public void testPost() {
        final String linea = "C1";
        final String parada = "768";
        doAnswer((invocation) -> {
            @SuppressWarnings("unchecked")
            Holder<ArrayOfPasoParada> holder = invocation.getArgumentAt(3, Holder.class);
            holder.value = new ArrayOfPasoParada();
            return null;
        }).when(port).getPasoParada(eq(linea), eq(parada), any(), any());

        ModelAndView modelAndView = controller.post(linea, parada);
        Map<String, Object> model = modelAndView.getModel();

        assertEquals(HomeController.HOME_VIEW, modelAndView.getViewName());
        assertNotNull(model.get("now"));
        assertEquals(linea, model.get("linea"));
        assertEquals(parada, model.get("parada"));
    }

    @Test
    public void testPostException() {
        final String linea = "C1";
        final String parada = "768";
        RemoteAccessException exception = new RemoteAccessException("Error");
        doThrow(exception).when(port).getPasoParada(eq(linea), eq(parada), any(), any());

        ModelAndView modelAndView = controller.post(linea, parada);
        Map<String, Object> model = modelAndView.getModel();

        assertEquals(HomeController.HOME_VIEW, modelAndView.getViewName());
        assertNotNull(model.get("now"));
        assertNotNull(model.get("error"));
        assertEquals(linea, model.get("linea"));
        assertEquals(parada, model.get("parada"));
    }

}