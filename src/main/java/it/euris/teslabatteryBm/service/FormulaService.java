package it.euris.teslabatteryBm.service;

import it.euris.teslabatteryBm.model.Componente;
import it.euris.teslabatteryBm.model.Formula;

import java.util.List;

public interface FormulaService {
  List<Formula> findAll();

  Formula insert(Formula address);

  Formula update(Formula address);

  Boolean deleteById(Long idFormula);

  Formula findById(Long idFormula);
}
