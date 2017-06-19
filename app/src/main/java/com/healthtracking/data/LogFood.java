package com.healthtracking.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by larisa on 6/18/17.
 */

@Entity
public class LogFood {
    @Id(autoincrement = true)
    private Long id;

    private int mealKind;

    private int mealReason;

    private int hungerLevelBefore;

    private int hungerLevelAfter;

    private String foodEaten;

    @Generated(hash = 852927996)
    public LogFood(Long id, int mealKind, int mealReason, int hungerLevelBefore,
            int hungerLevelAfter, String foodEaten) {
        this.id = id;
        this.mealKind = mealKind;
        this.mealReason = mealReason;
        this.hungerLevelBefore = hungerLevelBefore;
        this.hungerLevelAfter = hungerLevelAfter;
        this.foodEaten = foodEaten;
    }

    @Generated(hash = 919180472)
    public LogFood() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMealKind() {
        return this.mealKind;
    }

    public void setMealKind(int mealKind) {
        this.mealKind = mealKind;
    }

    public int getMealReason() {
        return this.mealReason;
    }

    public void setMealReason(int mealReason) {
        this.mealReason = mealReason;
    }

    public int getHungerLevelBefore() {
        return this.hungerLevelBefore;
    }

    public void setHungerLevelBefore(int hungerLevelBefore) {
        this.hungerLevelBefore = hungerLevelBefore;
    }

    public int getHungerLevelAfter() {
        return this.hungerLevelAfter;
    }

    public void setHungerLevelAfter(int hungerLevelAfter) {
        this.hungerLevelAfter = hungerLevelAfter;
    }

    public String getFoodEaten() {
        return this.foodEaten;
    }

    public void setFoodEaten(String foodEaten) {
        this.foodEaten = foodEaten;
    }
}
