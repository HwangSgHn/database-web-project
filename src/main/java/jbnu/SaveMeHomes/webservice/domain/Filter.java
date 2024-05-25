package jbnu.SaveMeHomes.webservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Filter {
  private int rating;
  private int deposit;
  private int monthly;
  private int pyeong;
  private String furniture;
}
