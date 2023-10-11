package it.euris.teslabatteryBm.service.impl;

import it.euris.teslabatteryBm.exception.IdMustBeNullException;
import it.euris.teslabatteryBm.exception.IdMustNotBeNullException;
import it.euris.teslabatteryBm.model.Formula;
import it.euris.teslabatteryBm.repository.FormulaRepository;
import it.euris.teslabatteryBm.service.FormulaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FormulaServiceImpl implements FormulaService {

  FormulaRepository formulaRepository;


  @Override
  public List<Formula> findAll() {
    return formulaRepository.findAll();
  }

  @Override
  public Formula insert(Formula formula) {
    if (formula.getId() != null) {
      throw new IdMustBeNullException();
    }
    return formulaRepository.save(formula);
  }

  @Override
  public Formula update(Formula formula) {
    if (formula.getId() == null) {
      throw new IdMustNotBeNullException();
    }
    return formulaRepository.save(formula);
  }

  @Override
  public Boolean deleteById(Long idFormula) {
    formulaRepository.deleteById(idFormula);
    return formulaRepository.findById(idFormula).isEmpty();
  }

  @Override
  public Formula findById(Long idFormula) {
    return formulaRepository.findById(idFormula).orElse(Formula.builder().build());
  }
}
