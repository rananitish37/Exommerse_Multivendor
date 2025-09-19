package com.codex.ecomerce.services;

import com.codex.ecomerce.model.Product;
import com.codex.ecomerce.model.Review;
import com.codex.ecomerce.model.User;
import com.codex.ecomerce.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest req, User user, Product product);
    List<Review> getReviewByProductId(Long productId);
    Review updateReview(Long reviewId, String reviewText, double reviewRating, Long userId) throws Exception;
    void deleteReview(Long reviewId, Long userId) throws Exception;
    Review getReviewById(Long reviewId) throws Exception;
}
