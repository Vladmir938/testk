package com.example.mykpp;

public class UserModel {
    private String Name, Surname, Patronymic, Email, LicenseIMG, id;

    private UserModel() {

    }

    public UserModel(String name, String surname, String patronymic, String email,
                      String licenseIMG, String id) {
        this.Name = name;
        this.Surname = surname;
        this.Patronymic = patronymic;
        this.Email = email;
        this.LicenseIMG = licenseIMG;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public void setPatronymic(String patronymic) {
        Patronymic = patronymic;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicenseIMG() {
        return LicenseIMG;
    }

    public void setLicenseIMG(String licenseIMG) {
        LicenseIMG = licenseIMG;
    }
}

