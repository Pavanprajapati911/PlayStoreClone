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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.playstore_clone.Adapters.AppContentpage_TagsRv_Adapter;
import com.example.playstore_clone.Adapters.Child_Recyclerview_1_row_books_Adapter;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Data_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_Books_content_page_content_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameAppBooks_Tags_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.OneRowBookLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameAppBooks_names_interface;
import com.example.playstore_clone.reyckerview_models.Books_content_page_ContentModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_books_DataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Books_Content_Activity extends AppCompatActivity {
    Toolbar toolbar;

    LinearLayout layout_to_hide;

    RecyclerView similar_ebooks_rv, tagsRv;
    ImageView reviews_menu_1, reviews_menu_2, reviews_menu_3, bookLogo, searchIcon, backButton;

    TextView bookName, authorName, publishedBy, ratings, totalreviews, bookType, totalPages, aboutThis, price;

    String bookname, bottomm_tab, top_tab, contentLocation;

    ArrayList<String> tagsForSuggestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_content);
        toolbar = findViewById(R.id.books_content_data_page_toolbar);
        similar_ebooks_rv = findViewById(R.id.books_content_page_similar_ebooks_recyclerview);
        tagsRv = findViewById(R.id.books_content_data_page_tagsRv);
        reviews_menu_1=findViewById(R.id.books_content_page_popup_menu_icon1);
        reviews_menu_2 = findViewById(R.id.books_content_page_popup_menu_icon2);
        reviews_menu_3 = findViewById(R.id.books_content_page_popup_menu_icon3);
        layout_to_hide = findViewById(R.id.books_content_layout_to_Hide);

        searchIcon = findViewById(R.id.books_content_data_page_search_icon);
        bookLogo = findViewById(R.id.books_content_data_page_booklogo);
        bookName = findViewById(R.id.books_content_data_page_book_name);
        authorName = findViewById(R.id.books_content_data_page_author_name);
        publishedBy = findViewById(R.id.books_content_data_page_publishedBy);
        ratings = findViewById(R.id.books_content_data_page_ratings);
        totalreviews = findViewById(R.id.books_content_data_page_totalreviews);
        bookType = findViewById(R.id.books_content_data_page_booktype);
        totalPages = findViewById(R.id.books_content_data_page_totalpages);
        aboutThis = findViewById(R.id.books_content_data_page_aboutThis);
        price = findViewById(R.id.books_content_data_page_price);
        backButton = findViewById(R.id.books_content_data_page_backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tagsForSuggestion = new ArrayList<>();

        bookname = getIntent().getStringExtra("BookName");

        contentLocation = "All_Books_Data";


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        layout_to_hide.setVisibility(View.GONE);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Books_Content_Activity.this, Search_activity.class);
                intent.putExtra("searchBarText","Search Books");
                startActivity(intent);
            }
        });


        GetContent(bookname, contentLocation, new Get_Books_content_page_content_interface() {
            @Override
            public void onDetailsFetched(Books_content_page_ContentModel model) {

                Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                imagesFromFirebaseStorage.Image_to_fetch(model.getImage(), new Fetch_image_interface() {
                    @Override
                    public void image_FetchSuccess(Bitmap bitmap) {
                        bookLogo.setImageBitmap(bitmap);
                        //Glide.with(Books_Content_Activity.this).load(bitmap).into(bookLogo);
                    }

                    @Override
                    public void image_FetchFailure(Exception exception) {
                        Log.d("imageException", "image_FetchFailure: " + exception);
                    }
                });

                bookName.setText(model.getName());
                authorName.setText(model.getAuthor_name());
                publishedBy.setText(model.getPublished_by());
                ratings.setText(model.getRatings());
                totalreviews.setText(model.getTotal_reviews());
                bookType.setText(model.getType());
                totalPages.setText(model.getPages());
                aboutThis.setText(model.getAbout_this());
                price.setText(model.getPrice());

                layout_to_hide.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        }, new Get_GameAppBooks_Tags_interface() {
            @Override
            public void onTagsFetched(ArrayList<String> tagslist) {
                tagsForSuggestion.addAll(tagslist);
                tagsRv.setLayoutManager(new LinearLayoutManager(Books_Content_Activity.this,RecyclerView.HORIZONTAL,false));
                tagsRv.setAdapter(new AppContentpage_TagsRv_Adapter(Books_Content_Activity.this, tagslist));

                GetSimilarBooks(bookname, contentLocation, tagsForSuggestion, new Get_GameAppBooks_names_interface() {
                    @Override
                    public void get_game_or_app_names(ArrayList<String> names) {
                        Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                        data_from_database.Get_Books_Details(names, 6, new OneRowBookLayout_interface() {
                            @Override
                            public void onDataFetched(ArrayList<Child_Recyclerview_1_row_books_DataModel> arrayList) {
                                Map<String,Bitmap> mapLogo = new HashMap<>();

                                for (Child_Recyclerview_1_row_books_DataModel logomodel : arrayList){
                                    Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                                    imagesFromFirebaseStorage.Image_to_fetch(logomodel.getImage(), new Fetch_image_interface() {
                                        @Override
                                        public void image_FetchSuccess(Bitmap bitmap) {

                                            mapLogo.put(logomodel.getImage(),bitmap);
                                            if (mapLogo.size()==arrayList.size()){

                                                ArrayList<Bitmap> logoList = new ArrayList<>();

                                                for (Child_Recyclerview_1_row_books_DataModel logo : arrayList){
                                                    logoList.add(mapLogo.get(logo.getImage()));
                                                }

                                                similar_ebooks_rv.setLayoutManager(new LinearLayoutManager(Books_Content_Activity.this,RecyclerView.HORIZONTAL,false));
                                                similar_ebooks_rv.setAdapter(new Child_Recyclerview_1_row_books_Adapter(Books_Content_Activity.this, logoList,arrayList, new Child_Recyclerview_1_row_books_Adapter.onItemClick() {
                                                    @Override
                                                    public void onclick(int position) {
                                                        Intent intent = new Intent(Books_Content_Activity.this, Books_Content_Activity.class);
                                                        intent.putExtra("BookName",arrayList.get(position).getName());
                                                        intent.putExtra("Bottom_Tab", bottomm_tab);
                                                        intent.putExtra("Top_Tab",top_tab);
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
                            public void onCancelled(DatabaseError error) {

                            }
                        },null);
                    }

                    @Override
                    public void cant_Fetch_names(DatabaseError error) {

                    }
                });

            }

            @Override
            public void onFailure(DatabaseError error) {

            }
        });




        //============================================================================================================//
        reviews_menu_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerForContextMenu(view);
                view.showContextMenu(1,1);
                unregisterForContextMenu(view);

            }
        });

        reviews_menu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerForContextMenu(view);
                view.showContextMenu(1,1);
                unregisterForContextMenu(view);

            }
        });

        reviews_menu_3.setOnClickListener(new View.OnClickListener() {
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
        new MenuInflater(this).inflate(R.menu.books_content_page_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        new MenuInflater(this).inflate(R.menu.app_content_page_reviews_menu,menu);
    }

    public void GetContent(String bookname, String contentLocation, Get_Books_content_page_content_interface booksContentPageContentInterface, Get_GameAppBooks_Tags_interface tagsInterface){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");
        Query query = databaseReference.child(contentLocation).child(bookname).orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Books_content_page_ContentModel model = snapshot.getValue(Books_content_page_ContentModel.class);
                booksContentPageContentInterface.onDetailsFetched(model);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query getTags = databaseReference.child(contentLocation).child(bookname).child("Tags").orderByKey();
        getTags.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> tagsList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    tagsList.add(dataSnapshot.getValue(String.class));
                }
                tagsInterface.onTagsFetched(tagsList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("booksContentPageError", "onCancelled: "+error);
            }
        });


    }

    public void GetSimilarBooks(String bookname, String contentLocation, ArrayList<String> tagsForSuggestion, Get_GameAppBooks_names_interface gameOrAppNamesInterface){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");
        ArrayList<String> booksToSuggest= new ArrayList<>();
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

                            if(Objects.equals(tagsForSuggestion.get(i), tagsSnapshot.getValue(String.class)) && !Objects.equals(dataSnapshot.getKey(), bookname)){

                                booksToSuggest.add(dataSnapshot.getKey());
                                tagsMatched = true;
                                break;
                            }
                        }
                        if (tagsMatched){
                            break;
                        }


                    }

                }
                gameOrAppNamesInterface.get_game_or_app_names(booksToSuggest);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}