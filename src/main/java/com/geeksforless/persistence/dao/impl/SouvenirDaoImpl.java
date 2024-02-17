package com.geeksforless.persistence.dao.impl;

import com.geeksforless.persistence.dao.SouvenirDao;
import com.geeksforless.persistence.entity.Souvenir;
import com.geeksforless.util.GenerateIdUtil;
import com.geeksforless.util.JsonHelper;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

public class SouvenirDaoImpl implements SouvenirDao {

    private static final String NAME_DB = "souvenir.json";
    private Collection<Souvenir> souvenirs = new HashSet<>();
    private static final Type type = new TypeToken<HashSet<Souvenir>>() {
    }.getType();
    private final JsonHelper<Souvenir> helper = new JsonHelper<>(NAME_DB, type);

    @Override
    public void create(Souvenir souvenir) {
        souvenirs = helper.readJson();
        souvenir.setId(GenerateIdUtil.generateId(souvenirs));
        souvenirs.add(souvenir);
        helper.writeJson(souvenirs);
    }

    @Override
    public void update(Souvenir souvenir) {
        souvenirs = helper.readJson();
        Souvenir current = findById(souvenir.getId()).orElseGet(Souvenir::new);
        current.setName(souvenir.getName());
        current.setPrice(souvenir.getPrice());
        current.setDateIssue(souvenir.getDateIssue());
        current.setCompanyDetails(souvenir.getCompanyDetails());
        helper.writeJson(souvenirs);
    }

    @Override
    public void delete(String id) {
        souvenirs = helper.readJson();
        souvenirs.removeIf(souvenir -> souvenir.getId().equals(id));
        helper.writeJson(souvenirs);
    }

    @Override
    public Optional<Souvenir> findById(String id) {
        souvenirs = helper.readJson();
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<Souvenir> findAll() {
        souvenirs = helper.readJson();
        return souvenirs;
    }

    @Override
    public Collection<Souvenir> findAllByCompanyId(String id) {
        souvenirs = helper.readJson();
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getCompanyDetails().getCompany().getId().equals(id))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Collection<Souvenir> findAllByCountry(String country) {
        souvenirs = helper.readJson();
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getCompanyDetails().getCompany().getCountry().equals(country))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Collection<Souvenir> findAllByYear(int year) {
        souvenirs = helper.readJson();
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getDateIssue().getYear() == year)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
