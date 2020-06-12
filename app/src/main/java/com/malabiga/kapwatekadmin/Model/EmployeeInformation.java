package com.malabiga.kapwatekadmin.Model;

public class EmployeeInformation {

    public String email;
    public String password;
    public String typeStatus;
    public String name;
    public String contact_Person;
    public String contact_Person_Number;
    public String validID_1;
    public String address;
    public String registered_As;
    public String user_Picture;
    public String validID_2;
    public String valone;
    public String valtwo;

    public EmployeeInformation() {
    }

    public EmployeeInformation(String valone, String valtwo) {
        this.valone = valone;
        this.valtwo = valtwo;
    }

    public String getValone() {
        return valone;
    }

    public void setValone(String valone) {
        this.valone = valone;
    }

    public String getValtwo() {
        return valtwo;
    }

    public void setValtwo(String valtwo) {
        this.valtwo = valtwo;
    }

    public EmployeeInformation(String user_Picture) {
        this.user_Picture = user_Picture;
    }

    public EmployeeInformation(String email, String password, String typeStatus, String name, String contact_Person, String contact_Person_Number, String validID_1, String address, String registered_As) {
        this.email = email;
        this.password = password;
        this.typeStatus = typeStatus;
        this.name = name;
        this.contact_Person = contact_Person;
        this.contact_Person_Number = contact_Person_Number;
        this.validID_1 = validID_1;
        this.address = address;
        this.registered_As = registered_As;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_Person() {
        return contact_Person;
    }

    public void setContact_Person(String contact_Person) {
        this.contact_Person = contact_Person;
    }

    public String getContact_Person_Number() {
        return contact_Person_Number;
    }

    public void setContact_Person_Number(String contact_Person_Number) {
        this.contact_Person_Number = contact_Person_Number;
    }

    public String getValidID_1() {
        return validID_1;
    }

    public void setValidID_1(String validID_1) {
        this.validID_1 = validID_1;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistered_As() {
        return registered_As;
    }

    public void setRegistered_As(String registered_As) {
        this.registered_As = registered_As;
    }

    public String getUser_Picture() {
        return user_Picture;
    }

    public void setUser_Picture(String user_Picture) {
        this.user_Picture = user_Picture;
    }

    public String getValidID_2() {
        return validID_2;
    }

    public void setValidID_2(String validID_2) {
        this.validID_2 = validID_2;
    }
}