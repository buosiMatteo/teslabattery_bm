package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.BatteriaDTO;
import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
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
@Table(name = "batteria")
@SQLDelete(sql = "UPDATE batteria SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Batteria implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "kwH")
  private Long kwH;

  @Column(name = "peso")
  private Long peso;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @OneToOne
  @JoinColumn(name = "id_formula")
  private Formula formula;


  @Override
  public BatteriaDTO toDto() {
    return BatteriaDTO
        .builder()
        .id(numberToString(id))
        .kwH(numberToString(kwH))
        .peso(numberToString(peso))
        .deleted(booleanToString(deleted))
        .idFormula(numberToString(formula.getId()))
        .build();
  }
}
