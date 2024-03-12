package com.example.playstore_clone.Fetching_data_from_firebase;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public interface Get_GameAppBooks_names_interface {
    void get_game_or_app_names(ArrayList<String> names);
    void cant_Fetch_names(DatabaseError error);
}
