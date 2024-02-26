package com.sparta.project.icylattei.review.service;

import com.sparta.project.icylattei.product.entity.Product;
import com.sparta.project.icylattei.product.repository.ProductRepository;
import com.sparta.project.icylattei.review.dto.requestDto.ReviewRequest;
import com.sparta.project.icylattei.review.dto.responseDto.ReviewResponse;
import com.sparta.project.icylattei.review.entity.Review;
import com.sparta.project.icylattei.review.repository.ReviewRepository;
import com.sparta.project.icylattei.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewResponse createReview(Long productId, ReviewRequest request, User user) {

        Product product = validProduct(productId);
        Review review = reviewRepository.save(new Review(request, user, product));

        return new ReviewResponse(user.getNickname(), review);
    }

    public List<ReviewResponse> LoadReviewList(Long productId, User user) {

        Product product = validProduct(productId);

        List<Review> reviewList = reviewRepository.findAllByProduct(product);
        List<ReviewResponse> responseList = new ArrayList<>();

        for (Review review : reviewList) {
            responseList.add(new ReviewResponse(review.getUser().getNickname(), review));
        }
        return responseList;
    }

    // 검증 메서드 ==================================================================================

    private Product validProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
            () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
        );
    }


}
