package jbnu.SaveMeHomes.webservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Review {
  private int reviewId;
  private int room_id;
  private double rating;
  private int deposit;
  private int monthly;
  private int floor;
  private int pyeong;
  private boolean kitchen_sep;
  private boolean middle_door;
  private int water_press;
  private String review_comment;
}
