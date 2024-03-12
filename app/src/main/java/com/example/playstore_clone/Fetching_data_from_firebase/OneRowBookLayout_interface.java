package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_books_DataModel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface OneRowBookLayout_interface {
    void onDataFetched(ArrayList<Child_Recyclerview_1_row_books_DataModel> arrayList);
    void onCancelled(DatabaseError error);

}
