package com.pwcore.data;

import java.util.List;
import java.util.Map;

public class TestDataContext {
    private static final ThreadLocal<List<Map<String, Object>>> testDataList = new ThreadLocal<>();
    private static final ThreadLocal<Integer> iterationIndex = new ThreadLocal<>();

    public static void set(List<Map<String, Object>> dataList) {
        testDataList.set(dataList);
        iterationIndex.set(0); // reset
    }

    public static Map<String, Object> current() {
        return testDataList.get().get(iterationIndex.get());
    }

    public static String get(String key) {
        return current().get(key)!=null? current().get(key).toString() : null;
    }

    public static Map<String, Object> getCurrentData(int index) {
        return testDataList.get().get(index);
    }

    public static int size() {
        return testDataList.get().size();
    }

    public static boolean hasNext() {
        return iterationIndex.get() < size();
    }

    public static void next() {
        iterationIndex.set(iterationIndex.get() + 1);
    }

    public static void clear() {
        testDataList.remove();
        iterationIndex.remove();
    }

    public static void setIteration(int i) {
        iterationIndex.set(i);
    }
}