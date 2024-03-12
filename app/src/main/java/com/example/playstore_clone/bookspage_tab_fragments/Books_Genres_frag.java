package com.example.playstore_clone.bookspage_tab_fragments;

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
// * Use the {@link Books_Genres_frag#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Books_Genres_frag extends Fragment {

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

    public Books_Genres_frag() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Books_Genres_frag.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Books_Genres_frag newInstance(String param1, String param2) {
//        Books_Genres_frag fragment = new Books_Genres_frag();
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
        View view =  inflater.inflate(R.layout.fragment_books__genres_frag, container, false);
        recyclerView = view.findViewById(R.id.books_genres_recyclerview);
        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.arts_and_entertainment_logo,"Arts and entertainment"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.biographics_and_memoirs_logo,"Biographies and memoirs"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.business_and_investing_logo,"Business and investing"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.childrens_books_logo,"Children's books"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.computers_and_technology_logo,"Computers and technology"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.cooking_food_and_wine_logo,"Cooking, food and wine"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.engineering_logo,"Engineering"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.fiction_and_literature_logo,"Fiction and literature"));
        arrayList.add(new Categories_and_genres_Datamodel(R.drawable.foreign_language_andstudy_logo,"Foreign language and study aids"));


        recyclerView.setAdapter(new Categories_and_genres_Adapter(getContext(),arrayList));


        return view;
    }
}