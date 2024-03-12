package com.example.playstore_clone.Fetching_data_from_firebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Fetching_Images_from_FirebaseStorage {

    Bitmap bitmap;
    public Bitmap Image_to_fetch(String image_location, Fetch_image_interface image_interface){

        StorageReference reference = FirebaseStorage.getInstance().getReference();
        StorageReference path = reference.child(image_location);

        final long ONE_MEGABYTE = 1024 * 2024;
        path.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                image_interface.image_FetchSuccess(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                image_interface.image_FetchFailure(e);
            }
        });

        return bitmap;
    }

}
