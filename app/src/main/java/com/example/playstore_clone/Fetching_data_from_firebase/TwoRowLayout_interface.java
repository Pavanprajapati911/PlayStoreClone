package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recylerview_2_row_data_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface TwoRowLayout_interface {
    void onDetailsFetched(ArrayList<Child_Recylerview_2_row_data_model> arrayList);
    void onCancelled(DatabaseError error);
}
