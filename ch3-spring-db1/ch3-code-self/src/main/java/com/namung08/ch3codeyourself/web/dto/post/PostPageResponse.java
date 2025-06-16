package com.namung08.ch3codeyourself.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPageResponse {
  private int page;
  private int size;
  private int totalCount;
  private int totalPage;
  private List<PostResponse> posts;
  public static PostPageResponse from(List<PostResponse> posts, PostSearchRequest searchRequest, int count) {
    int totalPage = (int) Math.ceil(((double) count / searchRequest.getSize()));
    return PostPageResponse.builder()
                           .posts(posts)
                           .size(searchRequest.getSize())
                           .page(searchRequest.getPage())
                           .totalPage(totalPage)
                           .totalCount(count)
                           .build();
  }
}
