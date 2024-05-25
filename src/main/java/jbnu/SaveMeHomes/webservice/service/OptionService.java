package jbnu.SaveMeHomes.webservice.service;

import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.Option;
import jbnu.SaveMeHomes.webservice.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionService {

  private final OptionRepository optionRepository;

  public List<Option> getOptionsByRoomId(int roomId) {
    return optionRepository.getOptionsByRoomId(roomId);
  }
}
