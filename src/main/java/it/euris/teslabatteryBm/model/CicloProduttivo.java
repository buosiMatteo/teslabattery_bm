package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.CicloProduttivoDTO;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.enums.StatusCicloProduttivo;
import it.euris.teslabatteryBm.utility.DataConversionUnit;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ciclo_produttivo")
@SQLDelete(sql = "UPDATE ciclo_produttivo SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class CicloProduttivo implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "data_inizio")
  private LocalDateTime dataInizio;

  @Column(name = "data_fine")
  private LocalDateTime dataFine;

  @Column(name = "status_ciclo")
  @Enumerated(EnumType.STRING)
  private StatusCicloProduttivo statusCiclo;

  @Column(name = "data_cambio_status")
  private LocalDateTime dataCambioStatus;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;


  @Override
  public CicloProduttivoDTO toDto() {
    return CicloProduttivoDTO
        .builder()
        .id(numberToString(id))
        .dataInizio(localDateTimeToString(dataInizio))
        .dataFine(localDateTimeToString(dataFine))
        .statusCiclo(statusToString(statusCiclo))
        .dataCambioStatus(localDateTimeToString(dataCambioStatus))
        .deleted(booleanToString(deleted))
        .build();
  }
}
