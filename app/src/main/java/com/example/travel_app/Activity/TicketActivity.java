package com.example.travel_app.Activity;

import android.content.Intent;  // Intent class ko import karta hai jo activity ke beech data transfer karne ke liye use hota hai.
import android.net.Uri;  // Uri class ko import karta hai jo kisi specific resource (jaise phone number, SMS) ko refer karne ke liye use hota hai.
import android.os.Bundle;  // Bundle ko import karta hai jo data ko activity ke beech transfer karne ke liye use hota hai.
import androidx.appcompat.app.AppCompatActivity;  // AppCompatActivity ko import karta hai, jo ek base class hai jo modern Android apps ke liye support karta hai.
import com.bumptech.glide.Glide;  // Glide ko import karta hai jo images ko efficiently load aur cache karne ke liye use hoti hai.
import com.example.travel_app.Domain.itemDomain;  // itemDomain ko import karta hai, jo ek custom class hai jo item details ko represent karti hai.
import com.example.travel_app.databinding.ActivityTicketBinding;  // ActivityTicketBinding ko import karta hai jo XML layout ko Java code ke saath bind karta hai (ViewBinding).

public class TicketActivity extends AppCompatActivity {
    ActivityTicketBinding binding;  // ViewBinding ka object jise hum layout ko access karenge.
    private itemDomain object;  // itemDomain type ka object jo ticket ki details ko represent karega.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activity ka layout bind karte hain using ViewBinding
        binding = ActivityTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();  // Intent se data ko receive karte hain.
        setVariable();     // Variables ko set karte hain, jaise image aur text views ko update karna.
    }

    // Method to set views and variables using the data received
    private void setVariable() {

        // Load the image of the ticket using Glide library
        Glide.with(TicketActivity.this)
                .load(object.getPic())  // Load ticket's image from object
                .into(binding.pic);  // Bind the image to the ImageView 'pic'

        // Load the tour guide's image using Glide
        Glide.with(TicketActivity.this)
                .load(object.getTourGuidePic())  // Load tour guide's image from object
                .into(binding.profile);  // Bind it to the ImageView 'profile'

        // Set back button functionality to finish the activity
        binding.backBtn.setOnClickListener(v -> finish());

        // Set title of the ticket
        binding.titleTxt.setText(object.getTitle());

        // Set the duration of the ticket
        binding.durationTxt.setText(object.getDuration());

        // Set the date of the tour
        binding.tourGuideTxt.setText(object.getDateTour());

        // Set the time of the tour
        binding.timeTxt.setText(object.getTimeTour());

        // Set the tour guide's name
        binding.tourGuideNameTxt.setText(object.getTourGuideName());

        // Set up SMS functionality for contacting the tour guide
        binding.callBtn.setOnClickListener(v -> {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);  // Create a new Intent to send SMS
            sendIntent.setData(Uri.parse("sms:" + object.getTourGuidePhone()));  // Set the tour guide's phone number as the recipient
            sendIntent.putExtra("sms_body", "type your message");  // Default SMS message body
            startActivity(sendIntent);  // Start the SMS app
        });

        // Set up phone call functionality for contacting the tour guide
        binding.messageBtn.setOnClickListener(v -> {
            String phone = object.getTourGuidePhone();  // Get the tour guide's phone number
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));  // Create an Intent to dial the phone number
            startActivity(intent);  // Start the phone dialer
        });
    }

    // Method to get the data passed via Intent
    private void getIntentExtra() {
        object = (itemDomain) getIntent().getSerializableExtra("object");  // Get the serialized object passed from previous activity
    }
}
