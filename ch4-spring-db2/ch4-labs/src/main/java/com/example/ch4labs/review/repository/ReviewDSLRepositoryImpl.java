package com.example.ch4labs.review.repository;

import com.example.ch4labs.infrastructure.exception.review.ReviewException;
import com.example.ch4labs.infrastructure.exception.review.ReviewExceptionCode;
import com.example.ch4labs.infrastructure.utils.dsl.LikePattern;
import com.example.ch4labs.review.model.QReview;
import com.example.ch4labs.review.model.Review;
import com.example.ch4labs.web.dto.review.request.ReviewSearchRequest;
import com.example.ch4labs.web.dto.review.response.ReviewResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.ch4labs.infrastructure.utils.dsl.QueryDSLUtils.*;

@Slf4j
@RequiredArgsConstructor
public class ReviewDSLRepositoryImpl implements ReviewDSLRepository {
  private final JPAQueryFactory factory;

  @Override
  public Page<ReviewResponse> searchReview(ReviewSearchRequest req) {
    Pageable pageable = PageRequest.of(req.getPage(), req.getSize());
    QReview review = QReview.review;
    JPAQuery<Review> reviewJPAQuery = factory.selectFrom(review);
    log.info(String.valueOf(StringUtils.hasText(req.getBookTitleContains())));
    BooleanBuilder builder = new BooleanBuilder();
//    if(StringUtils.hasText(req.getBookTitleContains())) {
//      builder.and(
//          review.bookTitle.contains(req.getBookTitleContains())
//      );
//    }
    searchAddCondition(builder, req, review);
    if (req.getSort() != null) {
      String[] sorts = req.getSort().split(",");
      String field = sorts[0];
      boolean desc = sorts.length > 1 && "desc".equalsIgnoreCase(sorts[1]);
      searchOrder(field, desc, reviewJPAQuery, review);
    } else {
      reviewJPAQuery.orderBy(review.createdAt.desc());
    }
    List<Review> reviews = reviewJPAQuery
        .where(builder)
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
    JPAQuery<Long> reviewJPAQuery1 = factory.select(review.count()).from(review);
    Long reviewSize = reviewJPAQuery1.where(builder).fetchOne();

    return new PageImpl<>(reviews.stream().map(ReviewResponse::from).toList(), pageable, reviewSize == null ? 0 : reviewSize);
  }

  private void searchAddCondition(BooleanBuilder builder, ReviewSearchRequest req, QReview review) {
    if(StringUtils.hasText(req.getAuthor())) {
      builder.and(
          review.author.eq(req.getAuthor())
      );
    }
    if(StringUtils.hasText(req.getBookTitle())) {
      builder.and(
          review.bookTitle.eq(req.getBookTitle())
      );
    }
    if(StringUtils.hasText(req.getBookTitleContains())) {
      builder.and(
          review.bookTitle.contains(req.getBookTitleContains())
      );
    }
    if(StringUtils.hasText(req.getBookAuthor())) {
      builder.and(
          review.bookAuthor.eq(req.getBookAuthor())
      );
    }
    if(StringUtils.hasText(req.getTitleContains())) {
      builder.and(
          review.title.contains(req.getTitleContains())
      );
    }
    if(StringUtils.hasText(req.getContentContains())) {
      builder.and(
          review.content.contains(req.getContentContains())
      );
    }
    if(req.getRating() != null) {
      builder.and(
          review.rating.eq(req.getRating())
      );
    }
    if(req.getMinRating() != null) {
      builder.and(
          review.rating.goe(req.getMinRating())
      );
    }
    if(req.getMaxRating() != null) {
      builder.and(
          review.rating.loe(req.getMaxRating())
      );
    }
  }

  private void searchOrder(String field, boolean desc, JPAQuery<?> query, QReview review) {
    switch (field) {
      case "rating" -> query.orderBy(searchOrder(desc, review.rating));
      case "createdAt" -> query.orderBy(desc ? review.createdAt.desc() : review.createdAt.asc());
      case "id" -> query.orderBy(desc ? review.id.desc() : review.id.asc());
      case "title" -> query.orderBy(desc ? review.title.desc() : review.title.asc());
      case "content" -> query.orderBy(desc ? review.content.desc() : review.content.asc());
      case "author" -> query.orderBy(desc ? review.author.desc() : review.author.asc());
      case "bookTitle" -> query.orderBy(desc ? review.bookTitle.desc() : review.bookTitle.asc());
      case "bookAuthor" -> query.orderBy(desc ? review.bookAuthor.desc() : review.bookAuthor.asc());
      default -> throw new ReviewException(ReviewExceptionCode.INTERNAL_SERVER_ERROR);
    }
  }

  private OrderSpecifier<?> searchOrder(boolean desc, NumberPath<?> path) {
    return desc ? path.desc() : path.asc();
  }
}
