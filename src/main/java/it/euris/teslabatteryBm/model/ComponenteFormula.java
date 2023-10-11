package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.ComponenteFormulaDTO;
import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.model.key.ComponenteFormulaKey;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.booleanToString;
import static it.euris.teslabatteryBm.utility.DataConversionUnit.numberToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "componente_formula")
@SQLDelete(sql = "UPDATE componente_formula SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
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

  @Column(name = "quantita")
  private Long quantita;

  @Column(name = "unita_di_misura")
  private String unitaDiMisura;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;



  @Override
  public ComponenteFormulaDTO toDto() {
    return ComponenteFormulaDTO
        .builder()
        .idComponente(numberToString(componente.getId()))
        .idFormula(numberToString(formula.getId()))
        .quantita(numberToString(quantita))
        .unitaDiMisura(unitaDiMisura)
        .deleted(booleanToString(deleted))
        .build();
  }
}
