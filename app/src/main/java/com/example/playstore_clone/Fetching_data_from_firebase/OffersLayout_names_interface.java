package com.example.playstore_clone.Fetching_data_from_firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface OffersLayout_names_interface {
    void onNamesFetched(ArrayList<String> names);
    void onNamesCantFetched(DatabaseError error);
}
