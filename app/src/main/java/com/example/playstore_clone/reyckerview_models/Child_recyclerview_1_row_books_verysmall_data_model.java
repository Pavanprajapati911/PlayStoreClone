package com.example.playstore_clone.reyckerview_models;

public class Child_recyclerview_1_row_books_verysmall_data_model {
    private String image;
    private String name,author_name,price, type, ratings;

    public Child_recyclerview_1_row_books_verysmall_data_model(){

    }

    public Child_recyclerview_1_row_books_verysmall_data_model(String image, String name, String author_name, String price, String type, String ratings) {
        this.image = image;
        this.name = name;
        this.author_name = author_name;
        this.price = price;
        this.type = type;
        this.ratings = ratings;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
