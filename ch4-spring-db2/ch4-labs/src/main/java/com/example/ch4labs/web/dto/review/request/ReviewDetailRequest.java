package com.example.ch4labs.web.dto.review.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDetailRequest {
  boolean includeComments = false;
  int page = 0;
  int size = 5;
}
