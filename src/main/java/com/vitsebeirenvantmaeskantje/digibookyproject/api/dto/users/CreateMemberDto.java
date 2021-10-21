package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;

public class CreateMemberDto {
    private String inss;
    private String firstName;
    private String lastName;
    private String mail;
    private String city;
    private String street;
    private String streetNumber;
    private int postalCode;
    private final User.Role role = User.Role.MEMBER;

    public String getInss() {
        return inss;
    }

    public CreateMemberDto setInss(String inss) {
        this.inss = inss;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CreateMemberDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateMemberDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public CreateMemberDto setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CreateMemberDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public CreateMemberDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public CreateMemberDto setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public CreateMemberDto setPostalCode(int postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public User.Role getRole() {
        return role;
    }

}
