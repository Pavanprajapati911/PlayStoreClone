package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_data_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface OneRowLayout_interface {
    public void onDetailsFetched(ArrayList<Child_Recyclerview_1_row_data_model> arrayList);
    void onDatabaseError(DatabaseError error);
}
