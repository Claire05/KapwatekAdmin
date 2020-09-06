package com.malabiga.kapwatekadmin.Model;

public class PostNewInformation {

    private String address;
    private String Announcement_Picture;
    private String Author;
    private String Campaign_Title;
    private String campaign_Type;
    private String Date_Posted;
    private String dateof_Event;
    private String end_of_Event;
    private String event_Tag;
    private long geofenceLayer;
    private String start_of_Event;
    private String Story_Description;
    private String time;
    private long Time_Posted;
    private String typeStatus;
    private String uid;
    private double Latitude;
    private double Longitude;
    private String First_Name, Last_Name;
    private String user;
    private String amount;
    private String preferred_Gender;
    private String age_Requirement;

    public PostNewInformation() {

    }

    public PostNewInformation(String first_Name, String last_Name, String user, String amount) {
        First_Name = first_Name;
        Last_Name = last_Name;
        this.user = user;
        this.amount = amount;
    }

    public PostNewInformation(String address, String announcement_Picture, String author, String campaign_Title,
                              String campaign_Type, String date_Posted, String dateof_Event,
                              String end_of_Event, String event_Tag, long geofenceLayer,
                              String start_of_Event, String story_Description, String time, long time_Posted,
                              String typeStatus, String uid, double latitude, double longitude, String preferred_Gender, String age_Requirement) {
        this.address = address;
        Announcement_Picture = announcement_Picture;
        Author = author;
        Campaign_Title = campaign_Title;
        this.campaign_Type = campaign_Type;
        Date_Posted = date_Posted;
        this.dateof_Event = dateof_Event;
        this.end_of_Event = end_of_Event;
        this.event_Tag = event_Tag;
        this.geofenceLayer = geofenceLayer;
        this.start_of_Event = start_of_Event;
        Story_Description = story_Description;
        this.time = time;
        Time_Posted = time_Posted;
        this.typeStatus = typeStatus;
        this.uid = uid;
        Latitude = latitude;
        Longitude = longitude;
        this.preferred_Gender = preferred_Gender;
        this.age_Requirement = age_Requirement;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAnnouncement_Picture() {
        return Announcement_Picture;
    }

    public void setAnnouncement_Picture(String announcement_Picture) {
        Announcement_Picture = announcement_Picture;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCampaign_Title() {
        return Campaign_Title;
    }

    public void setCampaign_Title(String campaign_Title) {
        Campaign_Title = campaign_Title;
    }

    public String getCampaign_Type() {
        return campaign_Type;
    }

    public void setCampaign_Type(String campaign_Type) {
        this.campaign_Type = campaign_Type;
    }

    public String getDate_Posted() {
        return Date_Posted;
    }

    public void setDate_Posted(String date_Posted) {
        Date_Posted = date_Posted;
    }

    public String getDateof_Event() {
        return dateof_Event;
    }

    public void setDateof_Event(String dateof_Event) {
        this.dateof_Event = dateof_Event;
    }

    public String getEnd_of_Event() {
        return end_of_Event;
    }

    public void setEnd_of_Event(String end_of_Event) {
        this.end_of_Event = end_of_Event;
    }

    public String getEvent_Tag() {
        return event_Tag;
    }

    public void setEvent_Tag(String event_Tag) {
        this.event_Tag = event_Tag;
    }


    public long getGeofenceLayer() {
        return geofenceLayer;
    }

    public void setGeofenceLayer(long geofenceLayer) {
        this.geofenceLayer = geofenceLayer;
    }

    public String getStart_of_Event() {
        return start_of_Event;
    }

    public void setStart_of_Event(String start_of_Event) {
        this.start_of_Event = start_of_Event;
    }

    public String getStory_Description() {
        return Story_Description;
    }

    public void setStory_Description(String story_Description) {
        Story_Description = story_Description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getTime_Posted() {
        return Time_Posted;
    }

    public void setTime_Posted(long time_Posted) {
        Time_Posted = time_Posted;
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPreferred_Gender() {
        return preferred_Gender;
    }

    public void setPreferred_Gender(String preferred_Gender) {
        this.preferred_Gender = preferred_Gender;
    }

    public String getAge_Requirement() {
        return age_Requirement;
    }

    public void setAge_Requirement(String age_Requirement) {
        this.age_Requirement = age_Requirement;
    }
}