package org.arp.resources;

import org.arp.model.Book;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class BookResource extends ResourceSupport {

    private String title;
    private Integer numPages;
    private PublisherResource publisher;

    public BookResource() {
        super();
    }

    public BookResource(Book book, Link selfRel) {
        super();
        this.title = book.getTitle();
        this.numPages = book.getNumPages();
        this.add(selfRel);
    }

    public String getTitle() {
        return title;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public PublisherResource getPublisher() {
        return publisher;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public void setPublisher(PublisherResource publisher) {
        this.publisher = publisher;
    }

    public Book toEntity() {
        Book book = new Book();
        book.setTitle(getTitle());
        book.setNumPages(getNumPages());
        return book;
    }

}
