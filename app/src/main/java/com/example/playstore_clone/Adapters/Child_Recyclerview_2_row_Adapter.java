package com.example.playstore_clone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Child_Recylerview_2_row_data_model;

import java.util.ArrayList;

public class Child_Recyclerview_2_row_Adapter extends RecyclerView.Adapter<Child_Recyclerview_2_row_Adapter.Viewholder> {
    Context context;
    ArrayList<Child_Recylerview_2_row_data_model> child_rv_2_row_array;

    itemOnClick itemonclick;



    public Child_Recyclerview_2_row_Adapter(Context context, ArrayList<Child_Recylerview_2_row_data_model> child_rv_2_row_array, itemOnClick itemonclick) {
        this.context = context;
        this.child_rv_2_row_array = child_rv_2_row_array;
        this.itemonclick = itemonclick;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_2_row_data_layout,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();

        imagesFromFirebaseStorage.Image_to_fetch(child_rv_2_row_array.get(position).getFirst_game_image(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.First_game_image.setImageBitmap(bitmap);
                //Glide.with(context).load(bitmap).into(holder.First_game_image);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });

        imagesFromFirebaseStorage.Image_to_fetch(child_rv_2_row_array.get(position).getSecond_game_image(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.Second_game_image.setImageBitmap(bitmap);
                //Glide.with(context).load(bitmap).into(holder.Second_game_image);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });


        holder.First_game_name.setText(child_rv_2_row_array.get(position).getFirst_game_name());
        holder.First_game_type.setText(child_rv_2_row_array.get(position).getFirst_type());
        holder.First_game_Ratings.setText(child_rv_2_row_array.get(position).getFirst_Game_ratings());

        holder.Second_game_name.setText(child_rv_2_row_array.get(position).getSecond_game_name());
        holder.Second_game_type.setText(child_rv_2_row_array.get(position).getSecond_type());
        holder.Second_game_Ratings.setText(child_rv_2_row_array.get(position).getSecond_Game_ratings());

        holder.first_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemonclick.onClick(position, child_rv_2_row_array.get(position).getFirst_game_name());
            }
        });

        holder.second_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemonclick.onClick(position, child_rv_2_row_array.get(position).getSecond_game_name());
            }
        });



    }

    @Override
    public int getItemCount() {
        return child_rv_2_row_array.size();
    }

    public interface itemOnClick{
        void onClick(int position, String game_or_app_Name);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView First_game_image,Second_game_image;
        TextView First_game_name,First_game_type, First_game_Ratings ,Second_game_name,Second_game_type, Second_game_Ratings;

        LinearLayout first_layout, second_layout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            First_game_image = itemView.findViewById(R.id.first_game_image);
            Second_game_image = itemView.findViewById(R.id.second_game_image);

            First_game_name = itemView.findViewById(R.id.first_game_name);
            First_game_type = itemView.findViewById(R.id.first_game_type);
            First_game_Ratings = itemView.findViewById(R.id.first_game_ratings);

            Second_game_name = itemView.findViewById(R.id.second_game_name);
            Second_game_type = itemView.findViewById(R.id.second_game_type);
            Second_game_Ratings = itemView.findViewById(R.id.second_game_ratings);

            first_layout = itemView.findViewById(R.id.child_Recyclerview_2_row__first_layout);
            second_layout = itemView.findViewById(R.id.child_Recyclerview_2_row__second_layout);

        }
    }
}
