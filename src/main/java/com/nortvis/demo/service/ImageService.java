package com.nortvis.demo.service;

import com.nortvis.demo.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface ImageService {
    ImageDto uploadImage(MultipartFile image);
    ImageDto getImage(String imageId);
    Boolean deleteImage(String imageId);
    Set<ImageDto> getImages();
}
