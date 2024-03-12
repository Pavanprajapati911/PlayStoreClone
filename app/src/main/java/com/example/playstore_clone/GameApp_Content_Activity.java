package com.example.playstore_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.playstore_clone.Adapters.AppContentpage_TagsRv_Adapter;
import com.example.playstore_clone.Adapters.App_or_game_images_recyclerview_Adapter;
import com.example.playstore_clone.Adapters.App_or_games_page_events_rv_Adapter;
import com.example.playstore_clone.Adapters.Child_Recyclerview_1_row_small_Adapter;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Data_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.Fetching_data_from_firebase.GetEventsAndOffers_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameApp_Content_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameApp_Images_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameAppBooks_Tags_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.OneRowSmallLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameAppBooks_names_interface;
import com.example.playstore_clone.reyckerview_models.App_content_page_events_and_offers_rv_data_model;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_small_Datamodel;
import com.example.playstore_clone.reyckerview_models.GameAppContent_Datamodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class GameApp_Content_Activity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView app_or_game_images_rv,suggested_games_or_apps_rv, events_and_offers_rv, tagsRv;

    LinearLayout layout_to_hide;
    ImageView reviews_menu, logo, searchIcon, backButton;
    String contentLocation, type, freeOrPremium;
    String name, bottom_tab_name, top_tab_name;

    Button price;

    TextView Name, aboutThis, companyNAme, ratedFor, ratedFor2, ratings, size , totalDownloads, totalReviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameapp_content);

        toolbar = findViewById(R.id.app_content_data_page_toolbar);
        backButton = findViewById(R.id.gameApp_content_data_page_backButton);
        searchIcon = findViewById(R.id.gameApp_content_data_page_search_icon);
        app_or_game_images_rv = findViewById(R.id.app_content_page__game_images_recyclerview);
        suggested_games_or_apps_rv = findViewById(R.id.app_content_page_suggested_games_or_apps_recyclerview);
        events_and_offers_rv = findViewById(R.id.app_content_page__event_recyclerview);
        tagsRv = findViewById(R.id.app_content_page_tagsRv);
        reviews_menu = findViewById(R.id.app_content_page_popup_menu_icon);
        logo = findViewById(R.id.app_content_page_logo);
        Name = findViewById(R.id.app_content_page_name);
        aboutThis = findViewById(R.id.app_content_page_aboutThis);
        companyNAme = findViewById(R.id.app_content_page_companyName);
        price = findViewById(R.id.app_content_page_freeORprice);
        ratedFor = findViewById(R.id.app_content_page_ratedFor);
        ratedFor2 = findViewById(R.id.app_content_page_ratedFor2);
        ratings = findViewById(R.id.app_content_page_ratings);
        size = findViewById(R.id.app_content_page_size);
        totalDownloads = findViewById(R.id.app_content_page_totalDownloads);
        totalReviews = findViewById(R.id.app_content_page_totalReviews);
        price = findViewById(R.id.app_content_page_freeORprice);
        layout_to_hide = findViewById(R.id.gameApp_content_layout_to_Hide);

        layout_to_hide.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameApp_Content_Activity.this, Search_activity.class);
                String searchBarTexttoSend = getString(R.string.search_bar_text);
                intent.putExtra("searchBarText",searchBarTexttoSend);
                startActivity(intent);
            }
        });

        if (getIntent().getStringExtra("GameAppNameFromOffersDetailsActivity")!=null){
            name = getIntent().getStringExtra("GameAppNameFromOffersDetailsActivity");
        } else if (getIntent().getStringExtra("GameAppNameFromActivity") != null) {
            name = getIntent().getStringExtra("GameAppNameFromActivity");
        } else {
            name = getIntent().getStringExtra("GameAppName");
        }
        bottom_tab_name = getIntent().getStringExtra("Bottom_Tab");
        top_tab_name = getIntent().getStringExtra("Top_Tab");


        if (Objects.equals(bottom_tab_name, "Games Tab")){

            type = "Game";
            if(Objects.equals(top_tab_name, "Premium Tab")){
                contentLocation = "All_PremiumGames_Data";
            }else {
                contentLocation = "All_Games_Data";
            }
        }else {
                type = "App";
                contentLocation = "All_Apps_Data";
        }

        Log.d("nameisnull", "onCreate: "+contentLocation);

        GetContent(name, contentLocation, new Get_GameApp_Content_interface() {
            @Override
            public void onContentFetched(GameAppContent_Datamodel model) {

                Name.setText(model.getName());
                companyNAme.setText(model.getCompany_name());
                ratedFor.setText(model.getRated_for());
                ratedFor2.setText(model.getRated_for());
                ratings.setText(model.getRatings());
                size.setText(model.getSize());
                totalDownloads.setText(model.getTotal_downloads());
                totalReviews.setText(model.getTotal_reviews());
                aboutThis.setText(model.getAbout_this());

                if (!Objects.equals(model.getPrice(), "Free")){
                    price.setText(model.getPrice());
                    freeOrPremium = "Premium";
                }
                else {
                    freeOrPremium = "Free";
                }

                Fetching_Images_from_FirebaseStorage getimages = new Fetching_Images_from_FirebaseStorage();
                getimages.Image_to_fetch(model.getLogo(), new Fetch_image_interface() {
                    @Override
                    public void image_FetchSuccess(Bitmap bitmap) {

                        logo.setImageBitmap(bitmap);
                        //Glide.with(GameApp_Content_Activity.this).load(bitmap).into(logo);
                    }

                    @Override
                    public void image_FetchFailure(Exception exception) {
                        Log.d("imageFailure", "image_FetchFailure: "+exception);
                    }
                });

                layout_to_hide.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFailure(DatabaseError error) {
                Log.d("Failure", "onFailure: "+error);
            }
        }, new Get_GameAppBooks_Tags_interface() {
            @Override
            public void onTagsFetched(ArrayList<String> tagslist) {

                tagsRv.setLayoutManager(new LinearLayoutManager(GameApp_Content_Activity.this,LinearLayoutManager.HORIZONTAL,false));
                tagsRv.setAdapter(new AppContentpage_TagsRv_Adapter(GameApp_Content_Activity.this,tagslist));

                getSimilarApps( name,contentLocation, tagslist, new Get_GameAppBooks_names_interface() {
                    @Override
                    public void get_game_or_app_names(ArrayList<String> names) {
                        Fetching_Data_from_Database suggestedGameApps = new Fetching_Data_from_Database();
                        suggestedGameApps.GameApp_1_row_Details(names, type, freeOrPremium, 5, null, null, new OneRowSmallLayout_interface() {
                            @Override
                            public void onDetailsFetched(ArrayList<Child_Recyclerview_1_row_small_Datamodel> arrayList) {
                                ArrayList<Bitmap> logoList = new ArrayList<>();

                                for (Child_Recyclerview_1_row_small_Datamodel logoLocation : arrayList){

                                    Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                                    imagesFromFirebaseStorage.Image_to_fetch(logoLocation.getLogo(), new Fetch_image_interface() {
                                        @Override
                                        public void image_FetchSuccess(Bitmap bitmap) {
                                                 logoList.add(bitmap);

                                                 if (logoList.size()==arrayList.size()){
                                                     suggested_games_or_apps_rv.setLayoutManager(new LinearLayoutManager(GameApp_Content_Activity.this, RecyclerView.HORIZONTAL,false));
                                                     suggested_games_or_apps_rv.setAdapter(new Child_Recyclerview_1_row_small_Adapter(GameApp_Content_Activity.this, logoList,arrayList, null, new Child_Recyclerview_1_row_small_Adapter.itemOnclicklistener() {
                                                         @Override
                                                         public void onclick(int position) {
                                                             Intent intent = new Intent(GameApp_Content_Activity.this, GameApp_Content_Activity.class);
                                                             intent.putExtra("GameAppNameFromActivity",arrayList.get(position).getName());
                                                             intent.putExtra("Bottom_Tab", bottom_tab_name);
                                                             intent.putExtra("Top_Tab",top_tab_name);
                                                             Log.d("NAME", "onclick: "+arrayList.get(position).getName());
                                                             startActivity(intent);
                                                         }
                                                     }));
                                                 }
                                        }
                                        @Override
                                        public void image_FetchFailure(Exception exception) {

                                        }
                                    });
                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }, null);
                    }

                    @Override
                    public void cant_Fetch_names(DatabaseError error) {

                    }
                });


            }

            @Override
            public void onFailure(DatabaseError error) {

            }
        }, new Get_GameApp_Images_interface() {
            @Override
            public void onImagesFetched(ArrayList<Bitmap> imageList) {
                app_or_game_images_rv.setLayoutManager(new LinearLayoutManager(GameApp_Content_Activity.this,RecyclerView.HORIZONTAL,false));
                app_or_game_images_rv.setAdapter(new App_or_game_images_recyclerview_Adapter(GameApp_Content_Activity.this, imageList, bottom_tab_name));
            }

            @Override
            public void onFailure(DatabaseError error) {

            }
        });



        getEventsAndOffer(name, contentLocation, new GetEventsAndOffers_interface() {
            @Override
            public void onDetailsFetched(ArrayList<App_content_page_events_and_offers_rv_data_model> eventsOffersList) {

                events_and_offers_rv.setLayoutManager(new LinearLayoutManager(GameApp_Content_Activity.this,RecyclerView.HORIZONTAL,false));
                events_and_offers_rv.setAdapter(new App_or_games_page_events_rv_Adapter(GameApp_Content_Activity.this, eventsOffersList, new App_or_games_page_events_rv_Adapter.item_onclick_listener() {
                    @Override
                    public void onclick(int position) {
                        Intent intent = new Intent(GameApp_Content_Activity.this,Offrs_details_Activity.class);
                        intent.putExtra("offerGameAppName",name);
                        intent.putExtra("offernumber",eventsOffersList.get(position).getEvent_or_offer_number());
                        intent.putExtra("Bottom_Tab",bottom_tab_name);
                        intent.putExtra("Top_Tab",top_tab_name);
                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(DatabaseError error) {

            }
        });





        reviews_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerForContextMenu(view);
                view.showContextMenu(1,1);
                unregisterForContextMenu(view);

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.app_content_page_menu,menu);

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        new MenuInflater(this).inflate(R.menu.app_content_page_reviews_menu,menu);

    }

    public void GetContent(String name, String contentLocation, Get_GameApp_Content_interface getContent, Get_GameAppBooks_Tags_interface tagsContent, Get_GameApp_Images_interface imagesContent){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        Query query = databaseReference.child(contentLocation).child(name).orderByKey();
        Log.d("apperror", "GetContent: "+contentLocation+" "+name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                GameAppContent_Datamodel model = snapshot.getValue(GameAppContent_Datamodel.class);
                getContent.onContentFetched(model);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                getContent.onFailure(error);
            }
        });

        Query queryForTags = databaseReference.child(contentLocation).child(name).child("Tags").orderByKey();
        queryForTags.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> tagsList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    tagsList.add(dataSnapshot.getValue(String.class));
                }
                tagsContent.onTagsFetched(tagsList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                tagsContent.onFailure(error);
            }
        });

        Query queryForImages = databaseReference.child(contentLocation).child(name).child("Images").orderByKey();
        queryForImages.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> imageLocationList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    imageLocationList.add(dataSnapshot.getValue(String.class));
                }

                ArrayList<Bitmap> imageList = new ArrayList<>();
                for (String imageLocation : imageLocationList){
                    Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                    imagesFromFirebaseStorage.Image_to_fetch(imageLocation, new Fetch_image_interface() {
                        @Override
                        public void image_FetchSuccess(Bitmap bitmap) {

                            imageList.add(bitmap);
                            Log.d("RAID SHADOW LEGENDS BUG", "onDataChange: "+imageLocation+" "+imageList.size()+" "+imageLocationList.size());
                            if (imageList.size()==imageLocationList.size()){
                                imagesContent.onImagesFetched(imageList);
                            }

                        }
                        @Override
                        public void image_FetchFailure(Exception exception) {
                            Log.d("RAID SHADOW LEGENDS BUG", "on image not found: "+imageLocation+" "+imageList.size()+" "+imageLocationList.size());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                imagesContent.onFailure(error);
            }
        });


    }

    public void getEventsAndOffer(String name, String contentLocation, GetEventsAndOffers_interface eventsAndOffersInterface){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        Query query = databaseReference.child(contentLocation).child(name).child("EventsAndOffers").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<App_content_page_events_and_offers_rv_data_model> arrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    App_content_page_events_and_offers_rv_data_model model = dataSnapshot.getValue(App_content_page_events_and_offers_rv_data_model.class);
                    model.setEvent_or_offer_number(dataSnapshot.getKey());
                    arrayList.add(model);
                }
                eventsAndOffersInterface.onDetailsFetched(arrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                eventsAndOffersInterface.onFailure(error);
            }
        });


    }

    public void getSimilarApps(String name, String contentLocation, ArrayList<String> tagsForSuggestion, Get_GameAppBooks_names_interface gameOrAppNamesInterface){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");
        ArrayList<String> gamesToSuggest= new ArrayList<>();
        Query query = databaseReference.child(contentLocation).orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean tagsMatched;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    tagsMatched = false;
                    DataSnapshot getTagsSnapshot = dataSnapshot.child("Tags");

                    for (DataSnapshot tagsSnapshot : getTagsSnapshot.getChildren()){

                        for (int i = 0; i<tagsForSuggestion.size();i++){

                            if(Objects.equals(tagsForSuggestion.get(i), tagsSnapshot.getValue(String.class)) && !Objects.equals(dataSnapshot.getKey(), name)){

                                gamesToSuggest.add(dataSnapshot.getKey());
                                tagsMatched = true;
                                break;
                            }
                        }
                        if (tagsMatched){
                            break;
                        }


                    }

                }
                gameOrAppNamesInterface.get_game_or_app_names(gamesToSuggest);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}