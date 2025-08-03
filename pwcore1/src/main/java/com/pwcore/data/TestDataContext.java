package com.pwcore.data;

import java.util.List;
import java.util.Map;

public class TestDataContext {
    private static final ThreadLocal<List<Map<String, Object>>> testData = new ThreadLocal<>();

    public static void set(List<Map<String, Object>> data) {
        testData.set(data);
    }

    public static List<Map<String, Object>> get() {
        return testData.get();
    }

    public static void clear() {
        testData.remove();
    }
}