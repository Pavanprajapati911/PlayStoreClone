package com.example.playstore_clone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class Notification_page extends AppCompatActivity {

    Toolbar toolbar;
    ImageView backButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_page);
        toolbar = findViewById(R.id.notificationpage_toolbar);
        backButton = findViewById(R.id.notification_page_backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");




    }
}