package com.malabiga.kapwatekadmin.Model;

public class Feedback {
    String title;
    String message;
    String user_Picture;
    String first_Name;
    String last_Name;

    public Feedback() {
    }

    public Feedback(String first_Name, String last_Name) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
    }

    public Feedback(String title, String message, String user_Picture) {
        this.title = title;
        this.message = message;
        this.user_Picture = user_Picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_Picture() {
        return user_Picture;
    }

    public void setUser_Picture(String user_Picture) {
        this.user_Picture = user_Picture;
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
}
