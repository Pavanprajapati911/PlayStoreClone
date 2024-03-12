package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Parent_rv_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface MainLayout_interface {
    void onDataFetched(ArrayList<Parent_rv_model> For_you_Tab_Data);
    void onDataError(DatabaseError error);
}
