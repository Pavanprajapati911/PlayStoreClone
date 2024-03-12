package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_verysmall_withImages_rv_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface SearchResultPage_OneRow_VerySmall_interface {

    void onDetailsFetched(ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppDetailsList);
    void onFailure(DatabaseError error);

}
