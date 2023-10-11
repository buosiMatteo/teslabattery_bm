package it.euris.teslabatteryBm.dto;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.model.CicloProduttivo;
import it.euris.teslabatteryBm.model.Supervisor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.stringToBoolean;
import static it.euris.teslabatteryBm.utility.DataConversionUnit.stringToLong;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorDTO implements Dto {
  private String id;
  
  private String nome;
  
  private String cognome;
  
  private String deleted;
  
  private String idCicloProduttivo;

  @Override
  public Supervisor toModel() {
    return Supervisor
        .builder()
        .id(stringToLong(id))
        .nome(nome)
        .cognome(cognome)
        .deleted(stringToBoolean(deleted))
        .cicloProduttivo(CicloProduttivo.builder().id(stringToLong(idCicloProduttivo)).build())
        .build();
  }
}
