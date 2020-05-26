package org.arp.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.arp.model.Book;
import org.arp.model.Review;
import org.arp.repositories.BookRepository;
import org.arp.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Transactional(readOnly = true)
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping
    public List<EntityModel<Book>> findAllBooks() {
    	return bookRepository.findAll().stream()
    			.map(this::convertToResource)
    			.collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<Book> findBook(@PathVariable("id") Long id) {
    	return convertToResource(findById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book newBook) {
    	Book book = findById(id);
        book.setTitle(newBook.getTitle());
        book.setNumPages(newBook.getNumPages());
        return convertToResource(bookRepository.save(book));
    }

    @PostMapping("/{id}/reviews")
    @Transactional
    public EntityModel<Review> createReview(@PathVariable("id") Long id, @RequestBody Review review) {
    	review.setBook(findById(id));
    	review.setDate(new Date());
        return EntityModel.of(reviewRepository.save(review));
    }

    private Book findById(Long id) {
    	return bookRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    private EntityModel<Book> convertToResource(Book book) {
    	Link selfRel = linkTo(methodOn(BookController.class).findBook(book.getId())).withSelfRel();
        return EntityModel.of(book, selfRel);
    }

}