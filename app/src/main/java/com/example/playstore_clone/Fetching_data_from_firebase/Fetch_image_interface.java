package com.example.playstore_clone.Fetching_data_from_firebase;

import android.graphics.Bitmap;

import com.google.firebase.storage.StorageException;

public interface Fetch_image_interface {
    void image_FetchSuccess(Bitmap bitmap);
    void image_FetchFailure(Exception exception);

}
