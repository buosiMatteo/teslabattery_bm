package it.euris.teslabatteryBm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.euris.teslabatteryBm.config.security.SecurityConf;
import it.euris.teslabatteryBm.model.CatenaDiMontaggio;
import it.euris.teslabatteryBm.model.Componente;
import it.euris.teslabatteryBm.service.CatenaDiMontaggioService;
import it.euris.teslabatteryBm.service.ComponenteService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(value = { SecurityConf.class })
class ComponenteControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ComponenteService componenteService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldGetOneComponente() throws Exception {

    Componente componente = TestSupport.getComponente(1L);
    List<Componente> componenti = List.of(componente);

    when(componenteService.findAll()).thenReturn(componenti);

    String auth = Base64.getEncoder().encodeToString(("admin:admin").getBytes());

    mockMvc.perform(MockMvcRequestBuilders.get("/componente/v1")
            .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(componente.toDto())))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].nome").value(componente.getNome()))
        .andExpect(jsonPath("$[0].prezzo").value(componente.getPrezzo()));
  }

  @Test
  void shouldInsertAComponente() throws Exception {

    Componente componente = TestSupport.getComponente(1L);

    when(componenteService.insert(any())).thenReturn(componente);

    String auth = Base64.getEncoder().encodeToString(("admin:admin").getBytes());

    mockMvc.perform(post("/componente/v1")
            .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(componente.toDto())))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.nome").value(componente.getNome()));
  }

  @Test
  void shouldReturnForbiddenWhenUserAuthenticated() throws Exception {

    Componente componente = TestSupport.getComponente(1L);

    when(componenteService.insert(any())).thenReturn(componente);

    String auth = Base64.getEncoder().encodeToString(("user:user").getBytes());

    mockMvc.perform(post("/componente/v1")
            .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(componente.toDto())))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

}