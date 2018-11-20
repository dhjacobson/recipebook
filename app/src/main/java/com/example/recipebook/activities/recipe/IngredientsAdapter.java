package com.example.recipebook.activities.recipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import com.example.recipebook.R;
import com.example.recipebook_commons.models.RecipeIngredient;

import java.util.List;

public class IngredientsAdapter extends ArrayAdapter<RecipeIngredient> {

    private final Context context;
    private final List<RecipeIngredient> ingredients;

    static class ViewHolder {
        public CheckBox checkBox;
    }

    public IngredientsAdapter(@NonNull Context context, @NonNull List<RecipeIngredient> ingredients) {
        super(context, -1, ingredients);
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;

        // reuse views
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.ingredient, null);

            // configure holder
            holder = new ViewHolder();
            holder.checkBox = rowView.findViewById(R.id.ingredient_checkbox);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // fill data
        holder.checkBox.setText(ingredients.get(position).toString());

        return rowView;
    }
}
