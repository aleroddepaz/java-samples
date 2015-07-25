package org.arp.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.ArrayList;
import java.util.List;

import org.arp.model.Book;
import org.arp.model.Publisher;
import org.arp.repositories.BookRepository;
import org.arp.repositories.PublisherRepository;
import org.arp.resources.BookResource;
import org.arp.resources.PublisherResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
@Transactional(readOnly = true)
public class PublisherController {

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(method = GET)
    public List<PublisherResource> findAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        List<PublisherResource> result = new ArrayList<PublisherResource>(publishers.size());
        for (Publisher publisher : publishers) {
            result.add(convertToResource(publisher));
        }
        return result;
    }

    @RequestMapping(method = GET, value = "/{id}")
    public PublisherResource findPublisher(@PathVariable("id") Long id) {
        Publisher publisher = publisherRepository.findOne(id);
        if (publisher == null) {
            throw new ResourceNotFoundException();
        }
        return convertToResource(publisher);
    }

    @RequestMapping(method = POST)
    @Transactional
    public PublisherResource createPublisher(@RequestBody PublisherResource resource) {
        Publisher publisher = publisherRepository.save(resource.toEntity());
        return convertToResource(publisher);
    }

    @RequestMapping(method = GET, value = "/{id}/books")
    public List<BookResource> getPublisherBook(@PathVariable("id") Long id) {
        Publisher publisher = publisherRepository.findOne(id);
        if (publisher == null) {
            throw new ResourceNotFoundException();
        }
        List<Book> books = publisher.getBooks();
        List<BookResource> result = new ArrayList<BookResource>(books.size());
        for (Book book : books) {
            result.add(BookController.convertToResource(book));
        }
        return result;
    }

    @RequestMapping(method = POST, value = "/{id}/books")
    @Transactional
    public BookResource createPublisherBook(@PathVariable("id") Long id, @RequestBody BookResource resource) {
        Publisher publisher = publisherRepository.findOne(id);
        if (publisher == null) {
            throw new ResourceNotFoundException();
        }
        Book book = resource.toEntity();
        book.setPublisher(publisher);
        book = bookRepository.save(book);
        return BookController.convertToResource(book);
    }

    @RequestMapping(method = PUT, value = "/{id}")
    @Transactional
    public PublisherResource updateBook(@PathVariable("id") Long id, @RequestBody PublisherResource resource) {
        Publisher publisher = publisherRepository.findOne(id);
        if (publisher == null) {
            throw new ResourceNotFoundException();
        }
        publisher.setName(resource.getName());
        publisher = publisherRepository.save(publisher);
        return convertToResource(publisher);
    }

    public static PublisherResource convertToResource(Publisher entity) {
        final Long publisherId = entity.getId();
        Link selfRel = linkTo(methodOn(PublisherController.class).findPublisher(publisherId)).withSelfRel();
        Link booksRel = linkTo(methodOn(PublisherController.class).getPublisherBook(publisherId)).withRel("books");

        PublisherResource resource = new PublisherResource(entity, selfRel);
        resource.add(booksRel);
        return resource;
    }

}
