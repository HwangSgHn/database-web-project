package jbnu.SaveMeHomes.webservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class AvgOfReviews {
  private int room_id;
  private double latitude;
  private double longitude;
  private String name;
  private int elevator;
  private int parking;
  private double rating;
  private int maxDeposit;
  private int minDeposit;
  private int maxMonthly;
  private int minMonthly;
  private double water_press;
}
