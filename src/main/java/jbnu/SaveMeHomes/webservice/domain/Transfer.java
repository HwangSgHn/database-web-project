package jbnu.SaveMeHomes.webservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Transfer {
  private int transfer_id;
  private int room_id;
  private String title;
  private String comment;
  private byte[] picture;
}
