package com.example.playstore_clone.reyckerview_models;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Search_result_page_1_row_verysmall_withImages_rv_model {

    private String Logo;
    private String Name, Company_name, Size, Rating, Total_downloads;

    private String location;
    private ArrayList<Bitmap> Images;

    public Search_result_page_1_row_verysmall_withImages_rv_model(){

    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getTotal_downloads() {
        return Total_downloads;
    }

    public void setTotal_downloads(String total_downloads) {
        Total_downloads = total_downloads;
    }

    public ArrayList<Bitmap> getImages() {
        return Images;
    }

    public void setImages(ArrayList<Bitmap> images) {
        Images = images;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
