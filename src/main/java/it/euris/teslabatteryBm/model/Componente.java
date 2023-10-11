package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "componente")
public class Componente implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "prezzo")
  private BigDecimal prezzo;

  @Column(name = "fornitore")
  private String fornitore;

  @Column(name = "pericoloso")
  private Boolean pericoloso;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;


  @Override
  public Dto toDto() {
    return null;
  }
}
