package com.codex.ecomerce.services.impl;

import com.codex.ecomerce.model.Product;
import com.codex.ecomerce.model.Review;
import com.codex.ecomerce.model.User;
import com.codex.ecomerce.repository.ReviewRepository;
import com.codex.ecomerce.request.CreateReviewRequest;
import com.codex.ecomerce.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    @Override
    public Review createReview(CreateReviewRequest req, User user, Product product) {
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReviewText(req.getReviewText());
        review.setRating(req.getReviewRating());
        review.setProductImage(req.getProductImages());

        product.getReviews().add(review);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepository.findByByProductId(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double reviewRating, Long userId) throws Exception {
        Review updatedReview = getReviewById(reviewId);
        if(updatedReview.getUser().getId().equals(userId)){
            updatedReview.setReviewText(reviewText);
            updatedReview.setRating(reviewRating);

            reviewRepository.save(updatedReview);
        }
        throw new Exception("You can't update this review");
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if(!review.getUser().getId().equals(userId)){
            throw new Exception("You can't delete this review");
        }
        reviewRepository.delete(review);
    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId).orElseThrow(()->
                new Exception("review not found for this Id")
        );
    }
}
