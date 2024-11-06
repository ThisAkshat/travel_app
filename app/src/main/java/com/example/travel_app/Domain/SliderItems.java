package com.example.travel_app.Domain; // Package declaration jo specify karti hai ke yeh class app ke domain package mein hai

// SliderItems class ko define kiya gaya hai jo slider images ke URLs ko hold karti hai
public class SliderItems {
    private String url; // yeh field ek image ka URL store karta hai

    // Default no-argument constructor
    public SliderItems() {
        // Firebase integration ke liye zaroori hai taake woh objects ko bina values ke bhi initialize kar sake
    }

    // Parameterized constructor jo ek URL ko pass karke object ko initialize karta hai
    public SliderItems(String url) {
        this.url = url;
    }

    // Getter method jo URL ko return karta hai
    public String getUrl() {
        return url;
    }

    // Setter method jo URL ko set karta hai
    public void setUrl(String url) {
        this.url = url;
    }
}
