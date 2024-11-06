package com.example.travel_app.Activity;  // Yeh line file ko "Activity" folder mein rakhta hai, jo code ko organize karta hai.

import android.content.Intent;  // Intent ko import karte hain jo ek activity se doosri activity tak data bhejne aur navigate karne mein madad karta hai.
import android.os.Bundle;  // Bundle ko import karte hain jo activity ke data ko save aur load karne ke liye use hota hai.
import android.view.View;  // View ko import karte hain jo UI components ko handle karne ke liye use hota hai.

import com.example.travel_app.databinding.ActivityIntroBinding;  // ActivityIntroBinding ko import karte hain, jo layout aur activity ke elements ko connect karta hai.


public class introActivity extends BaseActivity {
    ActivityIntroBinding binding;  // ActivityIntroBinding ka object jo layout file ko activity ke sath connect karega.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // onCreate method call hota hai jab activity start hoti hai.

        binding = ActivityIntroBinding.inflate(getLayoutInflater());  // Binding ko initialize karte hain aur layout ko inflate karte hain.
        setContentView(binding.getRoot());  // Layout ko screen par set karte hain.

        // Button click event ko handle karte hain:
        binding.introBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(introActivity.this, MainActivity.class));  // MainActivity ko open karne ka intent create karte hain.
                finish();  // IntroActivity ko close karte hain.
            }
        });

    }
}
