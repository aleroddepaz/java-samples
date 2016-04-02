package org.arp.modules;

import org.arp.servlets.HomeServlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {

    private static final String PERSISTENCE_UNIT_NAME = "GuiceWebappPu";
    
    @Override
    protected Injector getInjector() {
        ServicesModule servicesModule = new ServicesModule();
        return Guice.createInjector(servicesModule, new ServletModule() {
            @Override
            protected void configureServlets() {
                install(new JpaPersistModule(PERSISTENCE_UNIT_NAME));
                filter("/*").through(PersistFilter.class);

                serve("/").with(HomeServlet.class);
            }
        });
    }

}
