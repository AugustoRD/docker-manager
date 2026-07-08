package com.augustord.docker_manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.StartContainerCmd;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.Container;

public class DockerServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DockerClient dockerClient;

    @Mock
    private StartContainerCmd startContainerCmd;

    @InjectMocks
    private DockerService dockerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should list containers when listContainers is called with showAll=true")
    public void testListContainers() {
        // arrange
        List<Container> containers = Collections.emptyList();

        when(dockerClient.listContainersCmd().withShowAll(true).exec()).thenReturn(containers);

        // act
        List<Container> result = dockerService.listContainers(true);

        // assert
        assertEquals(containers, result);
        verify(dockerClient.listContainersCmd().withShowAll(true)).exec();
    }

    @Test
    @DisplayName("Should list containers when listContainers is called with showAll=false")
    public void testListContainersWithShowAllFalse() {
        // arrange
        List<Container> containers = Collections.emptyList();

        when(dockerClient.listContainersCmd().withShowAll(false).exec()).thenReturn(containers);

        // act
        List<Container> result = dockerService.listContainers(false);

        // assert
        assertEquals(containers, result);
        verify(dockerClient.listContainersCmd().withShowAll(false)).exec();
    }

    @Test
    @DisplayName("Should start container when startContainer is called")
    public void testStartContainer() {
        // arrange
        String containerId = UUID.randomUUID().toString();

        when(dockerClient.startContainerCmd(containerId)).thenReturn(startContainerCmd);

        // act
        dockerService.startContainer(containerId);

        // assert
        // verify(dockerClient.startContainerCmd(containerId)).exec();
        verify(dockerClient).startContainerCmd(containerId);
        verify(startContainerCmd).exec();
    }

    @Test
    @DisplayName("Should throw NotFoundException when container is not found")
    public void testStartContainerNotFound() {
        // arrange
        String containerId = UUID.randomUUID().toString();

        when(dockerClient.startContainerCmd(containerId)).thenReturn(startContainerCmd);
        when(startContainerCmd.exec()).thenThrow(new NotFoundException("Container not found"));

        // act
        assertThrows(NotFoundException.class, () -> dockerService.startContainer(containerId));

        // assert
        // verify(dockerClient.startContainerCmd(containerId)).exec();
        verify(dockerClient).startContainerCmd(containerId);
        verify(startContainerCmd).exec();
    }

}
