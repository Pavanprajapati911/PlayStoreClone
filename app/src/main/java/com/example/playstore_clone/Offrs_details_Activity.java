package com.example.playstore_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.Fetching_data_from_firebase.GetOfferDetails_page_inteface;
import com.example.playstore_clone.reyckerview_models.App_content_page_events_and_offers_rv_data_model;
import com.example.playstore_clone.reyckerview_models.Offer_details_page_GameAppDetails_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Offrs_details_Activity extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout navigate_to_app_content_page;

    RelativeLayout loadingViewBackground;
    LottieAnimationView loadingView;

    ImageView logo, eventOfferimage, backButton;

    TextView Appname, companyname, size, ratings, offerTitle, offerDescription;

    String name, bottom_tab_name, top_tab_name, contentLocation, offer_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offrs_details);
        toolbar = findViewById(R.id.offer_page_toolbar);
        logo = findViewById(R.id.offer_details_page_gameImage);
        eventOfferimage = findViewById(R.id.offer_details_page_game_offerimage);
        Appname = findViewById(R.id.offer_details_page_gameName);
        companyname = findViewById(R.id.offer_details_page_game_Companyname);
        size = findViewById(R.id.offer_details_page_game_size);
        ratings = findViewById(R.id.offer_details_page_game_ratings);
        offerTitle = findViewById(R.id.offer_details_page_game_offertitle);
        offerDescription = findViewById(R.id.offer_details_page_game_offerdescription);
        loadingView = findViewById(R.id.loading_animation_Offerdetails);
        loadingViewBackground = findViewById(R.id.loading_animation_Background_Offerdetails);
        backButton = findViewById(R.id.offer_details_page_backButton);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(!Objects.equals(getIntent().getStringExtra("appType"), null)){
            name = getIntent().getStringExtra("offerGameAppName");

            offer_number = getIntent().getStringExtra("eventOfferNumber");

            if(Objects.equals(getIntent().getStringExtra("appType"), "game")){
                if (Objects.equals(getIntent().getStringExtra("appPrice"), "Free")){
                    bottom_tab_name = "Games Tab";

                    top_tab_name = null;
                }else {
                    bottom_tab_name = "Games Tab";

                    top_tab_name = "Premium Tab";

                }

            }else {
                bottom_tab_name = "Apps Tab";

                top_tab_name = null;
            }


        }else {
            name = getIntent().getStringExtra("offerGameAppName");
            bottom_tab_name = getIntent().getStringExtra("Bottom_Tab");
            top_tab_name = getIntent().getStringExtra("Top_Tab");
            offer_number = getIntent().getStringExtra("offernumber");
        }
//============================================================================================================================//

        if (Objects.equals(bottom_tab_name, "Games Tab")){

            if(Objects.equals(top_tab_name, "Premium Tab")){
                contentLocation = "All_PremiumGames_Data";
            }else {
                contentLocation = "All_Games_Data";
            }
        }else {
            contentLocation = "All_Apps_Data";
        }

        navigate_to_app_content_page = findViewById(R.id.offers_details_activity__game_or_app_details_layout);

        navigate_to_app_content_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Offrs_details_Activity.this, GameApp_Content_Activity.class);
                intent.putExtra("GameAppNameFromOffersDetailsActivity",name);
                intent.putExtra("Bottom_Tab", bottom_tab_name);
                intent.putExtra("Top_Tab",top_tab_name);
                startActivity(intent);
            }
        });

        GetOfferDetails(name, offer_number,contentLocation, new GetOfferDetails_page_inteface() {
            @Override
            public void onDetailsFetched(App_content_page_events_and_offers_rv_data_model model, Offer_details_page_GameAppDetails_Model modelDetails) {

                Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                imagesFromFirebaseStorage.Image_to_fetch(modelDetails.getLogo(), new Fetch_image_interface() {
                    @Override
                    public void image_FetchSuccess(Bitmap bitmap) {
                        logo.setImageBitmap(bitmap);
                        //Glide.with(Offrs_details_Activity.this).load(bitmap).into(logo);
                    }

                    @Override
                    public void image_FetchFailure(Exception exception) {

                    }
                });

                imagesFromFirebaseStorage.Image_to_fetch(model.getImage(), new Fetch_image_interface() {
                    @Override
                    public void image_FetchSuccess(Bitmap bitmap) {

                        eventOfferimage.setImageBitmap(bitmap);
                        //Glide.with(Offrs_details_Activity.this).load(bitmap).into(eventOfferimage);
                    }

                    @Override
                    public void image_FetchFailure(Exception exception) {

                    }
                });

                Appname.setText(modelDetails.getName());
                companyname.setText(modelDetails.getCompany_name());
                ratings.setText(modelDetails.getRatings());
                size.setText(modelDetails.getSize());

                offerTitle.setText(model.getTitle());
                offerDescription.setText(model.getDescription());

                loadingView.cancelAnimation();
                loadingView.setVisibility(View.GONE);
                loadingViewBackground.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(DatabaseError error) {
                Log.d("offerdetailsPage", "onFailure: "+error);
            }
        });

    }

    public void GetOfferDetails(String name, String offer_number,String contentLocation, GetOfferDetails_page_inteface offerDetailsPageInteface){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Playstore_clone_database");
        Query query = databaseReference.child(contentLocation).child(name).orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                DataSnapshot dataSnapshot = snapshot.child("EventsAndOffers").child(offer_number);

                App_content_page_events_and_offers_rv_data_model model = dataSnapshot.getValue(App_content_page_events_and_offers_rv_data_model.class);
                Offer_details_page_GameAppDetails_Model modelDetails = snapshot.getValue(Offer_details_page_GameAppDetails_Model.class);

                offerDetailsPageInteface.onDetailsFetched(model, modelDetails);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("offerDetailsPageError", "onCancelled: "+error);
            }
        });


    }
}