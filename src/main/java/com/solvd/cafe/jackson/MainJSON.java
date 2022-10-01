package com.solvd.cafe.jackson;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class MainJSON {
    private static final Logger logger = LogManager.getLogger(MainJSON.class);

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Cafe cafe = new Cafe("NATO", "Kyiv", "Ukrainians", 31, 11);
        File file = new File("src/main/java/com/solvd/cafe/jackson/cafe.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, cafe);

        file = new File("src/main/java/com/solvd/cafe/jackson/cafe.json");
        Cafe readCafe = objectMapper.readValue(file, Cafe.class);
        logger.info(readCafe);

    }
}

