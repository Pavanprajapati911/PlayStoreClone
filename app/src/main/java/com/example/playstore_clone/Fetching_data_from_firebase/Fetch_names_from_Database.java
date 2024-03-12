package com.example.playstore_clone.Fetching_data_from_firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_2_row_getNames_dataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_3_row_getNames_datamodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Fetch_names_from_Database {

    public void get_Game_or_App_Names(int position, String bottom_Tab_Name, String top_Tab_Name, Get_GameAppBooks_names_interface get_names){
        ArrayList<String> names = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        if (Objects.equals(top_Tab_Name, "Top charts Tab") || Objects.equals(top_Tab_Name, "Top selling Tab") || Objects.equals(top_Tab_Name, "New releases Tab") || Objects.equals(top_Tab_Name, "Top free Tab")){

            Query query = databaseReference.child(bottom_Tab_Name).child(top_Tab_Name).orderByKey();

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        names.add(dataSnapshot.getValue(String.class));
                    }
                    get_names.get_game_or_app_names(names);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("top charts", String.valueOf(error));
                }
            });

        }else {
            Query query = databaseReference.child(bottom_Tab_Name).child(top_Tab_Name).child("layout "+(position+1)).orderByKey();

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        names.add(dataSnapshot.getValue(String.class));
                    }

                    get_names.get_game_or_app_names(names);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    get_names.cant_Fetch_names(error);
                }
            });
        }

    }

    public void get_GAmeApp_namesFor3Row(int position, String bottom_Tab_name, String top_Tab_Name, Get_gameApp_names_3Rowinterface gameAppNames3Rowinterface){
        ArrayList<Child_Recyclerview_3_row_getNames_datamodel> getNames = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        Query query = databaseReference.child(bottom_Tab_name).child(top_Tab_Name).child("layout "+(position+1)).orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Child_Recyclerview_3_row_getNames_datamodel model = dataSnapshot.getValue(Child_Recyclerview_3_row_getNames_datamodel.class);
                    getNames.add(model);
                }
                gameAppNames3Rowinterface.onFetchedNames(getNames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("database error", String.valueOf(error));
            }
        });

    }

    public void get_GAmeApp_namesFor2Row(int position, String bottom_Tab_name, String top_Tab_Name, Get_gameApp_names_2Row_interface gameAppNames2Rowinterface){
        ArrayList<Child_Recyclerview_2_row_getNames_dataModel> getNames = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        Query query = databaseReference.child(bottom_Tab_name).child(top_Tab_Name).child("layout "+(position+1)).orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Child_Recyclerview_2_row_getNames_dataModel model = dataSnapshot.getValue(Child_Recyclerview_2_row_getNames_dataModel.class);
                    getNames.add(model);
                }
                gameAppNames2Rowinterface.onFetchedNames(getNames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("database error", String.valueOf(error));
            }
        });

    }

}
