package com.geeksforless.persistence.dao.impl;

import com.geeksforless.persistence.dao.CompanyDao;
import com.geeksforless.persistence.dao.SouvenirDao;
import com.geeksforless.persistence.entity.Company;
import com.geeksforless.util.GenerateIdUtil;
import com.geeksforless.util.JsonHelper;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyDaoImpl implements CompanyDao {
    private static final String NAME_DB = "company.json";
    private Collection<Company> companies = new HashSet<>();
    private static final Type type = new TypeToken<HashSet<Company>>(){}.getType();
    private final JsonHelper<Company> helper = new JsonHelper<>(NAME_DB, type);
    private final SouvenirDao souvenirDao = new SouvenirDaoImpl();


    @Override
    public void create(Company company) {
        companies = helper.readJson();
        company.setId(GenerateIdUtil.generateId(companies));
        companies.add(company);
        helper.writeJson(companies);
    }

    @Override
    public void update(Company company) {
        companies = helper.readJson();
        Company current = findById(company.getId()).orElseGet(Company::new);
        current.setName(company.getName());
        current.setCountry(company.getCountry());
        helper.writeJson(companies);
    }

    @Override
    public void delete(String id) {
        companies = helper.readJson();
        var current = findById(id);
        var idCompany = current.isPresent() ? current.get().getId() : "";
        souvenirDao.findAll().stream()
                .filter(souvenir -> souvenir.getCompanyDetails().getCompany().getId().equals(idCompany))
                .forEach(souvenir -> souvenirDao.delete(souvenir.getId()));
        companies.removeIf(company -> company.getId().equals(id));
        helper.writeJson(companies);
    }

    @Override
    public Optional<Company> findById(String id) {
        companies = helper.readJson();
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<Company> findAll() {
        companies = helper.readJson();
        return companies;
    }

    @Override
    public Collection<Company> findAllLowerByPrice(BigDecimal price) {
        companies = helper.readJson();
        var souvenirs = souvenirDao.findAll();
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getPrice().compareTo(price) < 0)
                .map(souvenir -> souvenir.getCompanyDetails().getCompany())
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Collection<Company> findAllBySouvenirId(String id) {
        companies = helper.readJson();
        var souvenirs = souvenirDao.findAll();
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getId().equals(id))
                .map(souvenir -> souvenir.getCompanyDetails().getCompany())
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Collection<Company> findAllByYearOfIssue(int year) {
        companies = helper.readJson();
        var souvenirs = souvenirDao.findAll();
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getDateIssue().getYear() == year)
                .map(souvenir -> souvenir.getCompanyDetails().getCompany())
                .collect(Collectors.toCollection(HashSet::new));
    }
}
