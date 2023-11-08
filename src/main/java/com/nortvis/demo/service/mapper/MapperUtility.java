package com.nortvis.demo.service.mapper;

import com.nortvis.demo.entity.Image;
import com.nortvis.demo.exception.MapperException;
import com.nortvis.demo.feignclient.dto.ImgurApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nortvis.demo.constants.ApiConstants.*;

@Slf4j
public class MapperUtility {
    public static <T, D> D toObject(T t, Class<D> dtoClass) {
        log.info("Invoked toDto service");
        if (t == null)
            return null;
        D dto = null;
        try {
            dto = dtoClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new MapperException(e.getMessage());
        }
        BeanUtils.copyProperties(t, dto);
        return dto;
    }

    public static <T, D> Set<D> toObjectSet(Set<T> t, Class<D> dtoClass) {
        log.info("Invoked list toDtoList service");
        if (t == null)
            return null;
        return t.stream().map(m -> toObject(m, dtoClass)).collect(Collectors.toSet());
    }

    public static Optional<Image> toImage(ImgurApiResponse imgurApiResponse) {
        Map<String, Object> data = (Map<String, Object>) imgurApiResponse.getData();
        if (Objects.isNull(data))
            return Optional.empty();
        Image image = new Image();
        image.setId((String) data.get(ID));
        image.setLink((String) data.get(LINK));
        image.setDeleteHash((String) data.get(DELETE_HASH));
        return Optional.of(image);
    }
}
