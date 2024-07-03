package com.example.cookingclass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Update featured recipes and cooking tips text
        TextView featuredRecipe1 = rootView.findViewById(R.id.featuredRecipe1);
        TextView featuredRecipe2 = rootView.findViewById(R.id.featuredRecipe2);
        TextView cookingTip1 = rootView.findViewById(R.id.cookingTip1);
        TextView cookingTip2 = rootView.findViewById(R.id.cookingTip2);

        featuredRecipe1.setText("1. Spaghetti Bolognese - Quick and easy Italian favorite.");
        featuredRecipe2.setText("2. Chicken Curry - Fragrant and flavorful Indian dish.");
        cookingTip1.setText("1. Use fresh ingredients for better taste.");
        cookingTip2.setText("2. Don't forget to preheat the oven before baking.");

        return rootView;
    }
}

