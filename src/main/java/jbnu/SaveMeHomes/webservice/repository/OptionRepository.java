package jbnu.SaveMeHomes.webservice.repository;

import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class OptionRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<Option> getOptionsByRoomId(int roomId) {
    String sql = "select * from options where room_id = ?";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Option.class), roomId);
  }
}
