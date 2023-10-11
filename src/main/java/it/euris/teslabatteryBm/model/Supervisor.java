package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.SupervisorDTO;
import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.booleanToString;
import static it.euris.teslabatteryBm.utility.DataConversionUnit.numberToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supervisor")
@SQLDelete(sql = "UPDATE supervisor SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Supervisor implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "cognome")
  private String cognome;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @OneToOne
  @JoinColumn(name = "id_ciclo_produttivo")
  private CicloProduttivo cicloProduttivo;

  @Override
  public SupervisorDTO toDto() {
    return SupervisorDTO
        .builder()
        .id(numberToString(id))
        .nome(nome)
        .cognome(cognome)
        .deleted(booleanToString(deleted))
        .build();
  }
}
