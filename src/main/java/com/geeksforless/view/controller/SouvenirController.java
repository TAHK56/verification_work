package com.geeksforless.view.controller;

import com.geeksforless.exception.CompanyNotFoundException;
import com.geeksforless.persistence.entity.Company;
import com.geeksforless.persistence.entity.Souvenir;
import com.geeksforless.persistence.entity.value.Address;
import com.geeksforless.persistence.entity.value.AddressType;
import com.geeksforless.persistence.entity.value.CompanyDetails;
import com.geeksforless.persistence.entity.value.ContactDetails;
import com.geeksforless.persistence.entity.value.TaxDetails;
import com.geeksforless.service.CompanyService;
import com.geeksforless.service.SouvenirService;
import com.geeksforless.service.impl.CompanyServiceImpl;
import com.geeksforless.service.impl.SouvenirServiceImpl;
import com.geeksforless.util.UserInputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class SouvenirController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SouvenirController.class);
    private static final String ENTER_ID = "Please, enter id: ";
    private static final String NAME_CONTROLLER = "SouvenirController";
    private final SouvenirService souvenirService = new SouvenirServiceImpl();
    private final CompanyService companyService = new CompanyServiceImpl();

    public void create() {
        LOGGER.info("{}.create", NAME_CONTROLLER);
        LOGGER.info("Please, enter a souvenir name: ");
        String name = UserInputs.getUserInput();
        LOGGER.info("Please, enter the date of issue souvenir:(yyyy-MM-dd)");
        LocalDate date = createDateIssue();
        LOGGER.info("Please, enter a price of souvenir: ");
        BigDecimal price = createPrice();
        LOGGER.info("Please, enter details of company: ");
        CompanyDetails details = createCompanyDetails();

        Souvenir souvenir = new Souvenir();
        souvenir.setName(name);
        souvenir.setDateIssue(date);
        souvenir.setPrice(price);
        souvenir.setCompanyDetails(details);
        souvenirService.create(souvenir);
    }

    public void update() {
        LOGGER.info("{}.update", NAME_CONTROLLER);
        LOGGER.info(ENTER_ID);
        String id = UserInputs.getUserInput();
        LOGGER.info("Please, enter a souvenir name: ");
        String name = UserInputs.getUserInput();
        LOGGER.info("Please, enter the date of issue souvenir:(yyyy-MM-dd) ");
        LocalDate date = createDateIssue();
        LOGGER.info("Please, enter a price of souvenir: ");
        BigDecimal price = createPrice();
        LOGGER.info("Please, enter details of company: ");
        CompanyDetails details = createCompanyDetails();

        Souvenir souvenir = new Souvenir();
        souvenir.setId(id);
        souvenir.setName(name);
        souvenir.setDateIssue(date);
        souvenir.setPrice(price);
        souvenir.setCompanyDetails(details);
        souvenirService.update(souvenir);

    }

    public void delete() {
        LOGGER.info("{}.delete", NAME_CONTROLLER);
        LOGGER.info(ENTER_ID);
        String id = UserInputs.getUserInput();
        souvenirService.delete(id);
    }

    public void findById() {
        LOGGER.info("{}.findById", NAME_CONTROLLER);
        LOGGER.info(ENTER_ID);
        String id = UserInputs.getUserInput();
        souvenirService.findById(id).ifPresent(souvenir -> LOGGER.info(souvenir.toString()));
    }

    public void findAll() {
        LOGGER.info("{}.findAll", NAME_CONTROLLER);
        Collection<Souvenir> souvenirs = souvenirService.findAll();
        if (Objects.nonNull(souvenirs)) {
            souvenirs.forEach(souvenir -> LOGGER.info(souvenir.toString()));
        } else {
            LOGGER.info("Empty...");
        }
    }

    public void findAllByCompanyId() {
        LOGGER.info("{}.findAllByCompanyId", NAME_CONTROLLER);
        LOGGER.info(ENTER_ID);
        String id = UserInputs.getUserInput();
        Collection<Souvenir> souvenirs = souvenirService.findAllByCompanyId(id);
        souvenirs.forEach(souvenir -> LOGGER.info(souvenir.toString()));
    }

    public void findAllByCountry() {
        LOGGER.info("{}.findAllByCountry", NAME_CONTROLLER);
        LOGGER.info("Please, enter country of company: ");
        String country = UserInputs.getUserInput();
        Collection<Souvenir> souvenirs = souvenirService.findAllByCountry(country);
        souvenirs.forEach(souvenir -> LOGGER.info(souvenir.toString()));
    }

    public void findAllByYear() {
        LOGGER.info("{}.findAllByYear", NAME_CONTROLLER);
        LOGGER.info("Please, enter year: ");
        try {
            int year = Integer.parseInt(UserInputs.getUserInput());
            Collection<Souvenir> souvenirs = souvenirService.findAllByYear(year);
            souvenirs.forEach(souvenir -> LOGGER.info(souvenir.toString()));
        } catch (NumberFormatException e) {
            LOGGER.info("You have inputted incorrect year.Try again! {}", e.getMessage());
            findAllByYear();
        }
    }

    private CompanyDetails createCompanyDetails() {
        Company company = getCompany();
        CompanyDetails.Builder builder = new CompanyDetails.Builder(company);
        fillIban(builder);
        fillTaxDetails(builder);
        fillContactDetails(builder);
        fillAddressesDetails(builder);
        return builder.build();
    }

    private void fillIban(CompanyDetails.Builder builder) {
        LOGGER.info("Do you want to write IBAN(Y/N)");
        String answer = UserInputs.getUserInput();
        if (answer.equalsIgnoreCase("Y")) {
            LOGGER.info("Please, enter IBAN:");
            String bankDetails = UserInputs.getUserInput();
            builder.bankDetails(bankDetails);
        }
    }

    private void fillTaxDetails(CompanyDetails.Builder builder) {
        LOGGER.info("DO you want to create tax details(Y/N)");
        String answer = UserInputs.getUserInput();
        if (answer.equalsIgnoreCase("Y")) {
            TaxDetails taxDetails = createTaxDetails();
            builder.taxDetails(taxDetails);
        }
    }

    private void fillContactDetails(CompanyDetails.Builder builder) {
        LOGGER.info("DO you want to create contact details(Y/N)");
        String answer = UserInputs.getUserInput();
        if (answer.equalsIgnoreCase("Y")) {
            ContactDetails contact = createContactDetails();
            builder.contact(contact);
        }
    }

    private void fillAddressesDetails(CompanyDetails.Builder builder) {
        LOGGER.info("DO you want to create addresses details(Y/N)");
        String answer = UserInputs.getUserInput();
        if (answer.equalsIgnoreCase("Y")) {
            Map<AddressType, Address> addresses = fillAddresses();
            builder.addresses(addresses);
        }

    }

    private Company getCompany() {
        LOGGER.info("Please, choose company id:(You can only choose existence!!)");
        companyService.findAll().forEach(company -> LOGGER.info(company.toString()));
        String id = UserInputs.getUserInput();
        return companyService.findById(id).orElseThrow(() -> new CompanyNotFoundException("Not exist company!!!"));
    }


    private Map<AddressType, Address> fillAddresses() {
        Map<AddressType, Address> addresses = new EnumMap<>(AddressType.class);
        for (AddressType type : AddressType.values()) {
            LOGGER.info("Please, enter {} address", type.name());
            Address address = createAddress();
            addresses.put(type, address);
        }
        return addresses;
    }

    private Address createAddress() {
        LOGGER.info("Post index: ");
        String postIndex = UserInputs.getUserInput();
        LOGGER.info("Country: ");
        String country = UserInputs.getUserInput();
        LOGGER.info("City: ");
        String city = UserInputs.getUserInput();
        LOGGER.info("Street: ");
        String street = UserInputs.getUserInput();
        LOGGER.info("Number: ");
        String number = UserInputs.getUserInput();

        return new Address(postIndex, country, city, street, number);
    }

    private ContactDetails createContactDetails() {
        LOGGER.info("Please, enter phone number: ");
        String phoneNumber = UserInputs.getUserInput();
        LOGGER.info("Please, enter email: ");
        String email = UserInputs.getUserInput();
        return new ContactDetails(phoneNumber, email);
    }

    private TaxDetails createTaxDetails() {
        LOGGER.info("Please, enter the tax payer identification number: ");
        String taxPayer = UserInputs.getUserInput();
        LOGGER.info("Please, enter the tax register code: ");
        String taxRegister = UserInputs.getUserInput();
        LOGGER.info("Please, enter the tax primary registration number: ");
        String taxPrimary = UserInputs.getUserInput();
        return new TaxDetails(taxPayer, taxRegister, taxPrimary);
    }

    private BigDecimal createPrice() {
        try {
            return new BigDecimal(UserInputs.getUserInput());
        } catch (NumberFormatException ex) {
            LOGGER.debug("You have inputted incorrect number. {}", ex.getMessage());
            createPrice();
        }
        return BigDecimal.ONE;
    }

    private LocalDate createDateIssue() {
        try {
            LOGGER.info("Year: ");
            int year = Integer.parseInt(UserInputs.getUserInput());
            LOGGER.info("Month: ");
            int month = Integer.parseInt(UserInputs.getUserInput());
            LOGGER.info("Day: ");
            int day = Integer.parseInt(UserInputs.getUserInput());
            return LocalDate.of(year, month, day);
        } catch (DateTimeException | NumberFormatException e) {
            LOGGER.debug("You have inputted the incorrect date. {}", e.getMessage());
            createDateIssue();
        }
        return LocalDate.now();
    }
}
