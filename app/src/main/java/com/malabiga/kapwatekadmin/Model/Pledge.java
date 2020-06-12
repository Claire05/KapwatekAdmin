package com.malabiga.kapwatekadmin.Model;

public class Pledge {

    String Campaign_Title;
    String User;
    String campaign_id;
    long time_Created;
    String total_Volunteers;
    String user_Profile;
    String registered_As;

    public Pledge() {
    }

    public Pledge(String campaign_Title, String user, String campaign_id, long time_Created, String total_Volunteers, String user_Profile, String registered_As) {
        Campaign_Title = campaign_Title;
        User = user;
        this.campaign_id = campaign_id;
        this.time_Created = time_Created;
        this.total_Volunteers = total_Volunteers;
        this.user_Profile = user_Profile;
        this.registered_As = registered_As;
    }

    public String getCampaign_Title() {
        return Campaign_Title;
    }

    public void setCampaign_Title(String campaign_Title) {
        Campaign_Title = campaign_Title;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public long getTime_Created() {
        return time_Created;
    }

    public void setTime_Created(long time_Created) {
        this.time_Created = time_Created;
    }

    public String getTotal_Volunteers() {
        return total_Volunteers;
    }

    public void setTotal_Volunteers(String total_Volunteers) {
        this.total_Volunteers = total_Volunteers;
    }

    public String getUser_Profile() {
        return user_Profile;
    }

    public void setUser_Profile(String user_Profile) {
        this.user_Profile = user_Profile;
    }

    public String getRegistered_As() {
        return registered_As;
    }

    public void setRegistered_As(String registered_As) {
        this.registered_As = registered_As;
    }
}
