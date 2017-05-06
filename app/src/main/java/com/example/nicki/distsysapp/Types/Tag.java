package com.example.nicki.distsysapp.Types;

import com.google.api.client.util.Key;

/**
 * Created by Thomas on 26-04-2017.
 */

public class Tag {
    public String name;
    public int id;

    public Tag(int id, String name){
        this.id = id;
        this.name = name;
    }
}
