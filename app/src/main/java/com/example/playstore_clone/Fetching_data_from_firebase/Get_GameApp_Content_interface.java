package com.example.playstore_clone.Fetching_data_from_firebase;

import com.example.playstore_clone.reyckerview_models.GameAppContent_Datamodel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface Get_GameApp_Content_interface {
    void onContentFetched(GameAppContent_Datamodel model);
    void onFailure(DatabaseError error);
}
