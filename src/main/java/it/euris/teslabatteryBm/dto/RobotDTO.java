package it.euris.teslabatteryBm.dto;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.model.Robot;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RobotDTO implements Dto {
  
  private String id;
  
  private String mansione;

  private String dataAcquisto;
  
  private String prezzo;
  
  private String ordinePosizione;
  
  private String deleted;

  @Override
  public Robot toModel() {
    return Robot
        .builder()
        .id(stringToLong(id))
        .mansione(mansione)
        .dataAcquisto(stringToLocalDateTime(dataAcquisto))
        .prezzo(stringToDouble(prezzo))
        .ordinePosizione(stringToLong(ordinePosizione))
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
