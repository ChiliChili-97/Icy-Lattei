package com.sparta.project.icylattei.review.controller;

import com.sparta.project.icylattei.review.dto.requestDto.ReviewRequest;
import com.sparta.project.icylattei.review.dto.responseDto.ReviewResponse;
import com.sparta.project.icylattei.review.service.ReviewService;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/{productId}")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ReviewResponse createReview(@PathVariable Long productId,
        @RequestBody @Valid ReviewRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return reviewService.createReview(productId, request, userDetails.getUser());
    }
}
