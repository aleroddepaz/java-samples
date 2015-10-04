package org.arp.controllers;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.arp.services.CategoriesService;

@Named("category")
@RequestScoped
public class CategoriesController implements Serializable {

	private static final long serialVersionUID = 1L;

	private final CategoriesService service;	
	private Long parentId;
	private String name;
	private String description;

	@Inject
	public CategoriesController(CategoriesService service) {
		this.service = service;
	}
	
	public void createCategory() {
		service.create(parentId, name, description);
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}