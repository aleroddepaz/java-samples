package org.arp.modules;

import org.arp.servlets.HomeServlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        ServicesModule servicesModule = new ServicesModule();
        return Guice.createInjector(servicesModule, new ServletModule() {

            @Override
            protected void configureServlets() {
                serve("/").with(HomeServlet.class);
            }
        });
    }

}
