package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;

public class UserDto {
    private String id;
    private String inss;
    private String firstName;
    private String lastName;
    private String mail;
    private String city;
    private String street;
    private String streetNumber;
    private int postalCode;
    private User.Role role;


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

    public UserDto setInss(String inss) {
        this.inss = inss;
        return this;
    }

    public String getId() {
        return id;
    }

    public UserDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public UserDto setMail(String mail){
        this.mail = mail;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public UserDto setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public UserDto setPostalCode(int postalCode) {
        this.postalCode = postalCode;
        return this;
    }
}
