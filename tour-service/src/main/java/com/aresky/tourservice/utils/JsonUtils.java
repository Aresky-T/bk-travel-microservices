package com.aresky.tourservice.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class JsonUtils {
    public static final Gson GSON = new Gson();
    public static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> T convertFromString(String jsonString, Class<T> destinationType){
        try {
            JsonObject convertedObject = JsonParser.parseString(jsonString).getAsJsonObject();
            return GSON.fromJson(GSON.toJson(convertedObject), destinationType);
        } catch (JsonSyntaxException | IllegalStateException | NullPointerException ex){
            log.error("JsonUtils error occurred: {}", ex.getMessage());
            return null;
        }
    }

    public static <T> T convertFromString(String jsonString, Class<T> destinationType, Set<String> requiredKey){
        try {
            JsonObject rootObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonObject filteredObject = new JsonObject();

            requiredKey.forEach(key -> {
                filteredObject.add(key, rootObject.get(key));
            });

            String filteredObjectString = GSON.toJson(filteredObject);
            return GSON.fromJson(filteredObjectString, destinationType);
        } catch (JsonSyntaxException | IllegalStateException | NullPointerException ex){
            log.error("JsonUtils error occurred: {}", ex.getMessage());
            return null;
        }
    }
}
