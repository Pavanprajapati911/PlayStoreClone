package com.example.playstore_clone.gamespage_tab_fragments;

import android.os.Bundle;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.playstore_clone.Fetching_data_from_firebase.MainLayout_interface;
import com.example.playstore_clone.Adapters.Parent_rv_adapter;

import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Data_from_Database;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Parent_rv_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link For_you_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Games_For_you_fragment extends Fragment {

    RecyclerView Parent_recyclerView;
    Parent_rv_adapter adapter;

    LottieAnimationView loading;

    RelativeLayout loadingViewBackground;


    String Bottom_tab_name,Top_tab_name, freeOrPremium, type;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Games_For_you_fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment For_you_fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static For_you_fragment newInstance(String param1, String param2) {
//        For_you_fragment fragment = new For_you_fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

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
        View view= inflater.inflate(R.layout.fragment_games_for_you_fragment, container, false);
        ArrayList<Parent_rv_model> arrayList = new ArrayList<>();

        loading = view.findViewById(R.id.loading_animation_Game__For_you);
        loadingViewBackground = view.findViewById(R.id.loading_animation_Background_Game__For_you);

        Parent_recyclerView = view.findViewById(R.id.games_foryou_recyclerview);

        Parent_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Bottom_tab_name="Games Tab";
        Top_tab_name = "For you Tab";
        freeOrPremium = "Free";
        type = "Game";


        Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
        data_from_database.Main_rv_Data( Bottom_tab_name, Top_tab_name, new MainLayout_interface() {
                    @Override
                    public void onDataFetched(ArrayList<Parent_rv_model> For_you_Tab_Data) {
                        arrayList.addAll(For_you_Tab_Data);
                        adapter = new Parent_rv_adapter(arrayList,getContext(),Bottom_tab_name,Top_tab_name, freeOrPremium, type, loading, loadingViewBackground);
                        Parent_recyclerView.setAdapter(adapter);


                    }

                    @Override
                    public void onDataError(DatabaseError error) {
                        Log.d("onDataError: ", String.valueOf(error));
                    }
                });





        return view;
    }
}