package com.example.playstore_clone.reyckerview_models;

public class Parent_rv_model {
    public String title;
    public int layout_type;

    public Parent_rv_model(){

    }

    public Parent_rv_model(int layout_type, String title){

        this.layout_type = layout_type;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLayout_type() {
        return layout_type;
    }

    public void setLayout_type(int layout_type) {
        this.layout_type = layout_type;
    }
}
