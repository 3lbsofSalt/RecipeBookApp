package com.example.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExtendedFloatingActionButton addRecipeButton = findViewById(R.id.add_recipe_button);

        addRecipeButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddRecipeScreen.class);
            startActivity(intent);
        });
    }

    // pee
}