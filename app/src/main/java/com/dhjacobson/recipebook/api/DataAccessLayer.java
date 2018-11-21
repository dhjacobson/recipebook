//package com.dhjacobson.recipebook_commons.api;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.dhjacobson.recipebook_commons.models.Recipe;
//import com.dhjacobson.recipebook_commons.models.Recipe;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.List;
//
//
//public class DataAccessLayer {
//    // prepare sample data
//    RequestQueue queue = Volley.newRequestQueue(this);
//    String url = String.format("http://%s:8080/recipes/all", "10.0.2.2");
//
//    // Request a string response from the provided URL.
//    StringRequest fetchRecipeRequest = new StringRequest(Request.Method.GET, url,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    try {
//                        List<Recipe> recipesFromAPI = objectMapper.readValue(response.toString(), new TypeReference<List<Recipe>>(){});
//                        recipes.addAll(recipesFromAPI);
//                    } catch (IOException e) {
//                        recipes.add(new Recipe("Error parsing API response."));
//                    }
//                    recipesAdapter.notifyDataSetChanged();
//
//                }
//            }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            recipes.add(new Recipe("Could not fetch recipes from RecipeBook API."));
//            recipesAdapter.notifyDataSetChanged();\
//        }
//    });
//
//    queue.add(fetchRecipeRequest);
//}
