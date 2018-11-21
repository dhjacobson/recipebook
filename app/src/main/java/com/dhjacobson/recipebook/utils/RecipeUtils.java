package com.dhjacobson.recipebook.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RecipeUtils {

    public static String doubleToShortText(double d) {
        {
            if(d == (long) d)
                return Long.toString((long) d);
            else
                return String.format("%s", d);
        }
    }

    public static String minutesToText(int minutes) {
        int hours = minutes / 60;
        int leftoverMinutes = (minutes % 60);

        List<String> stringParts = new ArrayList<>();
        if (hours > 0) {stringParts.add(String.format("%d hrs", hours));}
        if (leftoverMinutes > 0) {stringParts.add(String.format("%d mins", leftoverMinutes));}
        return String.join(", ", stringParts);
    }

    public static String durationToText(Duration duration) {
        return minutesToText((int) duration.toMinutes());
    }

}