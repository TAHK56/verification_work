package com.geeksforless.service;

import com.geeksforless.persistence.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface BaseService<E extends BaseEntity, T> {

    void create(E entity);

    void update(E entity);

    void delete(T id);

    Optional<E> findById(T id);

    Collection<E> findAll();
}
