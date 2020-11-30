package ru.vtb.monitoring.vtb112.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmAccidentsRequest;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.response.VmAccidentResponse;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccidentsControllerTest extends PostgreSQL {

    private static final String JSON_RESOURCES = "src/test/resources/json/accidents";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ZonedDateTime dayNow = ZonedDateTime.parse("2020-11-16T19:00:00.00000+03:00");

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testAccidents() throws Exception {
        VmAccidentsRequest request = VmAccidentsRequest.builder()
                .limit(5)
                .page(3)
                .startDate(dayNow)
                .affectedSystems(Collections.singletonList("Платежи"))
                .keyword("Инцид")
                .build();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1.0/accidents")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        List<VmAccidentResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, VmAccidentResponse.class));

        Assert.assertEquals(1, response.size());
    }

    @Test
    void testAccidentsNew() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.ACCIDENTS + "/new")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getJson("new.json")));
    }

    @Test
    void testAccidentsInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.ACCIDENTS + "/info")
                .param("id", "5")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getJson("info.json")));
    }

    @Test
    void testAccidentsWorkers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.ACCIDENTS + "/workers")
                .param("id", "4")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getJson("workers.json")));
    }

    @Test
    void testAccidentsDescriptions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.ACCIDENTS + "/descriptions")
                .param("id", "3")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getJson("descriptions.json")));
    }

    @Test
    void testAccidentsHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.ACCIDENTS + "/history")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @NotNull
    private String getJson(String fileName) throws Exception {
        String file = JSON_RESOURCES + File.separator + fileName;
        return Files.readString(Paths.get(file), StandardCharsets.UTF_8);
    }
}
