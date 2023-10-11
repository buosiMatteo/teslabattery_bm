package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.CatenaDiMontaggioDTO;
import it.euris.teslabatteryBm.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalTime;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "catena_di_montaggio")
@SQLDelete(sql = "UPDATE catena_di_montaggio SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class CatenaDiMontaggio implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "tempo_di_completamento")
  private LocalTime tempoDiCompletamento;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @OneToOne
  @JoinColumn(name = "id_formula")
  private Formula formula;

  @ManyToOne
  @JoinColumn(name = "id_robot")
  private Robot robot;

  @Override
  public CatenaDiMontaggioDTO toDto() {
    return CatenaDiMontaggioDTO
        .builder()
        .id(numberToString(id))
        .nome(nome)
        .tempoDiCompletamento(localTimeToString(tempoDiCompletamento))
        .deleted(booleanToString(deleted))
        .idFormula(numberToString(formula.getId()))
        .idRobot(numberToString(robot.getId()))
        .build();
  }
}
