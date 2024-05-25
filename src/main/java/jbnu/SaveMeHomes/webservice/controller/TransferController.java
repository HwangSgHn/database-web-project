package jbnu.SaveMeHomes.webservice.controller;

import java.io.IOException;
import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.Transfer;
import jbnu.SaveMeHomes.webservice.domain.TransferWithBase64Picture;
import jbnu.SaveMeHomes.webservice.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class TransferController {

  private final TransferService transferService;

  @GetMapping("api/transfer/room/{roomId}")
  public List<TransferWithBase64Picture> getTransfers(@PathVariable int roomId) {
    return transferService.getTransferByRoomId(roomId);
  }

  @GetMapping("api/transfer/{transferId}")
  public TransferWithBase64Picture transfer(@PathVariable int transferId) {
    return transferService.getTransferByTransferId(transferId);
  }

  @PostMapping("api/transfer")
  public void saveTransfer(@RequestParam("room_id") int roomId,
      @RequestParam("title") String title,
      @RequestParam("comment") String comment,
      @RequestParam(value = "picture", required = false) MultipartFile picture) throws IOException {
    Transfer transfer = new Transfer();
    transfer.setRoom_id(roomId);
    transfer.setTitle(title);
    transfer.setComment(comment);
    if (picture != null && !picture.isEmpty()) {
      transfer.setPicture(picture.getBytes());
    }
    transferService.saveTransfer(transfer);
  }

  @PostMapping("api/transfer/delete")
  public int deleteTransfer(@RequestParam("transfer_id") int transferId) {
    return transferService.deleteTransferAndGetRoomId(transferId);
  }

  @PostMapping("api/transfer/update")
  public ResponseEntity<String> updateTransfer(@RequestParam("transfer_id") int transferId,
      @RequestParam("title") String title,
      @RequestParam("comment") String comment,
      @RequestParam(value = "picture", required = false) MultipartFile picture) throws IOException {
    Transfer transfer = new Transfer();
    transfer.setTransfer_id(transferId);
    transfer.setTitle(title);
    transfer.setComment(comment);
    if (picture != null && !picture.isEmpty()) {
      transfer.setPicture(picture.getBytes());
    }
    transferService.updateTransfer(transfer);
    return ResponseEntity.ok().body("{\"message\": \"Transfer updated successfully\"}");
  }
}
