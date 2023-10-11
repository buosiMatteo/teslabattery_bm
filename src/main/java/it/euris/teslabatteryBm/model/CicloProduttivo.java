package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.enums.StatusCicloProduttivo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ciclo_produttivo")
public class CicloProduttivo implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "data_inizio")
  private LocalDateTime dataInizio;

  @Column(name = "data_fine")
  private LocalDateTime dataFine;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusCicloProduttivo status;

  @Column(name = "data_cambio_status")
  private LocalDateTime dataCambioStatus;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;


  @Override
  public Dto toDto() {
    return null;
  }
}
