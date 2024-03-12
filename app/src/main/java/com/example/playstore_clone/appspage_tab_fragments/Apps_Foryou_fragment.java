package com.example.playstore_clone.appspage_tab_fragments;

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
import com.example.playstore_clone.Adapters.Parent_rv_adapter;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Data_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.MainLayout_interface;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Parent_rv_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Apps_Foryou_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Apps_Foryou_fragment extends Fragment {

    RecyclerView recyclerView;

    String Bottom_tab_name,top_tab_name, freeOrPremium, type;

    Parent_rv_adapter adapter;

    LottieAnimationView loading;

    RelativeLayout loadingViewBackground;




//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Apps_Foryou_fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Apps_Foryou_fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Apps_Foryou_fragment newInstance(String param1, String param2) {
//        Apps_Foryou_fragment fragment = new Apps_Foryou_fragment();
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


            View view= inflater.inflate(R.layout.fragment_apps__foryou_fragment, container, false);
            recyclerView = view.findViewById(R.id.apps_for_you_recyclerview);
            loading = view.findViewById(R.id.loading_animation_App__For_you);
            loadingViewBackground = view.findViewById(R.id.loading_animation_Background_Apps__For_you);


            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


            Bottom_tab_name="Apps Tab";
            top_tab_name= "For you Tab";
            freeOrPremium = "Free";
            type = "App";





        Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
        data_from_database.Main_rv_Data( Bottom_tab_name, top_tab_name, new MainLayout_interface() {
            @Override
            public void onDataFetched(ArrayList<Parent_rv_model> For_you_Tab_Data) {

                adapter = new Parent_rv_adapter(For_you_Tab_Data,getContext(),Bottom_tab_name,top_tab_name, freeOrPremium, type, loading, loadingViewBackground);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onDataError(DatabaseError error) {
                Log.d("onDataError: ", String.valueOf(error));
            }
        });


            return view;
    }
}