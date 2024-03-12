package com.example.playstore_clone.Fetching_data_from_firebase;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface Get_GameAppBooks_Tags_interface {
    void onTagsFetched(ArrayList<String> tagslist);
    void onFailure(DatabaseError error);
}
