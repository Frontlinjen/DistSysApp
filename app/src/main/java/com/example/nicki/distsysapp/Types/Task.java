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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getECT() {
        return ECT;
    }

    public void setECT(String ECT) {
        this.ECT = ECT;
    }

    public int getZipAddress() {
        return zipAddress;
    }

    public void setZipAddress(int zipAddress) {
        this.zipAddress = zipAddress;
    }

    public int getTags() {
        return tags;
    }

    public void setTags(int tags) {
        this.tags = tags;
    }
}
