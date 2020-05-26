package org.arp.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.arp.model.Author;
import org.arp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@Transactional(readOnly = true)
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping
    public List<EntityModel<Author>> findAllAuthors() {
    	return authorRepository.findAll().stream()
    			.map(this::convertToResource)
    			.collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<Author> findAuthor(@PathVariable("id") Long id) {
    	return authorRepository.findById(id)
    			.map(this::convertToResource)
    			.orElseThrow(ResourceNotFoundException::new);
    }

    public EntityModel<Author> convertToResource(Author author) {
    	Link selfRel = linkTo(methodOn(AuthorController.class).findAuthor(author.getId())).withSelfRel();
    	return EntityModel.of(author, selfRel);
    }

}