package jbnu.SaveMeHomes.webservice.repository;

import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransferRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<Transfer> getTransferByRoomId(int roomId) {
    String sql = "select * from transfers where room_id = ?";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transfer.class), roomId);
  }

  public Transfer getTransferByTransferId(int transferId) {
    String sql = "select * from transfers where transfer_id = ?";
    return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Transfer.class), transferId);
  }

  public void saveTransfer(Transfer transfer) {
    String sql = "insert into transfers values (null, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, transfer.getRoom_id(), transfer.getTitle(), transfer.getComment(), transfer.getPicture());
  }

  public int deleteTransferAndGetRoomId(int transferId) {
    String getRoomIdByTrasferIdSql = "select room_id from transfers where transfer_id = ?";
    int roomId = jdbcTemplate.queryForObject(getRoomIdByTrasferIdSql, Integer.class, transferId);
    String sql = "delete from transfers where transfer_id = ?";
    jdbcTemplate.update(sql, transferId);
    return roomId;
  }

  public void updateTransfer(Transfer transfer) {
    String sql;
    if (transfer.getPicture() == null) {
      sql = "update transfers set title = ?, comment = ? where transfer_id = ?";
      jdbcTemplate.update(sql, transfer.getTitle(), transfer.getComment(), transfer.getTransfer_id());
    } else {
      sql = "UPDATE transfers SET title = ?, comment = ?, picture = ? WHERE transfer_id = ?";
      jdbcTemplate.update(sql, transfer.getTitle(), transfer.getComment(), transfer.getPicture(), transfer.getTransfer_id());
    }
  }
}
