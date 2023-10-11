package it.euris.teslabatteryBm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.euris.teslabatteryBm.config.security.SecurityConf;
import it.euris.teslabatteryBm.model.CatenaDiMontaggio;
import it.euris.teslabatteryBm.model.Formula;
import it.euris.teslabatteryBm.service.CatenaDiMontaggioService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(value = { SecurityConf.class })
class CatenaDiMontaggioControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CatenaDiMontaggioService catenaDiMontaggioService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldGetOneCatenaDiMontaggio() throws Exception {

    CatenaDiMontaggio catenaDiMontaggio = TestSupport.getCatenaDiMontaggio(1L);
    List<CatenaDiMontaggio> formulas = List.of(catenaDiMontaggio);

    when(catenaDiMontaggioService.findAll()).thenReturn(formulas);

    String auth = Base64.getEncoder().encodeToString(("admin:admin").getBytes());

    mockMvc.perform(MockMvcRequestBuilders.get("/catena-di-montaggio/v1")
            .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(catenaDiMontaggio.toDto())))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].nome").value(catenaDiMontaggio.getNome()));
  }

  @Test
  void shouldInsertACatenaDiMontaggio() throws Exception {

    CatenaDiMontaggio catenaDiMontaggio = TestSupport.getCatenaDiMontaggio(1L);

    when(catenaDiMontaggioService.insert(any())).thenReturn(catenaDiMontaggio);

    String auth = Base64.getEncoder().encodeToString(("admin:admin").getBytes());

    mockMvc.perform(post("/catena-di-montaggio/v1")
            .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(catenaDiMontaggio.toDto())))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.nome").value(catenaDiMontaggio.getNome()));
  }

  @Test
  void shouldReturnForbiddenWhenUserAuthenticated() throws Exception {

    CatenaDiMontaggio catenaDiMontaggio = TestSupport.getCatenaDiMontaggio(1L);

    when(catenaDiMontaggioService.insert(any())).thenReturn(catenaDiMontaggio);

    String auth = Base64.getEncoder().encodeToString(("user:user").getBytes());

    mockMvc.perform(post("/catena-di-montaggio/v1")
            .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(catenaDiMontaggio.toDto())))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

}