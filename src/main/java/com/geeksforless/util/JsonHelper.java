package com.geeksforless.util;

import com.geeksforless.config.ConfigDb;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public final class JsonHelper<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonHelper.class);
    private final String nameDb;
    private final Type type;

    public JsonHelper(String nameDb, Type type) {
        this.nameDb = nameDb;
        this.type = type;
    }

    public Collection<T> readJson() {
        Gson gson = create();
        try (Reader reader = Files.newBufferedReader(ConfigDb.getPathDb(nameDb))) {
            Collection<T> collections  =  gson.fromJson(reader, type);
            if (Objects.nonNull(collections)) {
                return new HashSet<>(collections);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return new HashSet<>();
    }

    public void writeJson(Collection<T> entities) {
        Gson gson = create();
        try (Writer writer = Files.newBufferedWriter(ConfigDb.getPathDb(nameDb))) {
            writer.write(gson.toJson(entities));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_DATE));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_DATE);
        }
    }

    private Gson create() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
    }
}
