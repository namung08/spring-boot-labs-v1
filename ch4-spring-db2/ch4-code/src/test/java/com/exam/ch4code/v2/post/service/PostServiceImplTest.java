package com.exam.ch4code.v2.post.service;

import com.exam.ch4code.v2.post.model.Post;
import com.exam.ch4code.v2.post.repository.PostRepository;
import com.exam.ch4code.v2.web.dto.post.PostCreateRequest;
import com.exam.ch4code.v2.web.dto.post.PostPageResponse;
import com.exam.ch4code.v2.web.dto.post.PostResponse;
import com.exam.ch4code.v2.web.dto.post.PostSearchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

// 단위 테스트 vs 통합 테스트 => 통합 테스트
// 서비스 + 레포
// h2 인메모리 db (가볍고 빠름, 테스트 용)
@SpringBootTest
@Transactional
class PostServiceImplTest {
  @Autowired
  private PostService service;
  @Autowired
  private PostRepository repository;

  @Test
  void 게시글_생성_조회_성공() {
    // given
    PostCreateRequest req = PostCreateRequest.builder()
                                             .title("post create request test")
                                             .body("hello world")
                                             .build();
    // when
    PostResponse saved  = service.createPost(req);
    PostResponse found = service.getPost(saved.getId());
    // then
    assertThat(found.getTitle()).isEqualTo(req.title());
    assertThat(found.getBody()).isEqualTo(req.body());
  }

}
