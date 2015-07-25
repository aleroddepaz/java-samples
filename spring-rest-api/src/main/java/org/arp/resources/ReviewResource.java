package org.arp.resources;

import org.arp.model.Review;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class ReviewResource extends ResourceSupport {

    private Integer rate;
    private BookResource book;

    public ReviewResource() {
        super();
    }

    public ReviewResource(Review review, Link selfRel) {
        super();
        this.rate = review.getRate();
        this.add(selfRel);
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public BookResource getBook() {
        return book;
    }

    public void setBook(BookResource book) {
        this.book = book;
    }

    public Review toEntity() {
        Review review = new Review();
        review.setRate(getRate());
        return review;
    }

}
