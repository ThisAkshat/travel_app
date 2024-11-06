package com.example.travel_app.Adapter;

// Importing necessary libraries

import android.content.Context; // Context is needed to access resources and start activities
import android.content.Intent; // Intent is used to start new activities
import android.view.LayoutInflater; // LayoutInflater is used to inflate layouts from XML
import android.view.View; // View is the base class for all UI components
import android.view.ViewGroup; // ViewGroup is the base class for all layouts or containers

import androidx.annotation.NonNull; // This annotation ensures the method parameters are non-null
import androidx.recyclerview.widget.RecyclerView; // RecyclerView is used to display a list of items in a scrollable view

import com.bumptech.glide.Glide; // Glide is an image loading library to load images from URLs asynchronously
import com.example.travel_app.Activity.DetailActivity; // The activity that will be opened when an item is clicked
import com.example.travel_app.Domain.itemDomain; // The data model class that represents each item in the list
import com.example.travel_app.databinding.ViewholderPopularBinding; // ViewBinding class to access and bind views in the layout file

import java.util.ArrayList; // ArrayList is a collection class that holds a dynamic array of items

// Adapter class for Popular items in RecyclerView
public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {

    // List to store the items that will be shown in the RecyclerView
    ArrayList<itemDomain> items;

    // Context to access application-level resources and start activities
    Context context;

    // ViewBinding object to access the views of each item
    ViewholderPopularBinding binding;

    // Constructor of the PopularAdapter class, initializing it with the list of popular items
    public PopularAdapter(ArrayList<itemDomain> items) {
        this.items = items;  // Storing the passed list of items
    }

    // Called when a new ViewHolder is created for an item
    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView (viewholder_popular.xml)
        binding = ViewholderPopularBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        // Get the context from the parent view to use it in the activity and Glide
        context = parent.getContext();

        // Return a new ViewHolder that holds the reference to the item view
        return new Viewholder(binding);
    }

    // Called to bind data to each item in the RecyclerView
    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        // Set the title of the popular item in the title TextView
        binding.titleTxt.setText(items.get(position).getTitle());

        // Set the price of the popular item in the price TextView
        binding.priceTxt.setText("$" + items.get(position).getPrice());

        // Set the address of the popular item in the address TextView
        binding.addressTxt.setText(items.get(position).getAddress());

        // Set the score of the popular item in the score TextView
        binding.scoreTxt.setText("" + items.get(position).getScore());

        // Use Glide library to load the image from the URL into the ImageView
        Glide.with(context)
                .load(items.get(position).getPic())  // Get the image URL from the item
                .into(binding.pic);  // Load the image into the ImageView

        // Set an OnClickListener on the item view (when clicked, open DetailActivity)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the DetailActivity when an item is clicked
                Intent intent = new Intent(context, DetailActivity.class);

                // Pass the item data (itemDomain object) to the DetailActivity using putExtra
                intent.putExtra("object", items.get(position));

                // Start the DetailActivity
                context.startActivity(intent);
            }
        });
    }

    // Returns the total number of items in the list
    @Override
    public int getItemCount() {
        return items.size();  // Returns the size of the list of items
    }

    // ViewHolder class holds the reference to the views for each item in the list
    public class Viewholder extends RecyclerView.ViewHolder {

        // Constructor for the ViewHolder, receiving the ViewBinding
        public Viewholder(ViewholderPopularBinding binding) {
            super(binding.getRoot());  // Pass the root view of the ViewBinding to the parent constructor
        }
    }
}
