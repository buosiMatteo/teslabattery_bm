package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.RobotDTO;
import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "robot")
@SQLDelete(sql = "UPDATE robot SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
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
  public RobotDTO toDto() {
    return RobotDTO
        .builder()
        .id(numberToString(id))
        .mansione(mansione)
        .dataAcquisto(localDateTimeToString(dataAcquisto))
        .prezzo(numberToString(prezzo))
        .ordinePosizione(numberToString(ordinePosizione))
        .deleted(booleanToString(deleted))
        .build();
  }
}


