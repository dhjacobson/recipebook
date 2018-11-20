package com.example.recipebook.activities.my_recipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.recipebook.R;
import com.example.recipebook.utils.RecipeUtils;
import com.example.recipebook_commons.models.Recipe;

import java.util.List;

public class MyRecipesAdapter extends RecyclerView.Adapter<MyRecipesAdapter.RecipeViewHolder> {
    private List<Recipe> recipes;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titleView;
        public TextView byLineView;
        public TextView totalTimeView;
        public RecipeViewHolder(View view) {
            super(view);
            titleView = view.findViewById(R.id.title);
            byLineView = view.findViewById(R.id.by_line);
            totalTimeView = view.findViewById(R.id.total_time);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyRecipesAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_row, parent, false);

        return new RecipeViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        // - get element from your dataset at this position
        Recipe recipe = recipes.get(position);
        holder.titleView.setText(recipe.getTitle());
        holder.byLineView.setText(String.format("By %s", recipe.getAuthor()));
        if (recipe.getTotalTimeMinutes() != null) {
            holder.totalTimeView.setText(RecipeUtils.minutesToText(recipe.getTotalTimeMinutes()));
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return recipes.size();
    }
}