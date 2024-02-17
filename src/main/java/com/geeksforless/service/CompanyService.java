package com.geeksforless.service;

import com.geeksforless.persistence.entity.Company;

import java.math.BigDecimal;
import java.util.Collection;

public interface CompanyService extends BaseService<Company, String> {

    Collection<Company> findAllLowerByPrice(BigDecimal price);

    Collection<Company> findAllBySouvenirId(String id);

    Collection<Company> findAllByYearOfIssue(int year);
}
