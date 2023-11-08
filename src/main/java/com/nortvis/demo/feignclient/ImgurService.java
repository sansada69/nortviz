package com.nortvis.demo.feignclient;

import com.nortvis.demo.feignclient.dto.ImgurApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(value = "imgurClient", url = "${imgur.api.baseurl}")
public interface ImgurService {
    @GetMapping(value = "/{imageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ImgurApiResponse<Map<String, Object>> getImage(@PathVariable("imageId") String imageId,
                                                   @RequestHeader("Authorization") String header);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ImgurApiResponse<Map<String, Object>> uploadImage(@RequestPart(value = "image") MultipartFile image,
                                 @RequestHeader("Authorization") String header);

    @DeleteMapping(value = "/{deleteHash}", produces = MediaType.APPLICATION_JSON_VALUE)
    ImgurApiResponse<Boolean> deleteImage(@PathVariable("deleteHash") String deleteHash,
                                 @RequestHeader("Authorization") String header);
}
