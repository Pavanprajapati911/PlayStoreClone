package com.example.playstore_clone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_event_data_model;

import java.util.ArrayList;

public class Child_Recyclerview_event_Adapter extends RecyclerView.Adapter<Child_Recyclerview_event_Adapter.viewholder> {

    Context context;
    ArrayList<Child_Recyclerview_event_data_model> child_rv_array;
    itemOnClick itemonclick;

    public Child_Recyclerview_event_Adapter(Context context, ArrayList<Child_Recyclerview_event_data_model> child_rv_array, itemOnClick itemonclick){
        this.context=context;
        this.child_rv_array=child_rv_array;
        this.itemonclick = itemonclick;


    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.child_recyclerview_datalayout,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {

        Fetching_Images_from_FirebaseStorage images_from_firebaseStorage = new Fetching_Images_from_FirebaseStorage();
        Bitmap event_image_bitmap = images_from_firebaseStorage.Image_to_fetch(child_rv_array.get(position).getEvent_or_offer_image(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.event_background.setImageBitmap(bitmap);
                // Glide.with(context).load(bitmap).into(holder.event_background);
            }

            @Override
            public void image_FetchFailure(Exception exception) {
                Log.d("TAG", String.valueOf(exception));
            }
        });

        Bitmap game_logo_bitmap = images_from_firebaseStorage.Image_to_fetch(child_rv_array.get(position).getLogo(), new Fetch_image_interface() {
            @Override
            public void image_FetchSuccess(Bitmap bitmap) {
                holder.event_game_logo.setImageBitmap(bitmap);
                //Glide.with(context).load(bitmap).into(holder.event_game_logo);
            }

            @Override
            public void image_FetchFailure(Exception exception) {
                Log.d("TAG", String.valueOf(exception));
            }
        });

        holder.event_game_logo.setImageBitmap(game_logo_bitmap);
        holder.event_background.setImageBitmap(event_image_bitmap);
        holder.event_title.setText(child_rv_array.get(position).getEvent_or_offer_title());
        holder.event_description.setText(child_rv_array.get(position).getEvent_or_offer_description());
        holder.event_game_name.setText(child_rv_array.get(position).getName());
        holder.event_game_companyname.setText(child_rv_array.get(position).getCompany_name());
        holder.event_game_ratings.setText(child_rv_array.get(position).getRatings());

        if (child_rv_array.get(position).getEvent_gradient_color()!=null){
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,new int[] {Color.parseColor("#0AFFFFFF"),Color.parseColor(child_rv_array.get(position).getEvent_gradient_color()), Color.parseColor(child_rv_array.get(position).getEvent_gradient_color())});
            holder.event_Gradient_color.setBackground(gradientDrawable);
        }


        holder.navigate_to_appContentPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemonclick.onClick(position,R.id.child_recyclerview__navigate_to_app_content_page);
            }
        });

        holder.navigate_to_eventDetailsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemonclick.onClick(position, R.id.child_Recyclerview_navigate_to_event_details_page);
            }
        });

    }

    @Override
    public int getItemCount() {
        return child_rv_array.size();
    }

    public interface itemOnClick{
        void onClick(int position, int id);
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView event_background,event_game_logo;
        TextView event_title,event_description,event_game_name,event_game_companyname, event_game_ratings;

        RelativeLayout event_Gradient_color;
        LinearLayout navigate_to_appContentPage;

        CardView navigate_to_eventDetailsPage;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            event_background = itemView.findViewById(R.id.child_rv_event_background);
            event_game_logo = itemView.findViewById(R.id.child_rv_event_game_logo);
            event_title = itemView.findViewById(R.id.child_rv_event_title);
            event_description = itemView.findViewById(R.id.child_rv_event_description);
            event_game_name = itemView.findViewById(R.id.child_rv_event_game_name);
            event_game_companyname = itemView.findViewById(R.id.child_rv_event_game_companyname);
            event_game_ratings = itemView.findViewById(R.id.child_rv_event_game_ratings);
            navigate_to_appContentPage = itemView.findViewById(R.id.child_recyclerview__navigate_to_app_content_page);
            navigate_to_eventDetailsPage = itemView.findViewById(R.id.child_Recyclerview_navigate_to_event_details_page);
            event_Gradient_color = itemView.findViewById(R.id.child_rv_event_gradientBackground);


        }
    }
}
