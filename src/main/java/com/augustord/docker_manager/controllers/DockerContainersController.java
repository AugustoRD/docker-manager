package com.augustord.docker_manager.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.augustord.docker_manager.dtos.ContainerResponseDto;
import com.augustord.docker_manager.service.DockerService;
import com.github.dockerjava.api.model.Container;

@RestController
@RequestMapping("/api/containers")
public class DockerContainersController {

    private final DockerService dockerService;

    public DockerContainersController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("")
    public ResponseEntity<List<ContainerResponseDto>> listContainers(
            @RequestParam(required = false, defaultValue = "true") boolean showAll) {
        List<ContainerResponseDto> containers = dockerService.listContainers(showAll);
        return ResponseEntity.ok(containers);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Void> startContainer(@PathVariable String id) {
        dockerService.startContainer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/stop")
    public ResponseEntity<Void> stopContainer(@PathVariable String id) {
        dockerService.stopContainer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContainer(@PathVariable String id) {
        dockerService.deleteContainer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<Void> createContainer(@RequestParam String imageName) {
        dockerService.createContainer(imageName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
