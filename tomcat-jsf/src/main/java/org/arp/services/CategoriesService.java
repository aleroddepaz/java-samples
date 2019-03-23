package org.arp.services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.arp.domain.Category;
import org.slf4j.Logger;

@ApplicationScoped
public class CategoriesService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    EntityManager em;

    @Inject
    Logger logger;

    public boolean create(Category category) {
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            logger.debug("Created category: {}", category);
        } catch (RollbackException e) {
            logger.error("Error while creating a category", e);
            return false;
        }
        return true;
    }

    public boolean delete(Long categoryId) {
    	try {
	    	em.getTransaction().begin();
	    	Category category = em.find(Category.class, categoryId);
	    	em.remove(category);
	    	em.getTransaction().commit();
	    	logger.debug("Deleted category: {}", category);
    	} catch (RollbackException e) {
    		logger.error("Error while deleting a category", e);
            return false;
    	}
    	return true;
    }

    public List<Category> findRootCategories() {
        return em.createNamedQuery(Category.FIND_ROOT, Category.class).getResultList();
    }

}