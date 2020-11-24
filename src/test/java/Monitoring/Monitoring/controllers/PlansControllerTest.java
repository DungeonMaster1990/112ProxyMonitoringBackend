package Monitoring.Monitoring.controllers;

import Monitoring.Monitoring.dto.api.viewmodels.enums.VmPlanSection;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmPlanRequest;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmPlanSectionRequest;
import Monitoring.Monitoring.infrastructure.PostgreSQL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlansControllerTest extends PostgreSQL {

    private static final String API_URL = "/api/v1.0/plans/";
    private static final String JSON_RESOURCES = "src/test/resources/json/plans";

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testPlans() throws Exception {
        VmPlanRequest request = VmPlanRequest.builder()
                .limit(3)
                .page(2)
                .planSectionID(VmPlanSection.emergency)
                .keyword("Измен")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*])", hasSize(2)));
    }

    @Test
    void testPlansSections() throws Exception {
        VmPlanSectionRequest request = VmPlanSectionRequest.builder()
                .startDate(ZonedDateTime.parse("2020-11-09T15:00:00.00000+03:00"))
                .finishDate(ZonedDateTime.parse("2020-11-21T19:00:00.00000+03:00"))
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post(API_URL+"sections")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getJson("sections.json")));
    }

    @Test
    void testPlansInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(API_URL+"info")
                .param("id", "1")
                .contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getJson("info.json")));
    }

    @Test
    void testPlansWorkers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(API_URL+"workers")
                .param("id", "3")
                .contentType(MediaType.ALL)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['manager']['name'])").value("Иванов Василий"))
                .andExpect(jsonPath("$['workers'][0]['name'])").value("Петов Иван"));
    }

    @Test
    void testPlansHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(API_URL+"history")
                .param("id", "100")
                .contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @Test
    void testPlansDescriptions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(API_URL+"descriptions")
                .param("id", "2")
                .contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['name'])").value("Подробное описание"));
    }

    @NotNull
    private String getJson(String fileName) throws Exception {
        String file = JSON_RESOURCES + File.separator + fileName;
        return Files.readString(Paths.get(file), StandardCharsets.UTF_8);
    }

}
