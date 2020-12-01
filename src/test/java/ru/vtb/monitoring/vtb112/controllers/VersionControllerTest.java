package ru.vtb.monitoring.vtb112.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VersionControllerTest extends PostgreSQL {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCheckVersionNormal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.API + "/checkVersion")
                .param("version", PathConstants.VERSION))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['status'])").value("normal"));
    }

    @Test
    void testCheckVersionUnsupported() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PathConstants.API + "/checkVersion")
                .param("version", "777"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['status'])").value("unsupported"));
    }
}
