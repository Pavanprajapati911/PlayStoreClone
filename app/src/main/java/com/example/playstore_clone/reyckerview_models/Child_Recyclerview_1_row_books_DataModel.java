package com.example.playstore_clone.reyckerview_models;

public class Child_Recyclerview_1_row_books_DataModel {
    private String image;
    private String name,price, ratings;

    public Child_Recyclerview_1_row_books_DataModel(){

    }

    public Child_Recyclerview_1_row_books_DataModel(String image, String name, String price, String ratings) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.ratings = ratings;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
