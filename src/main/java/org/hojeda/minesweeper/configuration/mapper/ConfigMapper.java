package org.hojeda.minesweeper.configuration.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Objects;

public class ConfigMapper {

    private static ObjectMapper mapper;

    public static ObjectMapper get() {
        if (Objects.isNull(mapper)) {
            mapper = new ObjectMapper(new YAMLFactory());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
        return mapper;
    }
}
