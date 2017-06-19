package com.healthtracking.data;

import com.healthtracking.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by larisa on 6/11/17.
 */

@Entity
public class MealKind {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String name;

    private boolean isVisible;

    @Generated(hash = 134225633)
    public MealKind(Long id, @NotNull String name, boolean isVisible) {
        this.id = id;
        this.name = name;
        this.isVisible = isVisible;
    }

    @Generated(hash = 484025181)
    public MealKind() {
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

    public static void initMealKindTable(MealKindDao mealKindDao) {
        List<String> mealKinds = new LinkedList<>();
        mealKinds.add("Breakfast");
        mealKinds.add("Lunch");
        mealKinds.add("Dinner");
        mealKinds.add("Snack");

        for (String mealKindName: mealKinds) {
            MealKind mealKind = new MealKind();
            mealKind.setName(mealKindName);
            mealKind.setIsVisible(true);
            mealKindDao.insert(mealKind);
        }
    }
}
