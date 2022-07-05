package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonUtils {
    
    private static final Gson gson = new GsonBuilder()
            .create();
    
    public static <T> String parseToJson(Object object, Class<T> clazz) {
        return gson.toJson(object, clazz);
    }
    
}
