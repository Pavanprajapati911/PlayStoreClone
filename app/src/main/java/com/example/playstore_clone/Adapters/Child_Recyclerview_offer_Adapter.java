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
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_offer_datamodel;

import java.util.ArrayList;

public class Child_Recyclerview_offer_Adapter extends RecyclerView.Adapter<Child_Recyclerview_offer_Adapter.Viewholder> {
    Context context;
    ArrayList<Child_Recyclerview_offer_datamodel> arrayList;

    public Itemonclicklistener itemonclicklistener;

    public Child_Recyclerview_offer_Adapter(Context context, ArrayList<Child_Recyclerview_offer_datamodel> arrayList, Itemonclicklistener itemonclicklistener) {
        this.context = context;
        this.arrayList = arrayList;
        this.itemonclicklistener=itemonclicklistener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_offers_data_layout,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
        imagesFromFirebaseStorage.Image_to_fetch(arrayList.get(position).getEvent_or_offer_image(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.Offer_image.setImageBitmap(bitmap);
                //Glide.with(context).load(bitmap).into(holder.Offer_image);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });



        holder.Offer_game_name.setText(arrayList.get(position).getName());
        holder.Offer_title.setText(arrayList.get(position).getEvent_or_offer_title());
        holder.Offer_main_title.setText(arrayList.get(position).getMain_title());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemonclicklistener.onclick(position);
            }
        });


    }

    public interface Itemonclicklistener {
        public void onclick(int position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView Offer_image;
        TextView Offer_game_name,Offer_title,Offer_main_title;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Offer_image = itemView.findViewById(R.id.child_rv_offer_offer_iamge);
            Offer_game_name = itemView.findViewById(R.id.child_rv_offer_game_name);
            Offer_title = itemView.findViewById(R.id.child_rv_offers_title);
            Offer_main_title = itemView.findViewById(R.id.child_rv_offers_maintitle);
        }
    }
}
