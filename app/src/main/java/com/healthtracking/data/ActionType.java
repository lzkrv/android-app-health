package com.healthtracking.data;

import com.healthtracking.AddFoodActivity;
import com.healthtracking.AddHealthActivity;
import com.healthtracking.AddHobbyActivity;
import com.healthtracking.AddMoodActivity;
import com.healthtracking.AddSportActivity;
import com.healthtracking.R;

/**
 * Created by larisa on 6/10/17.
 */

public enum ActionType {
    FOOD ("#ffdc8c", AddFoodActivity.class, R.drawable.food),
    HOBBY ("#9bd0ff", AddHobbyActivity.class, R.drawable.hobby),
    SPORT ("#e0ff8c", AddSportActivity.class, R.drawable.sport),
    HEALTH ("#f785b3", AddHealthActivity.class, R.drawable.health),
    MOOD ("#da9bff", AddMoodActivity.class, R.drawable.mood);

    private final String color;
    private final Class activity;
    private final int image;

    ActionType(String color, Class activity, int image) {
        this.color = color;
        this.activity = activity;
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public Class getActivity() {
        return activity;
    }

    public int getImage() {
        return image;
    }
}
