package com.example.playstore_clone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Categories_and_genres_Datamodel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_small_Datamodel;

import java.util.ArrayList;

public class Categories_and_genres_Adapter extends RecyclerView.Adapter<Categories_and_genres_Adapter.Viewholder> {
    Context context;
    ArrayList<Categories_and_genres_Datamodel> arrayList;

    public Categories_and_genres_Adapter(Context context, ArrayList<Categories_and_genres_Datamodel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_and_genres_data_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.Image.setImageResource(arrayList.get(position).logo);
        holder.Title.setText(arrayList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Title;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.categories_and_genres_data_image);
            Title = itemView.findViewById(R.id.categories_and_genres_data_title);

        }
    }
}
