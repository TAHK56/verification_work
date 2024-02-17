package com.geeksforless.persistence.dao;

import com.geeksforless.persistence.entity.Souvenir;

import java.util.Collection;

public interface SouvenirDao extends BaseDao<Souvenir, String> {

    Collection<Souvenir> findAllByCompanyId(String id);

    Collection<Souvenir> findAllByCountry(String country);

    Collection<Souvenir> findAllByYear(int year);

}
