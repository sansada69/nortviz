package com.nortvis.demo.controller;

import com.nortvis.demo.dto.ApiResponse;
import com.nortvis.demo.dto.ImageDto;
import com.nortvis.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<ImageDto>> uploadImage(@RequestPart(value = "image") MultipartFile image) {
        return ResponseEntity.ok(new ApiResponse<>(imageService.uploadImage(image)));
    }

    @GetMapping(value = "/{imageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<ImageDto>> getImage(@PathVariable("imageId") String imageId) {
        return ResponseEntity.ok(new ApiResponse<>(imageService.getImage(imageId)));
    }

    @DeleteMapping(value = "/{imageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Boolean>> deleteImage(@PathVariable("imageId") String imageId) {
        return ResponseEntity.ok(new ApiResponse<>(imageService.deleteImage(imageId)));
    }
}
