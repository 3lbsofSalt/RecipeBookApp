package com.example.recipebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.recipebook.models.Recipe;
import com.example.recipebook.viewmodels.RecipeAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Recipe recipe = new Recipe();
            recipe.name = "Recipe " + i;
            recipe.description = "Description " + i;
            recipes.add(recipe);
        }

        ExtendedFloatingActionButton addRecipeButton = findViewById(R.id.add_recipe_button);
        RecyclerView recyclerView = findViewById(R.id.recipe_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecipeAdapter(recipes));

        addRecipeButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddRecipeScreen.class);
            startActivity(intent);
        });
    }
}