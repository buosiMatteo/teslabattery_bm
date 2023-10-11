package it.euris.teslabatteryBm.dto;

import it.euris.teslabatteryBm.dto.archetype.Dto;
import it.euris.teslabatteryBm.dto.archetype.Model;
import it.euris.teslabatteryBm.model.Componente;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static it.euris.teslabatteryBm.utility.DataConversionUnit.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponenteDTO implements Dto {
  private String id;

  private String nome;
  
  private String prezzo;
  
  private String fornitore;
  
  private String pericoloso;
  
  private String deleted;

  @Override
  public Componente toModel() {
    return Componente
        .builder()
        .id(stringToLong(id))
        .nome(nome)
        .prezzo(stringToDouble(prezzo))
        .fornitore(fornitore)
        .pericoloso(stringToBoolean(pericoloso))
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
