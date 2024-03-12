package com.example.playstore_clone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playstore_clone.gamespage_tab_fragments.Games_Categories_fragment;
import com.example.playstore_clone.gamespage_tab_fragments.Games_For_you_fragment;
import com.example.playstore_clone.gamespage_tab_fragments.Games_Kids_fragment;
import com.example.playstore_clone.gamespage_tab_fragments.Games_Premium_fragment;
import com.example.playstore_clone.gamespage_tab_fragments.Games_Top_charts_fragment;
import com.google.android.material.tabs.TabLayout;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Games_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Games_fragment extends Fragment {
    AppCompatActivity activity;
    Toolbar Games_frag_toolbar;
    TabLayout Games_frag_tablayout;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Games_fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Games_fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Games_fragment newInstance(String param1, String param2) {
//        Games_fragment fragment = new Games_fragment();
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
//
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games_fragment, container, false);
        Games_frag_toolbar = view.findViewById(R.id.games_frag_toolbar);
        Games_frag_tablayout = (TabLayout) view.findViewById(R.id.games_frag_tablayout);
        activity = ((AppCompatActivity) getActivity());

        activity.setSupportActionBar(Games_frag_toolbar);
        activity.getSupportActionBar().setTitle("");

        Games_frag_tablayout.addTab(Games_frag_tablayout.newTab().setText("For you"));
        Games_frag_tablayout.addTab(Games_frag_tablayout.newTab().setText("Top charts"));
        Games_frag_tablayout.addTab(Games_frag_tablayout.newTab().setText("Kids"));
        Games_frag_tablayout.addTab(Games_frag_tablayout.newTab().setText("Premium"));
        Games_frag_tablayout.addTab(Games_frag_tablayout.newTab().setText("Categories"));


        games_frag_transaction(new Games_For_you_fragment(),0);

        Games_frag_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText() == "For you"){
                    games_frag_transaction(new Games_For_you_fragment(),1);
                } else if (tab.getText() == "Top charts") {
                    games_frag_transaction(new Games_Top_charts_fragment(),1);
                } else if (tab.getText() == "Kids") {
                    games_frag_transaction(new Games_Kids_fragment(),1);
                } else if (tab.getText() == "Premium") {
                    games_frag_transaction(new Games_Premium_fragment(),1);
                }else {
                    games_frag_transaction(new Games_Categories_fragment(),1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
    public void games_frag_transaction(Fragment fragment,int flags){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(flags==0){
            fragmentTransaction.add(R.id.games_frag_framelayout,fragment).commit();
        }else{
            fragmentTransaction.replace(R.id.games_frag_framelayout,fragment).commit();

        }
    }
}