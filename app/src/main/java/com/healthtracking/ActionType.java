package com.healthtracking;

/**
 * Created by larisa on 6/10/17.
 */

public enum ActionType {
    FOOD ("#ffdc8c", ""),
    HOBBY ("#9bd0ff", ""),
    SPORT ("#e0ff8c", ""),
    HEALTH ("#f785b3", ""),
    MOOD ("#da9bff", "");

//    private final int index;
    private final String color;
    private final String activity;

    ActionType(String color, String activity) {
        this.color = color;
        this.activity = activity;
    }

    public String getColor() {
        return color;
    }

    public String getActivity() {
        return activity;
    }
}
