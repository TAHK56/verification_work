package com.geeksforless.persistence.dao;

import com.geeksforless.persistence.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface BaseDao <E extends BaseEntity, T> {

    void create(E entity);

    void update(E entity);

    void delete(T id);

    Optional<E> findById(T id);

    Collection<E> findAll();
}
