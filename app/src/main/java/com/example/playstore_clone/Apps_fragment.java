package com.example.playstore_clone;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playstore_clone.appspage_tab_fragments.Apps_Categories_fragment;
import com.example.playstore_clone.appspage_tab_fragments.Apps_Foryou_fragment;
import com.example.playstore_clone.appspage_tab_fragments.Apps_Kids_fragment;
import com.example.playstore_clone.appspage_tab_fragments.Apps_Topcharts_fragment;
import com.google.android.material.tabs.TabLayout;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Apps_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Apps_fragment extends Fragment {
    Toolbar toolbar;
    TabLayout tabLayout;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Apps_fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Apps_frgment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Apps_fragment newInstance(String param1, String param2) {
//        Apps_fragment fragment = new Apps_fragment();
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
        View view= inflater.inflate(R.layout.fragment_apps_frgment, container, false);
        toolbar = view.findViewById(R.id.apps_frag_toolbar);
        tabLayout = view.findViewById(R.id.apps_frag_tablayout);

        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");

        tabLayout.addTab(tabLayout.newTab().setText("For you"));
        tabLayout.addTab(tabLayout.newTab().setText("Top charts"));
        tabLayout.addTab(tabLayout.newTab().setText("Kids"));
        tabLayout.addTab(tabLayout.newTab().setText("Categories"));

        frag_transaction(new Apps_Foryou_fragment(),0);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText()=="For you"){
                    frag_transaction(new Apps_Foryou_fragment(),1);
                } else if (tab.getText()=="Top charts") {
                    frag_transaction(new Apps_Topcharts_fragment(),1);
                } else if (tab.getText()=="Kids"){
                    frag_transaction(new Apps_Kids_fragment(),1);
                }else{
                    frag_transaction(new Apps_Categories_fragment(),1);
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
    public void frag_transaction(Fragment fragment,int flag){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(flag==0){
            fragmentTransaction.add(R.id.apps_frag_framelayout,fragment).commit();
        }else {
            fragmentTransaction.replace(R.id.apps_frag_framelayout,fragment).commit();
        }

    }
}