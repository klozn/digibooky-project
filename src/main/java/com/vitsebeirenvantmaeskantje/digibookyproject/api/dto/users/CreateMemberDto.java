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

    public CreateMemberDto(String inss, String firstName, String lastName, String mail, String city, String street,
                           String streetNumber, int postalCode) {
        this.inss = inss;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }

    public String getInss() {
        return inss;
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

    public User.Role getRole() {
        return role;
    }

}
