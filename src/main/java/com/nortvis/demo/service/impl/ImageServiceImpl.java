package com.nortvis.demo.service.impl;

import com.nortvis.demo.dto.ImageDto;
import com.nortvis.demo.entity.Image;
import com.nortvis.demo.entity.User;
import com.nortvis.demo.exception.InternalServerError;
import com.nortvis.demo.exception.NotFoundException;
import com.nortvis.demo.feignclient.ImgurService;
import com.nortvis.demo.feignclient.dto.ImgurApiResponse;
import com.nortvis.demo.repository.ImageRepository;
import com.nortvis.demo.service.ImageService;
import com.nortvis.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.nortvis.demo.service.mapper.MapperUtility.toImage;
import static com.nortvis.demo.service.mapper.MapperUtility.toObject;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final UserService userService;
    private final ImageRepository imageRepository;
    private final ImgurService imgurService;

    @Value("${imgur.client.id}")
    private String clientId;

    @Override
    @Transactional
    public ImageDto uploadImage(MultipartFile image) {
        log.info("Request received to upload image");
        String userName = userService.getLoggedInUserName();
        User user = userService.getUserEntity(userName);
        // Imgur upload image API call
        ImgurApiResponse<Map<String, Object>> imgurApiResponse = imgurService.uploadImage(image, clientId);
        Optional<Image> imageOptional = toImage(imgurApiResponse);
        if (imageOptional.isEmpty())
            throw new InternalServerError("Imgur API service is failing");
        Set<Image> imageSet;
        if (Objects.isNull(user.getImageSet())) {
            imageSet = Set.of(imageOptional.get());
        } else  {
            imageSet = user.getImageSet();
            imageSet.add(imageOptional.get());
        }
        user.setImageSet(imageSet);
        userService.updateUser(user);
        log.info("Uploaded image successfully");
        return toObject(imageOptional.get(), ImageDto.class);
    }

    @Override
    public ImageDto getImage(String imageId) {
        log.info("Request received to fetch image by id: {}", imageId);
        Optional<Image> image = imageRepository.findById(imageId);
        if (image.isEmpty())
            throw new NotFoundException(String.format("Image with id %s does not exists", imageId));
        ImageDto imageDto = toObject(image.get(), ImageDto.class);
        log.info("Image details fetched for the id: {}", imageId);
        return imageDto;
    }

    @Override
    public Boolean deleteImage(String imageId) {
        log.info("Request received to delete image by id: {}", imageId);
        Optional<Image> image = imageRepository.findById(imageId);
        if (image.isEmpty())
            throw new NotFoundException(String.format("Image with id %s does not exists", imageId));
        imageRepository.deleteById(imageId);
        imgurService.deleteImage(image.get().getDeleteHash(), clientId);
        return true;
    }
}
