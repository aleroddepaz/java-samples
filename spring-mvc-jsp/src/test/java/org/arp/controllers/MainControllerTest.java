package org.arp.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class MainControllerTest {

    MainController controller = new MainController();

    @Test
    public final void testGetIndex() {
        ModelAndView model = controller.getIndex();

        assertEquals("index", model.getViewName());
        assertNotNull("now", model.getModel().get("now"));
    }

}
