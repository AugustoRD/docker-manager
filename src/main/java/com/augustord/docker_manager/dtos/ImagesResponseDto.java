package com.augustord.docker_manager.dtos;

public record ImagesResponseDto(
        String id,
        String name,
        Long size,
        Long created) {
}
