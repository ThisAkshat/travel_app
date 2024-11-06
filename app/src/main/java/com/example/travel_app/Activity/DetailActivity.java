package com.example.travel_app.Activity;  // Yeh line file ko "Activity" folder mein rakhta hai, jo code ko organize karta hai.


// Zaroori classes ko import karte hain:
// Bundle - activity ke data ko save aur reload karne ke liye.
// Glide - images ko load aur display karne ke liye.
// Intent - doosri screen par jaane ke liye.
// itemDomain - ek custom class jo items ka data store karti hai.
// ActivityDetailBinding - view binding ke liye, jo layout file se elements ko connect karta hai.
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.travel_app.Domain.itemDomain;
import com.example.travel_app.databinding.ActivityDetailBinding;


public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding; // ActivityDetailBinding object, jo layout ke elements ko is activity se connect karega.
    private itemDomain object; // itemDomain object, jo item ka data store karta hai aur display karne mein help karta hai.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater()); // Binding ka use layout file ko is activity ke sath connect karne ke liye.
        setContentView(binding.getRoot()); // Screen par layout display karna.

        getIntentExtra(); // getIntentExtra() method call karke data ko receive karna.
        setVariable();    // setVariable() method call karke layout ke elements mein data set karna.
    }

    private void setVariable() {
        binding.titleTxt.setText(object.getTitle());  // Title ko set karna
        binding.priceTxt.setText("$"+object.getPrice()); // Price ko display karna
        binding.backBtn.setOnClickListener(v -> finish()); // Back button click par activity close karna.
        binding.bedTxt.setText(""+object.getBed()); // Bed info display karna
        binding.durationTxt.setText(object.getDuration()); // Duration set karna
        binding.distanceTxt.setText(object.getDistance()); // Distance set karna
        binding.descriptionTxt.setText(object.getDescription()); // Description display karna
        binding.addressTxt.setText(object.getAddress()); // Address display karna
        binding.ratingTxt.setText(object.getScore()+" Rating"); // Rating text set karna
        binding.ratingBar.setRating((float) object.getScore()); // Rating bar set karna
        Glide.with(DetailActivity.this)
                .load(object.getPic()) // Glide se image load karna
                .into(binding.pic); // Image ko layout mein show karna

        binding.addToCartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, TicketActivity.class); // TicketActivity par navigate karne ka intent
            intent.putExtra("object",object); // Item ka data TicketActivity mein bhejna
            startActivity(intent); // TicketActivity ko open karna
        });
    }

    private void getIntentExtra() {
        object = (itemDomain) getIntent().getSerializableExtra("object"); // getIntent() ke through data receive karna aur "object" ko itemDomain object mein convert karna.
    }
}
