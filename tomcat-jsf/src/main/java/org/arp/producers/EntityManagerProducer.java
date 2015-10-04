package org.arp.producers;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class EntityManagerProducer {

	@PersistenceUnit
	private EntityManagerFactory factory;
	
	@Produces
	EntityManager createEntityManager() {
		return factory.createEntityManager();
	}

}