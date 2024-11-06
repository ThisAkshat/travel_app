package com.example.travel_app.Adapter;

// Import necessary Android and Glide libraries to handle views, binding, and image loading
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travel_app.Domain.Category;
import com.example.travel_app.R;
import com.example.travel_app.databinding.ViewholderCategoryBinding;

import java.util.List;

// This class is the adapter for displaying categories in a RecyclerView
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {
    private final List<Category> items;  // List to store Category objects, which will be displayed
    private int selectedPosition = -1;    // Keeps track of the currently selected position in the list
    private int lastSelectedPosition = -1; // Keeps track of the last selected position to update it
    private Context context;  // Context for accessing resources and loading views

    // Constructor to initialize the adapter with the list of Category items
    public CategoryAdapter(List<Category> items) {
        this.items = items;  // Assigning the passed list of categories to the 'items' variable
    }

    // This method creates and returns the ViewHolder for each item in the RecyclerView
    @NonNull
    @Override
    public CategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();  // Get the context of the parent view (Activity or Fragment)
        LayoutInflater inflater = LayoutInflater.from(context);  // Get the LayoutInflater to inflate the view
        ViewholderCategoryBinding binding = ViewholderCategoryBinding.inflate(inflater, parent, false);  // Binding for the category layout
        return new Viewholder(binding);  // Return the new ViewHolder with the inflated layout
    }

    // This method binds the data from the list (items) to the view in the ViewHolder
    @Override
    public void onBindViewHolder(@SuppressLint("RecyclerView") @NonNull CategoryAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
        Category item = items.get(position);  // Get the category item at the current position
        holder.binding.title.setText(item.getName());  // Set the category name in the TextView

        // Using Glide to load and display the category image into the ImageView
        Glide.with(holder.itemView.getContext())
                .load(item.getImagePath())  // Image URL or path from the Category object
                .into(holder.binding.pic);  // Set the image into the ImageView

        // Set an OnClickListener to handle click events on the item
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = selectedPosition;  // Store the previously selected position
                selectedPosition = position;  // Update the current selected position
                notifyItemChanged(lastSelectedPosition);  // Notify RecyclerView to update the last selected item
                notifyItemChanged(selectedPosition);  // Notify RecyclerView to update the newly selected item
            }
        });

        holder.binding.title.setTextColor(context.getResources().getColor(R.color.white));  // Set the title text color to white

        // Logic to change the appearance of the item based on whether it's selected or not
        if (selectedPosition == position) {
            // If the current item is selected, apply a blue background to the item
            holder.binding.pic.setBackgroundResource(0);  // Remove any background from the image
            holder.binding.mainLayout.setBackgroundResource(R.drawable.blue_bg);  // Set a blue background for the selected item
            holder.binding.title.setVisibility(View.VISIBLE);  // Show the title text
        } else {
            // If the current item is not selected, apply a gray background
            holder.binding.pic.setBackgroundResource(R.drawable.gray_bg);  // Set a gray background for non-selected item
            holder.binding.mainLayout.setBackgroundResource(0);  // Remove any background from the main layout
            holder.binding.title.setVisibility(View.GONE);  // Hide the title text
        }
    }

    // This method returns the total number of items in the list
    @Override
    public int getItemCount() {
        return items.size();  // Return the size of the category list
    }

    // ViewHolder class to hold the views for each category item in the RecyclerView
    public class Viewholder extends RecyclerView.ViewHolder {
        private final ViewholderCategoryBinding binding;  // Binding for the category item layout

        // Constructor to initialize the binding and item view
        public Viewholder(ViewholderCategoryBinding binding) {
            super(binding.getRoot());  // Get the root view of the item layout
            this.binding = binding;  // Assign the binding object
        }
    }
}
