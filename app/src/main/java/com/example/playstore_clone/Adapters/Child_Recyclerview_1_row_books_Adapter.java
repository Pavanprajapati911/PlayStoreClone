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
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_books_DataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_small_Datamodel;

import java.util.ArrayList;

public class Child_Recyclerview_1_row_books_Adapter extends RecyclerView.Adapter<Child_Recyclerview_1_row_books_Adapter.Viewholder> {

    Context context;
    ArrayList<Child_Recyclerview_1_row_books_DataModel> child_rv_1_row_books_array;

    onItemClick onitemclick;

    ArrayList<Bitmap> logoList;

    public Child_Recyclerview_1_row_books_Adapter(Context context, ArrayList<Bitmap> logoList,ArrayList<Child_Recyclerview_1_row_books_DataModel> child_rv_1_row_books_array,  onItemClick onitemclick) {
        this.context = context;
        this.child_rv_1_row_books_array = child_rv_1_row_books_array;
        this.onitemclick = onitemclick;
        this.logoList = logoList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_1_row_books_data_layout,parent,false);

        return new Viewholder(view);
    }

    public interface onItemClick{
        void onclick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        if (logoList!=null){

            holder.Book_logo.setImageBitmap(logoList.get(position));

        }else {
            Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
            imagesFromFirebaseStorage.Image_to_fetch(child_rv_1_row_books_array.get(position).getImage(), new Fetch_image_interface() {
                @Override
                public void image_FetchSuccess(Bitmap bitmap) {
                    holder.Book_logo.setImageBitmap(bitmap);
                    //Glide.with(context).load(bitmap).into(holder.Book_logo);
                }

                @Override
                public void image_FetchFailure(Exception exception) {

                }
            });
        }


        holder.Book_name.setText(child_rv_1_row_books_array.get(position).getName());
        holder.Book_price.setText(child_rv_1_row_books_array.get(position).getPrice());
        holder.Book_Ratings.setText(child_rv_1_row_books_array.get(position).getRatings());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onitemclick.onclick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return child_rv_1_row_books_array.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView Book_logo;
        TextView Book_name,Book_price, Book_Ratings;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Book_logo = itemView.findViewById(R.id.child_rv_1_row_books_logo);
            Book_name = itemView.findViewById(R.id.child_rv_1_row_books_name);
            Book_price = itemView.findViewById(R.id.child_rv_1_row_books_price);
            Book_Ratings = itemView.findViewById(R.id.child_rv_1_row_books_ratings);


        }
    }
}
