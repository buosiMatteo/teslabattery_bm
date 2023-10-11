package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.FormulaDTO;
import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
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
@Table(name = "formula")
@SQLDelete(sql = "UPDATE formula SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
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
  public FormulaDTO toDto() {
    return FormulaDTO
        .builder()
        .id(numberToString(id))
        .dataCreazione(localDateTimeToString(dataCreazione))
        .numeroUtilizzi(numberToString(numeroUtilizzi))
        .deleted(booleanToString(deleted))
        .build();
  }
}
