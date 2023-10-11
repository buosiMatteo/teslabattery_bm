package it.euris.teslabatteryBm.service;

import it.euris.teslabatteryBm.model.CatenaDiMontaggio;

import java.util.List;

public interface CatenaDiMontaggioService {
  List<CatenaDiMontaggio> findAll();

  CatenaDiMontaggio insert(CatenaDiMontaggio address);

  CatenaDiMontaggio update(CatenaDiMontaggio address);

  Boolean deleteById(Long idCatenaDiMontaggio);

  CatenaDiMontaggio findById(Long idCatenaDiMontaggio);
}
