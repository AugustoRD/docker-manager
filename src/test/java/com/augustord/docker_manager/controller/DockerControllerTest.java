package com.augustord.docker_manager.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.augustord.docker_manager.controllers.DockerContainersController;
import com.augustord.docker_manager.service.DockerService;
import com.github.dockerjava.api.model.Container;

public class DockerControllerTest {

    @Mock
    private DockerService dockerService;

    private MockMvc mockMvc;

    @InjectMocks
    private DockerContainersController dockerController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dockerController).build();
    }

    @Test
    void listContainers() throws Exception {
        List<Container> mockContainersList = Collections.emptyList();
        when(dockerService.listContainers(true)).thenReturn(mockContainersList);

        // mockMvc.perform(get("/containers?showAll=true"))

        mockMvc.perform(get("/api/containers"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(dockerService).listContainers(true);
    }

    @Test
    void listContainersWithShowAllFalse() throws Exception {
        List<Container> mockContainersList = Collections.emptyList();
        when(dockerService.listContainers(false)).thenReturn(mockContainersList);

        // mockMvc.perform(get("/containers?showAll=false"))

        mockMvc.perform(get("/api/containers")
                .param("showAll", "false"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(dockerService).listContainers(false);
    }

}
