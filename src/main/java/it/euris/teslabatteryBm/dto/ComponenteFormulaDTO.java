package it.euris.teslabatteryBm.dto;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.model.Componente;
import it.euris.teslabatteryBm.model.ComponenteFormula;
import it.euris.teslabatteryBm.model.Formula;
import it.euris.teslabatteryBm.model.key.ComponenteFormulaKey;
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
public class ComponenteFormulaDTO implements Dto {
  
  private String idComponente;
  
  private String idFormula;
  
  private String quantita;
  
  private String unitaDiMisura;
  
  private String deleted;

  @Override
  public ComponenteFormula toModel() {
    return ComponenteFormula
        .builder()
        .componenteFormulaKey(new ComponenteFormulaKey(stringToLong(idComponente),stringToLong(idFormula)))
        .componente(Componente.builder().id(stringToLong(idComponente)).build())
        .formula(Formula.builder().id(stringToLong(idFormula)).build())
        .quantita(stringToLong(quantita))
        .unitaDiMisura(unitaDiMisura)
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
