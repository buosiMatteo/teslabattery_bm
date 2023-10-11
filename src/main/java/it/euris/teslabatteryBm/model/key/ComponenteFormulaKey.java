package it.euris.teslabatteryBm.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Embeddable
public class ComponenteFormulaKey {

public ComponenteFormulaKey(Long idComponente, Long idFormula){
  this.idComponente=idComponente;
  this.idFormula=idFormula;
}
  @Column(name = "id_componente")
  private Long idComponente;

  @Column(name = "id_formula")
  private Long idFormula;

}