package com.example.playstore_clone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_small_Datamodel;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class Child_Recyclerview_1_row_small_Adapter extends RecyclerView.Adapter<Child_Recyclerview_1_row_small_Adapter.Viewholder> {
    Context context;

    ArrayList<Child_Recyclerview_1_row_small_Datamodel> child_rv_1_row_small_array;

    itemOnclicklistener onclicklistener;

    String top_tab_name;

    ArrayList<Bitmap> logoList;

    public Child_Recyclerview_1_row_small_Adapter(Context context, ArrayList<Bitmap> logoList,ArrayList<Child_Recyclerview_1_row_small_Datamodel> child_rv_1_row_small_array, String top_tab_name, itemOnclicklistener onclicklistener) {
        this.context = context;
        this.child_rv_1_row_small_array = child_rv_1_row_small_array;
        this.onclicklistener = onclicklistener;
        this.top_tab_name= top_tab_name;
        this.logoList = logoList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_1_row_small_data_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.Game_logo.setImageBitmap(logoList.get(position));
        holder.Game_title.setText(child_rv_1_row_small_array.get(position).getName());
        holder.Game_ratings.setText(child_rv_1_row_small_array.get(position).getRatings());

        if(Objects.equals(top_tab_name, "Premium Tab")){
            holder.Game_price_size.setText(child_rv_1_row_small_array.get(position).getPrice());
        }else {
            holder.Game_price_size.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclicklistener.onclick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return child_rv_1_row_small_array.size();
    }

    public interface itemOnclicklistener{
        void onclick(int position);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView Game_logo;
        TextView Game_title;

        TextView Game_price_size;
        TextView Game_ratings;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Game_logo = itemView.findViewById(R.id.child_rv_1_row_small_gamelogo);
            Game_title = itemView.findViewById(R.id.child_rv_1_row_small_gametitle);
            Game_price_size = itemView.findViewById(R.id.child_rv_1_row_small_gamePrice);
            Game_ratings = itemView.findViewById(R.id.child_rv_1_row_small_gameratings);

        }
    }
}
