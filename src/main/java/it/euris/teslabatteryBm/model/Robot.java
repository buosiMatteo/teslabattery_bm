package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "robot")
public class Robot implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "mansione")
  private String mansione;

  @Column(name = "data_acquisto")
  private LocalDateTime dataAcquisto;

  @Column(name = "prezzo")
  private Double prezzo;

  @Column(name = "ordine_posizione")
  private Long ordinePosizione;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;


  @Override
  public Dto toDto() {
    return null;
  }
}

