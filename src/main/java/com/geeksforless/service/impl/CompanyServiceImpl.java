package com.geeksforless.service.impl;

import com.geeksforless.persistence.dao.CompanyDao;
import com.geeksforless.persistence.dao.impl.CompanyDaoImpl;
import com.geeksforless.persistence.entity.Company;
import com.geeksforless.service.CompanyService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    public void create(Company company) {
        companyDao.create(company);
    }

    @Override
    public void update(Company company) {
        companyDao.update(company);
    }

    @Override
    public void delete(String id) {
        companyDao.delete(id);
    }

    @Override
    public Optional<Company> findById(String id) {
        return companyDao.findById(id);
    }

    @Override
    public Collection<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public Collection<Company> findAllLowerByPrice(BigDecimal price) {
        return companyDao.findAllLowerByPrice(price);
    }

    @Override
    public Collection<Company> findAllBySouvenirId(String id) {
        return companyDao.findAllBySouvenirId(id);
    }

    @Override
    public Collection<Company> findAllByYearOfIssue(int year) {
        return companyDao.findAllByYearOfIssue(year);
    }
}
