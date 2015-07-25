package org.arp.resources;

import org.arp.model.Author;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class AuthorResource extends ResourceSupport {

    private String name;

    public AuthorResource() {
        super();
    }

    public AuthorResource(Author author, Link selfRel) {
        super();
        this.name = author.getName();
        this.add(selfRel);
    }

    public String getName() {
        return name;
    }

}
