package com.example.travel_app.Activity;

// Android ke imports
import android.os.Bundle;  // Ye activity ke lifecycle ke dauran data ko store karne ke liye use hota hai
import android.view.View;   // Ye UI elements (buttons, etc.) par click hone wale events ko handle karta hai
import android.widget.ArrayAdapter;  // Ye Spinner (dropdown menu) mein items ko dikhane ke liye use hota hai
import android.widget.Toast;  // Ye short messages ko screen par show karta hai (jaise pop-up messages)


// AndroidX ke imports, jo UI aur layout ke management mein madad karte hain
import androidx.annotation.NonNull;  // Ye indicate karta hai ke method ka parameter null nahi ho sakta
import androidx.recyclerview.widget.LinearLayoutManager;  // Ye RecyclerView mein items ko line-wise (vertically/horizontally) dikhane ke liye use hota hai
import androidx.recyclerview.widget.RecyclerView;  // Ye large lists ko efficiently display karne ke liye use hota hai
import androidx.viewpager2.widget.CompositePageTransformer;  // Ye multiple transformations apply karta hai ViewPager2 (jaise zoom ya fade)
import androidx.viewpager2.widget.MarginPageTransformer;  // Ye ViewPager2 ke pages ke beech mein margin add karta hai

// Custom adapter aur domain class ke imports
import com.example.travel_app.Adapter.CategoryAdapter;  // Ye category list ko display karne ke liye custom adapter hai
import com.example.travel_app.Adapter.PopularAdapter;  // Ye popular items ko display karne ke liye custom adapter hai
import com.example.travel_app.Adapter.RecommendedAdapter;  // Ye recommended items ko display karne ke liye custom adapter hai
import com.example.travel_app.Adapter.SliderAdapter;  // Ye slider/banner images ko display karne ke liye custom adapter hai
import com.example.travel_app.Domain.Category;  // Ye category ki details ko represent karta hai
import com.example.travel_app.Domain.Location;  // Ye location ki details ko represent karta hai
import com.example.travel_app.Domain.SliderItems;  // Ye slider items ki details ko represent karta hai
import com.example.travel_app.Domain.itemDomain;  // Ye ek item ki details ko represent karta hai (jaise products ya services)


// Resources ko import karta hai
import com.example.travel_app.R;  // Ye app ke resources (strings, layouts, images) ko access karne mein help karta hai
import com.example.travel_app.databinding.ActivityMainBinding;  // Ye activity ke UI elements ko code ke saath bind karne mein help karta hai

// Firebase ke imports
import com.google.firebase.database.DataSnapshot;  // Ye Firebase se aaye data ka snapshot dikhata hai
import com.google.firebase.database.DatabaseError;  // Ye Firebase se koi error aaye to usko handle karta hai
import com.google.firebase.database.DatabaseReference;  // Ye Firebase database ke kisi specific location ko refer karta hai
import com.google.firebase.database.FirebaseDatabase;  // Ye Firebase database se connection banaata hai
import com.google.firebase.database.ValueEventListener;  // Ye Firebase database par changes sunne aur response dene ke liye use hota hai

// Java collections ke imports
import java.util.ArrayList;  // Ye ek dynamic array hota hai jo size automatically adjust karta hai jab items add karte hain
import java.util.List;  // Ye ek generic list hoti hai jo multiple data items ko store karne ke liye use hoti hai

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;  // Binding object to access UI components defined in the XML layout file
    private FirebaseDatabase database;  // Firebase database instance to fetch data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());  // Binding UI elements to activity
        setContentView(binding.getRoot());  // Setting the root layout of the activity

        // Initialize Firebase database
        database = FirebaseDatabase.getInstance();

        // Calling methods to initialize different sections of the app
        initLocation();      // Initialize the location list from Firebase
        initBanner();        // Initialize the banner section with data from Firebase
        initCategory();      // Initialize the categories list from Firebase
        initRecommended();   // Initialize the recommended items list from Firebase
        initPopular();       // Initialize the popular items list from Firebase
    }

    // Method to initialize popular items from Firebase
    private void initPopular() {
        DatabaseReference myRef = database.getReference("Popular");  // Firebase reference to the "Popular" node
        binding.progressBarPopular.setVisibility(View.VISIBLE);  // Show the progress bar while data is loading

        ArrayList<itemDomain> list = new ArrayList<>();  // Creating a list to store popular items
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {  // Listening for data from Firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {  // Check if data exists
                    for (DataSnapshot issue : snapshot.getChildren()) {  // Loop through each child in the snapshot
                        list.add(issue.getValue(itemDomain.class));  // Add each popular item to the list
                    }
                    if (!list.isEmpty()) {  // If the list is not empty
                        binding.recyclerViewPopular.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));  // Set horizontal layout for displaying items
                        RecyclerView.Adapter adapter = new PopularAdapter(list);  // Create a new adapter to display the list in RecyclerView
                        binding.recyclerViewPopular.setAdapter(adapter);  // Set the adapter to the RecyclerView
                    }
                    binding.progressBarPopular.setVisibility(View.GONE);  // Hide the progress bar once data is loaded
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error here if needed (data fetch was cancelled or failed)
            }
        });
    }

    // Method to initialize recommended items from Firebase
    private void initRecommended() {
        DatabaseReference myRef = database.getReference("Item");  // Firebase reference to the "Item" node
        binding.progressBarRecommended.setVisibility(View.VISIBLE);  // Show the progress bar while data is loading

        ArrayList<itemDomain> list = new ArrayList<>();  // Creating a list to store recommended items
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {  // Listening for data from Firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {  // Check if data exists
                    for (DataSnapshot issue : snapshot.getChildren()) {  // Loop through each child in the snapshot
                        list.add(issue.getValue(itemDomain.class));  // Add each recommended item to the list
                    }
                    if (!list.isEmpty()) {  // If the list is not empty
                        binding.recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));  // Set horizontal layout for displaying items
                        RecyclerView.Adapter adapter = new RecommendedAdapter(list);  // Create a new adapter to display the list in RecyclerView
                        binding.recyclerViewRecommended.setAdapter(adapter);  // Set the adapter to the RecyclerView
                    }
                    binding.progressBarRecommended.setVisibility(View.GONE);  // Hide the progress bar once data is loaded
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error here if needed (data fetch was cancelled or failed)
            }
        });
    }

    // Method to initialize categories from Firebase
    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");  // Firebase reference to the "Category" node
        binding.progressBarCategory.setVisibility(View.VISIBLE);  // Show the progress bar while data is loading

        ArrayList<Category> list = new ArrayList<>();  // Creating a list to store categories
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {  // Listening for data from Firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {  // Check if data exists
                    for (DataSnapshot issue : snapshot.getChildren()) {  // Loop through each child in the snapshot
                        list.add(issue.getValue(Category.class));  // Add each category to the list
                    }
                    if (!list.isEmpty()) {  // If the list is not empty
                        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));  // Set horizontal layout for displaying items
                        RecyclerView.Adapter adapter = new CategoryAdapter(list);  // Create a new adapter to display the list in RecyclerView
                        binding.recyclerViewCategory.setAdapter(adapter);  // Set the adapter to the RecyclerView
                    }
                    binding.progressBarCategory.setVisibility(View.GONE);  // Hide the progress bar once data is loaded
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error here if needed (data fetch was cancelled or failed)
            }
        });
    }

    // Method to initialize locations from Firebase and set them in the spinner
    private void initLocation() {
        DatabaseReference myRef = database.getReference("Location");  // Firebase reference to the "Location" node
        ArrayList<Location> list = new ArrayList<>();  // Creating a list to store location data

        // Listening for data from Firebase
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {  // Check if data exists in the snapshot
                    for (DataSnapshot issue : snapshot.getChildren()) {  // Loop through each child in the snapshot
                        Location location = issue.getValue(Location.class);  // Convert snapshot to Location object
                        if (location != null) {  // If the location is not null, add it to the list
                            list.add(location);
                        }
                    }
                    // Set up the ArrayAdapter to show the locations in the spinner
                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  // Set dropdown view style
                    binding.LocationSp.setAdapter(adapter);  // Set the adapter to the spinner
                } else {
                    // Show a toast message if no locations are found
                    Toast.makeText(MainActivity.this, "No locations found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Show a toast message if data fetch fails
                Toast.makeText(MainActivity.this, "Failed to load locations: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to set up the image slider/banner
    private void banners(ArrayList<SliderItems> items) {
        // Set the adapter to the ViewPager for displaying the slider/banner images
        binding.viewPagerSlider.setAdapter(new SliderAdapter(items, binding.viewPagerSlider));
        binding.viewPagerSlider.setClipToPadding(false);  // Disable clipping of padding
        binding.viewPagerSlider.setClipChildren(false);  // Disable clipping of children views
        binding.viewPagerSlider.setOffscreenPageLimit(3);  // Preload three pages for smooth scrolling
        binding.viewPagerSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);  // Disable overscroll effect

        // Create a CompositePageTransformer to add custom page transformations
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));  // Add margin transformation between pages
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer);  // Apply the page transformer to the ViewPager
    }

    // Method to initialize banner data from Firebase
    private void initBanner() {
        DatabaseReference myRef = database.getReference("Banner");  // Firebase reference to the "Banner" node
        binding.progressBarBanner.setVisibility(View.VISIBLE);  // Show the progress bar while loading data
        ArrayList<SliderItems> items = new ArrayList<>();  // Creating a list to store slider/banner items

        // Listening for data from Firebase
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {  // Check if data exists in the snapshot
                    for (DataSnapshot issue : snapshot.getChildren()) {  // Loop through each child in the snapshot
                        items.add(issue.getValue(SliderItems.class));  // Add each slider/banner item to the list
                    }
                    banners(items);  // Call the banners method to set up the ViewPager
                    binding.progressBarBanner.setVisibility(View.GONE);  // Hide the progress bar once data is loaded
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error here if needed (data fetch was cancelled or failed)
            }
        });
    }
}