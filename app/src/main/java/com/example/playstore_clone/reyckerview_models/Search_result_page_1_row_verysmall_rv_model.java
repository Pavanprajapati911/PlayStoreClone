package com.example.playstore_clone.reyckerview_models;

public class Search_result_page_1_row_verysmall_rv_model {

    private String Logo;
    private String Name, Company_name, Size, Rating, Total_downloads;


    Search_result_page_1_row_verysmall_rv_model(){

    }

    public Search_result_page_1_row_verysmall_rv_model(String Logo, String name, String company_name, String size, String rating, String Total_downloads) {
        this.Logo = Logo;
        this.Name = name;
        this.Company_name = company_name;
        this.Size = size;
        this.Rating = rating;
        this.Total_downloads = Total_downloads;


    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        this.Logo = logo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String company_name) {
        this.Company_name = company_name;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        this.Size = size;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        this.Rating = rating;
    }

    public String getTotal_downloads() {
        return Total_downloads;
    }

    public void setTotal_downloads(String total_downloads) {
        this.Total_downloads = total_downloads;
    }
}
