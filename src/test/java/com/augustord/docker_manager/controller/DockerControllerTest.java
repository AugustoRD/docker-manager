package com.augustord.docker_manager.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.augustord.docker_manager.controllers.DockerContainersController;
import com.augustord.docker_manager.dtos.ContainerResponseDto;
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
    @DisplayName("Should return 200 OK and empty list when no containers exist (showAll=true)")
    void listContainers() throws Exception {
        List<ContainerResponseDto> mockContainersList = Collections.emptyList();
        when(dockerService.listContainers(true)).thenReturn(mockContainersList);

        // mockMvc.perform(get("/containers?showAll=true"))

        mockMvc.perform(get("/api/containers"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(dockerService).listContainers(true);
    }

    @Test
    @DisplayName("Should return 200 OK and empty list when no containers exist (showAll=false)")
    void listContainersWithShowAllFalse() throws Exception {
        List<ContainerResponseDto> mockContainersList = Collections.emptyList();
        when(dockerService.listContainers(false)).thenReturn(mockContainersList);

        // mockMvc.perform(get("/containers?showAll=false"))

        mockMvc.perform(get("/api/containers")
                .param("showAll", "false"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(dockerService).listContainers(false);
    }

    @Test
    @DisplayName("Should return 204 No Content when starting a container")
    void startContainer() throws Exception {
        String containerId = "123456789abc";

        mockMvc.perform(post("/api/containers/{id}/start", containerId))
                .andExpect(status().isNoContent());

        verify(dockerService).startContainer(containerId);
    }

    @Test
    @DisplayName("Should return 204 No Content when stopping a container")
    void stopContainer() throws Exception {
        String containerId = "123456789abc";

        mockMvc.perform(post("/api/containers/{id}/stop", containerId))
                .andExpect(status().isNoContent());

        verify(dockerService).stopContainer(containerId);
    }

    @Test
    @DisplayName("Should return 204 No Content when deleting a container")
    void deleteContainer() throws Exception {
        String containerId = "123456789abc";

        mockMvc.perform(delete("/api/containers/{id}", containerId))
                .andExpect(status().isNoContent());

        verify(dockerService).deleteContainer(containerId);
    }

    @Test
    @DisplayName("Should return 201 Created when creating a container")
    void createContainer() throws Exception {
        String imageName = "nginx";

        mockMvc.perform(post("/api/containers")
                .param("imageName", imageName))
                .andExpect(status().isCreated());

        verify(dockerService).createContainer(imageName);
    }

}
