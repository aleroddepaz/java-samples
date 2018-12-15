package org.arp.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(method = GET)
    public ApiResource getEntryPoint() {
        ApiResource resource = new ApiResource();
        resource.add(linkTo(methodOn(BookController.class).findAllBooks()).withRel("books"));
        resource.add(linkTo(methodOn(PublisherController.class).findAllPublishers()).withRel("publishers"));
        resource.add(linkTo(methodOn(AuthorController.class).findAllAuthors()).withRel("authors"));
        return resource;
    }

    private static final class ApiResource extends ResourceSupport {
    }

}