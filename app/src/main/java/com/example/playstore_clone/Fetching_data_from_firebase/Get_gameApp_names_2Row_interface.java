package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_2_row_getNames_dataModel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface Get_gameApp_names_2Row_interface {
    void onFetchedNames(ArrayList<Child_Recyclerview_2_row_getNames_dataModel> names);
    void onCancelled(DatabaseError error);
}
