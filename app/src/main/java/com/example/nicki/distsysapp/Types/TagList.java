package com.example.nicki.distsysapp.Types;

import com.google.api.client.util.Key;

import java.util.List;

/**
 * Created by Thomas on 26-04-2017.
 */

public class TagList {
    @Key
    List<Tag> list;

    public List<Tag> getList() {
        return list;
    }
}
