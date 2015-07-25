package org.arp.resources;

import org.arp.model.Publisher;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class PublisherResource extends ResourceSupport {

    private String name;

    public PublisherResource() {
        super();
    }

    public PublisherResource(Publisher publisher, Link selfRel) {
        super();
        this.name = publisher.getName();
        this.add(selfRel);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher toEntity() {
        Publisher publisher = new Publisher();
        publisher.setName(getName());
        return publisher;
    }
}
