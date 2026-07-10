package com.augustord.docker_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.augustord.docker_manager.dtos.ContainerResponseDto;
import com.augustord.docker_manager.dtos.ImagesResponseDto;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;

@Service
public class DockerService {

    private final DockerClient dockerClient;

    public DockerService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public List<ContainerResponseDto> listContainers(boolean all) {
        List<Container> rawContainers = dockerClient.listContainersCmd().withShowAll(all).exec();

        return rawContainers.stream()
                .map(container -> new ContainerResponseDto(
                        container.getId(),
                        (container.getNames() != null && container.getNames().length > 0)
                                ? container.getNames()[0].replace("/", "")
                                : "",
                        container.getImage(),
                        container.getState(),
                        container.getStatus()))
                .toList();
    }

    public List<ImagesResponseDto> listImages() {
        List<Image> rawImages = dockerClient.listImagesCmd().exec();
        return mapToImagesResponseDto(rawImages);
    }

    public List<ImagesResponseDto> filterImages(String filterName) {
        List<Image> rawImages = dockerClient.listImagesCmd().withImageNameFilter(filterName).exec();
        return mapToImagesResponseDto(rawImages);
    }

    private List<ImagesResponseDto> mapToImagesResponseDto(List<Image> rawImages) {
        return rawImages.stream()
                .map(img -> new ImagesResponseDto(
                        img.getId().replace("sha256:", "").substring(0, 12),
                        (img.getRepoTags() != null && img.getRepoTags().length > 0) ? img.getRepoTags()[0] : "<none>",
                        img.getSize(),
                        img.getCreated()))
                .toList();
    }

    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }

    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }

    public void deleteContainer(String containerId) {
        dockerClient.removeContainerCmd(containerId).withForce(true).exec();
    }

    public void createContainer(String imageName) {
        dockerClient.createContainerCmd(imageName).exec();
    }

}
