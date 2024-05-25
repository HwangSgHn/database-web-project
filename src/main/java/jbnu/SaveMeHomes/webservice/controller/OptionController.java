package jbnu.SaveMeHomes.webservice.controller;

import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.Option;
import jbnu.SaveMeHomes.webservice.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OptionController {

  private final OptionService optionService;

  @GetMapping("/api/options/{room_id}")
  public List<Option> getOptionsByRoomId(@PathVariable("room_id") int roomId) {
    return optionService.getOptionsByRoomId(roomId);
  }
}
