package com.nortvis.demo.controller;

import com.nortvis.demo.feignclient.ImgurService;
import com.nortvis.demo.feignclient.dto.ImgurApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.nortvis.demo.constants.ApiConstants.*;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @Autowired
    ImgurService imgurService;

    @Value("${imgur.client.id}")
    String clientId;

    @PostMapping
    public ResponseEntity<ImgurApiResponse> test(@RequestPart(value = "image") MultipartFile image) {
        ImgurApiResponse imgurApiResponse = imgurService.uploadImage(image, clientId);
        Map<String, Object> data = (Map<String, Object>) imgurApiResponse.getData();
        String imageId = (String) data.get(ID);
        String deleteHash = (String) data.get(DELETE_HASH);
        log.info("Image uploaded - id: {}, deleteHash: {}", imageId, deleteHash);
        imgurApiResponse = imgurService.getImage(imageId, clientId);
        String link = (String) data.get(LINK);
        log.info("Image link : {}", link);
        imgurApiResponse = imgurService.deleteImage(deleteHash, clientId);
        return ResponseEntity.ok(imgurApiResponse);
    }

}
