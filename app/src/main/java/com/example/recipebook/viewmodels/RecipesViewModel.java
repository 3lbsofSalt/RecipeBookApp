package com.example.recipebook.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;

import com.example.recipebook.database.AppDatabase;
import com.example.recipebook.models.Recipe;

import java.util.ArrayList;

public class RecipesViewModel extends AndroidViewModel {
    private AppDatabase database;
    public RecipesViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, AppDatabase.class, "recipedb").build();
    }

    public void saveRecipe(String name, ArrayList<String> ingredients, ArrayList<String> steps, String description) {
        new Thread(() -> {
            Recipe newRecipe = new Recipe();

            newRecipe.name = name;
            newRecipe.description = description;
            newRecipe.ingredients = ingredients;
            newRecipe.steps = steps;
            newRecipe.createdAt = System.currentTimeMillis();
            newRecipe.id = database.getRecipesDao().insert(newRecipe);
        }).start();

    }
}
