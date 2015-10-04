package org.arp.factories;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFactory {
	
	@Produces
	Logger createLogger(InjectionPoint injectionPoint) {
		Class<?> clazz = injectionPoint.getMember().getDeclaringClass();
		return LoggerFactory.getLogger(clazz);
	}

}
