package com.example.playstore_clone.gamespage_tab_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.playstore_clone.Adapters.Child_Recyclerview_1_row_verysmall_Adapter;

import com.example.playstore_clone.GameApp_Content_Activity;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_names_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Data_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.OneRowVerySmallLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameAppBooks_names_interface;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_verysmall_datamodel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;


///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Top_charts_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Games_Top_charts_fragment extends Fragment {

    RecyclerView recyclerView;
    String Bottom_tab_name, top_tab_name, type;
    String freeOrPremium;

    RelativeLayout loadingViewBackground;
    LottieAnimationView loadingView;

    int layout_type_1_row_verySmall = 7;





//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Games_Top_charts_fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Top_charts_fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Top_charts_fragment newInstance(String param1, String param2) {
//        Top_charts_fragment fragment = new Top_charts_fragment();
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
        View view= inflater.inflate(R.layout.fragment_games_top_charts_fragment, container, false);
        recyclerView = view.findViewById(R.id.games_top_charts_recyclerview);
        loadingView = view.findViewById(R.id.loading_animation_Games__topcharts);
        loadingViewBackground = view.findViewById(R.id.loading_animation_Background_Games__topcharts);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Bottom_tab_name = "Games Tab";
        top_tab_name = "Top charts Tab";


        freeOrPremium = "Free";
        type = "Game";



        Fetch_names_from_Database namesFromDatabase = new Fetch_names_from_Database();
        namesFromDatabase.get_Game_or_App_Names(0, Bottom_tab_name, top_tab_name, new Get_GameAppBooks_names_interface() {
            @Override
            public void get_game_or_app_names(ArrayList<String> names) {
                Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                data_from_database.GameApp_1_row_Details(names, type, freeOrPremium,layout_type_1_row_verySmall, null, null, null, new OneRowVerySmallLayout_interface() {
                    @Override
                    public void onDetailsFetched(ArrayList<Child_Recyclerview_1_row_verysmall_datamodel> arrayList) {
                        Child_Recyclerview_1_row_verysmall_Adapter adapter = new Child_Recyclerview_1_row_verysmall_Adapter(getContext(), arrayList, new Child_Recyclerview_1_row_verysmall_Adapter.itemOnClick() {
                            @Override
                            public void onClick(int position) {
                                Intent intent = new Intent(getContext(), GameApp_Content_Activity.class);
                                intent.putExtra("GameAppName", arrayList.get(position).getName());
                                intent.putExtra("Bottom_Tab", Bottom_tab_name);
                                intent.putExtra("Top_Tab",top_tab_name);
                                startActivity(intent);
                            }
                        });

                        recyclerView.setAdapter(adapter);
                        loadingView.cancelAnimation();
                        loadingView.setVisibility(View.GONE);
                        loadingViewBackground.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void cant_Fetch_names(DatabaseError error) {

            }
        });



        return view;
    }
}