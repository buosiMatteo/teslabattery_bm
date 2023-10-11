package it.euris.teslabatteryBm.dto;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.enums.StatusCicloProduttivo;
import it.euris.teslabatteryBm.model.CicloProduttivo;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class CicloProduttivoDTO implements Dto {
  
  private String id;
  
  private String dataInizio;
  
  private String dataFine;
  
  private String statusCiclo;
  
  private String dataCambioStatus;
  
  private String deleted;

  @Override
  public CicloProduttivo toModel() {
    return CicloProduttivo
        .builder()
        .id(stringToLong(id))
        .dataInizio(stringToLocalDateTime(dataInizio))
        .dataFine(stringToLocalDateTime(dataFine))
        .statusCiclo(stringToStatus(statusCiclo))
        .dataCambioStatus(stringToLocalDateTime(statusCiclo))
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
