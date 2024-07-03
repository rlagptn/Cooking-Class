package com.example.cookingclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    RecipesFragment recipesFragment = new RecipesFragment();
    TimerFragment timerFragment = new TimerFragment();
    FeedbackFragment feedbackFragment = new FeedbackFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup the bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, homeFragment, "homeFragment")
                .add(R.id.container, recipesFragment, "recipesFragment")
                .add(R.id.container, timerFragment, "timerFragment")
                .add(R.id.container, feedbackFragment, "feedbackFragment")
                .hide(recipesFragment)
                .hide(timerFragment)
                .hide(feedbackFragment)
                .commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    getSupportFragmentManager().beginTransaction().hide(recipesFragment).hide(timerFragment).hide(feedbackFragment).show(homeFragment).commit();
                    return true;
                } else if (id == R.id.recipes) {
                    getSupportFragmentManager().beginTransaction().hide(homeFragment).hide(timerFragment).hide(feedbackFragment).show(recipesFragment).commit();
                    return true;
                } else if (id == R.id.timer) {
                    getSupportFragmentManager().beginTransaction().hide(homeFragment).hide(recipesFragment).hide(feedbackFragment).show(timerFragment).commit();
                    return true;
                } else if (id == R.id.feedback) {
                    getSupportFragmentManager().beginTransaction().hide(homeFragment).hide(recipesFragment).hide(timerFragment).show(feedbackFragment).commit();
                    return true;
                }

                return false;
            }
        });

        // Handle intent from RecipeDetailActivity
        if (getIntent() != null && "show_timer_fragment".equals(getIntent().getStringExtra("action"))) {
            String recipeName = getIntent().getStringExtra("recipe_name");
            switchToTimerFragment(recipeName);
        }
    }

    // Overloaded method to switch to TimerFragment with recipe name
    public void switchToTimerFragment(String recipeName) {
        Bundle args = new Bundle();
        args.putString("recipe_name", recipeName);
        timerFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(recipesFragment)
                .hide(feedbackFragment)
                .show(timerFragment)
                .commit();

        // Set the bottom navigation item to Timer
        bottomNavigationView.setSelectedItemId(R.id.timer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_login) {
            new AlertDialog.Builder(this)
                    .setTitle("Login")
                    .setMessage("Do you want to login?")
                    .setIcon(R.drawable.baseline_login_24)
                    .setPositiveButton("Definitely!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int btn) {
                            Toast.makeText(getApplicationContext(), "Logging in..", Toast.LENGTH_SHORT).show();
                        }})
                    .setNegativeButton("Nope!", null).show();
            return true;
        } else if (id == R.id.action_settings) {
            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                }
            }, 12, 0, true).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
