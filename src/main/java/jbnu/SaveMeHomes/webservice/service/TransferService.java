package jbnu.SaveMeHomes.webservice.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import jbnu.SaveMeHomes.webservice.domain.Transfer;
import jbnu.SaveMeHomes.webservice.domain.TransferWithBase64Picture;
import jbnu.SaveMeHomes.webservice.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferService {

  private final TransferRepository transferRepository;

  public List<TransferWithBase64Picture> getTransferByRoomId(int roomId) {
    List<TransferWithBase64Picture> list = new ArrayList<>();
    for (Transfer transfer : transferRepository.getTransferByRoomId(roomId)) {
      TransferWithBase64Picture transferWithBase64Picture = transferToTransferWithBase64Picture(transfer);
      list.add(transferWithBase64Picture);
    }
    return list;
  }

  public TransferWithBase64Picture getTransferByTransferId(int transferId) {
    Transfer transfer = transferRepository.getTransferByTransferId(transferId);
    TransferWithBase64Picture transferWithBase64Picture = transferToTransferWithBase64Picture(transfer);;
    return transferWithBase64Picture;
  }

  private TransferWithBase64Picture transferToTransferWithBase64Picture(Transfer transfer) {
    TransferWithBase64Picture transferWithBase64Picture = new TransferWithBase64Picture();
    transferWithBase64Picture.setTransfer_id(transfer.getTransfer_id());
    transferWithBase64Picture.setRoom_id(transfer.getRoom_id());
    transferWithBase64Picture.setTitle(transfer.getTitle());
    transferWithBase64Picture.setComment(transfer.getComment());
    if (transfer.getPicture() != null) {
      transferWithBase64Picture.setPicture(Base64.getEncoder().encodeToString(transfer.getPicture()));
      try (InputStream is = new ByteArrayInputStream(transfer.getPicture())) {
        transferWithBase64Picture.setPictureMimeType(URLConnection.guessContentTypeFromStream(is));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return transferWithBase64Picture;
  }

  public void saveTransfer(Transfer transfer) {
    transferRepository.saveTransfer(transfer);
  }

  public int deleteTransferAndGetRoomId(int transferId) {
    return transferRepository.deleteTransferAndGetRoomId(transferId);
  }

  public void updateTransfer(Transfer transfer) {
    transferRepository.updateTransfer(transfer);
  }

}
