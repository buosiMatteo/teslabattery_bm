package it.euris.teslabatteryBm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.euris.teslabatteryBm.dto.CatenaDiMontaggioDTO;
import it.euris.teslabatteryBm.exception.IdMustBeNullException;
import it.euris.teslabatteryBm.exception.IdMustNotBeNullException;
import it.euris.teslabatteryBm.model.CatenaDiMontaggio;
import it.euris.teslabatteryBm.service.CatenaDiMontaggioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@SecurityRequirement(name = "authentication")
@RequestMapping("/catena-di-montaggio")
public class CatenaDiMontaggioController {

  CatenaDiMontaggioService catenaDiMontaggioService;

  @GetMapping("/v1")
  @Operation(description = "Questo metodo espone tutte le catene di montaggio")
  public List<CatenaDiMontaggioDTO> getAllCatenaDiMontaggio() {
    return catenaDiMontaggioService.findAll()
        .stream()
        .map(CatenaDiMontaggio::toDto)
        .toList();
  }

  @PostMapping("/v1")
  public CatenaDiMontaggioDTO saveCatenaDiMontaggio(@RequestBody CatenaDiMontaggioDTO catenaDiMontaggioDTO) {
    try {
      CatenaDiMontaggio catenaDiMontaggio = catenaDiMontaggioDTO.toModel();
      return catenaDiMontaggioService.insert(catenaDiMontaggio).toDto();
    } catch (IdMustBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/v1")
  public CatenaDiMontaggioDTO updateCatenaDiMontaggio(@RequestBody CatenaDiMontaggioDTO catenaDiMontaggioDTO) {
    try {
      CatenaDiMontaggio catenaDiMontaggio = catenaDiMontaggioDTO.toModel();
      return catenaDiMontaggioService.update(catenaDiMontaggio).toDto();
    } catch (IdMustNotBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/v1/{id}")
  public Boolean deleteCatenaDiMontaggio(@PathVariable("id") Long idCatenaDiMontaggio) {
    return catenaDiMontaggioService.deleteById(idCatenaDiMontaggio);
  }

  @GetMapping("/v1/{id}")
  public CatenaDiMontaggioDTO getCatenaDiMontaggioById(@PathVariable("id") Long idCatenaDiMontaggio) {
    return catenaDiMontaggioService.findById(idCatenaDiMontaggio).toDto();
  }

}

