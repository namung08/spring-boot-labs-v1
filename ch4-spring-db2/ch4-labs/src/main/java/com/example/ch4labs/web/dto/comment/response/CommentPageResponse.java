package com.example.ch4labs.web.dto.comment.response;

import com.example.ch4labs.comment.model.Comment;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentPageResponse{
  private int page;
  private int size;
  private long totalElements;
  private int totalPages;
  private List<CommentResponse> comments;

  public static CommentPageResponse from(Page<CommentResponse> page){
    return CommentPageResponse.builder()
                              .page(page.getNumber())
                              .size(page.getSize())
                              .totalElements(page.getTotalElements())
                              .totalPages(page.getTotalPages())
                              .comments(page.getContent())
                              .build();
  }
}
