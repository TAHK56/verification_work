package com.geeksforless.persistence.entity;

public class Company extends BaseEntity {

    private String name;
    private String country;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return "Company{" +
               "id='" + getId() + '\'' +
               ", name='" + name + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}
