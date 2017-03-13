package org.arp.config;

import org.arp.services.TaskService;
import org.arp.services.TaskServiceImpl;
import org.arp.servlets.HomeServlet;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServicesModule(), new ServletModule() {
            @Override
            protected void configureServlets() {
                install(new JpaPersistModule("GuiceWebappPu"));

                filter("/*").through(PersistFilter.class);
                serve("/").with(HomeServlet.class);
            }
        });
    }

    private static final class ServicesModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(TaskService.class).to(TaskServiceImpl.class);
        }
    }

}