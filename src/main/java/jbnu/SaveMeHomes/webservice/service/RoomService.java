package jbnu.SaveMeHomes.webservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import jbnu.SaveMeHomes.webservice.domain.AvgOfReviews;
import jbnu.SaveMeHomes.webservice.domain.Filter;
import jbnu.SaveMeHomes.webservice.domain.Option;
import jbnu.SaveMeHomes.webservice.domain.RoomWithOption;
import jbnu.SaveMeHomes.webservice.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;

  public void saveRoomWithOption(RoomWithOption roomWithOption) {
    List<Option> options = new ArrayList<>();
    if (roomWithOption.getOption() != null) {
      StringTokenizer st = new StringTokenizer(roomWithOption.getOption(), ",");
      while (st.hasMoreTokens()) {
        String optionStr = st.nextToken();
        Option option = new Option();
        option.setFurniture(optionStr);
        options.add(option);
      }
    }
    roomRepository.saveRoomWithOption(roomWithOption, options);
  }

  public List<AvgOfReviews> getRoomsAndAvgOfReviews() {
    return roomRepository.getRoomsAndAvgOfReviews();
  }

  public List<AvgOfReviews> getRoomsAndAvgOfReviewsWithFilter(Filter filter) {

    StringTokenizer st = new StringTokenizer(filter.getFurniture(), ",");
    List<Option> options = new ArrayList<>();
    while (st.hasMoreTokens()) {
      Option option = new Option();
      option.setFurniture(st.nextToken());
      options.add(option);
    }

    return roomRepository.getRoomsAndAvgOfReviewsWithFilter(filter, options);
  }
}
