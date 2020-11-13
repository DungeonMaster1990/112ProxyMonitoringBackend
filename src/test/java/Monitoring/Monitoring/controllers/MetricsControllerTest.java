package Monitoring.Monitoring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import Monitoring.Monitoring.dto.api.viewmodels.request.VmMetricsRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MetricsControllerTest {
    @Autowired
    MetricsController metricsController;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void contextLoads() throws Exception {
        VmMetricsRequest request = new VmMetricsRequest(true, "hello", 10, 0);

        mockMvc.perform(MockMvcRequestBuilders
                                .post("/api/v1.0/metrics")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(status().isOk());
    }

}