package com.example.egzaminui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HWActivity extends AppCompatActivity {

    private ListView listView;
    private MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwactivity);

        listView = (ListView) findViewById(R.id.list);
        ArrayList<Item> myList = new ArrayList<Item>();
        myList.add(new Item("SERIAL:", Build.getSerial()));
        myList.add(new Item("MODEL:", Build.MODEL));
        myList.add(new Item("ID:", Build.ID));
        myList.add(new Item("MANUFACTURE:", Build.MANUFACTURER));
        myList.add(new Item("BRAND:", Build.BRAND));
        // And so on:)

        mAdapter = new MyListAdapter(this, R.layout.item_main, myList);
        listView.setAdapter(mAdapter);
        setTitle("Info");
    }
}