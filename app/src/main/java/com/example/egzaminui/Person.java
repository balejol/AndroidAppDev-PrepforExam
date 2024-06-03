package com.example.egzaminui;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "first_name")
    private String name;

    @NonNull
    @ColumnInfo(name = "last_name")
    private String surname;

    @NonNull
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;
}