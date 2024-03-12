package com.example.playstore_clone.Fetching_data_from_firebase;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_big_DataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_books_DataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_data_model;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_small_Datamodel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_verysmall_datamodel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_2_row_getNames_dataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_3_row_DataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_3_row_getNames_datamodel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_event_data_model;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_offer_datamodel;
import com.example.playstore_clone.reyckerview_models.Child_Recylerview_2_row_data_model;
import com.example.playstore_clone.reyckerview_models.Child_recyclerview_1_row_books_verysmall_data_model;
import com.example.playstore_clone.reyckerview_models.Parent_rv_model;
import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_book_verysmall_Datamodel;
import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_verysmall_rv_model;
import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_verysmall_withImages_rv_model;
import com.example.playstore_clone.reyckerview_models.WhenSearchedGameAppNotFound_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class Fetching_Data_from_Database {

    @SuppressLint("SuspiciousIndentation")
    public void Main_rv_Data(String Bottom_tab_name, String Top_tab_name, MainLayout_interface mainLayout_interface) {


        ArrayList<Parent_rv_model> For_you_Tab_Data = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        Query query = databaseReference.child(Bottom_tab_name).child(Top_tab_name).child("Layout Type and Title").orderByKey();


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Parent_rv_model model = dataSnapshot.getValue(Parent_rv_model.class);
                    For_you_Tab_Data.add(model);
                }
                mainLayout_interface.onDataFetched(For_you_Tab_Data);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mainLayout_interface.onDataError(error);
               Log.d("database error", "onCancelled: "+error);
            }
        });

    }
    public void eventlayout_GameApp_Details( ArrayList<String> name, String type , EventLayout_interface eventLayout_interface) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        ArrayList<Child_Recyclerview_event_data_model> arrayList = new ArrayList<>();

        String Details_to_Get;
        if(Objects.equals(type, "Game")){
            Details_to_Get = "All_Games_Data";
        } else {
            Details_to_Get = "All_Apps_Data";
        }

        for (String game_name : name) {
            Query query = databaseReference.child(Details_to_Get).orderByKey().equalTo(game_name);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    DataSnapshot dataSnapshot = snapshot.child(game_name);


                    Child_Recyclerview_event_data_model model = dataSnapshot.getValue(Child_Recyclerview_event_data_model.class);

                    arrayList.add(model);

                    if (arrayList.size() == name.size()) {
                        eventLayout_interface.onDataFetched(arrayList);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("TAG", "onCancelled: ");
                }
            });
        }
    }


    public void GameApp_1_row_Details(ArrayList<String> name, String type,String freeOrPremium,int layout_type ,OneRowLayout_interface oneRowLayoutInterface, OneRowBigLayout_interface oneRowBigLayoutInterface, OneRowSmallLayout_interface oneRowSmallLayoutInterface, OneRowVerySmallLayout_interface oneRowVerySmallLayoutInterface) {

        int     layout_type_1_row=1,
                layout_type_1_row_big=4,
                layout_type_1_row_small=5,
                layout_type_1_row_very_small = 7;
        String Details_to_Get;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

            if(Objects.equals(type, "Game")){
                if (Objects.equals(freeOrPremium, "Free")){
                    Details_to_Get = "All_Games_Data";
                }else {
                    Details_to_Get = "All_PremiumGames_Data";
                }
            } else{
                Details_to_Get = "All_Apps_Data";
            }


        ArrayList<Child_Recyclerview_1_row_data_model> arrayList_1_row = new ArrayList<>();
            ArrayList<Child_Recyclerview_1_row_big_DataModel> arrayList_1_rowBig = new ArrayList<>();
            ArrayList<Child_Recyclerview_1_row_small_Datamodel> arrayList_1_row_Small = new ArrayList<>();
            ArrayList<Child_Recyclerview_1_row_verysmall_datamodel> arrayList_1_rowVerySmall = new ArrayList<>();

        for (String game_name : name) {

            Query query = databaseReference.child(Details_to_Get).orderByKey().equalTo(game_name);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot dataSnapshot = snapshot.child(game_name);

                    if(layout_type == layout_type_1_row){

                        Child_Recyclerview_1_row_data_model model = dataSnapshot.getValue(Child_Recyclerview_1_row_data_model.class);

                        arrayList_1_row.add(model);

                        if (arrayList_1_row.size() == name.size()) {
                            oneRowLayoutInterface.onDetailsFetched(arrayList_1_row);
                        }

                    } else if (layout_type == layout_type_1_row_big) {
                        Child_Recyclerview_1_row_big_DataModel model = dataSnapshot.getValue(Child_Recyclerview_1_row_big_DataModel.class);

                        arrayList_1_rowBig.add(model);

                        if(arrayList_1_rowBig.size() == name.size()){
                            oneRowBigLayoutInterface.onDetailsFetched(arrayList_1_rowBig);
                        }
                    } else if (layout_type == layout_type_1_row_small) {
                        Child_Recyclerview_1_row_small_Datamodel model = dataSnapshot.getValue(Child_Recyclerview_1_row_small_Datamodel.class);

                        arrayList_1_row_Small.add(model);

                        if(arrayList_1_row_Small.size() == name.size()){
                            oneRowSmallLayoutInterface.onDetailsFetched(arrayList_1_row_Small);
                        }
                    }else {

                        Child_Recyclerview_1_row_verysmall_datamodel model = dataSnapshot.getValue(Child_Recyclerview_1_row_verysmall_datamodel.class);
                        arrayList_1_rowVerySmall.add(model);

                        if (arrayList_1_rowVerySmall.size()== name.size()){

                            oneRowVerySmallLayoutInterface.onDetailsFetched(arrayList_1_rowVerySmall);
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("TAG", "onCancelled: ");
                }
            });
        }

    }

    //=====================================================================================================//
    public void GameApp_3_row_Details(ArrayList<Child_Recyclerview_3_row_getNames_datamodel> name,String type, ThreeRowLayout_interface threeRowLayoutInterface) {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        ArrayList<Child_Recyclerview_3_row_DataModel> arrayList = new ArrayList<>();
        String Details_to_Get;
        if(Objects.equals(type, "Game")){
                Details_to_Get = "All_Games_Data";
        } else {
            Details_to_Get = "All_Apps_Data";
        }

        for (Child_Recyclerview_3_row_getNames_datamodel getName : name){

            Child_Recyclerview_3_row_DataModel Three_row_model = new Child_Recyclerview_3_row_DataModel();
            databaseReference.child(Details_to_Get).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.d("THREE", "onDataChange: "+getName.getItem_3());
                    DataSnapshot dataSnapshot = snapshot.child(getName.getItem_1());

                    Three_row_model.setFirst_game_name(dataSnapshot.child("Name").getValue(String.class));
                    Three_row_model.setFirst_type(dataSnapshot.child("Type").getValue(String.class));
                    Three_row_model.setFirst_game_size(dataSnapshot.child("Size").getValue(String.class));
                    Three_row_model.setFirst_game_logo(dataSnapshot.child("Logo").getValue(String.class));
                    Three_row_model.setFirst_game_ratings(dataSnapshot.child("Ratings").getValue(String.class));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("three row", "onCancelled: ");
                }
            });

            databaseReference.child(Details_to_Get).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot dataSnapshot = snapshot.child(getName.getItem_2());

                    Three_row_model.setSecond_game_name(dataSnapshot.child("Name").getValue(String.class));
                    Three_row_model.setSecond_type(dataSnapshot.child("Type").getValue(String.class));
                    Three_row_model.setSecond_game_size(dataSnapshot.child("Size").getValue(String.class));
                    Three_row_model.setSecond_game_logo(dataSnapshot.child("Logo").getValue(String.class));
                    Three_row_model.setSecond_game_ratings(dataSnapshot.child("Ratings").getValue(String.class));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("three row", "onCancelled: ");
                }
            });

            databaseReference.child(Details_to_Get).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot dataSnapshot = snapshot.child(getName.getItem_3());

                    Three_row_model.setThird_game_name(dataSnapshot.child("Name").getValue(String.class));
                    Three_row_model.setThird_type(dataSnapshot.child("Type").getValue(String.class));
                    Three_row_model.setThird_game_size(dataSnapshot.child("Size").getValue(String.class));
                    Three_row_model.setThird_game_logo(dataSnapshot.child("Logo").getValue(String.class));
                    Three_row_model.setThird_game_ratings(dataSnapshot.child("Ratings").getValue(String.class));

                    arrayList.add(Three_row_model);

                    if(arrayList.size()== name.size()){
                        threeRowLayoutInterface.onFetchedDetails(arrayList);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("three row", "onCancelled: ");
                }
            });



        }
    }
// =====================================================================================================================//

    public void GameApp_2_row_Details(ArrayList<Child_Recyclerview_2_row_getNames_dataModel> name, String type,TwoRowLayout_interface twoRowLayoutInterface) {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        ArrayList<Child_Recylerview_2_row_data_model> arrayList = new ArrayList<>();

        String Details_to_Get;
        if(Objects.equals(type, "Game")){
            Details_to_Get = "All_Games_Data";
        } else {
            Details_to_Get = "All_Apps_Data";
        }

        for (Child_Recyclerview_2_row_getNames_dataModel getName : name){

            Child_Recylerview_2_row_data_model two_row_model = new Child_Recylerview_2_row_data_model();
            databaseReference.child(Details_to_Get).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    DataSnapshot dataSnapshot = snapshot.child(getName.getItem_1());

                    two_row_model.setFirst_game_name(dataSnapshot.child("Name").getValue(String.class));
                    two_row_model.setFirst_type(dataSnapshot.child("Type").getValue(String.class));
                    two_row_model.setFirst_game_image(dataSnapshot.child("Background_image").getValue(String.class));
                    two_row_model.setFirst_Game_ratings(dataSnapshot.child("Ratings").getValue(String.class));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("three row", "onCancelled: ");
                }
            });

            databaseReference.child(Details_to_Get).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot dataSnapshot = snapshot.child(getName.getItem_2());

                    two_row_model.setSecond_game_name(dataSnapshot.child("Name").getValue(String.class));
                    two_row_model.setSecond_type(dataSnapshot.child("Type").getValue(String.class));
                    two_row_model.setSecond_game_image(dataSnapshot.child("Background_image").getValue(String.class));
                    two_row_model.setSecond_Game_ratings(dataSnapshot.child("Ratings").getValue(String.class));

                    arrayList.add(two_row_model);

                    if(arrayList.size()==name.size()){
                        twoRowLayoutInterface.onDetailsFetched(arrayList);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("three row", "onCancelled: ");
                }
            });

        }
    }


    public void Get_Books_Details(ArrayList<String> names, int layout_type, OneRowBookLayout_interface bookLayoutInterface, OneRowBooksVerySmall_interface oneRowBooksVerySmallInterface){

        int layout_type_1_row_books_very_small= 8;
        int layout_type_1_row_books = 6;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");


       ArrayList<Child_Recyclerview_1_row_books_DataModel> arrayList_books = new ArrayList<>();
       ArrayList<Child_recyclerview_1_row_books_verysmall_data_model> arrayList_books_verySall = new ArrayList<>();


        for (String name : names){

            Query query = databaseReference.child("All_Books_Data").orderByKey().equalTo(name);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        if(layout_type == layout_type_1_row_books){
                            Child_Recyclerview_1_row_books_DataModel model = dataSnapshot.getValue(Child_Recyclerview_1_row_books_DataModel.class);
                            arrayList_books.add(model);
                        }else {
                            Child_recyclerview_1_row_books_verysmall_data_model model = dataSnapshot.getValue(Child_recyclerview_1_row_books_verysmall_data_model.class);
                            arrayList_books_verySall.add(model);
                        }

                    }
                    if(arrayList_books.size()==names.size()){
                        bookLayoutInterface.onDataFetched(arrayList_books);
                    } else if (arrayList_books_verySall.size() == names.size()) {
                        oneRowBooksVerySmallInterface.onDataFetched(arrayList_books_verySall);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    bookLayoutInterface.onCancelled(error);
                }
            });
        }

    }


    public void getOffersDetails(OffersLayout_interface offersLayoutInterface){

        ArrayList<Child_Recyclerview_offer_datamodel> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        Query query = databaseReference.child("Offers Tab").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                long snapshotChildrenCount = snapshot.getChildrenCount();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Child_Recyclerview_offer_datamodel model = new Child_Recyclerview_offer_datamodel();
                    model.setMain_title(dataSnapshot.child("main_title").getValue(String.class));

                    if(Objects.equals(dataSnapshot.child("type").getValue(String.class), "game")){

                        String name = dataSnapshot.child("name_of_content").getValue(String.class);

                        Query getGameOfferDetailsFromnames = databaseReference.child("All_Games_Data").orderByKey().equalTo(name);
                        getGameOfferDetailsFromnames.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                               DataSnapshot dataSnapshot1 = snapshot.child(name);

                               model.setName(dataSnapshot1.child("Name").getValue(String.class));
                               model.setEvent_or_offer_image(dataSnapshot1.child("Event_or_offer_image").getValue(String.class));
                               model.setEvent_or_offer_title(dataSnapshot1.child("Event_or_offer_title").getValue(String.class));

                               model.setEvent_or_offer_number(dataSnapshot.child("Event_or_offer_number").getValue(String.class));
                               model.setType(dataSnapshot.child("type").getValue(String.class));
                               model.setPrice(dataSnapshot.child("price").getValue(String.class));
                               arrayList.add(model);

                               if(arrayList.size()==snapshotChildrenCount){
                                   offersLayoutInterface.onDataFetched(arrayList);
                               }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d("dataerror", "onCancelled: "+error);
                            }
                        });

                    } else if (Objects.equals(dataSnapshot.child("type").getValue(String.class), "app")) {

                        String name = dataSnapshot.child("name_of_content").getValue(String.class);
                        Query getAppOfferDetailsFromnames = databaseReference.child("All_Apps_Data").orderByKey().equalTo(name);
                        getAppOfferDetailsFromnames.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                DataSnapshot dataSnapshot1 = snapshot.child(name);

                                model.setName(dataSnapshot1.child("Name").getValue(String.class));
                                model.setEvent_or_offer_image(dataSnapshot1.child("Event_or_offer_image").getValue(String.class));
                                model.setEvent_or_offer_title(dataSnapshot1.child("Event_or_offer_title").getValue(String.class));

                                model.setEvent_or_offer_number(dataSnapshot.child("Event_or_offer_number").getValue(String.class));
                                model.setType(dataSnapshot.child("type").getValue(String.class));
                                model.setPrice(dataSnapshot.child("price").getValue(String.class));
                                arrayList.add(model);

                                if(arrayList.size()==snapshotChildrenCount){
                                    offersLayoutInterface.onDataFetched(arrayList);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void SearchResultPageItemsDetails(String contentLocation, ArrayList<String> namelist, SearchResultPage_OneRowBooks_VerySmall_interface booksVerySmallInterface, SearchResultPage_OneRow_VerySmall_interface gameAppverySmallInterface){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        if (Objects.equals(contentLocation, "All_Books_Data")){
            ArrayList<Search_result_page_1_row_book_verysmall_Datamodel> booksDetailsList = new ArrayList<>();


            for (String bookName : namelist){

                databaseReference.child(contentLocation).child(bookName).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Search_result_page_1_row_book_verysmall_Datamodel model = snapshot.getValue(Search_result_page_1_row_book_verysmall_Datamodel.class);
                            booksDetailsList.add(model);

                            if (booksDetailsList.size()==namelist.size()){
                                booksVerySmallInterface.onDetailsFetched(booksDetailsList);
                            }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("SEARCH", "onCancelled: "+error);
                    }
                });


            }


        }else {

            ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppList_withImages = new ArrayList<>();

            for (String gameAppName : namelist){

                databaseReference.child(contentLocation).child(gameAppName).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<String> imagesLocationList = new ArrayList<>();
                        Search_result_page_1_row_verysmall_rv_model model = snapshot.getValue(Search_result_page_1_row_verysmall_rv_model.class);

                        for (DataSnapshot images : snapshot.child("Images").getChildren()){
                            imagesLocationList.add(images.getValue(String.class));
                        }

                        Search_result_page_1_row_verysmall_withImages_rv_model modelWIthImages = new Search_result_page_1_row_verysmall_withImages_rv_model();
                        ArrayList<Bitmap> imagesList = new ArrayList<>();

                        for (String imageLocation : imagesLocationList){
                            Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                            imagesFromFirebaseStorage.Image_to_fetch(imageLocation, new Fetch_image_interface() {
                                @Override
                                public void image_FetchSuccess(Bitmap bitmap) {
                                    imagesList.add(bitmap);

                                    if (imagesList.size()==imagesLocationList.size()){
                                        modelWIthImages.setImages(imagesList);
                                        modelWIthImages.setName(model.getName());
                                        modelWIthImages.setCompany_name(model.getCompany_name());
                                        modelWIthImages.setLogo(model.getLogo());
                                        modelWIthImages.setRating(model.getRating());
                                        modelWIthImages.setSize(model.getSize());
                                        modelWIthImages.setTotal_downloads(model.getTotal_downloads());

                                        gameAppList_withImages.add(modelWIthImages);
                                        if (gameAppList_withImages.size() == namelist.size()){

                                            gameAppverySmallInterface.onDetailsFetched(gameAppList_withImages);
                                        }
                                    }
                                }
                                @Override
                                public void image_FetchFailure(Exception exception) {
                                    Log.d("SEARCH BUG FIXING", "image_FetchFailure: "+exception);
                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }


        }

    }

    public void WhenSearchedBookNotFound(SearchResultPage_OneRowBooks_VerySmall_interface booksVerySmallInterface){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        databaseReference.child("WhenSearchedBookNotFound").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> bookNamesList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    bookNamesList.add(dataSnapshot.getValue(String.class));
                }

                ArrayList<Search_result_page_1_row_book_verysmall_Datamodel> bookWithDetails = new ArrayList<>();
                for (String bookName : bookNamesList){
                    databaseReference.child("All_Books_Data").child(bookName).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Search_result_page_1_row_book_verysmall_Datamodel model = snapshot.getValue(Search_result_page_1_row_book_verysmall_Datamodel.class);
                            bookWithDetails.add(model);

                            if (bookWithDetails.size()==bookNamesList.size()){
                                booksVerySmallInterface.onDetailsFetched(bookWithDetails);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("SEARCH", "onCancelled: "+error);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("SEARCH", "onCancelled: "+error);
            }
        });


    }

    public void WhenSearchedGameAppNotFound( SearchResultPage_OneRow_VerySmall_interface gameAppverySmallInterface){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");

        databaseReference.child("WhenSearchedGameAppNotFound").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<WhenSearchedGameAppNotFound_model> gameAppList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    gameAppList.add(dataSnapshot.getValue(WhenSearchedGameAppNotFound_model.class));
                }

                ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppList_withImages = new ArrayList<>();

                for (WhenSearchedGameAppNotFound_model gameAppNameAndLocation : gameAppList){

                    String contentLocation = gameAppNameAndLocation.getLocation();
                    String gameAppName = gameAppNameAndLocation.getName();

                    databaseReference.child(contentLocation).child(gameAppName).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ArrayList<String> imagesLocationList = new ArrayList<>();
                            Search_result_page_1_row_verysmall_rv_model model = snapshot.getValue(Search_result_page_1_row_verysmall_rv_model.class);
                            for (DataSnapshot images : snapshot.child("Images").getChildren()){
                                imagesLocationList.add(images.getValue(String.class));
                            }

                            Search_result_page_1_row_verysmall_withImages_rv_model modelWIthImages = new Search_result_page_1_row_verysmall_withImages_rv_model();
                            ArrayList<Bitmap> imagesList = new ArrayList<>();

                            for (String imageLocation : imagesLocationList){
                                Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                                imagesFromFirebaseStorage.Image_to_fetch(imageLocation, new Fetch_image_interface() {
                                    @Override
                                    public void image_FetchSuccess(Bitmap bitmap) {
                                        imagesList.add(bitmap);

                                        if (imagesList.size()==imagesLocationList.size()){
                                            modelWIthImages.setImages(imagesList);
                                            modelWIthImages.setName(model.getName());
                                            modelWIthImages.setCompany_name(model.getCompany_name());
                                            modelWIthImages.setLogo(model.getLogo());
                                            modelWIthImages.setRating(model.getRating());
                                            modelWIthImages.setSize(model.getSize());
                                            modelWIthImages.setTotal_downloads(model.getTotal_downloads());
                                            modelWIthImages.setLocation(contentLocation);

                                            gameAppList_withImages.add(modelWIthImages);

                                            if (gameAppList_withImages.size()==gameAppList.size()){
                                                gameAppverySmallInterface.onDetailsFetched(gameAppList_withImages);
                                            }
                                        }

                                    }

                                    @Override
                                    public void image_FetchFailure(Exception exception) {

                                    }
                                });

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
