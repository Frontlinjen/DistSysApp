package com.example.nicki.distsysapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nicki.distsysapp.Types.Tag;

import java.util.ArrayList;

/**
 * Created by nicki on 3/12/17.
 */

public class TaskList extends AppCompatActivity{

    ListView lv;
    TaskCategoryList tcl = new TaskCategoryList();
    String chosenCategory;// = tcl.getTag();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        lv =(ListView)findViewById(R.id.tasks);
        lv.setClickable(true);

        Bundle b = getIntent().getExtras();
        Tag tag = new Tag(b.getInt("id"), b.getString("name"));

        //TODO Get taskList based on tag
        ArrayList<String> categoryList = null;

        //TODO switch to handle that whatever chosen
        switch(chosenCategory){

        }

        lv.setAdapter(new ArrayAdapter< String >(this, R.layout.categories, categoryList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {



            }

        });
    }
}
