package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.model.key.ComponenteFormulaKey;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "componente_formula")
public class ComponenteFormula implements Model {

  @EmbeddedId
  private ComponenteFormulaKey componenteFormulaKey;

  @ManyToOne
  @MapsId("idComponente")
  @JoinColumn(name = "id_componente")
  private Componente componente;

  @ManyToOne
  @MapsId("idFormula")
  @JoinColumn(name = "id_formula")
  private Formula formula;

  @Column(name = "quantità")
  private Long quantità;

  @Column(name = "unità_di_misura")
  private String unitàDiMisura;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;



  @Override
  public Dto toDto() {
    return null;
  }
}
