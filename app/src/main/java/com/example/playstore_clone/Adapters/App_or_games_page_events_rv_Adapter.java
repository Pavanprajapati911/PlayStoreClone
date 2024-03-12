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
import com.example.playstore_clone.reyckerview_models.App_content_page_events_and_offers_rv_data_model;

import java.util.ArrayList;

public class App_or_games_page_events_rv_Adapter extends RecyclerView.Adapter<App_or_games_page_events_rv_Adapter.Viewholder> {
    Context context;
    ArrayList<App_content_page_events_and_offers_rv_data_model> arrayList;

    item_onclick_listener itemOnclickListener;

    public App_or_games_page_events_rv_Adapter(Context context, ArrayList<App_content_page_events_and_offers_rv_data_model> arrayList, item_onclick_listener itemOnclickListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.itemOnclickListener = itemOnclickListener;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_content_page_events_and_offers_rv_layout,parent,false);

        return new Viewholder(view);

    }

    public interface item_onclick_listener{

        void onclick(int position);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        Fetching_Images_from_FirebaseStorage images = new Fetching_Images_from_FirebaseStorage();
        images.Image_to_fetch(arrayList.get(position).getImage(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {

               holder.image.setImageBitmap(bitmap);
                // Glide.with(context).load(bitmap).into(holder.image);
            }

            @Override
            public void image_FetchFailure(Exception exception) {

            }
        });

        holder.ending_date.setText(arrayList.get(position).getEnds_on());
        holder.title_1.setText(arrayList.get(position).getTitle());
        holder.title_2.setText(arrayList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnclickListener.onclick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class Viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView ending_date,title_1,title_2;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.app_content_page_events_rv_image);
            ending_date = itemView.findViewById(R.id.app_content_page_events_rv_ending_date);
            title_1 = itemView.findViewById(R.id.app_content_page_events_rv_title_1);
            title_2 = itemView.findViewById(R.id.app_content_page_events_rv_description);


        }
    }
}
