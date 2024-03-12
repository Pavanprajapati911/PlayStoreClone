package com.example.playstore_clone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Search_page_listview_datamodel;

import java.util.ArrayList;
import java.util.List;

public class Search_page_history_listview_Adapter extends BaseAdapter {
    Context context;
    List<String> array;

    item_on_click item_On_Click;

    public Search_page_history_listview_Adapter(Context context, List<String> array, item_on_click item_On_Click) {
        this.context = context;
        this.array = array;
        this.item_On_Click = item_On_Click;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public interface item_on_click{
        void onCLick(int position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.search_page_listview_layout,viewGroup,false);
        TextView textView = view.findViewById(R.id.search_page_history_data);
        textView.setText(array.get(position));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_On_Click.onCLick(position);
            }
        });

        return view;
    }
}
