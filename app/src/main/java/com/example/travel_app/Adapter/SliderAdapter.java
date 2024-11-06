package com.example.travel_app.Adapter;

// Zaroori libraries import kar rahe hain jo SliderAdapter mein istemaal hongi

import android.content.Context; // Context se hum resources aur services ko access karte hain
import android.view.LayoutInflater; // LayoutInflater se layout XML ko view mein convert karte hain
import android.view.View; // View class jo UI elements ka base hota hai
import android.view.ViewGroup; // ViewGroup ek container hota hai jo doosre views ko rakhta hai
import android.widget.ImageView; // ImageView jo images ko display karta hai

import androidx.annotation.NonNull; // NonNull annotation, jo null safety ke liye use hoti hai
import androidx.recyclerview.widget.RecyclerView; // RecyclerView ek list ya grid ke items dikhane ke liye use hota hai
import androidx.viewpager2.widget.ViewPager2; // ViewPager2 swipe karne wale layouts ke liye

import com.bumptech.glide.Glide; // Glide library jo images ko asynchronously load karti hai
import com.example.travel_app.Domain.SliderItems; // SliderItems model class jo slider item ka data rakhti hai
import com.example.travel_app.R; // R file jo resources ke references ko rakhti hai

import java.util.ArrayList; // ArrayList jo list of items ko dynamically store karti hai

// SliderAdapter class jo RecyclerView.Adapter ko extend karti hai aur slider ke liye custom adapter banati hai
public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewholder> {

    // Ye ArrayList slider ke items ko rakhti hai
    private ArrayList<SliderItems> sliderItems;

    // ViewPager2 jo slider ko dikhane mein madad karta hai
    private ViewPager2 viewPager2;

    // Context jo resources aur Glide ke liye use hoga
    private Context context;

    // Runnable ek action ya process ko delay ke saath run karne ke liye use hota hai
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Yeh line slider items ko list mein dohra kar add karti hai
            sliderItems.addAll(sliderItems);

            // Data set badalne ke baad notify karte hain taake view update ho
            notifyDataSetChanged();
        }
    };

    // Constructor jo slider items aur ViewPager2 ko initialize karta hai
    public SliderAdapter(ArrayList<SliderItems> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    // onCreateViewHolder: naya ViewHolder create karta hai jab naya item dikhana ho
    @NonNull
    @Override
    public SliderAdapter.SliderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Context set karte hain parent view ke context se
        context = parent.getContext();

        // LayoutInflater se slider item layout ko inflate karte hain aur naya ViewHolder return karte hain
        return new SliderViewholder(LayoutInflater.from(context).inflate(R.layout.slider_item_container, parent, false));
    }

    // onBindViewHolder: views ke data ko set karta hai har slider item ke liye
    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewholder holder, int position) {
        // Null check aur position slider items ke limit mein hone ka check
        if (sliderItems != null && position < sliderItems.size()) {
            // Holder mein item ka image set karte hain
            holder.setImage(sliderItems.get(position));

            // Agar position list ke end ke qareeb ho to, runnable ko post karte hain
            if (position == sliderItems.size() - 2) { // Last se pehle wale item par
                viewPager2.post(runnable); // Runnable ko execute karte hain
            }
        }
    }

    // getItemCount: total items ki count return karta hai jo slider mein hai
    @Override
    public int getItemCount() {
        return sliderItems != null ? sliderItems.size() : 0; // Null check ke sath size return
    }

    // SliderViewholder class jo RecyclerView.ViewHolder ko extend karti hai aur slider item ko hold karti hai
    public class SliderViewholder extends RecyclerView.ViewHolder {

        // ImageView jo image ko display karta hai
        private ImageView imageView;

        // Constructor jo layout mein se imageView find karta hai aur use initialize karta hai
        public SliderViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide); // imageSlide ko findViewById ke through set karte hain
        }

        // setImage method jo Glide ke zariye imageView mein slider item ka image set karta hai
        void setImage(SliderItems sliderItem) {
            // Agar sliderItem aur uska URL null nahi hai, to image load karte hain
            if (sliderItem != null && sliderItem.getUrl() != null) {
                Glide.with(context)
                        .load(sliderItem.getUrl()) // Image URL ko load karte hain
                        .into(imageView); // Image ko imageView mein set karte hain
            } else {
                // Agar URL null hai to placeholder image set karte hain
                imageView.setImageResource(R.drawable.intro_pic); // Placeholder ke liye drawable resource
            }
        }
    }
}
