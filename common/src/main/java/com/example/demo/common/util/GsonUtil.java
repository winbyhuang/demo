package com.example.demo.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class GsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T parseJson(String jsonData, Class<T> type) {
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    public static <T> List<T> parseJsonToList(String jsonData, Class<T> type) {
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {}.getType());
        return result;
    }

    public static <T> Map<String, T> parseJsonToMap(String jsonData, Class<T> type) {
        Map<String, T> result = gson.fromJson(jsonData, new TypeToken<Map<String, T>>() {}.getType());
        return result;
    }

}
