package org.arp.modules;

import org.arp.services.TaskService;
import org.arp.services.impl.InMemoryTaskService;

import com.google.inject.AbstractModule;

public class ServicesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TaskService.class).to(InMemoryTaskService.class);
    }

}
