package it.euris.teslabatteryBm.service.impl;

import it.euris.teslabatteryBm.exception.IdMustBeNullException;
import it.euris.teslabatteryBm.exception.IdMustNotBeNullException;
import it.euris.teslabatteryBm.model.Componente;
import it.euris.teslabatteryBm.repository.ComponenteRepository;
import it.euris.teslabatteryBm.service.ComponenteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ComponenteServiceImpl implements ComponenteService {

  ComponenteRepository componenteRepository;


  @Override
  public List<Componente> findAll() {
    return componenteRepository.findAll();
  }

  @Override
  public Componente insert(Componente componente) {
    if (componente.getId() != null) {
      throw new IdMustBeNullException();
    }
    return componenteRepository.save(componente);
  }

  @Override
  public Componente update(Componente componente) {
    if (componente.getId() == null) {
      throw new IdMustNotBeNullException();
    }
    return componenteRepository.save(componente);
  }

  @Override
  public Boolean deleteById(Long idComponente) {
    componenteRepository.deleteById(idComponente);
    return componenteRepository.findById(idComponente).isEmpty();
  }

  @Override
  public Componente findById(Long idComponente) {
    return componenteRepository.findById(idComponente).orElse(Componente.builder().build());
  }
}
