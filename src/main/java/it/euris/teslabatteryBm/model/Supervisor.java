package it.euris.teslabatteryBm.model;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supervisor")
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
  public Dto toDto() {
    return null;
  }
}
