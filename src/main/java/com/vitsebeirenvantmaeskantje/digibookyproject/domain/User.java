package com.vitsebeirenvantmaeskantje.digibookyproject.domain;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final String id;
    private final String inss;
    private String firstName;
    private final String lastName;
    private final String mail;
    private String city;
    private String street;
    private String streetNumber;
    private int postalCode;
    private Role role;

    public User(String inss, String lastName, String mail, String city) {
        this.id = UUID.randomUUID().toString();
        this.inss = inss;
        this.lastName = lastName;
        this.mail = assertValidMail(mail);
        this.city = city;
        role = Role.MEMBER;
    }

    private String assertValidMail(String mail) {
        if (!EmailValidator.getInstance().isValid(mail)) {
            throw new IllegalArgumentException("Invalid email for user");
        }
        return mail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getInss() {
        return inss;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(inss, user.inss) || Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inss, mail);
    }

    public enum Role {
        MEMBER, LIBRARIAN, ADMIN
    }
}
