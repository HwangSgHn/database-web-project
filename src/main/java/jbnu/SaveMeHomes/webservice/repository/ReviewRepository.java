package jbnu.SaveMeHomes.webservice.repository;

import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.DetailedReview;
import jbnu.SaveMeHomes.webservice.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

  private final JdbcTemplate jdbcTemplate;

  public void saveReview(Review review) {
    String sql = "insert into reviews values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, review.getRoom_id(), review.getRating(), review.getDeposit(),review.getMonthly(), review.getFloor(), review.getPyeong(), review.isKitchen_sep(), review.isMiddle_door(), review.getWater_press(), review.getReview_comment());
  }

  // 층, 평, 주방분리, 중문, 리뷰
  public List<DetailedReview> findDetailedReviewsByRoomId(int roomId) {
    String sql = "select floor, pyeong, kitchen_sep, middle_door, review_comment from reviews where room_id = ?";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DetailedReview.class), roomId);
  }
}
