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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_data_model;

import java.util.ArrayList;
import java.util.Objects;

public class Child_Recyclerview_1_row_Adapter extends RecyclerView.Adapter<Child_Recyclerview_1_row_Adapter.Viewholder> {

    Context context;
    ArrayList<Child_Recyclerview_1_row_data_model> child_rv_1_row_array;
    String top_tab_name;
    itemOnclicklistener onclicklistener;

    public Child_Recyclerview_1_row_Adapter(Context context, ArrayList<Child_Recyclerview_1_row_data_model> child_rv_1_row_array, String top_tab_name, itemOnclicklistener onclicklistener) {
        this.context = context;
        this.child_rv_1_row_array = child_rv_1_row_array;
        this.top_tab_name = top_tab_name;
        this.onclicklistener = onclicklistener;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_1_row_data_layout,parent,false);
        return new Viewholder(view);
    }

    public interface itemOnclicklistener{
        void onclick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
        imagesFromFirebaseStorage.Image_to_fetch(child_rv_1_row_array.get(position).getBackground_image(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
               holder.Background_image.setImageBitmap(bitmap);
                // Glide.with(context).load(bitmap).into(holder.Background_image);
            }
            @Override
            public void image_FetchFailure(Exception exception) {
                Log.d("Storage exception", String.valueOf(exception));
            }
        });

        imagesFromFirebaseStorage.Image_to_fetch(child_rv_1_row_array.get(position).getLogo(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                //Glide.with(context).load(bitmap).into(holder.Game_logo);
                holder.Game_logo.setImageBitmap(bitmap);

            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });

        holder.Game_name.setText(child_rv_1_row_array.get(position).getName());
        holder.Game_type.setText(child_rv_1_row_array.get(position).getType());
        holder.Game_Ratings.setText(child_rv_1_row_array.get(position).getRatings());


        if(Objects.equals(top_tab_name, "Premium Tab")){
            holder.Game_price.setText(child_rv_1_row_array.get(position).getPrice());
        }else {
            holder.Game_price.setText(child_rv_1_row_array.get(position).getSize());
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
        return child_rv_1_row_array.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView Background_image,Game_logo;
        TextView Game_name,Game_type,Game_price, Game_Ratings;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Background_image = itemView.findViewById(R.id.child_rv_1_row_background_image);
            Game_logo = itemView.findViewById(R.id.child_rv_1_row_game_logo);
            Game_name = itemView.findViewById(R.id.child_rv_1_row_gameName);
            Game_type = itemView.findViewById(R.id.child_rv_1_row_gameType);
            Game_price = itemView.findViewById(R.id.child_rv_1_row_gamePrice);
            Game_Ratings = itemView.findViewById(R.id.child_rv_1_row_gameRatings);

        }
    }
}
