package com.dhjacobson.recipebook.activities.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dhjacobson.recipebook.R;
import com.dhjacobson.recipebook.activities.recipe.edit.OnClickListenerEditRecipe;
import com.dhjacobson.recipebook_commons.models.Recipe;
import com.dhjacobson.recipebook_commons.models.RecipeStep;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RecipeActivity extends AppCompatActivity {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private int recipeId;
    private Recipe recipe;
    private boolean isEditMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // use parameters to determine recipe and isEditMode
        Bundle b = getIntent().getExtras();
        recipeId = b.getInt("recipe_id");
        try {
            recipe = objectMapper.readValue(b.getString("recipe"), Recipe.class);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Could not parse recipe.", Toast.LENGTH_LONG).show();
            return;
        }
        isEditMode = b.getBoolean("is_edit_mode");

        // set content view based on isEditMode
        int layoutId = isEditMode ? R.layout.edit_activity_recipe : R.layout.activity_recipe;
        setContentView(layoutId);

        // title
        TextView titleView = findViewById(R.id.title);
        titleView.setText(recipe.getTitle() + (isEditMode ? " (editing)" : ""));

        // list view for ingredients
        ListView ingredientsView = findViewById(R.id.ingredients);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(
                getApplicationContext(),
                recipe.getIngredients());
        ingredientsView.setAdapter(ingredientsAdapter);


        // scroll view for instructions
        ScrollView stepsView = findViewById(R.id.steps);

        final LinearLayout stepsLayout = new LinearLayout(getApplicationContext());
        stepsLayout.setOrientation(LinearLayout.VERTICAL);
        stepsView.addView(stepsLayout);

        List<RecipeStep> steps = recipe.getSteps();
        for (int i=0; i<steps.size(); i++) {
            TextView stepView = new TextView(getApplicationContext());

            stepView.setText(String.format(Locale.US, "%d. %s", i+1, steps.get(i).getText()));
            stepsLayout.addView(stepView);
        }

        if (isEditMode) {
            EditText newStep = new EditText(getApplicationContext());
            newStep.setText(String.format(Locale.US, "%d. ", steps.size() + 1));
            stepsLayout.addView(newStep);

        }

        // edit recipe floating action button listener
        if (!isEditMode) {
            FloatingActionButton buttonCreateStudent = findViewById(R.id.edit_recipe_button);
            buttonCreateStudent.setOnClickListener(new OnClickListenerEditRecipe());

        }

    }

    public void transitionToEdit() {
        Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
        intent.putExtra("recipe_id", recipeId);
        intent.putExtra("is_edit_mode", true);
        startActivity(intent);
    }
}
