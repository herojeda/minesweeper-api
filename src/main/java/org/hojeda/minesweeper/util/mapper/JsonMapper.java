package org.hojeda.minesweeper.util.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.Objects;

public class JsonMapper {

    private static ObjectMapper mapper;

    public static ObjectMapper get() {
        if (Objects.isNull(mapper)) {
            mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
        return mapper;
    }
}
