package jbnu.SaveMeHomes.webservice.service;

import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.DetailedReview;
import jbnu.SaveMeHomes.webservice.domain.Review;
import jbnu.SaveMeHomes.webservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;

  public void saveReview(Review review) {
    reviewRepository.saveReview(review);
  }

  public List<DetailedReview> findDetailedReviewsByRoomId(int roomId) {
    return reviewRepository.findDetailedReviewsByRoomId(roomId);
  }
}
