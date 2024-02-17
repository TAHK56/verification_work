package com.geeksforless.persistence.entity;

import com.geeksforless.persistence.entity.value.CompanyDetails;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Souvenir extends BaseEntity {

    private String name;
    private CompanyDetails companyDetails;
    private LocalDate dateIssue;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    public LocalDate getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(LocalDate dateIssue) {
        this.dateIssue = dateIssue;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Souvenir{" +
               "id='" + getId() + '\'' +
               ", name='" + name + '\'' +
               ", companyDetails=" + companyDetails +
               ", dateIssue=" + dateIssue +
               ", price=" + price +
               '}';
    }
}
