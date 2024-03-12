package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_3_row_DataModel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface ThreeRowLayout_interface {
    void onFetchedDetails(ArrayList<Child_Recyclerview_3_row_DataModel> arrayList);
    void onCancelled(DatabaseError error);
}
