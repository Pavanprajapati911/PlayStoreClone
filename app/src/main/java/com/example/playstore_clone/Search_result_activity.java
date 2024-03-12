package com.example.playstore_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.playstore_clone.Adapters.Search_result_page_1_row_verysmall_rv_adapter;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Data_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.Fetching_data_from_firebase.GetSearchedItem_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameAppBooks_Tags_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameAppBooks_names_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.SearchResultPage_OneRowBooks_VerySmall_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.SearchResultPage_OneRow_VerySmall_interface;
import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_book_verysmall_Datamodel;
import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_verysmall_rv_model;
import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_verysmall_withImages_rv_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search_result_activity extends AppCompatActivity {

    RecyclerView search_list;

    RelativeLayout loadingViewBackground;

    LottieAnimationView loadingView;

    TextView searchedItemTxt;

    String SearchedItemType, searchedItem;

    ImageView searchIcon, backButton;

    int imageFetchCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        search_list = findViewById(R.id.searchresult_page_recyclerview);
        searchedItemTxt = findViewById(R.id.search_result_page_searched_value);
        searchIcon = findViewById(R.id.search_result_page_searchicon);
        loadingView = findViewById(R.id.loading_animation_SearchResult);
        backButton = findViewById(R.id.search_result_page_backbutton);
        loadingViewBackground = findViewById(R.id.loading_animation_Background_SearchResult);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchBarTextTOSend = getString(R.string.search_bar_text);
                Intent intent = new Intent(Search_result_activity.this, Search_activity.class);
                intent.putExtra("searchBarText", searchBarTextTOSend);
                startActivity(intent);
            }
        });

        SearchedItemType = getIntent().getStringExtra("SearchedItemType");

        searchedItemTxt.setText(getIntent().getStringExtra("SearchedItem"));
        searchedItem = getIntent().getStringExtra("SearchedItem");

        search_list.setLayoutManager(new LinearLayoutManager(this));


        FindSearchedItem(searchedItem, SearchedItemType, new GetSearchedItem_interface() {
            @Override
            public void onBookFound(Search_result_page_1_row_book_verysmall_Datamodel model) {
                FindSimilarItems("All_Books_Data", true, model.getName(), new SearchResultPage_OneRowBooks_VerySmall_interface() {
                    @Override
                    public void onDetailsFetched(ArrayList<Search_result_page_1_row_book_verysmall_Datamodel> arrayList) {
                        Map<String, Bitmap> mapLogo = new HashMap<>();

                        for (Search_result_page_1_row_book_verysmall_Datamodel logo : arrayList){

                            Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                            imagesFromFirebaseStorage.Image_to_fetch(logo.getImage(), new Fetch_image_interface() {
                                @Override
                                public void image_FetchSuccess(Bitmap bitmap) {

                                    mapLogo.put(logo.getImage(), bitmap);

                                    if (mapLogo.size()==arrayList.size()){
                                        ArrayList<Bitmap> logoList = new ArrayList<>();

                                        for (Search_result_page_1_row_book_verysmall_Datamodel model : arrayList){
                                            logoList.add(mapLogo.get(model.getImage()));

                                        }

                                        search_list.setAdapter(new Search_result_page_1_row_verysmall_rv_adapter(Search_result_activity.this ,"Book",true, logoList,null, arrayList, new Search_result_page_1_row_verysmall_rv_adapter.onBookCLick() {
                                            @Override
                                            public void onClick(int position) {
                                                Intent intent = new Intent(Search_result_activity.this, Books_Content_Activity.class);
                                                intent.putExtra("BookName", arrayList.get(position).getName());
                                                startActivity(intent);

                                            }
                                        }, null));

                                        finishLoading();

                                    }
                                }

                                @Override
                                public void image_FetchFailure(Exception exception) {
                                    Log.d("SEARCH", "onFailure: "+exception);
                                }
                            });

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.d("SEARCH", "onFailure: "+error);
                    }
                }, null);
            }

            @Override
            public void onBookNotFound() {

                    Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                    data_from_database.WhenSearchedBookNotFound(new SearchResultPage_OneRowBooks_VerySmall_interface() {
                        @Override
                        public void onDetailsFetched(ArrayList<Search_result_page_1_row_book_verysmall_Datamodel> booklist) {

                            Map<String, Bitmap> mapLogo = new HashMap<>();

                            for (Search_result_page_1_row_book_verysmall_Datamodel logo : booklist){

                                Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                                imagesFromFirebaseStorage.Image_to_fetch(logo.getImage(), new Fetch_image_interface() {
                                    @Override
                                    public void image_FetchSuccess(Bitmap bitmap) {

                                        mapLogo.put(logo.getImage(), bitmap);

                                        if (mapLogo.size()==booklist.size()){
                                            ArrayList<Bitmap> logoList = new ArrayList<>();

                                            for (Search_result_page_1_row_book_verysmall_Datamodel model : booklist){

                                                logoList.add(mapLogo.get(model.getImage()));

                                            }

                                            search_list.setAdapter(new Search_result_page_1_row_verysmall_rv_adapter(Search_result_activity.this ,"Book",false, logoList,null, booklist, new Search_result_page_1_row_verysmall_rv_adapter.onBookCLick() {
                                                @Override
                                                public void onClick(int position) {
                                                    Intent intent = new Intent(Search_result_activity.this, Books_Content_Activity.class);
                                                    intent.putExtra("BookName", booklist.get(position).getName());
                                                    startActivity(intent);

                                                }
                                            }, null));

                                            finishLoading();

                                        }
                                    }

                                    @Override
                                    public void image_FetchFailure(Exception exception) {
                                        Log.d("FIXING SEARCH", "image_FetchFailure: "+exception);
                                    }
                                });

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            Log.d("SEARCH", "onCancelled: "+error);
                        }
                    });

            }

            @Override
            public void onGameAppFound(Search_result_page_1_row_verysmall_rv_model model, String type) {

                if (Objects.equals(type, "Game")){
                    Log.d("SEARCH BUG FIXING", "Game: ");
                    FindSimilarItems("All_Games_Data", true, model.getName(), null, new SearchResultPage_OneRow_VerySmall_interface() {
                        @Override
                        public void onDetailsFetched(ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppDetailsList) {
                            Log.d("SEARCH BUG FIXING", "Game details fetched: ");
                            Map<String, Bitmap> logoMap = new HashMap<>();

                            for (Search_result_page_1_row_verysmall_withImages_rv_model logo : gameAppDetailsList) {
                                final String currentLogoName = logo.getLogo();

                                Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                                imagesFromFirebaseStorage.Image_to_fetch(currentLogoName, new Fetch_image_interface() {
                                    @Override
                                    public void image_FetchSuccess(Bitmap bitmap) {

                                        logoMap.put(currentLogoName, bitmap);

                                        imageFetchCounter++;


                                        if (imageFetchCounter == gameAppDetailsList.size()) {

                                            ArrayList<Bitmap> logoList = new ArrayList<>();
                                            for (Search_result_page_1_row_verysmall_withImages_rv_model gameApp : gameAppDetailsList) {
                                                logoList.add(logoMap.get(gameApp.getLogo()));
                                            }

                                            search_list.setAdapter(new Search_result_page_1_row_verysmall_rv_adapter(Search_result_activity.this, "Game", true, logoList, gameAppDetailsList, null, null, new Search_result_page_1_row_verysmall_rv_adapter.onGameAppClick() {
                                                @Override
                                                public void onClick(int position) {
                                                    Intent intent = new Intent(Search_result_activity.this, GameApp_Content_Activity.class);
                                                    intent.putExtra("GameAppName", gameAppDetailsList.get(position).getName());
                                                    intent.putExtra("Bottom_Tab", "Games Tab");
                                                    intent.putExtra("Top_Tab", "no value");
                                                    startActivity(intent);
                                                }
                                            }));

                                            finishLoading();
                                        }
                                    }

                                    @Override
                                    public void image_FetchFailure(Exception exception) {
                                        Log.d("SEARCH", "image_FetchFailure: "+exception);
                                    }
                                });
                            }


                        }

                        @Override
                        public void onFailure(DatabaseError error) {
                            Log.d("SEARCH", "onFailure: "+error);
                        }
                    });
                }else if (Objects.equals(type, "Premium Game")){

                    FindSimilarItems("All_PremiumGames_Data", true, model.getName(), null, new SearchResultPage_OneRow_VerySmall_interface() {
                        @Override
                        public void onDetailsFetched(ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppDetailsList) {
                            Map<String,Bitmap> logoMap = new HashMap<>();


                            for (Search_result_page_1_row_verysmall_withImages_rv_model logolocation : gameAppDetailsList){

                                Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                                imagesFromFirebaseStorage.Image_to_fetch(logolocation.getLogo(), new Fetch_image_interface() {
                                    @Override
                                    public void image_FetchSuccess(Bitmap bitmap) {

                                        logoMap.put(logolocation.getLogo(), bitmap);



                                        if (logoMap.size()==gameAppDetailsList.size()){
                                            ArrayList<Bitmap> logoList = new ArrayList<>();

                                            for (Search_result_page_1_row_verysmall_withImages_rv_model model : gameAppDetailsList){
                                                logoList.add(logoMap.get(model.getLogo()));
                                            }
                                            search_list.setAdapter(new Search_result_page_1_row_verysmall_rv_adapter(Search_result_activity.this,"Game",true, logoList,gameAppDetailsList, null, null, new Search_result_page_1_row_verysmall_rv_adapter.onGameAppClick() {
                                                @Override
                                                public void onClick(int position) {

                                                    Intent intent = new Intent(Search_result_activity.this, GameApp_Content_Activity.class);
                                                    intent.putExtra("GameAppName",gameAppDetailsList.get(position).getName());
                                                    intent.putExtra("Bottom_Tab", "Games Tab");
                                                    intent.putExtra("Top_Tab", "Premium Tab");
                                                    startActivity(intent);

                                                }
                                            }));

                                            finishLoading();
                                        }
                                    }

                                    @Override
                                    public void image_FetchFailure(Exception exception) {
                                        Log.d("SEARCH", "image_FetchFailure: "+exception);
                                    }
                                });

                            }

                        }

                        @Override
                        public void onFailure(DatabaseError error) {
                            Log.d("SEARCH", "onFailure: "+error);
                        }
                    });
                }else {

                    FindSimilarItems("All_Apps_Data", true, model.getName(), null, new SearchResultPage_OneRow_VerySmall_interface() {
                        @Override
                        public void onDetailsFetched(ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppDetailsList) {
                            Map<String, Bitmap> maplogo = new HashMap<>();

                            for (Search_result_page_1_row_verysmall_withImages_rv_model logoLocation : gameAppDetailsList){

                                Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                                imagesFromFirebaseStorage.Image_to_fetch(logoLocation.getLogo(), new Fetch_image_interface() {
                                    @Override
                                    public void image_FetchSuccess(Bitmap bitmap) {


                                        maplogo.put(logoLocation.getLogo(), bitmap);



                                        if (maplogo.size()==gameAppDetailsList.size()){
                                            ArrayList<Bitmap> logoList = new ArrayList<>();

                                            for (Search_result_page_1_row_verysmall_withImages_rv_model model : gameAppDetailsList){
                                                logoList.add(maplogo.get(model.getLogo()));
                                            }

                                            search_list.setAdapter(new Search_result_page_1_row_verysmall_rv_adapter(Search_result_activity.this,"App",true, logoList,gameAppDetailsList, null, null, new Search_result_page_1_row_verysmall_rv_adapter.onGameAppClick() {
                                                @Override
                                                public void onClick(int position) {
                                                    Intent intent = new Intent(Search_result_activity.this, GameApp_Content_Activity.class);
                                                    intent.putExtra("GameAppName",gameAppDetailsList.get(position).getName());
                                                    intent.putExtra("Bottom_Tab", "Apps Tab");
                                                    intent.putExtra("Top_Tab", "no value");
                                                    startActivity(intent);
                                                }
                                            }));
                                            finishLoading();

                                        }
                                    }

                                    @Override
                                    public void image_FetchFailure(Exception exception) {
                                        Log.d("SEARCH", "onFailure: "+exception);
                                    }
                                });

                            }

                        }

                        @Override
                        public void onFailure(DatabaseError error) {
                            Log.d("SEARCH", "onFailure: "+error);
                        }
                    });
                }

            }

            @Override
            public void onGameAppNotFound() {

                Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                data_from_database.WhenSearchedGameAppNotFound(new SearchResultPage_OneRow_VerySmall_interface() {
                    @Override
                    public void onDetailsFetched(ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppDetailsList) {
                        Map<String,Bitmap> maplogo = new HashMap<>();

                        for (Search_result_page_1_row_verysmall_withImages_rv_model logoLocation : gameAppDetailsList){

                            Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                            imagesFromFirebaseStorage.Image_to_fetch(logoLocation.getLogo(), new Fetch_image_interface() {
                                @Override
                                public void image_FetchSuccess(Bitmap bitmap) {

                                    maplogo.put(logoLocation.getLogo(), bitmap);



                                    if (maplogo.size() == gameAppDetailsList.size()){
                                        ArrayList<Bitmap> logoList = new ArrayList<>();

                                        for (Search_result_page_1_row_verysmall_withImages_rv_model model : gameAppDetailsList){
                                            logoList.add(maplogo.get(model.getLogo()));
                                        }
                                        search_list.setAdapter(new Search_result_page_1_row_verysmall_rv_adapter(Search_result_activity.this, null,false, logoList,gameAppDetailsList, null, null, new Search_result_page_1_row_verysmall_rv_adapter.onGameAppClick() {
                                            @Override
                                            public void onClick(int position) {
                                                Intent intent = new Intent(Search_result_activity.this, GameApp_Content_Activity.class);
                                                intent.putExtra("GameAppName",gameAppDetailsList.get(position).getName());

                                                if (Objects.equals(gameAppDetailsList.get(position).getLocation(), "All_Games_Data")){
                                                    intent.putExtra("Bottom_Tab", "Games Tab");
                                                    intent.putExtra("Top_Tab", "no value");
                                                } else if (Objects.equals(gameAppDetailsList.get(position).getLocation(), "All_PremiumGames_Data")) {
                                                    intent.putExtra("Bottom_Tab", "Games Tab");
                                                    intent.putExtra("Top_Tab", "Premium Tab");
                                                }else {
                                                    intent.putExtra("Bottom_Tab", "Apps Tab");
                                                    intent.putExtra("Top_Tab", "no value");
                                                }

                                                startActivity(intent);

                                            }
                                        }));

                                        finishLoading();
                                    }




                                }

                                @Override
                                public void image_FetchFailure(Exception exception) {
                                    Log.d("SEARCH", "onFailure: "+exception);
                                }
                            });

                        }

                    }

                    @Override
                    public void onFailure(DatabaseError error) {
                        Log.d("SEARCH", "onFailure: "+error);
                    }
                });

            }

            @Override
            public void onFailure(DatabaseError error) {
                Log.d("SEARCH", "onFailure: "+error);
            }
        });

//---------------------------------------------------------------------------------------------------------------------------------//



    }
//=====================================================================================================================================//

    public void finishLoading(){
        loadingView.cancelAnimation();
        loadingView.setVisibility(View.GONE);
        loadingViewBackground.setVisibility(View.GONE);
    }

    public void FindSearchedItem(String searchedItem, String searchedItemType, GetSearchedItem_interface getSearchedItemInterface){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");




        if (Objects.equals(searchedItemType, "Book")){
            Query query = databaseReference.child("All_Books_Data").orderByKey();
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean itemFound = false;
                    Search_result_page_1_row_book_verysmall_Datamodel model;

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        Pattern pattern = Pattern.compile(searchedItem,Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(Objects.requireNonNull(dataSnapshot.getKey()));

                        if (matcher.find()){

                            model = dataSnapshot.getValue(Search_result_page_1_row_book_verysmall_Datamodel.class);
                            getSearchedItemInterface.onBookFound(model);
                            itemFound = true;
                            break;
                        }
                    }

                    if (!itemFound){
                        getSearchedItemInterface.onBookNotFound();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    getSearchedItemInterface.onFailure(error);
                }
            });

        }else {
            Query getGame = databaseReference.child("All_Games_Data").orderByKey();
            getGame.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Search_result_page_1_row_verysmall_rv_model gameModel;
                    boolean gameFound = false;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Pattern pattern = Pattern.compile(searchedItem,Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(Objects.requireNonNull(dataSnapshot.getKey()));
                        if (matcher.find()){

                            gameModel = dataSnapshot.getValue(Search_result_page_1_row_verysmall_rv_model.class);
                            getSearchedItemInterface.onGameAppFound(gameModel, "Game");
                            gameFound = true;
                            break;
                        }
                    }

                    if (!gameFound){

                        Query getPremiumGame = databaseReference.child("All_PremiumGames_Data").orderByKey();
                        getPremiumGame.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                boolean premiumGameFound = false;
                                Search_result_page_1_row_verysmall_rv_model premiumGameModel;
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                                    Pattern pattern = Pattern.compile(searchedItem, Pattern.CASE_INSENSITIVE);
                                    Matcher matcher = pattern.matcher(Objects.requireNonNull(dataSnapshot.getKey()));

                                    if (matcher.find()){
                                        premiumGameModel = dataSnapshot.getValue(Search_result_page_1_row_verysmall_rv_model.class);
                                        getSearchedItemInterface.onGameAppFound(premiumGameModel, "Premium Game");
                                        premiumGameFound = true;
                                        break;
                                    }

                                }

                                if (!premiumGameFound){

                                    Query getApp = databaseReference.child("All_Apps_Data").orderByKey();
                                    getApp.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            boolean appFound = false;
                                            Search_result_page_1_row_verysmall_rv_model appModel;
                                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                                                Pattern pattern = Pattern.compile(searchedItem,Pattern.CASE_INSENSITIVE);
                                                Matcher matcher = pattern.matcher(Objects.requireNonNull(dataSnapshot.getKey()));

                                                if (matcher.find()){

                                                    appModel = dataSnapshot.getValue(Search_result_page_1_row_verysmall_rv_model.class);
                                                    getSearchedItemInterface.onGameAppFound(appModel, "App");
                                                    appFound = true;
                                                    break;
                                                }
                                            }
                                            if (!appFound){
                                                getSearchedItemInterface.onGameAppNotFound();
                                            }


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                                getSearchedItemInterface.onFailure(error);
                                        }
                                    });


                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                getSearchedItemInterface.onFailure(error);
                            }
                        });

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                        getSearchedItemInterface.onFailure(error);
                }
            });



        }


    }

    public void FindSimilarItems(String contentLocation, boolean itemFound, String itemName, SearchResultPage_OneRowBooks_VerySmall_interface booksVerySmallInterface, SearchResultPage_OneRow_VerySmall_interface gameAppVerySmallInterface){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        if (itemFound){

            GetTags(contentLocation, itemName, new Get_GameAppBooks_Tags_interface() {
                @Override
                public void onTagsFetched(ArrayList<String> tagslist) {

                    GetNames(contentLocation, itemName, tagslist, new Get_GameAppBooks_names_interface() {
                        @Override
                        public void get_game_or_app_names(ArrayList<String> names) {

                            Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();

                            data_from_database.SearchResultPageItemsDetails(contentLocation, names, new SearchResultPage_OneRowBooks_VerySmall_interface() {
                                @Override
                                public void onDetailsFetched(ArrayList<Search_result_page_1_row_book_verysmall_Datamodel> arrayList) {
                                    booksVerySmallInterface.onDetailsFetched(arrayList);
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    Log.d("SEARCH", "oncancelled: " + error);
                                }
                            }, new SearchResultPage_OneRow_VerySmall_interface() {
                                @Override
                                public void onDetailsFetched(ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppDetailsList) {

                                    gameAppVerySmallInterface.onDetailsFetched(gameAppDetailsList);
                                }

                                @Override
                                public void onFailure(DatabaseError error) {
                                    Log.d("SEARCH", "onFailure: "+error);
                                }
                            });

                        }

                        @Override
                        public void cant_Fetch_names(DatabaseError error) {
                            Log.d("SEARCH", "cant_Fetch_names: "+error);
                        }
                    });

                }

                @Override
                public void onFailure(DatabaseError error) {

                }
            });

        }

    }

    public void GetTags(String contentLocation, String itemName, Get_GameAppBooks_Tags_interface getGameAppBooksTagsInterface){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        ArrayList<String> tags = new ArrayList<>();

        Query query = databaseReference.child(contentLocation).child(itemName).child("Tags").orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    tags.add(dataSnapshot.getValue(String.class));

                }
                getGameAppBooksTagsInterface.onTagsFetched(tags);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("SEARCH", "onCancelled: "+error);
            }
        });


    }

    public void GetNames(String contentLocation, String itemName, ArrayList<String> tagslist, Get_GameAppBooks_names_interface getGameAppBooksNamesInterface){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");
        ArrayList<String> similarItemNames = new ArrayList<>();

        Query query = databaseReference.child(contentLocation).orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                similarItemNames.add(itemName);
                boolean tagsMatched;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    tagsMatched = false;
                    DataSnapshot getTagsSnapshot = dataSnapshot.child("Tags");

                    for (DataSnapshot tagsSnapshot : getTagsSnapshot.getChildren()){

                        for (int i = 0; i<tagslist.size();i++){

                            if(Objects.equals(tagslist.get(i), tagsSnapshot.getValue(String.class)) && !Objects.equals(dataSnapshot.getKey(), itemName)){

                                similarItemNames.add(dataSnapshot.getKey());
                                tagsMatched = true;
                                break;
                            }
                        }
                        if (tagsMatched){
                            break;
                        }


                    }

                }
                getGameAppBooksNamesInterface.get_game_or_app_names(similarItemNames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("SEARCH", "onCancelled: "+error);
            }
        });


    }



}