package ru.vtb.monitoring.vtb112.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VersionControllerTest extends PostgreSQL {

    private static final String API_URL = "/api/v1.0/";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCheckVersion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(API_URL + "checkVersion")
                .param("version", "1.0")
                .contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['status'])").value("normal"));
    }
}
