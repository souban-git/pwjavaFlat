package com.pwcore.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonReader {
    public static <T> T read(String path, Class<T> clazz) throws Exception {
        return new ObjectMapper().readValue(new File(path), clazz);
    }
}