package com.solvd.onlineshop.entities;

public class Rating {
    private long id;
    private long productId;
    private long reviewerId;
    private int rating;
    private String review;

    public Rating(long id, long productId, long reviewerId, int rating, String review) {
        this.id = id;
        this.productId = productId;
        this.reviewerId = reviewerId;
        this.rating = rating;
        this.review = review;
    }

    public long getRatingId() {
        return id;
    }

    public long getProductId() {
        return productId;
    }

    public long getReviewerId() {
        return reviewerId;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}
