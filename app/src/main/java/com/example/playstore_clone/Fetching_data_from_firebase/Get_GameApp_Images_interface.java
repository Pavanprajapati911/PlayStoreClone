package com.example.playstore_clone.Fetching_data_from_firebase;

import android.graphics.Bitmap;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface Get_GameApp_Images_interface {
    void onImagesFetched(ArrayList<Bitmap> imageList);
    void onFailure(DatabaseError error);
}
