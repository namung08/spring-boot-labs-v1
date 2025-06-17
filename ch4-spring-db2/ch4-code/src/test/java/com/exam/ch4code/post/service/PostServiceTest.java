package com.exam.ch4code.post.service;

import com.exam.ch4code.post.model.Post;
import com.exam.ch4code.post.repository.PostRepository;
import com.exam.ch4code.web.dto.post.PostCreateRequest;
import com.exam.ch4code.web.dto.post.PostResponse;
import com.exam.ch4code.web.dto.post.PostUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

// Junit : 테스트 코드 실행 주체
// Mockito : Mocking 기능
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

  // 테스트를 위해 필요한 객체
  // (1) PostService
  // (2) PostRepository


  // 필요는 하지만 잘 작동하는지 확인할 필요가 없어 Mock annotation 을 붙임
  // 가짜 객체로 생성
  @Mock
  private PostRepository repository;
  // 현제 테스트를 하는 객체는 service
  // 의존성을 주입하기 위해서는 InjectMocks 를 사용하여 주입을 하는데
  // 이때 넣는 객체는 Mock 로 생성된 객체를 할당
  @InjectMocks
  private PostServiceImpl service;

  @Test
  void 게시글_생성_성공() {
    // PostCreateRequest 객체 필요
    // PostRepository 정상 실행 된다는 것을 가정
    // PostResponse 리턴값 검증

    // Given(조건)
    PostCreateRequest req = PostCreateRequest.builder()
                                             .title("test request title").body("test request body").build();
    given(repository.save(any(Post.class)))
        .willReturn(new Post(1L, "test request title", "test request body"));

    // When(테스트 대상)
    PostResponse res = service.createPost(req);
    // Then(검증)
    assertThat(res.getId()).isEqualTo(1L);
    assertThat(res.getTitle()).isEqualTo("test request title");
    assertThat(res.getBody()).isEqualTo("test request body");
  }

  @Test
  void 게시글_구성_성공() {
    // given
    Long id = 1L;
    PostUpdateRequest req = PostUpdateRequest.builder()
                                             .title("update test request title")
                                             .body("update test request body")
                                             .build();
    // 아이디로 찾았을 때의 기존 데이터
    Post post = new Post(1L, "REST API 설계 가이드", "RESTful하게 설계하는 핵심 원칙과 URI 구조를 정리합니다.");
    Post updatedPost = new Post(1L, "update test request title", "update test request body");

    given(repository.findById(id))
        .willReturn(Optional.of(post));

    // when
    PostResponse res = service.updatePost(id, req);

    // then
    assertThat(res.getId()).isEqualTo(updatedPost.getId());
    assertThat(res.getTitle()).isEqualTo(updatedPost.getTitle());
    assertThat(res.getBody()).isEqualTo(updatedPost.getBody());

    // repository 의 findById 가 호출이 되었는지 검증하는 메소드
    // 행동 검증 메서드
    // 즉 메서드가 정말 실행이 되었는지 검증
    verify(repository).findById(id);
  }

  @Test
  void 게시글_단건_조회_성공() {
    //given
    Long id = 1L;
    Post post = new Post(1L, "REST API 설계 가이드", "RESTful하게 설계하는 핵심 원칙과 URI 구조를 정리합니다.");
    given(repository.findById(id))
        .willReturn(Optional.of(post));

    // when
    PostResponse res = service.getPost(id);

    // then
    assertThat(res.getId()).isEqualTo(post.getId());
    assertThat(res.getTitle()).isEqualTo(post.getTitle());
    assertThat(res.getBody()).isEqualTo(post.getBody());
  }
}
