package org.hojeda.minesweeper.util;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class JsonLoader {

    public static String readFromFile(String path, Map<String, String> params) {
        var stream = JsonLoader.class.getResourceAsStream(path);
        try (final Reader reader = new InputStreamReader(stream)) {
            return loadParams(CharStreams.toString(reader), params);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String loadParams(String json, Map<String, String> params) {
        for (Map.Entry<String, String> param : params.entrySet()) {
            json = json.replace("%"+ param.getKey() + "%", param.getValue());
        }
        return json;
    }

}
