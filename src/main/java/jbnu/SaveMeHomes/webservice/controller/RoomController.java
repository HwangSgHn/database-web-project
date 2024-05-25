package jbnu.SaveMeHomes.webservice.controller;

import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.AvgOfReviews;
import jbnu.SaveMeHomes.webservice.domain.Filter;
import jbnu.SaveMeHomes.webservice.domain.RoomWithOption;
import jbnu.SaveMeHomes.webservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @PostMapping("/api/room")
  public ResponseEntity<String> saveRoomWithOption(@RequestBody RoomWithOption roomWithOption) {
    roomService.saveRoomWithOption(roomWithOption);
    return ResponseEntity.ok().body("{\"message\": \"Room added successfully\"}");
  }

  // 처음 지도 가지고 올때
  @GetMapping("/api/rooms")
  public List<AvgOfReviews> rooms() {
    return roomService.getRoomsAndAvgOfReviews();
  }

  // 보증금 월세 전용면적 옵션
  @PostMapping("/api/rooms")
  public List<AvgOfReviews> addRoom(@RequestParam(value = "rating") String rating,
      @RequestParam(value = "deposit") String deposit,
      @RequestParam(value = "monthly") String monthly,
      @RequestParam(value = "area") String pyeong,
      @RequestParam(value = "option") String furniture) {

    Filter filter = new Filter();
    filter.setRating(Integer.parseInt(rating));
    filter.setDeposit(Integer.parseInt(deposit));
    filter.setMonthly(Integer.parseInt(monthly));
    filter.setPyeong(Integer.parseInt(pyeong));
    filter.setFurniture(furniture);

    return roomService.getRoomsAndAvgOfReviewsWithFilter(filter);
  }
}
