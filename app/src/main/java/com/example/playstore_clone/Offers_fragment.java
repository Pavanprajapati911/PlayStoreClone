package com.example.playstore_clone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.playstore_clone.Adapters.Child_Recyclerview_offer_Adapter;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Data_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.OffersLayout_interface;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_offer_datamodel;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Offers_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Offers_fragment extends Fragment {
    RecyclerView recyclerView;

    RelativeLayout loadingViewBackground;

    LottieAnimationView loadingView;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Offers_fragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Offers_fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Offers_fragment newInstance(String param1, String param2) {
//        Offers_fragment fragment = new Offers_fragment();
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
        View view= inflater.inflate(R.layout.fragment_offers_fragment, container, false);

        recyclerView = view.findViewById(R.id.child_rv_offers);
        loadingView = view.findViewById(R.id.loading_animation_offers);
        loadingViewBackground = view.findViewById(R.id.loading_animation_Background_offers);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
        data_from_database.getOffersDetails(new OffersLayout_interface() {
            @Override
            public void onDataFetched(ArrayList<Child_Recyclerview_offer_datamodel> arrayList) {

                recyclerView.setAdapter(new Child_Recyclerview_offer_Adapter(getContext(), arrayList, new Child_Recyclerview_offer_Adapter.Itemonclicklistener() {
                    @Override
                    public void onclick(int position) {

                        Intent intent = new Intent(getContext(),Offrs_details_Activity.class);
                        intent.putExtra("offerGameAppName",arrayList.get(position).getName());
                        intent.putExtra("eventOfferNumber",arrayList.get(position).getEvent_or_offer_number());
                        intent.putExtra("appType",arrayList.get(position).getType());
                        intent.putExtra("appPrice",arrayList.get(position).getPrice());
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


        return view;
    }
}