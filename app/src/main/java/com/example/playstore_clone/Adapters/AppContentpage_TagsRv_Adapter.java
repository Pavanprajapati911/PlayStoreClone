package com.example.playstore_clone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playstore_clone.R;

import java.util.ArrayList;

public class AppContentpage_TagsRv_Adapter extends RecyclerView.Adapter<AppContentpage_TagsRv_Adapter.Viewholder> {
    Context context;
    ArrayList<String> arrayList;

    public AppContentpage_TagsRv_Adapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_content_page_tagsrv_datalayout, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.tagsText.setText(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        Button tagsText;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            tagsText = itemView.findViewById(R.id.app_content_page_tags);
        }
    }

}
