package com.pwcore.hooks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwcore.base.BaseTest;
import com.pwcore.data.JsonDataLoader;
import com.pwcore.data.TestDataContext;
import com.pwcore.utils.JsonReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class TestLifecycleHook {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("🔹 Starting scenario: " + scenario.getName());
        BaseTest.setUp();

        for (String tag : scenario.getSourceTagNames()) {
            if (tag.startsWith("@DataFile_")) {
                String fileName = tag.replace("@DataFile_", "").trim();
                String fullPath = "testdata/" + fileName;

                try {
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    URL resource = classLoader.getResource(fullPath);

                    if (resource == null) {
                        throw new RuntimeException("Test data file not found: " + fullPath);
                    }

                    File file = new File(resource.toURI());
                    System.out.println("Found test data file: " + file.getAbsolutePath());

                    List<Map<String, Object>> dataList = JsonDataLoader.loadData(fileName);
                    TestDataContext.set(dataList);

                    System.out.println("Loaded test data from: " + fileName);
                } catch (Exception e) {
                    throw new RuntimeException("Error reading test data: " + fullPath, e);
                }
            }
        }
    }

    @After
    public void tearDown() {
        BaseTest.tearDown();
        TestDataContext.clear();
        System.out.println("🧹 Playwright and test data cleaned up");
    }
}
