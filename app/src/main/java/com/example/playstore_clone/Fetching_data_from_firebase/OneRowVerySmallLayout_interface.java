package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_verysmall_datamodel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface OneRowVerySmallLayout_interface {
    void onDetailsFetched(ArrayList<Child_Recyclerview_1_row_verysmall_datamodel> arrayList);
    void onCancelled(DatabaseError databaseError);
}
