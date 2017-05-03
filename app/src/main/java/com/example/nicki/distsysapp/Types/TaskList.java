package com.example.nicki.distsysapp.Types;

import com.google.api.client.util.Key;

import java.util.List;

/**
 * Created by Thomas on 01-05-2017.
 */

public class TaskList {
    @Key
    List<Task> list;

    public List<Task> getList() {
        return list;
    }
}
