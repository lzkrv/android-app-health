package com.healthtracking.data;

import com.healthtracking.R;

/**
 * Created by larisa on 6/10/17.
 */

public enum HealthLevel {
    EXCELLENT (R.drawable.health5, "Excellent! :)"),
    GOOD (R.drawable.health4, "Good! :)"),
    AVERAGE (R.drawable.health3, "OK."),
    UNHEALTHY (R.drawable.health2, "Something's wrong.."),
    POOR (R.drawable.health1, "Bad :(");

    private final int image;
    private final String description;

    HealthLevel(int image, String description) {
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
