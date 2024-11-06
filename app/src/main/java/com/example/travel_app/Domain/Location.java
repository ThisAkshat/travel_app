package com.example.travel_app.Domain; // Yeh package statement hai jo class ka location define karta hai is app ke domain mein

// Location class ko define karte hain jo ek location ka ID aur naam store karti hai
public class Location {
    private int Id; // Location ka unique ID store karta hai
    private String Loc; // Location ka naam ya address store karta hai

    // Default constructor jo empty object create karta hai jab kisi specific value ki zaroorat na ho
    public Location() {
    }

    // toString method override kiya gaya hai taake jab bhi Location object ko print kiya jaye ya string mein convert kiya jaye,
    // to uska sirf `Loc` ka naam hi return ho
    @Override
    public String toString() {
        return Loc;
    }

    // Getter aur setter methods jo Location ke fields ki values ko get aur set karne ke liye hain

    // Location ka ID return karta hai
    public int getId() {
        return Id;
    }

    // Location ka ID set karta hai
    public void setId(int id) {
        Id = id;
    }

    // Location ka naam return karta hai
    public String getLoc() {
        return Loc;
    }

    // Location ka naam set karta hai
    public void setLoc(String loc) {
        Loc = loc;
    }
}
