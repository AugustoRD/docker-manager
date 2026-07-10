package com.augustord.docker_manager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.augustord.docker_manager.dtos.ImagesResponseDto;
import com.augustord.docker_manager.service.DockerService;

@RestController
@RequestMapping("/api/images")
public class DockerImagesController {

    private final DockerService dockerService;

    public DockerImagesController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("")
    public ResponseEntity<List<ImagesResponseDto>> listImages() {
        List<ImagesResponseDto> images = dockerService.listImages();
        return ResponseEntity.ok(images);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ImagesResponseDto>> filterImages(
            @RequestParam(required = false, defaultValue = "image-") String filterName) {
        List<ImagesResponseDto> filteredImages = dockerService.filterImages(filterName);
        return ResponseEntity.ok(filteredImages);
    }

}
