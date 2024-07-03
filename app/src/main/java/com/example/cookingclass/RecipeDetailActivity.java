package com.example.cookingclass;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    private ImageView recipeImageView;
    private TextView recipeTitleTextView;
    private TextView recipeIngredientsTextView;
    private TextView recipeInstructionsTextView;
    private Button buttonStartTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeImageView = findViewById(R.id.recipe_image_view);
        recipeTitleTextView = findViewById(R.id.recipe_title_text_view);
        recipeIngredientsTextView = findViewById(R.id.recipe_ingredients_text_view);
        recipeInstructionsTextView = findViewById(R.id.recipe_instructions_text_view);
        buttonStartTimer = findViewById(R.id.button_start_timer);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");

        if (recipe != null) {
            recipeImageView.setImageResource(recipe.getImageResourceId());
            recipeTitleTextView.setText(recipe.getTitle());
            recipeIngredientsTextView.setText(recipe.getIngredients());
            recipeInstructionsTextView.setText(recipe.getInstructions());
        }

        buttonStartTimer.setOnClickListener(view -> {
            Intent intent = new Intent(RecipeDetailActivity.this, MainActivity.class);
            intent.putExtra("action", "show_timer_fragment");
            intent.putExtra("recipe_name", recipe.getTitle());
            startActivity(intent);
        });
    }
}
