package com.example.recipebook.models;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String description;

    @ColumnInfo
    public List<String> ingredients;

    @ColumnInfo
    public List<String> steps;

    @ColumnInfo(name = "created_at")
    public long createdAt;
}
