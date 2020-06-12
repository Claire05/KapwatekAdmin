package com.malabiga.kapwatekadmin.Model;

public class PostNewInformation {

    private String address;
    private String Announcement_Picture;
    private String Author;
    private String Campaign_Title;
    private String campaign_Type;
    private String Date_Posted;
    private String dateof_Event;
    private String donations_Goal;
    private String volunteers_Goal;
    private String endof_Event;
    private String event_Tag;
    private long geofenceLayer;
    private String start_of_Event;
    private String Story_Description;
    private String time;
    private long Time_Posted;
    private Long Total_DONATIONS;
    private Long Total_VOLUNTEERS;
    private String typeStatus;
    private String uid;

    private double Latitude;
    private double Longitude;
    private String category;

    private String First_Name, Last_Name;

    public String jobSkill0;
    public String jobSkill1;
    public String jobSkill2;
    public String jobSkill3;
    public String jobSkill4;
    public String jobSkill5;
    public String jobSkill6;
    public String jobSkill7;
    public String jobSkill8;
    public String jobSkill9;
    public String jobSkill10;
    public String jobSkill11;
    public String jobSkill12;
    public String jobSkill13;
    private String user;
    private String amount;

    public PostNewInformation() {

    }

    public PostNewInformation(String first_Name, String last_Name, String user, String amount) {
        First_Name = first_Name;
        Last_Name = last_Name;
        this.user = user;
        this.amount = amount;
    }

    public PostNewInformation(String address, String announcement_Picture, String author, String campaign_Title,
                              String campaign_Type, String date_Posted, String dateof_Event, String donations_Goal, String volunteers_Goal,
                              String end_of_Event, String event_Tag, long geofenceLayer,
                              String start_of_Event, String story_Description, String time, long time_Posted,
                              Long total_DONATIONS, Long total_VOLUNTEERS, String typeStatus, String uid, String jobSkill0, String jobSkill1,
                              String jobSkill2, String jobSkill3, String jobSkill4, String jobSkill5, String jobSkill6,
                              String jobSkill7, String jobSkill8, String jobSkill9, String jobSkill10, String jobSkill11,
                              String jobSkill12, String jobSkill13, double latitude, double longitude, String category) {
        this.address = address;
        Announcement_Picture = announcement_Picture;
        Author = author;
        Campaign_Title = campaign_Title;
        this.campaign_Type = campaign_Type;
        Date_Posted = date_Posted;
        this.dateof_Event = dateof_Event;
        this.donations_Goal = donations_Goal;
        this.volunteers_Goal = volunteers_Goal;
        this.endof_Event = end_of_Event;
        this.event_Tag = event_Tag;
        this.geofenceLayer = geofenceLayer;
//        this.geofence_Name = geofence_Name;
        this.start_of_Event = start_of_Event;
        Story_Description = story_Description;
        this.time = time;
        Time_Posted = time_Posted;
        Total_DONATIONS = total_DONATIONS;
        Total_VOLUNTEERS = total_VOLUNTEERS;
        this.typeStatus = typeStatus;
        this.uid = uid;
        this.jobSkill0 = jobSkill0;
        this.jobSkill1 = jobSkill1;
        this.jobSkill2 = jobSkill2;
        this.jobSkill3 = jobSkill3;
        this.jobSkill4 = jobSkill4;
        this.jobSkill5 = jobSkill5;
        this.jobSkill6 = jobSkill6;
        this.jobSkill7 = jobSkill7;
        this.jobSkill8 = jobSkill8;
        this.jobSkill9 = jobSkill9;
        this.jobSkill10 = jobSkill10;
        this.jobSkill11 = jobSkill11;
        this.jobSkill12 = jobSkill12;
        this.jobSkill13 = jobSkill13;
        Latitude = latitude;
        Longitude = longitude;
        this.category = category;
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

    public String getDonations_Goal() {
        return donations_Goal;
    }

    public void setDonations_Goal(String donations_Goal) {
        this.donations_Goal = donations_Goal;
    }

    public String getVolunteers_Goal() {
        return volunteers_Goal;
    }

    public void setVolunteers_Goal(String volunteers_Goal) {
        this.volunteers_Goal = volunteers_Goal;
    }

    public String getEndof_Event() {
        return endof_Event;
    }

    public void setEndof_Event(String endof_Event) {
        this.endof_Event = endof_Event;
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

    public Long getTotal_DONATIONS() {
        return Total_DONATIONS;
    }

    public void setTotal_DONATIONS(Long total_DONATIONS) {
        Total_DONATIONS = total_DONATIONS;
    }

    public Long getTotal_VOLUNTEERS() {
        return Total_VOLUNTEERS;
    }

    public void setTotal_VOLUNTEERS(Long total_VOLUNTEERS) {
        Total_VOLUNTEERS = total_VOLUNTEERS;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getJobSkill0() {
        return jobSkill0;
    }

    public void setJobSkill0(String jobSkill0) {
        this.jobSkill0 = jobSkill0;
    }

    public String getJobSkill1() {
        return jobSkill1;
    }

    public void setJobSkill1(String jobSkill1) {
        this.jobSkill1 = jobSkill1;
    }

    public String getJobSkill2() {
        return jobSkill2;
    }

    public void setJobSkill2(String jobSkill2) {
        this.jobSkill2 = jobSkill2;
    }

    public String getJobSkill3() {
        return jobSkill3;
    }

    public void setJobSkill3(String jobSkill3) {
        this.jobSkill3 = jobSkill3;
    }

    public String getJobSkill4() {
        return jobSkill4;
    }

    public void setJobSkill4(String jobSkill4) {
        this.jobSkill4 = jobSkill4;
    }

    public String getJobSkill5() {
        return jobSkill5;
    }

    public void setJobSkill5(String jobSkill5) {
        this.jobSkill5 = jobSkill5;
    }

    public String getJobSkill6() {
        return jobSkill6;
    }

    public void setJobSkill6(String jobSkill6) {
        this.jobSkill6 = jobSkill6;
    }

    public String getJobSkill7() {
        return jobSkill7;
    }

    public void setJobSkill7(String jobSkill7) {
        this.jobSkill7 = jobSkill7;
    }

    public String getJobSkill8() {
        return jobSkill8;
    }

    public void setJobSkill8(String jobSkill8) {
        this.jobSkill8 = jobSkill8;
    }

    public String getJobSkill9() {
        return jobSkill9;
    }

    public void setJobSkill9(String jobSkill9) {
        this.jobSkill9 = jobSkill9;
    }

    public String getJobSkill10() {
        return jobSkill10;
    }

    public void setJobSkill10(String jobSkill10) {
        this.jobSkill10 = jobSkill10;
    }

    public String getJobSkill11() {
        return jobSkill11;
    }

    public void setJobSkill11(String jobSkill11) {
        this.jobSkill11 = jobSkill11;
    }

    public String getJobSkill12() {
        return jobSkill12;
    }

    public void setJobSkill12(String jobSkill12) {
        this.jobSkill12 = jobSkill12;
    }

    public String getJobSkill13() {
        return jobSkill13;
    }

    public void setJobSkill13(String jobSkill13) {
        this.jobSkill13 = jobSkill13;
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
}