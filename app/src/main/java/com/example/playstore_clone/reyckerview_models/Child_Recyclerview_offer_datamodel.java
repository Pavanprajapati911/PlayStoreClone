package com.example.playstore_clone.reyckerview_models;

public class Child_Recyclerview_offer_datamodel {

    private String Event_or_offer_image;

    private String Event_or_offer_number;
    private String Name, Event_or_offer_title, main_title, Type, Price;

    public Child_Recyclerview_offer_datamodel(){

    }


    public String getEvent_or_offer_image() {
        return Event_or_offer_image;
    }

    public void setEvent_or_offer_image(String event_or_offer_image) {
        Event_or_offer_image = event_or_offer_image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEvent_or_offer_title() {
        return Event_or_offer_title;
    }

    public void setEvent_or_offer_title(String event_or_offer_title) {
        Event_or_offer_title = event_or_offer_title;
    }

    public String getMain_title() {
        return main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title;
    }

    public String getEvent_or_offer_number() {
        return Event_or_offer_number;
    }

    public void setEvent_or_offer_number(String event_or_offer_number) {
        Event_or_offer_number = event_or_offer_number;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
