package com.mitskevich.syberrytesttask.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class FilesUtil {

    @SneakyThrows
    public static String readFromFile(final String pathFile) {
        return Files.readString(Paths.get(pathFile));
    }

    @SneakyThrows
    public static <T> T getObjectFromTestFiles(String filePath, TypeReference<T> tTypeReference) {
        File file = Path.of("src", "test", "resources", filePath).toFile();
        return getMapper().readValue(file, tTypeReference);
    }

    @SneakyThrows
    public static <T> T getObjectFromString(String json, TypeReference<T> tTypeReference) {
        return getMapper().readValue(json, tTypeReference);
    }

    @SneakyThrows
    public static <T> T readObjectFromJson(String string, TypeReference<T> tTypeReference) {
        return getMapper().readValue(string, tTypeReference);
    }

    public static ObjectMapper getMapper() {
        return Jackson2ObjectMapperBuilder
            .json()
            .modulesToInstall(new JavaTimeModule())
            .build();
    }

}
