package org.arp.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.ArrayList;
import java.util.List;

import org.arp.model.Book;
import org.arp.model.Review;
import org.arp.repositories.BookRepository;
import org.arp.repositories.ReviewRepository;
import org.arp.resources.BookResource;
import org.arp.resources.ReviewResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(method = GET)
    public List<BookResource> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResource> result = new ArrayList<BookResource>(books.size());
        for (Book book : books) {
            result.add(convertToResource(book));
        }
        return result;
    }

    @RequestMapping(method = GET, value = "/{id}")
    public BookResource findBook(@PathVariable("id") Long id) {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new ResourceNotFoundException();
        }
        return convertToResource(book);
    }

    @RequestMapping(method = PUT, value = "/{id}")
    @Transactional
    public BookResource updateBook(@PathVariable("id") Long id, @RequestBody BookResource resource) {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new ResourceNotFoundException();
        }
        book.setTitle(resource.getTitle());
        book.setNumPages(resource.getNumPages());
        book = bookRepository.save(book);
        return convertToResource(book);
    }

    @RequestMapping(method = POST, value = "/{id}/reviews")
    @Transactional
    public ReviewResource createReview(@PathVariable("id") Long id, @RequestBody ReviewResource resource) {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new ResourceNotFoundException();
        }
        Review review = resource.toEntity();
        review.setBook(book);
        review = reviewRepository.save(review);
        return convertToResource(review);
    }

    @RequestMapping(method = GET, value = "/{id}/reviews")
    public List<ReviewResource> findAllReviews(@PathVariable("id") Long id) {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new ResourceNotFoundException();
        }
        List<Review> reviews = book.getReviews();
        List<ReviewResource> result = new ArrayList<ReviewResource>(reviews.size());
        for (Review review : reviews) {
            result.add(convertToResource(review));
        }
        return result;
    }

    @RequestMapping(method = GET, value = "/{id}/reviews/{reviewId}")
    public ReviewResource findReview(@PathVariable("id") Long id, @PathVariable("reviewId") Long reviewId) {
        Review review = reviewRepository.findOne(reviewId);
        if (review == null || review.getBook().getId() != id) {
            throw new ResourceNotFoundException();
        }
        return convertToResource(review);
    }

    public static BookResource convertToResource(Book book) {
        if (book == null) {
            return null;
        }
        final Long id = book.getId();
        Link selfRel = linkTo(methodOn(BookController.class).findBook(id)).withSelfRel();
        Link reviewsRel = linkTo(methodOn(BookController.class).findAllReviews(id)).withRel("reviews");

        BookResource resource = new BookResource(book, selfRel);
        resource.setPublisher(PublisherController.convertToResource(book.getPublisher()));
        resource.add(reviewsRel);
        return resource;
    }

    public static ReviewResource convertToResource(Review review) {
        if (review == null) {
            return null;
        }
        Link selfRel = linkTo(methodOn(BookController.class).findBook(review.getId())).withSelfRel();
        ReviewResource resource = new ReviewResource(review, selfRel);
        resource.setBook(BookController.convertToResource(review.getBook()));
        return resource;
    }
}