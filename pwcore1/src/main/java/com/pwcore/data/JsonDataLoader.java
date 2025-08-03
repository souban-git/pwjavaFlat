package com.pwcore.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwcore.utils.PropertyUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class JsonDataLoader {

    private static final String BASE_PATH = PropertyUtil.getProperty("testdata.path");

    public static Map<String, Object> load(String fileName) {
        String fullPath = BASE_PATH + fileName;

        InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(fullPath);

        if (is == null) {
            throw new RuntimeException("SON file not found at: " + fullPath);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading test data: " + fullPath, e);
        }
    }

    public static List<Map<String, Object>> loadData(String fileName) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resource = classLoader.getResource("testdata/" + fileName);

            if (resource == null) {
                throw new RuntimeException("❌ File not found: testdata/" + fileName);
            }

            File file = new File(resource.toURI());
            System.out.println("✅ Loading file from: " + file.getAbsolutePath());

            return new ObjectMapper().readValue(file, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error reading test data: testdata/" + fileName, e);
        }
    }
}

