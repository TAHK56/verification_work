package com.geeksforless.service.impl;

import com.geeksforless.persistence.dao.SouvenirDao;
import com.geeksforless.persistence.dao.impl.SouvenirDaoImpl;
import com.geeksforless.persistence.entity.Souvenir;
import com.geeksforless.service.SouvenirService;

import java.util.Collection;
import java.util.Optional;

public class SouvenirServiceImpl implements SouvenirService {

    private final SouvenirDao souvenirDao = new SouvenirDaoImpl();

    @Override
    public void create(Souvenir souvenir) {
        souvenirDao.create(souvenir);
    }

    @Override
    public void update(Souvenir souvenir) {
        souvenirDao.update(souvenir);
    }

    @Override
    public void delete(String id) {
        souvenirDao.delete(id);
    }

    @Override
    public Optional<Souvenir> findById(String id) {
        return souvenirDao.findById(id);
    }

    @Override
    public Collection<Souvenir> findAll() {
        return souvenirDao.findAll();
    }

    @Override
    public Collection<Souvenir> findAllByCompanyId(String id) {
        return souvenirDao.findAllByCompanyId(id);
    }

    @Override
    public Collection<Souvenir> findAllByCountry(String country) {
        return souvenirDao.findAllByCountry(country);
    }

    @Override
    public Collection<Souvenir> findAllByYear(int year) {
        return souvenirDao.findAllByYear(year);
    }
}
