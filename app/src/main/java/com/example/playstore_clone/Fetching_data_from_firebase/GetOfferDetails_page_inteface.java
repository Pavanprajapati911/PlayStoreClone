package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.App_content_page_events_and_offers_rv_data_model;
import com.example.playstore_clone.reyckerview_models.Offer_details_page_GameAppDetails_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface GetOfferDetails_page_inteface {

    void onDetailsFetched(App_content_page_events_and_offers_rv_data_model model, Offer_details_page_GameAppDetails_Model modelDetails);
    void onFailure(DatabaseError error);
}
