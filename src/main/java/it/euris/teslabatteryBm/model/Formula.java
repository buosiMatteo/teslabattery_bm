package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formula")
public class Formula implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "data_creazione")
  private LocalDateTime dataCreazione;

  @Column(name = "numero_utilizzi")
  private Long numeroUtilizzi;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;


  @Override
  public Dto toDto() {
    return null;
  }
}
