package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;

public class UserDto {
    private final String id;
    private final String inss;
    private final String firstName;
    private final String lastName;
    private final String mail;
    private final String city;
    private final String street;
    private final String streetNumber;
    private final int postalCode;
    private final User.Role role;

    public UserDto(String id, String inss, String firstName, String lastName, String mail, String city, String street,
                   String streetNumber, int postalCode, User.Role role) {
        this.id = id;
        this.inss = inss;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.role = role;
    }

    public User.Role getRole() {
        return role;
    }

    public UserDto setRole(User.Role role) {
        this.role = role;
        return this;
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

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }


}
