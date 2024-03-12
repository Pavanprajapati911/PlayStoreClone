package com.example.playstore_clone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playstore_clone.R;

import java.util.ArrayList;

public class App_or_game_images_recyclerview_Adapter extends RecyclerView.Adapter<App_or_game_images_recyclerview_Adapter.Viewholder> {

    Context context;

    ArrayList<Bitmap> imagelist;

    String Bottom_tab_name;

    public App_or_game_images_recyclerview_Adapter(Context context, ArrayList<Bitmap> imagelist, String Bottom_tab_name) {
        this.context = context;
        this.Bottom_tab_name = Bottom_tab_name;
        this.imagelist = imagelist;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (Bottom_tab_name!=null && Bottom_tab_name.equals("Apps Tab")){
            view = LayoutInflater.from(context).inflate(R.layout.app_imagesdatalayout,parent,false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.game_imagesdata_layout,parent,false);
        }

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

//            if (Bottom_tab_name!=null && Bottom_tab_name.equals("Apps Tab")){
//
//                ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
//                layoutParams.height = holder.imageView.getHeight();
//                int tempHeight = holder.imageView.getHeight()/2;
//                int halfOfTempHeight = tempHeight/2;
//                layoutParams.width = tempHeight+halfOfTempHeight;
//
//                holder.imageView.setLayoutParams(layoutParams);
//                holder.imageView.setImageBitmap(imagelist.get(position));
//
//            }else {
//                holder.imageView.setImageBitmap(imagelist.get(position));
//            }

        if (Bottom_tab_name!=null && Bottom_tab_name.equals("Apps Tab")){
            holder.appImageView.setImageBitmap(imagelist.get(position));
        }else {
            holder.imageView.setImageBitmap(imagelist.get(position));
        }


    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageView, appImageView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.game_imagesdata_imageview);
            appImageView = itemView.findViewById(R.id.app_imagesdata_imageview);

        }
    }

}
