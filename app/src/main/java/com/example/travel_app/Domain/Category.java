package com.example.travel_app.Domain;

// Yeh `Category` class ko define karta hai jo category ke data ko hold karne ke liye hai
public class Category {
    private int Id; // Category ka ek unique ID (ID number)
    private String ImagePath; // Category ka image path (image ki file ka location ya URL)
    private String Name; // Category ka naam (e.g., "Beaches", "Mountains", etc.)

    // Default constructor jo bina arguments ke ek `Category` object banata hai
    public Category() {

    }

    // `getId()` method jo ID ko return karega jab isay call kiya jata hai
    public int getId() {
        return Id;
    }

    // `setId()` method jo ID ko set karne ke liye use hota hai
    public void setId(int id) {
        Id = id;
    }

    // `getImagePath()` method jo category ka image path return karta hai
    public String getImagePath() {
        return ImagePath;
    }

    // `setImagePath()` method jo category ka image path set karne ke liye hai
    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    // `getName()` method jo category ka naam return karta hai
    public String getName() {
        return Name;
    }

    // `setName()` method jo category ka naam set karne ke liye hai
    public void setName(String name) {
        Name = name;
    }
}
