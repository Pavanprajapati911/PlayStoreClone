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
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_big_DataModel;

import java.util.ArrayList;

public class Child_Recyclerview_1_row_big_Adapter extends RecyclerView.Adapter<Child_Recyclerview_1_row_big_Adapter.Viewholder> {
    Context context;
    ArrayList<Child_Recyclerview_1_row_big_DataModel> child_rv_1_row_big_array;

    itemOnCLick item_on_cLick;

    public Child_Recyclerview_1_row_big_Adapter(Context context, ArrayList<Child_Recyclerview_1_row_big_DataModel> child_rv_1_row_big_array, itemOnCLick item_on_cLick) {
        this.context = context;
        this.child_rv_1_row_big_array = child_rv_1_row_big_array;
        this.item_on_cLick = item_on_cLick;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_1_row_big_data_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
        imagesFromFirebaseStorage.Image_to_fetch(child_rv_1_row_big_array.get(position).getBackground_image(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.Content_image.setImageBitmap(bitmap);
                //Glide.with(context).load(bitmap).into(holder.Content_image);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });

        imagesFromFirebaseStorage.Image_to_fetch(child_rv_1_row_big_array.get(position).getLogo(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                //Glide.with(context).load(bitmap).into(holder.Game_logo);
                holder.Game_logo.setImageBitmap(bitmap);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });

        holder.Game_name.setText(child_rv_1_row_big_array.get(position).getName());
        holder.Company_name.setText(child_rv_1_row_big_array.get(position).getCompany_name());
        holder.Ratings.setText(child_rv_1_row_big_array.get(position).getRatings());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_on_cLick.onCLick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return child_rv_1_row_big_array.size();
    }

    public interface itemOnCLick{
        void onCLick(int position);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView Content_image,Game_logo;
        TextView Game_name,Company_name, Ratings;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Content_image = itemView.findViewById(R.id.child_rv_1_row_big_content_image);
            Game_logo = itemView.findViewById(R.id.child_rv_1_row_big_game_logo);
            Game_name = itemView.findViewById(R.id.child_rv_1_row_big_GameName);
            Company_name = itemView.findViewById(R.id.child_rv_1_row_big_company_name);
            Ratings = itemView.findViewById(R.id.child_rv_1_row_big_ratings);
        }
    }
}
