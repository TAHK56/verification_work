package com.geeksforless.util;

import com.geeksforless.persistence.entity.BaseEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public final class GenerateIdUtil {

    private GenerateIdUtil() {
        throw new AssertionError("You cannot create this class!");
    }

    public static String generateId(Collection<? extends BaseEntity> entities) {
        entities = Objects.requireNonNullElseGet(entities, HashSet::new);
        String id = UUID.randomUUID().toString();
        if (entities.stream().anyMatch(entity -> entity.getId().equals(id))) {
            return generateId(entities);
        }
        return id;
    }
}
