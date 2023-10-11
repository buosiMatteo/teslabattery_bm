package it.euris.teslabatteryBm.service.impl;

import it.euris.teslabatteryBm.exception.IdMustBeNullException;
import it.euris.teslabatteryBm.exception.IdMustNotBeNullException;
import it.euris.teslabatteryBm.model.CatenaDiMontaggio;
import it.euris.teslabatteryBm.repository.CatenaDiMontaggioRepository;
import it.euris.teslabatteryBm.service.CatenaDiMontaggioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CatenaDiMontaggioServiceImpl implements CatenaDiMontaggioService {


  CatenaDiMontaggioRepository catenaDiMontaggioRepository;

  @Override
  public List<CatenaDiMontaggio> findAll() {
    return catenaDiMontaggioRepository.findAll();
  }

  @Override
  public CatenaDiMontaggio insert(CatenaDiMontaggio catenaDiMontaggio) {
    if (catenaDiMontaggio.getId() != null) {
      throw new IdMustBeNullException();
    }
    return catenaDiMontaggioRepository.save(catenaDiMontaggio);
  }

  @Override
  public CatenaDiMontaggio update(CatenaDiMontaggio catenaDiMontaggio) {
    if (catenaDiMontaggio.getId() == null) {
      throw new IdMustNotBeNullException();
    }
    return catenaDiMontaggioRepository.save(catenaDiMontaggio);
  }

  @Override
  public Boolean deleteById(Long idCatenaDiMontaggio) {
    catenaDiMontaggioRepository.deleteById(idCatenaDiMontaggio);
    return catenaDiMontaggioRepository.findById(idCatenaDiMontaggio).isEmpty();
  }

  @Override
  public CatenaDiMontaggio findById(Long idCatenaDiMontaggio) {
    return catenaDiMontaggioRepository.findById(idCatenaDiMontaggio).orElse(CatenaDiMontaggio.builder().build());
  }
}

