package com.dhjacobson.recipebook.activities.recipe.edit;

import android.view.View;
import com.dhjacobson.recipebook.activities.recipe.RecipeActivity;

public class OnClickListenerEditRecipe implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        RecipeActivity activity = (RecipeActivity) view.getContext();
        activity.transitionToEdit();
    }
}
