package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users;

// CODEREVIEW The CreateAdminDTO and CreateLibrarianDTO are the same (with the exception of their name)
public class CreateAdminDto {
    private final String adminId;
    private final String inss;
    private final String firstName;
    private final String lastName;
    private final String mail;
    private final String city;
    private final String street;
    private final String streetNumber;
    private final int postalCode;

    public CreateAdminDto(String adminId, String inss, String firstName, String lastName, String mail, String city, String street, String streetNumber, int postalCode) {
        this.adminId = adminId;
        this.inss = inss;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }

    public String getAdminId() {
        return adminId;
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

}
