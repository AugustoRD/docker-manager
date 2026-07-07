package com.augustord.docker_manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;

public class DockerServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DockerClient dockerClient;

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

}
