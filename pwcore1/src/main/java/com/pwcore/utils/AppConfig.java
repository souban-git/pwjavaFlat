package com.pwcore.utils;

import java.io.*;
import java.util.*;

public class AppConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = new FileInputStream("application.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("application.properties not found");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
