package com.example.playstore_clone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
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
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_3_row_DataModel;

import java.util.ArrayList;

public class Child_Recyclerview_3_row_Adapter extends RecyclerView.Adapter<Child_Recyclerview_3_row_Adapter.Viewholder> {
    Context context;
    ArrayList<Child_Recyclerview_3_row_DataModel> child_rv_3_row_array;

    itemOnClick itemonclick;

    public Child_Recyclerview_3_row_Adapter(Context context, ArrayList<Child_Recyclerview_3_row_DataModel> child_rv_3_row_array, itemOnClick itemonclick) {
        this.context = context;
        this.child_rv_3_row_array = child_rv_3_row_array;
        this.itemonclick = itemonclick;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_3_row_data_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
        imagesFromFirebaseStorage.Image_to_fetch(child_rv_3_row_array.get(position).getFirst_game_logo(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.First_Game_Image.setImageBitmap(bitmap);
                //Glide.with(context).load(bitmap).into(holder.First_Game_Image);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });

        imagesFromFirebaseStorage.Image_to_fetch(child_rv_3_row_array.get(position).getSecond_game_logo(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.Second_Game_Image.setImageBitmap(bitmap);
                //Glide.with(context).load(bitmap).into(holder.Second_Game_Image);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });

        imagesFromFirebaseStorage.Image_to_fetch(child_rv_3_row_array.get(position).getThird_game_logo(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                    holder.Third_Game_Image.setImageBitmap(bitmap);
                // Glide.with(context).load(bitmap).into(holder.Third_Game_Image);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });


        holder.First_Game_Name.setText(child_rv_3_row_array.get(position).getFirst_game_name());
        holder.First_Game_Type.setText(child_rv_3_row_array.get(position).getFirst_type());
        holder.First_Game_Size.setText(child_rv_3_row_array.get(position).getFirst_game_size());
        holder.First_Game_ratings.setText(child_rv_3_row_array.get(position).getFirst_game_ratings());

        holder.Second_Game_Name.setText(child_rv_3_row_array.get(position).getSecond_game_name());
        holder.Second_Game_Type.setText(child_rv_3_row_array.get(position).getSecond_type());
        holder.Second_Game_Size.setText(child_rv_3_row_array.get(position).getSecond_game_size());
        holder.Second_Game_ratings.setText(child_rv_3_row_array.get(position).getSecond_game_ratings());

        holder.Third_Game_Name.setText(child_rv_3_row_array.get(position).getThird_game_name());
        holder.Third_Game_Type.setText(child_rv_3_row_array.get(position).getThird_type());
        holder.Third_Game_Size.setText(child_rv_3_row_array.get(position).getThird_game_size());
        holder.Third_Game_ratings.setText(child_rv_3_row_array.get(position).getThird_game_ratings());

        holder.first_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemonclick.onClick(position, child_rv_3_row_array.get(position).getFirst_game_name());
            }
        });

        holder.second_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemonclick.onClick(position, child_rv_3_row_array.get(position).getSecond_game_name());
            }
        });

        holder.third_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemonclick.onClick(position, child_rv_3_row_array.get(position).getThird_game_name());
            }
        });


    }

    @Override
    public int getItemCount() {
        return child_rv_3_row_array.size();
    }

    public interface itemOnClick{
        void onClick(int position, String app_or_game_Name);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView First_Game_Image,Second_Game_Image,Third_Game_Image;
        TextView First_Game_Name,First_Game_Type,First_Game_Size, First_Game_ratings,
                Second_Game_Name,Second_Game_Type,Second_Game_Size, Second_Game_ratings,
                Third_Game_Name,Third_Game_Type,Third_Game_Size, Third_Game_ratings;

        LinearLayout first_layout, second_layout, third_layout;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            First_Game_Image = itemView.findViewById(R.id.child_rv_3_row_FirstGame_logo);
            Second_Game_Image = itemView.findViewById(R.id.child_rv_3_row_SecondGame_logo);
            Third_Game_Image = itemView.findViewById(R.id.child_rv_3_row_ThirdGame_logo);

            First_Game_Name = itemView.findViewById(R.id.child_rv_3_row_FirstGame_name);
            First_Game_Type = itemView.findViewById(R.id.child_rv_3_row_FirstGame_type);
            First_Game_Size = itemView.findViewById(R.id.child_rv_3_row_FirstGame_size);
            First_Game_ratings = itemView.findViewById(R.id.child_rv_3_row_FirstGame_ratings);

            Second_Game_Name = itemView.findViewById(R.id.child_rv_3_row_SecondGame_name);
            Second_Game_Type = itemView.findViewById(R.id.child_rv_3_row_SecondGame_type);
            Second_Game_Size = itemView.findViewById(R.id.child_rv_3_row_SecondGame_size);
            Second_Game_ratings = itemView.findViewById(R.id.child_rv_3_row_SecondGame_ratings);

            Third_Game_Name = itemView.findViewById(R.id.child_rv_3_row_ThirdGame_name);
            Third_Game_Type = itemView.findViewById(R.id.child_rv_3_row_ThirdGame_type);
            Third_Game_Size = itemView.findViewById(R.id.child_rv_3_row_ThirdGame_size);
            Third_Game_ratings = itemView.findViewById(R.id.child_rv_3_row_ThirdGame_ratings);

            first_layout = itemView.findViewById(R.id.child_Recyclerview_3_row__first_layout);
            second_layout = itemView.findViewById(R.id.child_Recyclerview_3_row__second_layout);
            third_layout = itemView.findViewById(R.id.child_Recyclerview_3_row__third_layout);

        }
    }
}
