package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_event_data_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface EventLayout_interface {

    void onDataFetched(ArrayList<Child_Recyclerview_event_data_model> arrayList);
    void onDataError(DatabaseError error);
}
