package it.euris.teslabatteryBm.utility;

import it.euris.teslabatteryBm.model.CatenaDiMontaggio;
import it.euris.teslabatteryBm.model.Componente;
import it.euris.teslabatteryBm.model.Formula;
import it.euris.teslabatteryBm.model.Robot;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestSupport {

  public static Formula getFormula(Long id) {
    return Formula
        .builder()
        .id(id)
        .dataCreazione(LocalDateTime.now())
        .numeroUtilizzi(100L)
        .build();
  }

  public static Robot getRobot(Long id) {
    return Robot
        .builder()
        .id(id)
        .mansione("Test mansione")
        .dataAcquisto(LocalDateTime.now())
        .prezzo(100000.00)
        .ordinePosizione(1L)
        .build();
  }

  public static CatenaDiMontaggio getCatenaDiMontaggio(Long id) {
    return CatenaDiMontaggio
        .builder()
        .id(id)
        .nome("Test Catena Montaggio")
        .tempoDiCompletamento(LocalTime.of(1,0,0))
        .formula(getFormula(1L))
        .robot(getRobot(1L))
        .build();
  }

  public static Componente getComponente(Long id) {
    return Componente
        .builder()
        .id(id)
        .nome("Test Componente")
        .prezzo(100.00)
        .fornitore("Test Fornitore")
        .pericoloso(false)
        .build();
  }

}
