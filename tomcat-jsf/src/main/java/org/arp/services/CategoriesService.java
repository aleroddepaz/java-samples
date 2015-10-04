package org.arp.services;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.arp.domain.Category;

public class CategoriesService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Inject
	private Logger logger;

	public Category create(Long parentId, String name, String description) {
		Category category = new Category();
		category.setParentId(parentId);
		category.setName(name);
		category.setDescription(description);

		em.persist(category);
		logger.info("Created a new category: " + category);

		return category;
	}

	public List<Category> findRootCategories() {
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.parentId IS NULL",
				Category.class);
		return query.getResultList();
	}

	public List<Category> findChildCategories(Long parentId) {
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.parentId = :parentId",
				Category.class);
		query.setParameter("parentId", parentId);
		return query.getResultList();
	}

}