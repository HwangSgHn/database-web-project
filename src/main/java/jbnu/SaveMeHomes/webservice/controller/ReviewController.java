package jbnu.SaveMeHomes.webservice.controller;

import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.DetailedReview;
import jbnu.SaveMeHomes.webservice.domain.Review;
import jbnu.SaveMeHomes.webservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  // 층, 평, 주방분리, 중문, 리뷰
  @GetMapping("/api/reviews/{roomId}")
  public List<DetailedReview> getDetailedReviewsByRoomId(@PathVariable int roomId) {
    return reviewService.findDetailedReviewsByRoomId(roomId);
  }


  // 입력된 리뷰 저장
  @PostMapping("/api/review")
  public ResponseEntity<String> saveReview(@RequestBody Review review) {
    reviewService.saveReview(review);
    return ResponseEntity.ok().body("{\"message\": \"Review submitted successfully\"}");
  }
}
