package com.pentalog.sr.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Class that builds and serializes a json object with a passed resource bundle key
 * @author acozma and bpopovici
 *
 */
public class BundleMapSerializer implements JsonSerializer<Map<String, String>> {

    @Override
    public JsonElement serialize(final Map<String, String> bundleMap, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject resultJson =  new JsonObject();

        for (final String key: bundleMap.keySet()) {
            try {
                createFromBundleKey(resultJson, key, bundleMap.get(key));
            } catch (final IOException e) {
            }
        }

        return resultJson;
    }
    public static JsonObject createFromBundleKey(final JsonObject resultJson, final String key, final String value) throws IOException {
        if (!key.contains(".")) {
            resultJson.addProperty(key, value);

            return resultJson;
        }

        final String currentKey = firstKey(key);
        if (currentKey != null) {
            final String subRightKey = key.substring(currentKey.length() + 1, key.length());
            final JsonObject childJson = getJsonIfExists(resultJson, currentKey);

            resultJson.add(currentKey, createFromBundleKey(childJson, subRightKey, value));
        }

        return resultJson;
    }

        private static String firstKey(final String fullKey) {
            final String[] splittedKey = fullKey.split("\\.");

            return (splittedKey.length != 0) ? splittedKey[0] : fullKey;
        }

        private static JsonObject getJsonIfExists(final JsonObject parent, final String key) {
            if (parent == null) {
                return null;
            }

            if (parent.get(key) != null && !(parent.get(key) instanceof JsonObject)) {
                throw new IllegalArgumentException("Invalid key \'" + key + "\' for parent: " + parent + "\nKey can not be JSON object and property or array in one time");
            }

            if (parent.getAsJsonObject(key) != null) {
                return parent.getAsJsonObject(key);
            } else {
                return new JsonObject();
            }
       }
		
}
