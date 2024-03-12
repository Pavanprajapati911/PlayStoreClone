package com.example.playstore_clone.appspage_tab_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playstore_clone.Adapters.Categories_and_genres_Adapter;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Categories_and_genres_Datamodel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_small_Datamodel;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Apps_Categories_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Apps_Categories_fragment extends Fragment {
    RecyclerView recyclerView;

    ArrayList<Categories_and_genres_Datamodel> arrayList;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Apps_Categories_fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Apps_Categories_fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Apps_Categories_fragment newInstance(String param1, String param2) {
//        Apps_Categories_fragment fragment = new Apps_Categories_fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apps__categories_fragment, container, false);
        recyclerView = view.findViewById(R.id.apps_categories_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();

        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.watch_apps_logo,"Watch apps"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.watch_faces_logo,"Watch faces"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.art_and_design_logo,"Art and Design"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.augmented_reality,"Augmented reality"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.auto_and_vehicles,"Auto and Vehicles"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.beauty,"Beauty"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.books_and_references,"Books and Reference"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.business_logo,"Business"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.comics_logo,"Comics"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.communication_logo,"Communiucation"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.dating_logo,"Dating"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.educational_logo,"Education"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.entertainment_logo,"Entertainment"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.events_logo,"Events"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.finance_logo,"Finance"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.food_and_drink_logo,"Food and Drink"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.games_logo,"Games"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.google_cast_logo,"Google Cast"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.health_and_fitness_logo,"Health and fitness"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.house_and_home_logo,"House and home"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.kids_logo,"Kids"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.libraries_and_demo_logo,"Libraries and Demo"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.lifestyle_logo,"Lifestyle"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.maps_and_navigation_logo,"Maps and navigation"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.medical_logo,"Medical"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.music_and_audio_logo,"Music and Audio"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.news_and_magazines_logo,"News and Magazines"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.parenting_logo,"parenting"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.personalization_logo,"Personalization"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.photography_logo,"Photography"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.productivity_logo,"Productivity"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.shopping_logo,"Shopping"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.social_logo,"Social"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.sports_logo,"Sports"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.tools_logo,"Tools"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.travel_and_local_logo,"Travel and Local"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.video_players_and_editors_logo,"Video Players and Editors"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.weather_logo,"Weather"));

        recyclerView.setAdapter(new Categories_and_genres_Adapter(getContext(),arrayList));

        return view;
    }
}