//package com.exam.ch4code.v2.post.service;
//
//import com.exam.ch4code.v2.post.model.Post;
//import com.exam.ch4code.v2.post.repository.PostRepository;
//import com.exam.ch4code.v2.web.dto.post.PostCreateRequest;
//import com.exam.ch4code.v2.web.dto.post.PostPageResponse;
//import com.exam.ch4code.v2.web.dto.post.PostResponse;
//import com.exam.ch4code.v2.web.dto.post.PostSearchRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//// 단위 테스트 vs 통합 테스트 => 통합 테스트
//// 서비스 + 레포
//// h2 인메모리 db (가볍고 빠름, 테스트 용)
//@SpringBootTest
//@Transactional
//class PostServiceImplTest {
//  @Autowired
//  private PostService service;
//  @Autowired
//  private PostRepository repository;
//
//  @Test
//  void 게시글_생성_조회_성공() {
//    // given
//    PostCreateRequest req = PostCreateRequest.builder()
//                                             .title("post create request test")
//                                             .body("hello world")
//                                             .build();
//    // when
//    PostResponse saved  = service.createPost(req);
//    PostResponse found = service.getPost(saved.getId());
//    // then
//    assertThat(found.getTitle()).isEqualTo(req.title());
//    assertThat(found.getBody()).isEqualTo(req.body());
//  }
//
//  @Test
//  void 게시글_검색_페이징() {
//    // given
//    for (int i = 1; i <= 20; i++) {
//      repository.save(new Post(null, "post " + i, "post body " + i));
//    }
//
//    PostSearchRequest searchRequest = new PostSearchRequest(0, 10, "post");
//
//    // when
//    PostPageResponse res = service.getPosts(searchRequest);
//
//    // then
//    assertThat(res.getPage()).isEqualTo(0);
//    assertThat(res.getSize()).isEqualTo(10);
//    assertThat(res.getTotalCount()).isEqualTo(20);
//    assertThat(res.getTotalPage()).isEqualTo(2);
//    assertThat(res.getPosts()).hasSize(10);
//
//  }
//}
