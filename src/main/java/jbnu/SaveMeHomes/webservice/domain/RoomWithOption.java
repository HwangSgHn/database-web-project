package jbnu.SaveMeHomes.webservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class RoomWithOption {
  private int room_id;
  private String name;
  private double latitude;
  private double longitude;
  private boolean elevator;
  private boolean parking;
  private String option;
}
