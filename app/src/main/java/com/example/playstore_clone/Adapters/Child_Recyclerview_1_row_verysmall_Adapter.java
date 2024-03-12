package com.example.playstore_clone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_verysmall_datamodel;

import java.util.ArrayList;

public class Child_Recyclerview_1_row_verysmall_Adapter extends RecyclerView.Adapter<Child_Recyclerview_1_row_verysmall_Adapter.Viewholder> {

    Context context;
    ArrayList<Child_Recyclerview_1_row_verysmall_datamodel> child_rv_1_row_verysmall_array;

    itemOnClick itemonclick;


    public Child_Recyclerview_1_row_verysmall_Adapter(Context context, ArrayList<Child_Recyclerview_1_row_verysmall_datamodel> child_rv_1_row_array, itemOnClick itemonclick) {
        this.context = context;
        this.child_rv_1_row_verysmall_array = child_rv_1_row_array;
        this.itemonclick = itemonclick;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
            view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_1_row_small_lineardata_layout,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
        imagesFromFirebaseStorage.Image_to_fetch(child_rv_1_row_verysmall_array.get(position).getLogo(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.Game_logo.setImageBitmap(bitmap);
                //Glide.with(context).load(bitmap).into(holder.Game_logo);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });


            holder.Game_name.setText(child_rv_1_row_verysmall_array.get(position).getName());
            holder.Game_type.setText(child_rv_1_row_verysmall_array.get(position).getType());
            holder.Game_size.setText(child_rv_1_row_verysmall_array.get(position).getSize());
            holder.Game_Ratings.setText(child_rv_1_row_verysmall_array.get(position).getRatings());

            holder.game_position.setText(String.valueOf(position+1));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemonclick.onClick(position);
                }
            });

    }

    @Override
    public int getItemCount() {
        return child_rv_1_row_verysmall_array.size();
    }

    public interface itemOnClick{
        void onClick(int position);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView Game_logo;
        TextView Game_name,Game_type,Game_size, game_position, Game_Ratings;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            Game_logo = itemView.findViewById(R.id.child_rv_1_row_verysmall_image);
            Game_name = itemView.findViewById(R.id.child_rv_1_row_verysmall_gamename);
            Game_type = itemView.findViewById(R.id.child_rv_1_row_verysmall_gametype);
            Game_size = itemView.findViewById(R.id.child_rv_1_row_verysmall_game_size);
            Game_Ratings = itemView.findViewById(R.id.child_rv_1_row_verysmall_game_ratings);

            game_position = itemView.findViewById(R.id.child_rv_1_row_verysmall_position);

        }
    }
}
