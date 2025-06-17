package com.example.ch4labs.web.dto.review.request;

import com.example.ch4labs.review.model.Review;

public interface ReviewRequest {
  Review toDomain();
}
