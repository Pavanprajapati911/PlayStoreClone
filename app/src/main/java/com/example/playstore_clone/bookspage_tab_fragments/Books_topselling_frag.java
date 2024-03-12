package com.example.playstore_clone.bookspage_tab_fragments;

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
import com.example.playstore_clone.Adapters.Child_Recyclerview_1_row_books_verysmall_adapter;
import com.example.playstore_clone.Books_Content_Activity;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_names_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Data_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.OneRowBooksVerySmall_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameAppBooks_names_interface;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Child_recyclerview_1_row_books_verysmall_data_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Books_topselling_frag#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Books_topselling_frag extends Fragment {

    RecyclerView recyclerView;
    String Bottom_tab_name, top_tab_name;

    LottieAnimationView loadingView;

    RelativeLayout loadingViewBackground;
    int layout_type_1_row_books_very_small= 8;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Books_topselling_frag() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Books_topselling_frag.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Books_topselling_frag newInstance(String param1, String param2) {
//        Books_topselling_frag fragment = new Books_topselling_frag();
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
        View view = inflater.inflate(R.layout.fragment_books_topselling_frag, container, false);

        recyclerView = view.findViewById(R.id.books_topselling_recyclerview);
        loadingView = view.findViewById(R.id.loading_animation_Books__topselling);
        loadingViewBackground = view.findViewById(R.id.loading_animation_Background_Books__topselling);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Bottom_tab_name = "Books Tab";
        top_tab_name= "Top selling Tab";


        Fetch_names_from_Database namesFromDatabase = new Fetch_names_from_Database();
        namesFromDatabase.get_Game_or_App_Names(0, Bottom_tab_name, top_tab_name, new Get_GameAppBooks_names_interface() {
            @Override
            public void get_game_or_app_names(ArrayList<String> names) {
                Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                data_from_database.Get_Books_Details(names, layout_type_1_row_books_very_small, null, new OneRowBooksVerySmall_interface() {
                    @Override
                    public void onDataFetched(ArrayList<Child_recyclerview_1_row_books_verysmall_data_model> arrayList) {
                        recyclerView.setAdapter(new Child_Recyclerview_1_row_books_verysmall_adapter(getContext(), arrayList, new Child_Recyclerview_1_row_books_verysmall_adapter.itemOnClick() {
                            @Override
                            public void onClick(int position) {
                                Intent intent = new Intent(getContext(), Books_Content_Activity.class);
                                intent.putExtra("BookName",arrayList.get(position).getName());
                                intent.putExtra("Bottom_Tab", Bottom_tab_name);
                                intent.putExtra("Top_Tab",top_tab_name);
                                startActivity(intent);
                            }
                        }));

                        loadingView.cancelAnimation();
                        loadingView.setVisibility(View.GONE);
                        loadingViewBackground.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

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