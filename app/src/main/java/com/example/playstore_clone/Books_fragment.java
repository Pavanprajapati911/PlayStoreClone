package com.example.playstore_clone;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playstore_clone.bookspage_tab_fragments.Books_Genres_frag;
import com.example.playstore_clone.bookspage_tab_fragments.Books_audiobooks_frag;
import com.example.playstore_clone.bookspage_tab_fragments.Books_comics_frag;
import com.example.playstore_clone.bookspage_tab_fragments.Books_newrelease_frag;
import com.example.playstore_clone.bookspage_tab_fragments.Books_topfree_frag;
import com.example.playstore_clone.bookspage_tab_fragments.Books_topselling_frag;
import com.example.playstore_clone.bookspage_tab_fragments.Books_Ebooks_frag;
import com.google.android.material.tabs.TabLayout;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Books_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Books_fragment extends Fragment {
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

    public Books_fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Books_fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Books_fragment newInstance(String param1, String param2) {
//        Books_fragment fragment = new Books_fragment();
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
        View view= inflater.inflate(R.layout.fragment_books_fragment, container, false);
        toolbar = view.findViewById(R.id.books_frag_toolbar);
        tabLayout = view.findViewById(R.id.books_frag_tablayout);

        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");

        tabLayout.addTab(tabLayout.newTab().setText("Ebooks"));
        tabLayout.addTab(tabLayout.newTab().setText("Audiobooks"));
        tabLayout.addTab(tabLayout.newTab().setText("Comics"));
        tabLayout.addTab(tabLayout.newTab().setText("Genres"));
        tabLayout.addTab(tabLayout.newTab().setText("Top selling"));
        tabLayout.addTab(tabLayout.newTab().setText("New releases"));
        tabLayout.addTab(tabLayout.newTab().setText("Top free"));

        books_frag_transaction(new Books_Ebooks_frag(),0);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText()=="Ebooks"){
                    books_frag_transaction(new Books_Ebooks_frag(),1);
                } else if (tab.getText()=="Audiobooks") {
                    books_frag_transaction(new Books_audiobooks_frag(),1);
                } else if (tab.getText()=="Comics") {
                    books_frag_transaction(new Books_comics_frag(),1);
                } else if (tab.getText()=="Genres") {
                    books_frag_transaction(new Books_Genres_frag(),1);
                } else if (tab.getText()=="Top selling") {
                    books_frag_transaction(new Books_topselling_frag(),1);
                } else if (tab.getText()=="New releases") {
                    books_frag_transaction(new Books_newrelease_frag(),1);
                }else {
                    books_frag_transaction(new Books_topfree_frag(),1);
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
    public void books_frag_transaction(Fragment fragment,int flag){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(flag==0){
            fragmentTransaction.add(R.id.books_frag_framelayout,fragment).commit();
        }else {
            fragmentTransaction.replace(R.id.books_frag_framelayout,fragment).commit();
        }

    }
}