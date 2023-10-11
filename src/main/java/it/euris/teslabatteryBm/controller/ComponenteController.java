package it.euris.teslabatteryBm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.euris.teslabatteryBm.dto.ComponenteDTO;
import it.euris.teslabatteryBm.exception.IdMustBeNullException;
import it.euris.teslabatteryBm.exception.IdMustNotBeNullException;
import it.euris.teslabatteryBm.model.Componente;
import it.euris.teslabatteryBm.service.ComponenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@SecurityRequirement(name = "authentication")
@RequestMapping("/componente")
public class ComponenteController {

  ComponenteService componenteService;

  @GetMapping("/v1")
  @Operation(description = "Questo metodo espone tutti i componenti")
  public List<ComponenteDTO> getAllComponente() {
    return componenteService.findAll()
        .stream()
        .map(Componente::toDto)
        .toList();
  }

  @PostMapping("/v1")
  public ComponenteDTO saveComponente(@RequestBody ComponenteDTO componenteDTO) {
    try {
      Componente componente = componenteDTO.toModel();
      return componenteService.insert(componente).toDto();
    } catch (IdMustBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/v1")
  public ComponenteDTO updateComponente(@RequestBody ComponenteDTO componenteDTO) {
    try {
      Componente componente = componenteDTO.toModel();
      return componenteService.update(componente).toDto();
    } catch (IdMustNotBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/v1/{id}")
  public Boolean deleteComponente(@PathVariable("id") Long idComponente) {
    return componenteService.deleteById(idComponente);
  }

  @GetMapping("/v1/{id}")
  public ComponenteDTO getComponenteById(@PathVariable("id") Long idComponente) {
    return componenteService.findById(idComponente).toDto();
  }
}
