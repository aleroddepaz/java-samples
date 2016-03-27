package org.arp.services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import org.arp.domain.Category;
import org.slf4j.Logger;

@ApplicationScoped
public class CategoriesService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    EntityManager em;

    @Inject
    Logger logger;

    public boolean create(final Category category) {
        boolean ok = true;
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            logger.debug("Created a new category: {}", category);
        } catch (RollbackException e) {
            logger.error("Error while creating a new category", e);
            ok = false;
        }
        return ok;
    }

    public List<Category> findRootCategories() {
        TypedQuery<Category> query = em.createNamedQuery(Category.FIND_ROOT, Category.class);
        List<Category> categories = query.getResultList();

        logger.debug("{} root categories found", categories.size());
        return categories;
    }

    public List<Category> findChildCategories(Long parentId) {
        TypedQuery<Category> query = em.createNamedQuery(Category.FIND_CHILDREN, Category.class);
        query.setParameter("parentId", parentId);
        return query.getResultList();
    }

}