package it.euris.teslabatteryBm.service;

import it.euris.teslabatteryBm.model.Componente;

import java.util.List;

public interface ComponenteService {
  List<Componente> findAll();

  Componente insert(Componente address);

  Componente update(Componente address);

  Boolean deleteById(Long idComponente);

  Componente findById(Long idComponente);
}
