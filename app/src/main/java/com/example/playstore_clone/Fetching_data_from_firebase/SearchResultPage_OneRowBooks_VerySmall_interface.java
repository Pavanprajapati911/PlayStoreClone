package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_book_verysmall_Datamodel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface SearchResultPage_OneRowBooks_VerySmall_interface {

    void onDetailsFetched(ArrayList<Search_result_page_1_row_book_verysmall_Datamodel> arrayList);
    void onCancelled(DatabaseError error);

}
