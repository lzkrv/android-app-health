package com.healthtracking.data;

import com.healthtracking.R;

/**
 * Created by larisa on 6/10/17.
 */

public enum MoodLevel {
    EXCELLENT (R.drawable.mood1, "Excellent! :)"),
    GOOD (R.drawable.mood2, "Good! :)"),
    AVERAGE (R.drawable.mood3, "OK."),
    BAD (R.drawable.mood4, "Something's wrong.."),
    AWFUL (R.drawable.mood5, "Bad :(");

    private final int image;
    private final String description;

    MoodLevel(int image, String description) {
        this.image = image;
        this.description = description;
    }

    public int getImage() {
        return this.image;
    }

    public String getDescription() {
        return this.description;
    }
}
