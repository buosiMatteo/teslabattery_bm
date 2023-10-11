package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "catena_di_montaggio")
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
  public Dto toDto() {
    return null;
  }
}
