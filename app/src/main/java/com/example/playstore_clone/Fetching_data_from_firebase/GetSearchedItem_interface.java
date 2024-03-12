package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_book_verysmall_Datamodel;
import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_verysmall_rv_model;
import com.google.firebase.database.DatabaseError;

public interface GetSearchedItem_interface {
    void onBookFound(Search_result_page_1_row_book_verysmall_Datamodel model);
    void onBookNotFound();

    void onGameAppFound(Search_result_page_1_row_verysmall_rv_model model, String type);

    void onGameAppNotFound();

    void onFailure(DatabaseError error);
}
