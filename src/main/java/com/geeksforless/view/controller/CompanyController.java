package com.geeksforless.view.controller;

import com.geeksforless.persistence.entity.Company;
import com.geeksforless.service.CompanyService;
import com.geeksforless.service.impl.CompanyServiceImpl;
import com.geeksforless.util.UserInputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
    private static final String ENTER_ID = "Please, enter id: ";
    private static final String NAME_CONTROLLER = "CompanyController";
    private final CompanyService companyService = new CompanyServiceImpl();


    public void create() {
        LOGGER.info("{}.create", NAME_CONTROLLER);
        LOGGER.info("Please, enter a company name: ");
        String name = UserInputs.getUserInput();
        LOGGER.info("Please, enter a company country: ");
        String country = UserInputs.getUserInput();
        Company company = new Company();
        company.setName(name);
        company.setCountry(country);
        companyService.create(company);
    }

    public void update() {
        LOGGER.info("{}.update", NAME_CONTROLLER);
        LOGGER.info(ENTER_ID);
        String id = UserInputs.getUserInput();
        LOGGER.info("Please, enter a company name: ");
        String name = UserInputs.getUserInput();
        LOGGER.info("Please, enter a company country: ");
        String country = UserInputs.getUserInput();
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setCountry(country);
        companyService.update(company);
    }

    public void delete() {
        LOGGER.info("{}.delete", NAME_CONTROLLER);
        LOGGER.info(ENTER_ID);
        String id = UserInputs.getUserInput();
        companyService.delete(id);
    }

    public void findById() {
        LOGGER.info("{}.findById", NAME_CONTROLLER);
        LOGGER.info(ENTER_ID);
        String id = UserInputs.getUserInput();
        companyService.findById(id).ifPresent(company -> LOGGER.info(company.toString()));
    }

    public void findAll() {
        LOGGER.info("{}.findAll", NAME_CONTROLLER);
        Collection<Company> companies = companyService.findAll();
        if (Objects.nonNull(companies)) {
            companies.forEach(company -> LOGGER.info(company.toString()));
        } else {
            LOGGER.info("Empty...");
        }
    }

    public void findAllLowerByPrice() {
        LOGGER.info("{}.findAllLowerByPrice", NAME_CONTROLLER);
        LOGGER.info("Please, enter price: ");
        try {
            BigDecimal price = new BigDecimal(UserInputs.getUserInput());
            Collection<Company> companies = companyService.findAllLowerByPrice(price);
            companies.forEach(company -> LOGGER.info(company.toString()));
        } catch (NumberFormatException e) {
            LOGGER.info("You have inputted incorrect price.Try again! {}", e.getMessage());
            findAllLowerByPrice();
        }
    }

    public void findAllBySouvenirId() {
        LOGGER.info("{}.findAllBySouvenirId", NAME_CONTROLLER);
        LOGGER.info(ENTER_ID);
        String id = UserInputs.getUserInput();
        Collection<Company> companies = companyService.findAllBySouvenirId(id);
        companies.forEach(company -> LOGGER.info(company.toString()));
    }

    public void findAllByYearOfIssue() {
        LOGGER.info("{}.findAllByYearOfIssue", NAME_CONTROLLER);
        LOGGER.info("Please, enter year: ");
        try {
            int year = Integer.parseInt(UserInputs.getUserInput());
            Collection<Company> companies = companyService.findAllByYearOfIssue(year);
            companies.forEach(company -> LOGGER.info(company.toString()));
        } catch (NumberFormatException e) {
            LOGGER.info("You have inputted incorrect year.Try again! {}", e.getMessage());
            findAllByYearOfIssue();
        }
    }
}
