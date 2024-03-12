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
import com.example.playstore_clone.reyckerview_models.Child_recyclerview_1_row_books_verysmall_data_model;

import java.util.ArrayList;

public class Child_Recyclerview_1_row_books_verysmall_adapter extends RecyclerView.Adapter<Child_Recyclerview_1_row_books_verysmall_adapter.Viewholder> {
    Context context;
    ArrayList<Child_recyclerview_1_row_books_verysmall_data_model> arrayList;

    itemOnClick itemonclick;

    public Child_Recyclerview_1_row_books_verysmall_adapter(Context context, ArrayList<Child_recyclerview_1_row_books_verysmall_data_model> arrayList, itemOnClick itemonclick) {
        this.context = context;
        this.arrayList = arrayList;
        this.itemonclick = itemonclick;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_1_row_books_verysmall_lineardata_layout,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
        imagesFromFirebaseStorage.Image_to_fetch(arrayList.get(position).getImage(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.Book_image.setImageBitmap(bitmap);
                //Glide.with(context).load(bitmap).into(holder.Book_image);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });
            holder.Book_Position.setText(String.valueOf(position+1));
            holder.Book_name.setText(arrayList.get(position).getName());
            holder.Author_name.setText(arrayList.get(position).getAuthor_name());
            holder.Book_price.setText(arrayList.get(position).getPrice());
            holder.Book_ratings.setText(arrayList.get(position).getRatings());
            holder.Book_type.setText(arrayList.get(position).getType());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemonclick.onClick(position);
                }
            });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface itemOnClick{
        void onClick(int position);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView Book_image;
        TextView Book_name,Author_name,Book_price, Book_ratings, Book_type, Book_Position;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Book_image = itemView.findViewById(R.id.child_rv_1_row_books_verysmall_booklogo);
            Book_name = itemView.findViewById(R.id.child_rv_1_row_books_verysmall_bookname);
            Author_name = itemView.findViewById(R.id.child_rv_1_row_books_verysmall_companyname);
            Book_price = itemView.findViewById(R.id.child_rv_1_row_books_verysmall_bookprice);
            Book_ratings = itemView.findViewById(R.id.child_rv_1_row_books_verysmall_ratings);
            Book_type = itemView.findViewById(R.id.child_rv_1_row_books_verysmall_type);
            Book_Position = itemView.findViewById(R.id.child_rv_1_row_books_verysmall_position);
        }
    }
}
