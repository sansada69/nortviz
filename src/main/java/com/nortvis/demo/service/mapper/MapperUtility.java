package com.nortvis.demo.service.mapper;

import com.nortvis.demo.exception.MapperException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

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
}
