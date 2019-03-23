package org.arp.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.arp.domain.Category;
import org.arp.services.CategoriesService;
import org.slf4j.Logger;

@Named("categoriesCtrl")
@ViewScoped
public class CategoriesController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CategoriesService service;

	@Inject
	Logger logger;

	private List<Category> categories;
	private Category newCategory;

	@PostConstruct
	public void init() {
		logger.debug("init()");
		categories = service.findRootCategories();
		newCategory = new Category();
	}

	public String create() {
		logger.debug("create()");
		service.create(newCategory);
		return "index";
	}

	public String delete(Long categoryId) {
		logger.debug("delete()");
		service.delete(categoryId);
		return "index";
	}

	public Category getNewCategory() {
		return newCategory;
	}

	public List<Category> getCategories() {
		return categories;
	}

}