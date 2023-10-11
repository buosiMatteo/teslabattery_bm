package it.euris.teslabatteryBm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.euris.teslabatteryBm.dto.FormulaDTO;
import it.euris.teslabatteryBm.exception.IdMustBeNullException;
import it.euris.teslabatteryBm.exception.IdMustNotBeNullException;
import it.euris.teslabatteryBm.model.Formula;
import it.euris.teslabatteryBm.service.FormulaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@SecurityRequirement(name = "authentication")
@RequestMapping("/formula")
public class FormulaController {

  FormulaService formulaService;

  @GetMapping("/v1")
  @Operation(description = "Questo metodo espone tutte le formule")
  public List<FormulaDTO> getAllFormula() {
    return formulaService.findAll()
        .stream()
        .map(Formula::toDto)
        .toList();
  }

  @PostMapping("/v1")
  public FormulaDTO saveFormula(@RequestBody FormulaDTO formulaDTO) {
    try {
      Formula formula = formulaDTO.toModel();
      return formulaService.insert(formula).toDto();
    } catch (IdMustBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/v1")
  public FormulaDTO updateFormula(@RequestBody FormulaDTO formulaDTO) {
    try {
      Formula formula = formulaDTO.toModel();
      return formulaService.update(formula).toDto();
    } catch (IdMustNotBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/v1/{id}")
  public Boolean deleteFormula(@PathVariable("id") Long idFormula) {
    return formulaService.deleteById(idFormula);
  }

  @GetMapping("/v1/{id}")
  public FormulaDTO getFormulaById(@PathVariable("id") Long idFormula) {
    return formulaService.findById(idFormula).toDto();
  }
}
