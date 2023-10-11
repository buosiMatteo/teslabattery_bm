package it.euris.teslabatteryBm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.euris.teslabatteryBm.config.security.SecurityConf;
import it.euris.teslabatteryBm.model.Formula;
import it.euris.teslabatteryBm.service.FormulaService;
import it.euris.teslabatteryBm.utility.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Base64;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@Import(value = { SecurityConf.class })
public class FormulaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FormulaService formulaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetOneFormula() throws Exception {

      Formula formula = TestSupport.getFormula(1L);
      List<Formula> formulas = List.of(formula);

      when(formulaService.findAll()).thenReturn(formulas);

      String auth = Base64.getEncoder().encodeToString(("admin:admin").getBytes());

      mockMvc.perform(MockMvcRequestBuilders.get("/formula/v1")
              .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(formula.toDto())))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(jsonPath("$").isArray())
          .andExpect(jsonPath("$.length()").value(1))
          .andExpect(jsonPath("$[0].numeroUtilizzi").value(formula.getNumeroUtilizzi()));
    }

    @Test
    void shouldInsertAFormula() throws Exception {

      Formula formula = TestSupport.getFormula(1L);

      when(formulaService.insert(any())).thenReturn(formula);

      String auth = Base64.getEncoder().encodeToString(("admin:admin").getBytes());

      mockMvc.perform(post("/formula/v1")
              .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(formula.toDto())))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(jsonPath("$.numeroUtilizzi").value(formula.getNumeroUtilizzi()));
    }

    @Test
    void shouldReturnForbiddenWhenUserAuthenticated() throws Exception {

      Formula formula = TestSupport.getFormula(1L);

      when(formulaService.insert(any())).thenReturn(formula);

      String auth = Base64.getEncoder().encodeToString(("user:user").getBytes());

      mockMvc.perform(post("/formula/v1")
              .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(formula.toDto())))
          .andDo(print())
          .andExpect(status().isForbidden());
    }

}
