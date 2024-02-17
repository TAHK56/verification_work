package com.geeksforless.persistence.entity.value;

import com.geeksforless.persistence.entity.Company;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("all")
public class CompanyDetails {

    private Map<AddressType, Address> addresses;
    private String bankDetails;
    private TaxDetails taxDetails;
    private ContactDetails contact;
    private final Company company;

    public static class Builder {
        private String bankDetails;
        private TaxDetails taxDetails;
        private ContactDetails contact;
        private final Company company;

        private Map<AddressType, Address> addresses = new EnumMap<>(AddressType.class);

        public Builder(Company company) {
            this.company = company;
        }

        public Builder bankDetails(String iban) {
            this.bankDetails = iban;
            return this;
        }

        public Builder taxDetails(TaxDetails taxDetails) {
            this.taxDetails = taxDetails;
            return this;
        }

        public Builder contact(ContactDetails contact) {
            this.contact = contact;
            return this;
        }

        public Builder addresses(Map<AddressType, Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public CompanyDetails build() {
            return new CompanyDetails(this);
        }
    }

    private CompanyDetails(Builder builder) {
        bankDetails = builder.bankDetails;
        taxDetails = builder.taxDetails;
        contact = builder.contact;
        addresses = builder.addresses;
        company = builder.company;
    }

    public Map<AddressType, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<AddressType, Address> addresses) {
        this.addresses = addresses;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public TaxDetails getTaxDetails() {
        return taxDetails;
    }

    public void setTaxDetails(TaxDetails taxDetails) {
        this.taxDetails = taxDetails;
    }

    public ContactDetails getContact() {
        return contact;
    }

    public void setContact(ContactDetails contact) {
        this.contact = contact;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (Objects.isNull(object) || getClass() != object.getClass()) {
            return false;
        }
        CompanyDetails details = (CompanyDetails) object;
        return Objects.equals(addresses, details.addresses) && Objects.equals(bankDetails, details.bankDetails)
               && Objects.equals(taxDetails, details.taxDetails) && Objects.equals(contact, details.contact)
               && Objects.equals(company, details.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addresses, bankDetails, taxDetails, contact, company);
    }

    @Override
    public String toString() {
        return "CompanyDetails{" +
               "addresses=" + addresses +
               ", bankDetails='" + bankDetails + '\'' +
               ", taxDetails=" + taxDetails +
               ", contact=" + contact +
               ", company=" + company +
               '}';
    }
}
