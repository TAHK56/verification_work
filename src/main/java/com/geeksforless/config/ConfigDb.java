package com.geeksforless.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigDb {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDb.class);

    private ConfigDb() {
        throw new AssertionError("You cannot create this class...");
    }

    private static final Path pathRoot = FileSystems.getDefault().getPath("db");

    public static Path getPathDb(String db) {
        Path path = Path.of(pathRoot.toString(), File.separator, db);
        if (!Files.exists(path)) {
            createIfNotExist(path);
        }
        return path;
    }

    private static void createIfNotExist(Path path) {
        try {
            if (!Files.exists(pathRoot)) {
                Files.createDirectory(pathRoot);
            }

            Files.createFile(path);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
