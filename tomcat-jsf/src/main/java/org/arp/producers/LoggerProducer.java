package org.arp.producers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class LoggerProducer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<Class<?>, Logger> cache = new HashMap<>();

    @Produces
    public Logger createLogger(InjectionPoint ip) {
        Class<?> clazz = ip.getMember().getDeclaringClass();
        return cache.computeIfAbsent(clazz, (key) -> LoggerFactory.getLogger(key));
    }

}
