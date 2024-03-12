package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_3_row_DataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_3_row_getNames_datamodel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface Get_gameApp_names_3Rowinterface {
    void onFetchedNames(ArrayList<Child_Recyclerview_3_row_getNames_datamodel> names);
    void onCancelled(DatabaseError error);
}
