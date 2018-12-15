package org.arp.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.arp.model.Author;
import org.arp.repositories.AuthorRepository;
import org.arp.resources.AuthorResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@Transactional(readOnly = true)
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(method = GET)
    public List<AuthorResource> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResource> result = new ArrayList<AuthorResource>(authors.size());
        for (Author author : authors) {
            result.add(convertToResource(author));
        }
        return null;
    }

    @RequestMapping(method = GET, value = "/{id}")
    public AuthorResource findAuthor(@PathVariable("id") Long id) {
        Author author = authorRepository.getOne(id);
        if (author == null) {
            throw new ResourceNotFoundException();
        }
        return convertToResource(author);
    }

    public static AuthorResource convertToResource(Author author) {
        Link selfRel = linkTo(methodOn(AuthorController.class).findAuthor(author.getId())).withSelfRel();
        return new AuthorResource(author, selfRel);
    }

}
