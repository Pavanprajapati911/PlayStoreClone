package com.example.playstore_clone.gamespage_tab_fragments;

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
// * Use the {@link Games_Categories_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Games_Categories_fragment extends Fragment {

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

    public Games_Categories_fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Games_Categories_fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Games_Categories_fragment newInstance(String param1, String param2) {
//        Games_Categories_fragment fragment = new Games_Categories_fragment();
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
        View view= inflater.inflate(R.layout.fragment_games__categories_fragment, container, false);
        recyclerView = view.findViewById(R.id.games_categories_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();

        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.action_logo,"Action"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.adventure_logo,"Adventure"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.arcade_logo,"Arcade"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.board_logo,"Board"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.card_logo,"card"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.casino_logo,"Casino"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.casual_logo,"Casual"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.educational_logo,"Educational"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.music_logo,"Music"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.puzzle_logo,"Puzzle"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.racing_logo,"Racing"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.role_playing_logo,"Role Playing"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.simulation_logo,"Simulation"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.sports_logo,"Sports"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.strategy_logo,"Strategy"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.trivia_logo,"Trivia"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.word_logo,"Word"));


        recyclerView.setAdapter(new Categories_and_genres_Adapter(getContext(),arrayList));

        return view;
    }
}