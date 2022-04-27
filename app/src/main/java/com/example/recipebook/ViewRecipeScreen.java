package com.example.recipebook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipebook.viewmodels.RecipesViewModel;

import java.io.File;

public class ViewRecipeScreen extends AppCompatActivity {

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe_screen);

        Intent intent = getIntent();
        long recipe_id = intent.getLongExtra("recipe_id", 1);

        RecipesViewModel viewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
        viewModel.getSingleRecipe(recipe_id).observe(this, (recipe) -> {
            if(!recipe.imagePath.isEmpty()) {
                ImageView image = findViewById(R.id.imageView);
                //Uri photo = Uri.parse("content:/" + recipe.imagePath);

                File myPath = new File(recipe.imagePath);
                Uri photo = Uri.fromFile(myPath);

                if(myPath.exists()) {
                    Log.d("aorstn", "eioarsntoar");
                }

                image.setImageURI(photo);

                //Bitmap bm = BitmapFactory.decodeFile(myPath.getPath());
                //image.setImageDrawable(Drawable.createFromPath(myPath.getPath()));
                //image.setImageBitmap(bm);
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
