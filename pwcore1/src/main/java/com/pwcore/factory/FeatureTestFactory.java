package com.pwcore.factory;

import com.pwcore.data.JsonDataLoader;
import com.pwcore.data.TestDataContext;
import org.junit.jupiter.api.DynamicTest;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;


import java.io.File;
import java.util.*;
import java.util.stream.*;

public class FeatureTestFactory {

    public static Stream<DynamicTest> runAllFeaturesWithData(Class<?> runnerClass) {
        File[] jsonFiles = new File("testdata/").listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null || jsonFiles.length == 0)
            throw new RuntimeException("No test data files found in testdata/");

        return Stream.of(jsonFiles).flatMap(file -> {
            String fileName = file.getName();
            List<Map<String, Object>> dataList = JsonDataLoader.loadData(fileName);

            return IntStream.range(0, dataList.size())
                    .mapToObj(i -> DynamicTest.dynamicTest(
                            fileName + " - iteration " + (i + 1),
                            () -> {
                                TestDataContext.set(dataList);
                                TestDataContext.setIteration(i);

                                LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                                        .selectors(DiscoverySelectors.selectClass(runnerClass))
                                        .build();

                                LauncherFactory.create().execute(request);
                            }));
        });
    }
}
