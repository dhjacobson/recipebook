package com.example.recipebook.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.recipebook.R;
import com.example.recipebook.decorations.PrettyDividerItemDecoration;

import com.example.recipebook.activities.my_recipes.MyRecipesAdapter;
import com.example.recipebook.activities.my_recipes.RecyclerItemTouchListener;
import com.example.recipebook.activities.recipe.RecipeActivity;
import com.example.recipebook_commons.api.StupidDataAccessLayer;
import com.example.recipebook_commons.api.RecipebookApiError;
import com.example.recipebook_commons.models.Recipe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    private RecyclerView myRecipesRecyclerView;
    private RecyclerView.Adapter recipesAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecipesRecyclerView = (RecyclerView) findViewById(R.id.my_recipes_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        myRecipesRecyclerView.setHasFixedSize(true);

        // use a linear layout manager and default item animator
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        myRecipesRecyclerView.setLayoutManager(layoutManager);
        myRecipesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // add dividers between items
        myRecipesRecyclerView.addItemDecoration(new PrettyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        // specify an adapter
        recipesAdapter = new MyRecipesAdapter(recipes);
        myRecipesRecyclerView.setAdapter(recipesAdapter);

        // specify what happens when you click on a recipe
        RecyclerItemTouchListener recyclerItemTouchListener = new RecyclerItemTouchListener(
            getApplicationContext(),
            myRecipesRecyclerView,
            new RecyclerItemTouchListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
//                    Toast.makeText(getApplicationContext(), recipe.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
                    intent.putExtra("recipe_id", position);
                    intent.putExtra("is_edit_mode", false);
                    startActivity(intent);
                }

                @Override
                public void onLongItemClick(View view, int position) {

                }
            }
        );
        myRecipesRecyclerView.addOnItemTouchListener(recyclerItemTouchListener);

        // prepare sample data
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("http://%s:8080/recipes/all", "10.0.2.2");

        // Request a string response from the provided URL.
        StringRequest fetchRecipeRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            List<Recipe> recipesFromAPI = objectMapper.readValue(response.toString(), new TypeReference<List<Recipe>>(){});
                            recipes.addAll(recipesFromAPI);
                        } catch (IOException e) {
                            recipes.add(new Recipe("Error parsing API response."));
                        }
                        recipesAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        recipes.add(new Recipe("Could not fetch recipes from RecipeBook API."));recipesAdapter.notifyDataSetChanged();
                        recipesAdapter.notifyDataSetChanged();
                    }
                });

        queue.add(fetchRecipeRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_settings:
//                // User chose the "Settings" item, show the app settings UI...
//                return true;

            case R.id.action_add_recipe:
                // User touches the plus to add a new recipe
                // create a new empty recipe
//                StupidDataAccessLayer dal = new StupidDataAccessLayer("192.168.1.103");
//                int recipeId = dal.createRecipe(new Recipe("Horse Soup"));

                // take us to the edit screen for the new recipe
//                MainActivity activity = (MainActivity) view.getContext();
//                this.transitionToNewRecipe(recipeId);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void transitionToNewRecipe(int recipeId) {

        Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
        intent.putExtra("recipe_id", recipeId);
        intent.putExtra("is_edit_mode", true);
        startActivity(intent);
    }

}
