package jbnu.SaveMeHomes.webservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class DetailedReview {
  private int floor;
  private int pyeong;
  private boolean kitchen_sep;
  private boolean middle_door;
  private String review_comment;
}
