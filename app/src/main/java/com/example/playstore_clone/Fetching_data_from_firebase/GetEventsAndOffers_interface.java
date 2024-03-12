package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.App_content_page_events_and_offers_rv_data_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface GetEventsAndOffers_interface {
    void onDetailsFetched(ArrayList<App_content_page_events_and_offers_rv_data_model> eventsOffersList);
    void onFailure(DatabaseError error);
}
