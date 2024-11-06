package com.example.travel_app.Domain;

import java.io.Serializable; // Serializable ko import kar rahe hain taake hum class ka object intents ke zariye pass kar saken

// itemDomain class banayi hai jo Serializable hai (ye object ko data pass karte waqt bundle ya intent mein convert kar deta hai)
public class itemDomain implements Serializable {
    private String title; // Yeh field item ke title ya naam ko represent karti hai
    private String address; // Item ka address ya location store karta hai
    private String description; // Item ka description ya detail
    private String pic; // Item ki image ka path ya URL
    private String duration; // Tour ya package ka duration (kitna time lagega)
    private String timeTour; // Tour ka time store karta hai
    private String dateTour; // Tour ka date store karta hai
    private String tourGuideName; // Tour guide ka naam
    private String tourGuidePhone; // Tour guide ka contact number
    private String tourGuidePic; // Tour guide ki picture ka URL ya path
    private int price; // Item ka price ya cost
    private int bed; // Available beds ki quantity
    private String distance; // Distance information, e.g., tour ka distance
    private double score; // Item ka score ya rating (jaise 4.5 stars)

    // Default constructor - jab koi specific values na ho, to yeh empty object create karta hai
    public itemDomain() {
    }

    // Getter aur setter methods fields ki values ko get aur set karne ke liye hain

    // Address ko return karta hai
    public String getAddress() {
        return address;
    }

    // Address ko set karta hai
    public void setAddress(String address) {
        this.address = address;
    }

    // Title ko return karta hai
    public String getTitle() {
        return title;
    }

    // Title ko set karta hai
    public void setTitle(String title) {
        this.title = title;
    }

    // Description ko return karta hai
    public String getDescription() {
        return description;
    }

    // Description ko set karta hai
    public void setDescription(String description) {
        this.description = description;
    }

    // Image path ya URL ko return karta hai
    public String getPic() {
        return pic;
    }

    // Image path ya URL ko set karta hai
    public void setPic(String pic) {
        this.pic = pic;
    }

    // Duration ko return karta hai
    public String getDuration() {
        return duration;
    }

    // Duration ko set karta hai
    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Time of tour ko return karta hai
    public String getTimeTour() {
        return timeTour;
    }

    // Time of tour ko set karta hai
    public void setTimeTour(String timeTour) {
        this.timeTour = timeTour;
    }

    // Date of tour ko return karta hai
    public String getDateTour() {
        return dateTour;
    }

    // Date of tour ko set karta hai
    public void setDateTour(String dateTour) {
        this.dateTour = dateTour;
    }

    // Tour guide ka naam return karta hai
    public String getTourGuideName() {
        return tourGuideName;
    }

    // Tour guide ka naam set karta hai
    public void setTourGuideName(String tourGuideName) {
        this.tourGuideName = tourGuideName;
    }

    // Tour guide ka phone number return karta hai
    public String getTourGuidePhone() {
        return tourGuidePhone;
    }

    // Tour guide ka phone number set karta hai
    public void setTourGuidePhone(String tourGuidePhone) {
        this.tourGuidePhone = tourGuidePhone;
    }

    // Tour guide ki image ka URL return karta hai
    public String getTourGuidePic() {
        return tourGuidePic;
    }

    // Tour guide ki image ka URL set karta hai
    public void setTourGuidePic(String tourGuidePic) {
        this.tourGuidePic = tourGuidePic;
    }

    // Price ko return karta hai
    public int getPrice() {
        return price;
    }

    // Price ko set karta hai
    public void setPrice(int price) {
        this.price = price;
    }

    // Beds ki quantity ko return karta hai
    public int getBed() {
        return bed;
    }

    // Beds ki quantity ko set karta hai
    public void setBed(int bed) {
        this.bed = bed;
    }

    // Distance ko return karta hai
    public String getDistance() {
        return distance;
    }

    // Distance ko set karta hai
    public void setDistance(String distance) {
        this.distance = distance;
    }

    // Score ya rating ko return karta hai
    public float getScore() {
        return (float) score;
    }

    // Score ya rating ko set karta hai
    public void setScore(double score) {
        this.score = score;
    }
}
