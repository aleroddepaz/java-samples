package org.arp.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.arp.domain.Category;
import org.arp.services.CategoriesService;
import org.slf4j.Logger;

@Named("categoriesCtrl")
@RequestScoped
public class CategoriesController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    CategoriesService service;
    
    @Inject
    Logger logger;

    private List<Category> categories;
    private Category newCategory = new Category();

    public void loadCategories(ComponentSystemEvent e) {
        logger.trace("loadCategories()");
        categories = service.findRootCategories();
    }

    public void create() {
        logger.trace("create()");
        if (service.create(newCategory)) {
            newCategory = new Category();
        }
    }

    public Category getNewCategory() {
        return newCategory;
    }

    public List<Category> getCategories() {
        return categories;
    }

}