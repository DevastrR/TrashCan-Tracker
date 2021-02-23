package com.example.tracker.Database;

public class UserHelperClass {
    String Registration , Fullname , Email , Password , PhoneNumber , Gender ;

    public UserHelperClass(){}

    public UserHelperClass(String registration, String fullname, String email, String password, String phoneNumber, String gender) {
        Registration = registration;
        Fullname = fullname;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
        Gender = gender;
    }


    public String getRegistration() {
        return Registration;
    }

    public void setRegistration(String registration) {
        Registration = registration;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }
}
