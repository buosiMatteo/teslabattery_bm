package it.euris.teslabatteryBm.dto;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.model.Formula;
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
public class FormulaDTO implements Dto {
  
  private String id;

  private String dataCreazione;
  
  private String numeroUtilizzi;
  
  private String deleted;

  @Override
  public Formula toModel() {
    return Formula
        .builder()
        .id(stringToLong(id))
        .dataCreazione(stringToLocalDateTime(dataCreazione))
        .numeroUtilizzi(stringToLong(numeroUtilizzi))
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
