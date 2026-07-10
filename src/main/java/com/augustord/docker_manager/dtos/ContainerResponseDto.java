package com.augustord.docker_manager.dtos;

public record ContainerResponseDto(
        String id,
        String name,
        String image,
        String state,
        String status) {
}