package it.euris.teslabatteryBm.dto;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.model.Batteria;
import it.euris.teslabatteryBm.model.Formula;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.stringToBoolean;
import static it.euris.teslabatteryBm.utility.DataConversionUnit.stringToLong;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatteriaDTO implements Dto {
  
  private String id;
  
  private String kwH;
  
  private String peso;

  private String deleted;

  private String idFormula;

  @Override
  public Batteria toModel() {
    return Batteria
        .builder()
        .id(stringToLong(id))
        .kwH(stringToLong(kwH))
        .peso(stringToLong(peso))
        .deleted(stringToBoolean(deleted))
        .formula(Formula.builder().id(stringToLong(idFormula)).build())
        .build();
  }
}
