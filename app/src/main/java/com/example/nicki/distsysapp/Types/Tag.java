package com.example.nicki.distsysapp.Types;

import com.google.api.client.util.Key;

/**
 * Created by Thomas on 26-04-2017.
 */

public class Tag {
    @Key
    int id;
    @Key
    int name;
    @Key
    int parentId;

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public int getParentId() {
        return parentId;
    }
}
