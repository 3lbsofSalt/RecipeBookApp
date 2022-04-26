package com.example.recipebook;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipebook.viewmodels.RecipesViewModel;

public class ViewRecipeScreen extends AppCompatActivity {
    RecipesViewModel viewModel = new ViewModelProvider(this).get(RecipesViewModel.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe_screen);

        viewModel.getCurrentRecipe().observe(this, (recipe) -> {
            if(!recipe.imagePath.isEmpty()) {
                ImageView image = findViewById(R.id.imageView);
                image.setImageURI(Uri.parse(recipe.imagePath));
            }

            TextView name = findViewById(R.id.recipe_name);
            name.setText(recipe.name);

            TextView description = findViewById(R.id.recipe_description);
            description.setText(recipe.description);

            ArrayAdapter<String> ingredientsAdapter;
            ArrayAdapter<String> stepsAdapter;

            ListView ingredientsList;
            ListView stepsList;

            ingredientsList = findViewById(R.id.ingredients_list);
            stepsList = findViewById(R.id.steps_list);

            ingredientsAdapter = new ArrayAdapter<>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, recipe.ingredients);
            ingredientsList.setAdapter(ingredientsAdapter);

            stepsAdapter = new ArrayAdapter<>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, recipe.steps);
            stepsList.setAdapter(stepsAdapter);


        });
    }
}
