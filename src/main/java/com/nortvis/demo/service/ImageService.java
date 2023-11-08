package com.nortvis.demo.service;

import com.nortvis.demo.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageDto uploadImage(MultipartFile image);
    ImageDto getImage(String imageId);
    Boolean deleteImage(String imageId);
}
