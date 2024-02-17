package com.geeksforless.service;

import com.geeksforless.persistence.entity.Souvenir;

import java.util.Collection;

public interface SouvenirService extends BaseService<Souvenir, String> {

    Collection<Souvenir> findAllByCompanyId(String id);

    Collection<Souvenir> findAllByCountry(String country);

    Collection<Souvenir> findAllByYear(int year);
}
