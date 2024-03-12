package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_verysmall_datamodel;
import com.example.playstore_clone.reyckerview_models.Child_recyclerview_1_row_books_verysmall_data_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface OneRowBooksVerySmall_interface {
    void onDataFetched(ArrayList<Child_recyclerview_1_row_books_verysmall_data_model> arrayList);
    void onCancelled(DatabaseError error);

}
