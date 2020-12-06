package ru.vtb.monitoring.vtb112.controllers;

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
import ru.vtb.monitoring.vtb112.dto.api.enums.VmPlanSection;
import ru.vtb.monitoring.vtb112.dto.api.request.VmPlanRequest;
import ru.vtb.monitoring.vtb112.dto.api.request.VmPlanSectionRequest;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

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
class PlansControllerTest extends PostgreSQL {

    private static final String JSON_RESOURCES = "src/test/resources/json/plans";

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testPlansEmptyKeyword() throws Exception {
        VmPlanRequest request = VmPlanRequest.builder()
                .limit(10)
                .page(1)
                .planSectionId(VmPlanSection.EMERGENCY.getId())
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.PLANS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testPlansEmptySectionId() throws Exception {
        VmPlanRequest request = VmPlanRequest.builder()
                .limit(10)
                .page(1)
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.PLANS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testInvalidPagingPlans() throws Exception {
        VmPlanRequest request = VmPlanRequest.builder()
                .limit(0)
                .page(2)
                .planSectionId(VmPlanSection.EMERGENCY.getId())
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.PLANS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        request.setLimit(0);
        request.setPage(1);
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.PLANS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPlans() throws Exception {
        VmPlanRequest request = VmPlanRequest.builder()
                .limit(3)
                .page(2)
                .planSectionId(VmPlanSection.EMERGENCY.getId())
                .keyword("Измен")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.PLANS)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*])", hasSize(2)));
    }

    @Test
    void testPlannedPlansWithKeyword() throws Exception {
        VmPlanRequest request = VmPlanRequest.builder()
                .limit(100)
                .page(1)
                .planSectionId(VmPlanSection.NORMAL.getId())
                .startDate(ZonedDateTime.now().minusDays(3).minusHours(2))
                .finishDate(ZonedDateTime.now().plusDays(3).plusHours(2))
                .keyword("IM")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.PLANS)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*])", hasSize(4)))
                .andExpect(jsonPath("$[0]['name'])").value("Изменение IM-11"))
                .andExpect(jsonPath("$[1]['name'])").value("Изменение IM-10"))
                .andExpect(jsonPath("$[2]['name'])").value("Изменение IM-9"))
                .andExpect(jsonPath("$[3]['name'])").value("Изменение IM-8"));
    }

    @Test
    void testPlannedPlansNoKeyword() throws Exception {
        VmPlanRequest request = VmPlanRequest.builder()
                .limit(100)
                .page(1)
                .planSectionId(VmPlanSection.STANDARD.getId())
                .startDate(ZonedDateTime.now().minusDays(3).minusHours(2))
                .finishDate(ZonedDateTime.now().plusDays(3).plusHours(2))
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.PLANS)
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
                .startDate(ZonedDateTime.now().minusDays(3))
                .finishDate(ZonedDateTime.now().plusDays(3))
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post(PathConstants.PLANS + "/sections")
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
                .get(PathConstants.PLANS + "/info")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getJson("info.json")));
    }

    @Test
    void testPlansWorkers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.PLANS + "/workers")
                .param("id", "3")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['manager']['name'])").value("Иванов Василий"))
                .andExpect(jsonPath("$['workers'][0]['name'])").value("Петров Иван"));
    }

    @Test
    void testPlansHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.PLANS + "/history")
                .param("id", "100")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @Test
    void testPlansDescriptions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.PLANS + "/descriptions")
                .param("id", "2")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['name'])").value("Для сотрудников"))
                .andExpect(jsonPath("$[0]['value'])").value("Подробное описание"));
    }

    @NotNull
    private String getJson(String fileName) throws Exception {
        String file = JSON_RESOURCES + File.separator + fileName;
        return Files.readString(Paths.get(file), StandardCharsets.UTF_8);
    }

}
