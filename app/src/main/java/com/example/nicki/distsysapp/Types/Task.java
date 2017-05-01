package com.example.nicki.distsysapp.Types;

/**
 * Created by Thomas on 01-05-2017.
 */

public class Task {
    String title, description, price, provider, urgency, address, ECT;
    int zipAddress, tags;

    public Task(String title, String description, String price, String provider, String urgency, String address, String ECT, int zipAddress, int tags){
        this.title = title;
        this.description = description;
        this.price = price;
        this.provider = provider;
        this.urgency = urgency;
        this.address = address;
        this.ECT = ECT;
        this.zipAddress = zipAddress;
        this.tags = tags;
    }
}
