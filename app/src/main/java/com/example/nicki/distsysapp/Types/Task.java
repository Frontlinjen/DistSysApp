package com.example.nicki.distsysapp.Types;

import java.util.List;

/**
 * Created by nicki on 5/1/17.
 */

public class Task {
    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getETC() {
        return ETC;
    }

    public int getSupplies() {
        return supplies;
    }

    public int getUrgent() {
        return urgent;
    }

    public int getViews() {
        return views;
    }

    public int getZipaddress() {
        return zipaddress;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStreet() {
        return street;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public List<Integer> getTags() {
        return tags;
    }

    int id, price, ETC, supplies, urgent, views, zipaddress;
    String title, description, street, creatorid;
    List<Integer> tags;
}
