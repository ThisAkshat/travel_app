package com.example.travel_app.Adapter;

// Zaroori libraries import kar rahe hain

import android.content.Context; // Context se hum application ke resources aur services ko access karte hain
import android.content.Intent; // Intent se hum doosri activity ko start karte hain
import android.view.LayoutInflater; // LayoutInflater se hum layout XML file ko inflate karte hain, jo view objects banata hai
import android.view.View; // View Android mein sab UI elements ka base class hai
import android.view.ViewGroup; // ViewGroup ek container hota hai jo multiple views (ya doosre ViewGroups) rakhta hai

import androidx.annotation.NonNull; // Ye annotation batata hai ke method parameters null nahi honay chahiye
import androidx.recyclerview.widget.RecyclerView; // RecyclerView ek list item ko dikhane ke liye use hota hai

import com.bumptech.glide.Glide; // Glide ek library hai jo images ko asynchronously load karne ke liye use hoti hai
import com.example.travel_app.Activity.DetailActivity; // Ye woh activity hai jo item click hone par open hogi
import com.example.travel_app.Domain.itemDomain; // Ye model class hai jo items ki details rakhti hai
import com.example.travel_app.databinding.ViewholderCategoryBinding; // ViewBinding category item ke layout ko manage karta hai
import com.example.travel_app.databinding.ViewholderRecommendedBinding; // ViewBinding recommended item ke layout ko manage karta hai

import java.util.ArrayList; // ArrayList ek dynamic list hoti hai jo items ko rakhti hai

// Adapter class jo recommended items ko RecyclerView mein dikhata hai
public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.Viewholder> {

    // ArrayList jisme recommended items rakhe jate hain
    ArrayList<itemDomain> items;

    // Context object jo resources aur activities ko access karne ke liye use hota hai
    Context context;

    // ViewBinding object jo item layout ko bind karne ke liye use hota hai
    ViewholderRecommendedBinding binding;

    // Constructor jo recommended items ko receive karta hai
    public RecommendedAdapter(ArrayList<itemDomain> items) {
        this.items = items; // Items ko constructor se initialize karte hain
    }

    // Jab naya ViewHolder chahiye hota hai, to yeh method call hota hai (naya item view banane ke liye)
    @NonNull
    @Override
    public RecommendedAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Recommended item ka layout (viewholder_recommended.xml) inflate karte hain
        binding = ViewholderRecommendedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        // Parent view ka context lete hain, jo Glide aur resources ke liye use hoga
        context = parent.getContext();

        // Inflate kiye hue view se naya ViewHolder banate hain aur return karte hain
        return new Viewholder(binding);
    }

    // Yeh method har item ko bind karta hai (items ko views ke andar set karte hain)
    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.Viewholder holder, int position) {
        // Item ke title ko TextView mein set karte hain
        binding.titleTxt.setText(items.get(position).getTitle());

        // Item ka price TextView mein set karte hain
        binding.priceTxt.setText("$" + items.get(position).getPrice());

        // Item ka address TextView mein set karte hain
        binding.addressTxt.setText(items.get(position).getAddress());

        // Item ka score TextView mein set karte hain
        binding.scoreTxt.setText("" + items.get(position).getScore());

        // Glide ke saath image ko asynchronously load karte hain aur ImageView mein dikhate hain
        Glide.with(context)
                .load(items.get(position).getPic()) // Item ka image URL get karte hain
                .into(binding.pic); // Image ko ImageView mein load karte hain

        // Har item ke liye onClickListener set karte hain
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent create karte hain taake DetailActivity open ho jaye jab item click ho
                Intent intent = new Intent(context, DetailActivity.class);

                // Selected item ka data DetailActivity ko pass karte hain
                intent.putExtra("object", items.get(position));

                // DetailActivity ko start karte hain
                context.startActivity(intent);
            }
        });
    }

    // Yeh method total items ki count return karta hai
    @Override
    public int getItemCount() {
        return items.size(); // Items ki size ko return karte hain
    }

    // Viewholder class jo har item ke liye view hold karti hai
    public class Viewholder extends RecyclerView.ViewHolder {

        // Constructor jo ViewBinding ko use karke views ko bind karta hai
        public Viewholder(ViewholderRecommendedBinding binding) {
            super(binding.getRoot()); // ViewBinding ka root view parent constructor mein pass karte hain
        }
    }
}
