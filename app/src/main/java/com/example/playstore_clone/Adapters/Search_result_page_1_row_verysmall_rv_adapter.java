package com.example.playstore_clone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetch_image_interface;
import com.example.playstore_clone.Fetching_data_from_firebase.Fetching_Images_from_FirebaseStorage;
import com.example.playstore_clone.R;
import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_book_verysmall_Datamodel;
import com.example.playstore_clone.reyckerview_models.Search_result_page_1_row_verysmall_withImages_rv_model;

import java.util.ArrayList;
import java.util.Objects;

public class Search_result_page_1_row_verysmall_rv_adapter extends RecyclerView.Adapter<Search_result_page_1_row_verysmall_rv_adapter.Viewholder> {
    Context context;

    String bottom_tab_name;

    String itemType;
    ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppList;

    ArrayList<Search_result_page_1_row_book_verysmall_Datamodel> bookslist;

    ArrayList<Bitmap> logoList;

    boolean itemFound;

    onBookCLick onbookclicked;

    onGameAppClick ongameappClicked;

    public Search_result_page_1_row_verysmall_rv_adapter(Context context, String itemType, boolean itemFound, ArrayList<Bitmap> logoList, ArrayList<Search_result_page_1_row_verysmall_withImages_rv_model> gameAppList, ArrayList<Search_result_page_1_row_book_verysmall_Datamodel> bookslist, onBookCLick onbookclicked, onGameAppClick ongameappClicked) {
        this.context = context;
        this.itemFound = itemFound;
        this.gameAppList = gameAppList;
        this.bookslist = bookslist;
        this.onbookclicked = onbookclicked;
        this.ongameappClicked = ongameappClicked;
        this.itemType = itemType;
        this.logoList = logoList;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (gameAppList!=null){
            view = LayoutInflater.from(context).inflate(R.layout.search_result_page_rv_layout,parent,false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.search_result_page_1_row_book_verysmall_layout, parent, false);
        }

        return new Viewholder(view);
    }

    public interface onBookCLick{

        void onClick(int position);
    }

    public interface onGameAppClick{

        void onClick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        if (gameAppList!=null){

            holder.logo.setImageBitmap(logoList.get(position));

            holder.name.setText(gameAppList.get(position).getName());
            holder.company_name.setText(gameAppList.get(position).getCompany_name());
            holder.rating.setText(gameAppList.get(position).getRating());
            holder.size.setText(gameAppList.get(position).getSize());
            holder.total_downlaods.setText(gameAppList.get(position).getTotal_downloads());

            holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

            if (Objects.equals(gameAppList.get(position).getLocation(), "All_Apps_Data")){
                bottom_tab_name = "Apps Tab";
            } else if (Objects.equals(itemType, "App")) {
                bottom_tab_name = "Apps Tab";
            } else {
                bottom_tab_name = "Games Tab";
            }

            holder.recyclerView.setAdapter(new App_or_game_images_recyclerview_Adapter(context, gameAppList.get(position).getImages(),bottom_tab_name));

            if (itemFound && position==0){
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.expand_logo.setScaleY(-1);
            }else {
                holder.recyclerView.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ongameappClicked.onClick(position);
                }
            });



            holder.expand_logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if( holder.expand_logo.getScaleY() == 1){

                        holder.expand_logo.setScaleY(-1);
                        holder.recyclerView.setVisibility(View.VISIBLE);

                    }else {

                        holder.expand_logo.setScaleY(1);
                        holder.recyclerView.setVisibility(View.GONE);
                    }
                }
            });

        }else {

            holder.bookLogo.setImageBitmap(logoList.get(position));
            holder.bookName.setText(bookslist.get(position).getName());
            holder.bookAuthorName.setText(bookslist.get(position).getAuthor_name());
            holder.bookType.setText(bookslist.get(position).getType());
            holder.bookRatings.setText(bookslist.get(position).getRatings());
            holder.bookPrice.setText(bookslist.get(position).getPrice());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onbookclicked.onClick(position);
                }
            });

        }


    }
    @Override
    public int getItemCount() {

        if (gameAppList!=null){
            return gameAppList.size();
        }else {
            return bookslist.size();
        }

    }


    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView logo, expand_logo;
        TextView name, company_name, rating, size,total_downlaods;

        ImageView bookLogo;

        TextView bookName, bookAuthorName, bookType, bookRatings, bookPrice;

        LinearLayout layout;
        RecyclerView recyclerView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.search_result_page_logo);
            name = itemView.findViewById(R.id.search_result_page_name);
            company_name = itemView.findViewById(R.id.search_result_page_company_name);
            rating = itemView.findViewById(R.id.search_result_page_rating);
            size = itemView.findViewById(R.id.search_result_page_size);
            total_downlaods = itemView.findViewById(R.id.search_result_page_total_dowloads);
            recyclerView = itemView.findViewById(R.id.search_result_page_game_images_rv);
            expand_logo = itemView.findViewById(R.id.search_result_page_expand_logo);
            layout = itemView.findViewById(R.id.search_result_page__layout_to_expand);

            bookLogo = itemView.findViewById(R.id.searchpage_1_row_books_verysmall_booklogo);
            bookName = itemView.findViewById(R.id.searchpage_1_row_books_verysmall_bookname);
            bookAuthorName = itemView.findViewById(R.id.searchpage_1_row_books_verysmall_authorname);
            bookType = itemView.findViewById(R.id.searchpage_1_row_books_verysmall_type);
            bookRatings = itemView.findViewById(R.id.searchpage_1_row_books_verysmall_ratings);
            bookPrice = itemView.findViewById(R.id.searchpage_1_row_books_verysmall_bookprice);


        }
    }

}
