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
@Table(name = "batteria")
public class Batteria implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "kwH")
  private Long kwH;

  @Column(name = "peso")
  private Long peso;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @OneToOne
  @JoinColumn(name = "id_formula")
  private Formula formula;


  @Override
  public Dto toDto() {
    return null;
  }
}
