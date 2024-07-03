package com.example.cookingclass;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cookingclass.R;
import com.example.cookingclass.Recipe;
import com.example.cookingclass.RecipeDetailActivity;
import com.example.cookingclass.RecipeAdapter;
import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize recipe list and adapter
        recipeList = new ArrayList<>();

        // Add sample recipes with ingredients and instructions
        recipeList.add(new Recipe("Bolognese pasta (10mins)", R.mipmap.ic_bolognese_foreground,
                "# Ingredients\n\n" +
                        "- 500g pasta\n" +
                        "- 400g ground beef\n" +
                        "- 1 onion, chopped\n" +
                        "- 2 cloves garlic, minced\n" +
                        "- 1 can (400g) crushed tomatoes\n" +
                        "- 1/2 cup red wine (optional)\n" +
                        "- 1/4 cup tomato paste\n" +
                        "- 1 tsp dried oregano\n" +
                        "- 1 tsp dried basil\n" +
                        "- Salt and pepper to taste\n",
                "# Instructions\n\n" +
                        "1. Cook spaghetti according to package instructions.\n" +
                        "2. In a large skillet, brown ground beef over medium heat. Add onion and garlic, sauté until onion is soft.\n" +
                        "3. Stir in crushed tomatoes, tomato paste, red wine (if using), oregano, basil, salt, and pepper. Simmer for 15-20 minutes.\n" +
                        "4. Serve Bolognese sauce over cooked spaghetti. Enjoy!"));

        recipeList.add(new Recipe("Chicken Curry (10mins)", R.mipmap.ic_chickencurry_foreground,
                "# Ingredients\n\n" +
                        "- 2 tbsp vegetable oil\n" +
                        "- 1 onion, chopped\n" +
                        "- 2 cloves garlic, minced\n" +
                        "- 1 tbsp ginger, minced\n" +
                        "- 1 lb chicken breast, diced\n" +
                        "- 2 potatoes, peeled and diced\n" +
                        "- 1 carrot, sliced\n" +
                        "- 1 can (400ml) coconut milk\n" +
                        "- 2 tbsp curry powder\n" +
                        "- 1 tsp turmeric powder\n" +
                        "- Salt and pepper to taste\n",
                "# Instructions\n\n" +
                        "1. Heat oil in a large pot over medium heat. Add onion, garlic, and ginger; sauté until fragrant.\n" +
                        "2. Add diced chicken and cook until lightly browned.\n" +
                        "3. Stir in potatoes, carrot, curry powder, turmeric powder, salt, and pepper. Mix well.\n" +
                        "4. Pour in coconut milk and bring to a boil. Reduce heat and simmer uncovered for 20-25 minutes or until potatoes are tender.\n" +
                        "5. Serve hot with steamed rice. Enjoy!"));

        recipeAdapter = new RecipeAdapter(recipeList, getActivity());
        recyclerView.setAdapter(recipeAdapter);

        recipeAdapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
                intent.putExtra("Recipe", recipeList.get(position));
                startActivity(intent);
            }
        });

        return view;
    }
}
