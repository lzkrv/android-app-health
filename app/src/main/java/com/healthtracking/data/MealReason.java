package com.healthtracking.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by larisa on 6/11/17.
 */

@Entity
public class MealReason {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String name;

    private boolean isVisible;

    @Generated(hash = 256490536)
    public MealReason(Long id, @NotNull String name, boolean isVisible) {
        this.id = id;
        this.name = name;
        this.isVisible = isVisible;
    }

    @Generated(hash = 1947860012)
    public MealReason() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static void initMealReasonTable(MealReasonDao mealReasonDao) {
        List<String> mealReasons = new LinkedList<>();
        mealReasons.add("Hunger");
        mealReasons.add("Emotional - Event");
        mealReasons.add("Emotional - Sadness");
        mealReasons.add("Emotional - Stress");
        mealReasons.add("Emotional - Guilt");

        for (String mealReasonName: mealReasons) {
            MealReason mealReason = new MealReason();
            mealReason.setName(mealReasonName);
            mealReason.setIsVisible(true);
            mealReasonDao.insert(mealReason);
        }
    }
}
