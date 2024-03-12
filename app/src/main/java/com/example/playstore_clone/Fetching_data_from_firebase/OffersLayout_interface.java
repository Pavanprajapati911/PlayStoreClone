package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_offer_datamodel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface OffersLayout_interface {

    void onDataFetched(ArrayList<Child_Recyclerview_offer_datamodel> arrayList);
    void onCancelled(DatabaseError error);

}
