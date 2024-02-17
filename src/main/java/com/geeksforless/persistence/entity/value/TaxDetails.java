package com.geeksforless.persistence.entity.value;

public record TaxDetails(String taxPayerIdentificationNumber, String taxRegistrationReasonCode,
                         String primaryStateRegistrationNumber) {
}
