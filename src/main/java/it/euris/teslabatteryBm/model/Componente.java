package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.ComponenteDTO;
import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.booleanToString;
import static it.euris.teslabatteryBm.utility.DataConversionUnit.numberToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "componente")
@SQLDelete(sql = "UPDATE componente SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Componente implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "prezzo")
  private Double prezzo;

  @Column(name = "fornitore")
  private String fornitore;

  @Column(name = "pericoloso")
  private Boolean pericoloso;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;


  @Override
  public ComponenteDTO toDto() {
    return ComponenteDTO
        .builder()
        .id(numberToString(id))
        .prezzo(numberToString(prezzo))
        .fornitore(fornitore)
        .pericoloso(booleanToString(pericoloso))
        .deleted(booleanToString(deleted))
        .build();
  }
}
