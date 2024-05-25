package jbnu.SaveMeHomes.webservice.repository;

import java.util.ArrayList;
import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.AvgOfReviews;
import jbnu.SaveMeHomes.webservice.domain.Filter;
import jbnu.SaveMeHomes.webservice.domain.Option;
import jbnu.SaveMeHomes.webservice.domain.RoomWithOption;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoomRepository {

  private final JdbcTemplate jdbcTemplate;

  public void saveRoomWithOption(RoomWithOption roomWithOption, List<Option> options) {
    String sql = "insert into rooms values(null, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, roomWithOption.getName(), roomWithOption.getLatitude(), roomWithOption.getLongitude(), roomWithOption.isElevator(), roomWithOption.isParking());
    sql = "select room_id from rooms where name = ?";
    int roomId = jdbcTemplate.queryForObject(sql, Integer.class, roomWithOption.getName());
    sql = "insert into options values(null, ?, ?)";
    for (Option option : options) {
      jdbcTemplate.update(sql, roomId, option.getFurniture());
    }
  }

  public List<AvgOfReviews> getRoomsAndAvgOfReviews() {
    String sql = "select Rm.room_id, latitude, longitude, name, elevator, parking, ifnull(avg(rating), -1) as rating, ifnull(max(deposit), -1) as max_deposit, ifnull(min(deposit), -1) as min_deposit, ifnull(max(monthly), -1) as max_monthly, ifnull(min(monthly), -1) as min_monthly, ifnull(avg(water_press), -1) as water_press from rooms Rm left join reviews Rv on Rm.room_id = Rv.room_id group by Rm.room_id";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AvgOfReviews.class));
  }

  public List<AvgOfReviews> getRoomsAndAvgOfReviewsWithFilter(Filter filter, List<Option> options) {

    List<Integer> roomIds = new ArrayList<>();

    if (options.size() == 0) {

      String roomOptionSql = "select room_id from rooms natural join options group by room_id";
      List<Integer> list = jdbcTemplate.queryForList(roomOptionSql, Integer.class);
      for (Integer roomId : list) {
        roomIds.add(roomId);
      }

    } else {

      String roomOptionSql = "select room_id, furniture from rooms natural join options order by room_id";
      List<Option> list = jdbcTemplate.query(roomOptionSql, new BeanPropertyRowMapper<>(Option.class));

      int count = 0;
      int roomId = 0;
      for (Option option : list) {
        if (option.getRoom_id() != roomId) {
          roomId = option.getRoom_id();
          count = 0;
        }
        for (Option ot : options) {
          if (option.getFurniture().equals(ot.getFurniture())) {
            count++;
            break;
          }
        }
        if (count == options.size()) {
          roomIds.add(roomId);
        }
      }
    }

    List<AvgOfReviews> markers = new ArrayList<>();

    for (Integer room_id : roomIds) {
      String sql = "select Rm.room_id, latitude, longitude, name, elevator, parking, ifnull(avg(rating), -1) as rating, ifnull(max(deposit), -1) as max_deposit, ifnull(min(deposit), -1) as min_deposit, ifnull(max(monthly), -1) as max_monthly, ifnull(min(monthly), -1) as min_monthly, ifnull(avg(water_press), -1) as water_press from rooms Rm left join reviews Rv on Rm.room_id = Rv.room_id where Rm.room_id = " + room_id + " group by room_id having";
      StringBuilder sb = new StringBuilder();
      if (filter.getRating() != 0) {
        sb.append(" rating >= ").append(filter.getRating()).append(" and");
      }
      if (filter.getDeposit() != 0) {
        sb.append(" min_deposit <= ").append(filter.getDeposit()).append(" and");
      }
      if (filter.getMonthly() != 0) {
        sb.append(" min_monthly <= ").append(filter.getMonthly()).append(" and");
      }
      if (filter.getPyeong() != 0) {
        if (filter.getPyeong() == 3) {
          sb.append(" min_pyeong <= 3").append(" and");
        } else if (filter.getPyeong() == 6) {
          sb.append(" min_pyeong >= 3 and max_pyeong <= 6").append(" and");
        } else if (filter.getPyeong() == 9) {
          sb.append(" min_pyeong >= 6 and max_pyeong <= 9").append(" and");
        } else {
          sb.append(" min_pyeong >= 9").append(" and");
        }
      }
      sql += sb.toString().substring(0, sb.toString().length() - 4);
      for (AvgOfReviews aor : jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AvgOfReviews.class))) {
        markers.add(aor);
      }
    }
    return markers;
  }
}
