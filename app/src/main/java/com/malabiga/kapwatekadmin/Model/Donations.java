package com.malabiga.kapwatekadmin.Model;

public class Donations {

        private String Amount;
        private long Time_Donated;
        private String User;
        public String user_Profile;

    public Donations(String amount, long time_Donated, String user, String user_Profile) {
        Amount = amount;
        Time_Donated = time_Donated;
        User = user;
        this.user_Profile = user_Profile;
    }

    public Donations() {

    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public long getTime_Donated() {
        return Time_Donated;
    }

    public void setTime_Donated(long time_Donated) {
        Time_Donated = time_Donated;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getUser_Profile() {
        return user_Profile;
    }

    public void setUser_Profile(String user_Profile) {
        this.user_Profile = user_Profile;
    }

    public void getUser_Profile(String image1) {
    }
}