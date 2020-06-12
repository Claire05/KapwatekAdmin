package com.malabiga.kapwatekadmin.Model;

public class VolunteerInformation {

    private String email;
    private String typeStatus;
    private String registered_As;
    private String first_Name, last_Name, middle_Name;
    private String address;
    private String contact;
    private String city;
    private String user_Picture;
    private String password;
    public String profession;
    public String birthday;
    public String gender;

    public String validone;
    public String validtwo;
    public String validID_1;
    public String validID_2;

    public VolunteerInformation(String validone, String validtwo, String validID_1, String validID_2) {
        this.validone = validone;
        this.validtwo = validtwo;
        this.validID_1 = validID_1;
        this.validID_2 = validID_2;
    }

    public VolunteerInformation(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getValidone() {
        return validone;
    }

    public void setValidone(String validone) {
        this.validone = validone;
    }

    public String getValidtwo() {
        return validtwo;
    }

    public void setValidtwo(String validtwo) {
        this.validtwo = validtwo;
    }

    public VolunteerInformation() {

    }

    public VolunteerInformation(String Email, String typeStatus, String registered_As, String First_Name, String Last_Name, String Middle_Name, String Address, String Contact, String City, String volIdImage, String password, String birthday, String gender) {
        this.email = Email;
        this.typeStatus = typeStatus;
        this.registered_As = registered_As;
        this.first_Name = First_Name;
        this.last_Name = Last_Name;
        this.address = Address;
        this.contact = Contact;
        this.city = City;
        this.user_Picture = volIdImage;
        this.middle_Name = Middle_Name;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getRegistered_As() {
        return registered_As;
    }

    public void setRegistered_As(String registered_As) {
        this.registered_As = registered_As;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getMiddle_Name() {
        return middle_Name;
    }

    public void setMiddle_Name(String middle_Name) {
        this.middle_Name = middle_Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUser_Picture() {
        return user_Picture;
    }

    public void setUser_Picture(String user_Picture) {
        this.user_Picture = user_Picture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidID_2() {
        return validID_2;
    }

    public void setValidID_2(String validID_2) {
        this.validID_2 = validID_2;
    }

    public String getValidID_1() {
        return validID_1;
    }

    public void setValidID_1(String validID_1) {
        this.validID_1 = validID_1;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
