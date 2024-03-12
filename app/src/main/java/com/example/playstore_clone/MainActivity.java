package com.example.playstore_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView Notification_logo,Profile_page,Profile_dialog_cancel_button;

    Dialog dialog;

    RelativeLayout search_bar;
    TextView searchBar_text;

    String searchBarTextTOSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_bar = findViewById(R.id.home_page_search_bar);
        bottomNavigationView = findViewById(R.id.bottom_navigationview);
        Profile_page = findViewById(R.id.profile_page);
        Notification_logo = findViewById(R.id.notification_logo);
        searchBar_text = findViewById(R.id.main_page_searchBar_text);

        searchBarTextTOSend = getString(R.string.search_bar_text);
        //---------------------------------------------------- SEARCH_BAR -------------------------------------------------------//

        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Search_activity.class);
                intent.putExtra("searchBarText", searchBarTextTOSend);
                startActivity(intent);
            }
        });


        //---------------------------------------------------- Profile page Dialog -------------------------------------------------------//

        dialog = new Dialog(MainActivity.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.profile_dialog_layout);
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        Profile_dialog_cancel_button = dialog.findViewById(R.id.profile_dialog_cancel_button);

        Profile_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

            }
        });



        Profile_dialog_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        //----------------------------------------------------- NOtification Activity---------------------------------------------------//
        Notification_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Notification_page.class);
                startActivity(intent);
            }
        });

        //-------------------------------------------------------- Bottom_navigation --------------------------------------------------//

        frag_transaction(new Games_fragment(),0);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.bottom_games){
                    searchBar_text.setText(R.string.search_bar_text);
                    searchBarTextTOSend = getString(R.string.search_bar_text);
                    frag_transaction(new Games_fragment(),1);

                } else if (item.getItemId()==R.id.bottom_apps) {
                    searchBar_text.setText(R.string.search_bar_text);
                    searchBarTextTOSend = getString(R.string.search_bar_text);
                    frag_transaction(new Apps_fragment(),1);

                } else if (item.getItemId()==R.id.bottom_offers) {
                    searchBar_text.setText(R.string.search_bar_text);
                    searchBarTextTOSend = getString(R.string.search_bar_text);
                    frag_transaction(new Offers_fragment(),1);

                }else {
                    searchBarTextTOSend = "Search Books";
                    searchBar_text.setText(searchBarTextTOSend);

                    frag_transaction(new Books_fragment(),1);

                }
                return true;
            }
        });



    }
    public void frag_transaction(Fragment fragment,int flag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(flag==0) {
            fragmentTransaction.add(R.id.homepage_framelayout, fragment);
        }else{
            fragmentTransaction.replace(R.id.homepage_framelayout, fragment);
        }
        fragmentTransaction.commit();
    }
}