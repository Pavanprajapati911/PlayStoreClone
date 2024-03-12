package com.example.playstore_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.playstore_clone.Adapters.Search_page_history_listview_Adapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search_activity extends AppCompatActivity {
    EditText search_bar;
    ListView search_history;

    ImageView image_to_change, backButton;

    String SearchedItemType;

    List<String> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        backButton = findViewById(R.id.search_page_backbutton);
        search_bar = findViewById(R.id.search_page_edittext);
        search_history = findViewById(R.id.search_page_history_listview);
        image_to_change = findViewById(R.id.search_page_image_to_change);

        String searchBarText = getIntent().getStringExtra("searchBarText");

        search_bar.setHint(searchBarText);

       Pattern pattern = Pattern.compile("Books",Pattern.CASE_INSENSITIVE);
       Matcher matcher = pattern.matcher(searchBarText);
       if(matcher.find()){
           SearchedItemType = "Book";
       }else {
           SearchedItemType = "App_Or_Game";
       }

        list = new ArrayList<>();
        list = loadhistory();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(list!=null){

            search_history.setAdapter( new Search_page_history_listview_Adapter(this, list, new Search_page_history_listview_Adapter.item_on_click() {
                @Override
                public void onCLick(int position) {
                    Intent intent = new Intent(Search_activity.this, Search_result_activity.class);
                    intent.putExtra("SearchedItem",list.get(position));
                    intent.putExtra("SearchedItemType",SearchedItemType);
                    startActivity(intent);

                    Savehistory(list, list.get(position), position);
                    finish();
                }
            }));

        }else {
            list = new ArrayList<>();
        }

// -------------------------------------------------------------------------------------------------------------------//

        search_bar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

            if( !search_bar.getText().toString().isEmpty() && i == EditorInfo.IME_ACTION_SEARCH){


                Intent intent = new Intent(Search_activity.this,Search_result_activity.class);
                intent.putExtra("SearchedItem",search_bar.getText().toString());
                intent.putExtra("SearchedItemType",SearchedItemType);
                startActivity(intent);

                Savehistory(list, null,0);
                finish();

            }else {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(search_bar.getWindowToken(),0);

            }

            return true;
            }
        });


        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()==0){
                    image_to_change.setImageResource(R.drawable.mike_icon);
                }else{
                    image_to_change.setImageResource(R.drawable.x_logo);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    image_to_change.setImageResource(R.drawable.x_logo);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()==0){
                    image_to_change.setImageResource(R.drawable.mike_icon);
                }
            }
        });
//--------------------------------------------------------------------------------------------------------------//
        image_to_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(search_bar.getText().toString()!=""){
                    search_bar.setText("");
                }
            }
        });

    }
//====================================================================================================================//
//====================================================================================================================//
    private void Savehistory(List<String> list, String clickedItem, int position){


        if (clickedItem!=null){
            list.remove(position);
            list.add(0,clickedItem);
        } else if (list.size() == 4) {
            list.add(0, search_bar.getText().toString());
            list.remove(list.size()-1);
        }else {
            list.add(0, search_bar.getText().toString());
        }


        SharedPreferences sharedPreferences = this.getSharedPreferences("User_Search_History",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String history = gson.toJson(list);

        editor.putString("search_history",history);
        editor.apply();
    }


    private List<String> loadhistory(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("User_Search_History",MODE_PRIVATE);

        Gson gson = new Gson();
        String history = sharedPreferences.getString("search_history","null");

        Type type = new TypeToken<List<String>>() {}.getType();

        return gson.fromJson(history, type);
    }
}