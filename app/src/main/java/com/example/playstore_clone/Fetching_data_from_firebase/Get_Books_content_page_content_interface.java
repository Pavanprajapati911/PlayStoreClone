package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.Books_content_page_ContentModel;
import com.google.firebase.database.DatabaseError;

public interface Get_Books_content_page_content_interface {
    void onDetailsFetched(Books_content_page_ContentModel model);
    void onCancelled(DatabaseError error);
}
