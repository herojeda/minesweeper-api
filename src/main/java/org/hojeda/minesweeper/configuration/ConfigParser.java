package org.hojeda.minesweeper.configuration;

import org.hojeda.minesweeper.configuration.model.BaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ConfigParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigParser.class);
    private static final String CONFIG_FILE_LOCATION = "/configuration/config.yaml";

    public static BaseConfiguration read() {
        var configStream = ConfigParser.class.getResourceAsStream(CONFIG_FILE_LOCATION);
        try {
            return ConfigMapper.get().readValue(configStream, BaseConfiguration.class);
        } catch (IOException e) {
            var message = "Error when try to parse configuration file.";
            LOGGER.error(message, e);
            throw new RuntimeException(message, e);
        }
    }
}
