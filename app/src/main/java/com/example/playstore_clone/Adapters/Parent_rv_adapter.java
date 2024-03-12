package com.example.playstore_clone.Adapters;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.GameApp_Content_Activity;

import com.example.playstore_clone.Books_Content_Activity;
import com.example.playstore_clone.Fetching_data_from_firebase.EventLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_names_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Data_from_Database;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_gameApp_names_2Row_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_gameApp_names_3Rowinterface;
import com.example.playstore_clone.Fetching_data_from_firebase.OneRowBigLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.OneRowBookLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.OneRowLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.OneRowSmallLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.ThreeRowLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.TwoRowLayout_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Get_GameAppBooks_names_interface;
import com.example.playstore_clone.Offrs_details_Activity;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_big_DataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_books_DataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_data_model;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_1_row_small_Datamodel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_2_row_getNames_dataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_3_row_DataModel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_3_row_getNames_datamodel;
import com.example.playstore_clone.reyckerview_models.Child_Recyclerview_event_data_model;
import com.example.playstore_clone.reyckerview_models.Child_Recylerview_2_row_data_model;
import com.example.playstore_clone.reyckerview_models.Parent_rv_model;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class Parent_rv_adapter extends RecyclerView.Adapter<Parent_rv_adapter.viewholder> {
    ArrayList<Parent_rv_model> parent_rv_array;

    LottieAnimationView loadingView;
    RelativeLayout loadingViewBackground;
    Context context;

    String bottom_tab_name, top_tab_name, freeOrPremium, type;




    int layout_type_event=0,
            layout_type_1_row=1,
            layout_type_2_row=2,
            layout_type_3_row=3,
            layout_type_1_row_big=4,
            layout_type_1_row_small=5,
            layout_type_1_row_books=6;



    public Parent_rv_adapter(ArrayList<Parent_rv_model> parent_rv_array, Context context, String bottom_tab_name, String top_tab_name, String freeOrPremium, String type, LottieAnimationView loadingView, RelativeLayout loadingViewBackground){
        this.parent_rv_array = parent_rv_array;
        this.context = context;
        this.bottom_tab_name=bottom_tab_name;
        this.top_tab_name = top_tab_name;
        this.freeOrPremium = freeOrPremium;
        this.type=type;
        this.loadingView = loadingView;
        this.loadingViewBackground = loadingViewBackground;

    }

    @Override
    public int getItemViewType(int position) {
        if(parent_rv_array.get(position).layout_type== layout_type_event){
            return layout_type_event;
        } else if (parent_rv_array.get(position).layout_type== layout_type_1_row) {

            return layout_type_1_row;
        } else if (parent_rv_array.get(position).layout_type== layout_type_2_row) {

            return layout_type_2_row;
        } else if (parent_rv_array.get(position).layout_type== layout_type_3_row) {

            return layout_type_3_row;
        } else if (parent_rv_array.get(position).layout_type== layout_type_1_row_big) {

            return layout_type_1_row_big;
        } else if (parent_rv_array.get(position).layout_type== layout_type_1_row_small) {

            return layout_type_1_row_small;
        } else if (parent_rv_array.get(position).layout_type== layout_type_1_row_books) {

            return layout_type_1_row_books;
        }else {

            return -1;
        }
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if(viewType== layout_type_event){
            view = inflater.inflate(R.layout.child_recyclerview_layout,parent,false);
        } else if (viewType== layout_type_1_row) {
            view = inflater.inflate(R.layout.child_recyclerview_1_row_layout,parent,false);
        } else if (viewType== layout_type_2_row) {
            view = inflater.inflate(R.layout.child_recyclerview_2_row_layout,parent,false);
        } else if (viewType== layout_type_3_row) {
            view = inflater.inflate(R.layout.child_recyclerview_3_row_layout,parent,false);
        } else if(viewType== layout_type_1_row_big){
            view = inflater.inflate(R.layout.child_recyclerview_1_row_big_layout,parent,false);
        } else if (viewType== layout_type_1_row_small) {
            view = inflater.inflate(R.layout.child_recyclerview_1_row_small_layout,parent,false);
        }else {
            view = inflater.inflate(R.layout.child_recyclerview_1_row_books_layout,parent,false);
        }

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {

                if(holder.getItemViewType()== layout_type_event){

                            if(parent_rv_array.get(position).title.equals("No title")){
                                holder.layout_to_remove.setVisibility(View.GONE);
                            }else {
                                holder.getAdapterPosition();
                                holder.Child_Recyclerview_title.setText(parent_rv_array.get(position).title);
                            }

                            ArrayList<Child_Recyclerview_event_data_model> child_rv_array = new ArrayList<>();
                            holder.Child_Recyclerview_data.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

                            Fetch_names_from_Database namesFromDatabase = new Fetch_names_from_Database();
                            namesFromDatabase.get_Game_or_App_Names(position, bottom_tab_name, top_tab_name, new Get_GameAppBooks_names_interface() {
                                @Override
                                public void get_game_or_app_names(ArrayList<String> names) {

                                    Fetching_Data_from_Database database = new Fetching_Data_from_Database();
                                    database.eventlayout_GameApp_Details(names, type,new EventLayout_interface() {
                                        @Override
                                        public void onDataFetched(ArrayList<Child_Recyclerview_event_data_model> arrayList) {

                                            child_rv_array.addAll(arrayList);

                                            Child_Recyclerview_event_Adapter adapter = new Child_Recyclerview_event_Adapter(context, child_rv_array, new Child_Recyclerview_event_Adapter.itemOnClick() {
                                                @Override
                                                public void onClick(int position, int id) {

                                                    if(id == R.id.child_recyclerview__navigate_to_app_content_page){
                                                        Intent intent = new Intent(context, GameApp_Content_Activity.class);
                                                        intent.putExtra("GameAppName", arrayList.get(position).getName());
                                                        intent.putExtra("Bottom_Tab", bottom_tab_name);
                                                        intent.putExtra("Top_Tab",top_tab_name);
                                                        context.startActivity(intent);
                                                    }else {
                                                        Intent intent = new Intent(context, Offrs_details_Activity.class);
                                                        intent.putExtra("offerGameAppName",arrayList.get(position).getName());
                                                        intent.putExtra("offernumber",arrayList.get(position).getEvent_or_offer_number());
                                                        intent.putExtra("Bottom_Tab",bottom_tab_name);
                                                        intent.putExtra("Top_Tab",top_tab_name);
                                                        context.startActivity(intent);
                                                    }
                                                }
                                            });

                                            holder.Child_Recyclerview_data.setAdapter(adapter);
                                            if (position==parent_rv_array.size()-1){
                                                        loadingView.cancelAnimation();
                                                        loadingView.setVisibility(View.GONE);
                                                        loadingViewBackground.setVisibility(View.GONE);

                                            }

                                        }

                                        @Override
                                        public void onDataError(DatabaseError error) {
                                            Log.d("database error ", String.valueOf(error));
                                        }
                                    });

                                }

                                @Override
                                public void cant_Fetch_names(DatabaseError error) {
                                    Log.d("database error ", String.valueOf(error));
                                }
                            });

    // ====================================================================================================================================================//

                } else if (holder.getItemViewType()== layout_type_1_row) {

                            holder.Child_Recyclerview_1_row_title.setText(parent_rv_array.get(position).title);
                            holder.Child_Recyclerview_1_row_data.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

                            Fetch_names_from_Database namesFromDatabase = new Fetch_names_from_Database();
                            namesFromDatabase.get_Game_or_App_Names(position, bottom_tab_name, top_tab_name, new Get_GameAppBooks_names_interface() {
                                @Override
                                public void get_game_or_app_names(ArrayList<String> names) {

                                    Fetching_Data_from_Database getDetails = new Fetching_Data_from_Database();
                                    getDetails.GameApp_1_row_Details(names,type, freeOrPremium, layout_type_1_row,  new OneRowLayout_interface() {
                                        @Override
                                        public void onDetailsFetched(ArrayList<Child_Recyclerview_1_row_data_model> arrayList) {


                                            Child_Recyclerview_1_row_Adapter adapter = new Child_Recyclerview_1_row_Adapter(context,arrayList, top_tab_name, new Child_Recyclerview_1_row_Adapter.itemOnclicklistener() {
                                                @Override
                                                public void onclick(int position) {
                                                    Intent intent = new Intent(context, GameApp_Content_Activity.class);
                                                    intent.putExtra("GameAppName", arrayList.get(position).getName());
                                                    intent.putExtra("Bottom_Tab", bottom_tab_name);
                                                    intent.putExtra("Top_Tab",top_tab_name);
                                                    context.startActivity(intent);
                                                }
                                            });

                                            holder.Child_Recyclerview_1_row_data.setAdapter(adapter);
                                            if (position==parent_rv_array.size()-1){

                                                        loadingView.cancelAnimation();
                                                        loadingView.setVisibility(View.GONE);
                                                        loadingViewBackground.setVisibility(View.GONE);
                                            }

                                        }

                                        @Override
                                        public void onDatabaseError(DatabaseError error) {

                                        }
                                    },null, null, null);
                                }

                                @Override
                                public void cant_Fetch_names(DatabaseError error) {

                                }
                            });
// ===========================================================================================================================================//

                } else if (holder.getItemViewType()== layout_type_2_row) {


                           holder.Child_Recyclerview_2_row_title.setText(parent_rv_array.get(position).title);
                           holder.Child_Recyclerview_2_row_data.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

                           Fetch_names_from_Database namesFromDatabase = new Fetch_names_from_Database();
                           namesFromDatabase.get_GAmeApp_namesFor2Row(position, bottom_tab_name, top_tab_name, new Get_gameApp_names_2Row_interface() {
                               @Override
                               public void onFetchedNames(ArrayList<Child_Recyclerview_2_row_getNames_dataModel> names) {
                                   Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                                   data_from_database.GameApp_2_row_Details(names,type, new TwoRowLayout_interface() {
                                       @Override
                                       public void onDetailsFetched(ArrayList<Child_Recylerview_2_row_data_model> arrayList) {
                                           Child_Recyclerview_2_row_Adapter adapter = new Child_Recyclerview_2_row_Adapter(context, arrayList, new Child_Recyclerview_2_row_Adapter.itemOnClick() {
                                               @Override
                                               public void onClick(int position, String game_or_app_Name) {

                                                   Intent intent = new Intent(context, GameApp_Content_Activity.class);
                                                   intent.putExtra("GameAppName",game_or_app_Name);
                                                   intent.putExtra("Bottom_Tab", bottom_tab_name);
                                                   intent.putExtra("Top_Tab",top_tab_name);
                                                   context.startActivity(intent);
                                               }
                                           });

                                           holder.Child_Recyclerview_2_row_data.setAdapter(adapter);
                                           if (position==parent_rv_array.size()-1){

                                                       loadingView.cancelAnimation();
                                                       loadingView.setVisibility(View.GONE);
                                                       loadingViewBackground.setVisibility(View.GONE);


                                           }
                                       }

                                       @Override
                                       public void onCancelled(DatabaseError error) {

                                       }
                                   });

                               }

                               @Override
                               public void onCancelled(DatabaseError error) {

                               }
                           });


                } else if (holder.getItemViewType()== layout_type_3_row) {


                           holder.Child_Recyclerview_3_row_title.setText(parent_rv_array.get(position).title);
                           holder.Child_Recyclerview_3_row_data.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

                          Fetch_names_from_Database namesFromDatabase = new Fetch_names_from_Database();
                          namesFromDatabase.get_GAmeApp_namesFor3Row(position, bottom_tab_name, top_tab_name, new Get_gameApp_names_3Rowinterface() {
                              @Override
                              public void onFetchedNames(ArrayList<Child_Recyclerview_3_row_getNames_datamodel> names) {


                                  Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                                  data_from_database.GameApp_3_row_Details(names,type, new ThreeRowLayout_interface() {
                                      @Override
                                      public void onFetchedDetails(ArrayList<Child_Recyclerview_3_row_DataModel> arrayList) {

                                          Child_Recyclerview_3_row_Adapter adapter = new Child_Recyclerview_3_row_Adapter(context, arrayList, new Child_Recyclerview_3_row_Adapter.itemOnClick() {
                                              @Override
                                              public void onClick(int position, String app_or_game_Name) {
                                                  Intent intent = new Intent(context, GameApp_Content_Activity.class);
                                                  intent.putExtra("GameAppName",app_or_game_Name);
                                                  intent.putExtra("Bottom_Tab", bottom_tab_name);
                                                  intent.putExtra("Top_Tab",top_tab_name);
                                                  context.startActivity(intent);
                                              }
                                          });

                                          holder.Child_Recyclerview_3_row_data.setAdapter(adapter);
                                          if (position==parent_rv_array.size()-1){
                                                      loadingView.cancelAnimation();
                                                      loadingView.setVisibility(View.GONE);
                                                      loadingViewBackground.setVisibility(View.GONE);

                                          }

                                      }

                                      @Override
                                      public void onCancelled(DatabaseError error) {

                                      }
                                  });

                              }

                              @Override
                              public void onCancelled(DatabaseError error) {

                              }
                          });
// ======================================================================================================================//

                } else if(holder.getItemViewType()== layout_type_1_row_big){

                            holder.Child_Recyclerview_1_row_big_title.setText(parent_rv_array.get(position).title);
                            holder.Child_Recyclerview_1_row_big_data.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));


                            Fetch_names_from_Database namesFromDatabase = new Fetch_names_from_Database();
                            namesFromDatabase.get_Game_or_App_Names(position, bottom_tab_name, top_tab_name, new Get_GameAppBooks_names_interface() {
                                @Override
                                public void get_game_or_app_names(ArrayList<String> names) {
                                    Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                                    data_from_database.GameApp_1_row_Details(names, type, freeOrPremium,layout_type_1_row_big, null, new OneRowBigLayout_interface() {
                                        @Override
                                        public void onDetailsFetched(ArrayList<Child_Recyclerview_1_row_big_DataModel> arrayList) {
                                            Child_Recyclerview_1_row_big_Adapter adapter = new Child_Recyclerview_1_row_big_Adapter(context, arrayList, new Child_Recyclerview_1_row_big_Adapter.itemOnCLick() {
                                                @Override
                                                public void onCLick(int position) {
                                                    Intent intent = new Intent(context, GameApp_Content_Activity.class);
                                                    intent.putExtra("GameAppName", arrayList.get(position).getName());
                                                    intent.putExtra("Bottom_Tab", bottom_tab_name);
                                                    intent.putExtra("Top_Tab",top_tab_name);
                                                    context.startActivity(intent);
                                                }
                                            });

                                            holder.Child_Recyclerview_1_row_big_data.setAdapter(adapter);
                                            if (position==parent_rv_array.size()-1){
                                                        loadingView.cancelAnimation();
                                                        loadingView.setVisibility(View.GONE);
                                                        loadingViewBackground.setVisibility(View.GONE);

                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    }, null, null);

                                }

                                @Override
                                public void cant_Fetch_names(DatabaseError error) {

                                }
                            });


                } else if (holder.getItemViewType()== layout_type_1_row_small) {

                            holder.Child_Recyclerview_1_row_small_title.setText(parent_rv_array.get(position).title);
                            holder.Child_Recyclerview_1_row_small_data.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

                            Fetch_names_from_Database namesFromDatabase = new Fetch_names_from_Database();
                            namesFromDatabase.get_Game_or_App_Names(position, bottom_tab_name, top_tab_name, new Get_GameAppBooks_names_interface() {
                                @Override
                                public void get_game_or_app_names(ArrayList<String> names) {
                                    Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                                    data_from_database.GameApp_1_row_Details(names, type, freeOrPremium,layout_type_1_row_small, null, null, new OneRowSmallLayout_interface() {
                                        @Override
                                        public void onDetailsFetched(ArrayList<Child_Recyclerview_1_row_small_Datamodel> arrayList) {

                                            ArrayList<Bitmap> logoList = new ArrayList<>();
                                            for (Child_Recyclerview_1_row_small_Datamodel logoLocation : arrayList){

                                                Fetching_Images_from_FirebaseStorage imagesFromFirebaseStorage = new Fetching_Images_from_FirebaseStorage();
                                                imagesFromFirebaseStorage.Image_to_fetch(logoLocation.getLogo(), new Fetch_image_interface() {
                                                    @Override
                                                    public void image_FetchSuccess(Bitmap bitmap) {
                                                        logoList.add(bitmap);

                                                        if (logoList.size()==arrayList.size()){
                                                            Child_Recyclerview_1_row_small_Adapter adapter = new Child_Recyclerview_1_row_small_Adapter(context, logoList,arrayList, top_tab_name,new Child_Recyclerview_1_row_small_Adapter.itemOnclicklistener() {
                                                                @Override
                                                                public void onclick(int position) {
                                                                    Intent intent = new Intent(context, GameApp_Content_Activity.class);
                                                                    intent.putExtra("GameAppName", arrayList.get(position).getName());
                                                                    intent.putExtra("Bottom_Tab", bottom_tab_name);
                                                                    intent.putExtra("Top_Tab",top_tab_name);
                                                                    context.startActivity(intent);
                                                                }
                                                            });

                                                            holder.Child_Recyclerview_1_row_small_data.setAdapter(adapter);

                                                            if (position==parent_rv_array.size()-1){
                                                                loadingView.cancelAnimation();
                                                                loadingView.setVisibility(View.GONE);
                                                                loadingViewBackground.setVisibility(View.GONE);
                                                            }

                                                        }

                                                    }

                                                    @Override
                                                    public void image_FetchFailure(Exception exception) {

                                                    }
                                                });

                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    }, null);
                                }

                                @Override
                                public void cant_Fetch_names(DatabaseError error) {

                                }
                            });

                }else {
                           holder.Child_Recyclerview_1_row_books_title.setText(parent_rv_array.get(position).title);
                           holder.Child_Recyclerview_1_row_books_data.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));


                            Fetch_names_from_Database namesFromDatabase = new Fetch_names_from_Database();
                            namesFromDatabase.get_Game_or_App_Names(position, bottom_tab_name, top_tab_name, new Get_GameAppBooks_names_interface() {
                                @Override
                                public void get_game_or_app_names(ArrayList<String> names) {


                                    Fetching_Data_from_Database data_from_database = new Fetching_Data_from_Database();
                                    data_from_database.Get_Books_Details(names, layout_type_1_row_books, new OneRowBookLayout_interface() {
                                        @Override
                                        public void onDataFetched(ArrayList<Child_Recyclerview_1_row_books_DataModel> arrayList) {

                                          holder.Child_Recyclerview_1_row_books_data.setAdapter(new Child_Recyclerview_1_row_books_Adapter(context, null,arrayList, new Child_Recyclerview_1_row_books_Adapter.onItemClick() {
                                              @Override
                                              public void onclick(int position) {
                                                  Intent intent = new Intent(context, Books_Content_Activity.class);
                                                  intent.putExtra("BookName",arrayList.get(position).getName());
                                                  context.startActivity(intent);
                                              }
                                              }));
                                            if (position==parent_rv_array.size()-1){
                                                        loadingView.cancelAnimation();
                                                        loadingView.setVisibility(View.GONE);
                                                        loadingViewBackground.setVisibility(View.GONE);
                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {

                                        }
                                    },null);

                                }

                                @Override
                                public void cant_Fetch_names(DatabaseError error) {

                                }
                            });
                }


    }

    @Override
    public int getItemCount() {
        return parent_rv_array.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        LinearLayout layout_to_remove;
        TextView Child_Recyclerview_title, Child_Recyclerview_1_row_title, Child_Recyclerview_2_row_title, Child_Recyclerview_3_row_title, Child_Recyclerview_1_row_big_title, Child_Recyclerview_1_row_small_title, Child_Recyclerview_1_row_books_title;
        RecyclerView Child_Recyclerview_data, Child_Recyclerview_1_row_data, Child_Recyclerview_2_row_data, Child_Recyclerview_3_row_data, Child_Recyclerview_1_row_big_data, Child_Recyclerview_1_row_small_data, Child_Recyclerview_1_row_books_data;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            layout_to_remove = itemView.findViewById(R.id.child_viewpager_layout_to_remove);
            Child_Recyclerview_title = itemView.findViewById(R.id.child_rv_textview);
            Child_Recyclerview_1_row_title = itemView.findViewById(R.id.child_vp_1_row_textview);
            Child_Recyclerview_2_row_title = itemView.findViewById(R.id.child_vp_2_row_textview);
            Child_Recyclerview_3_row_title = itemView.findViewById(R.id.child_vp_3_row_textview);
            Child_Recyclerview_1_row_big_title = itemView.findViewById(R.id.child_vp_1_row_big_textview);
            Child_Recyclerview_1_row_small_title = itemView.findViewById(R.id.child_rv_1_row_small_textview);
            Child_Recyclerview_1_row_books_title = itemView.findViewById(R.id.child_rv_1_row_books_textview);


            Child_Recyclerview_data = itemView.findViewById(R.id.child_Recyclerview);
            Child_Recyclerview_1_row_data = itemView.findViewById(R.id.child_REcyclerview_1_row);
            Child_Recyclerview_2_row_data = itemView.findViewById(R.id.child_Recyclerview_2_row);
            Child_Recyclerview_3_row_data = itemView.findViewById(R.id.child_Recyclerview_3_row);
            Child_Recyclerview_1_row_big_data = itemView.findViewById(R.id.child_Recyclerview_1_row_big);
            Child_Recyclerview_1_row_small_data = itemView.findViewById(R.id.child_Recyclerview_1_row_small);
            Child_Recyclerview_1_row_books_data = itemView.findViewById(R.id.child_Recyclerview_1_row_books);




        }
    }

}
