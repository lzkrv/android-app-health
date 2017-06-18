package com.healthtracking.data;

import com.healthtracking.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.HashMap;

/**
 * Created by larisa on 6/11/17.
 */

@Entity
public class Hobby {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String name;

    private int imageDrawableId;

    private boolean isVisible;

    private int selectedTimes;

    @Generated(hash = 92508346)
    public Hobby(Long id, @NotNull String name, int imageDrawableId,
            boolean isVisible, int selectedTimes) {
        this.id = id;
        this.name = name;
        this.imageDrawableId = imageDrawableId;
        this.isVisible = isVisible;
        this.selectedTimes = selectedTimes;
    }

    @Generated(hash = 23756816)
    public Hobby() {
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

    public int getImageDrawableId() {
        return this.imageDrawableId;
    }

    public void setImageDrawableId(int imageDrawableId) {
        this.imageDrawableId = imageDrawableId;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getSelectedTimes() {
        return this.selectedTimes;
    }

    public void setSelectedTimes(int selectedTimes) {
        this.selectedTimes = selectedTimes;
    }

    public static void initHobbyTable(HobbyDao hobbyDao) {
        HashMap<String, Integer> initialHobbyMap = new HashMap<String, Integer>();
        initialHobbyMap.put("Cooking", R.drawable.hobby_cooking);
        initialHobbyMap.put("Watching TV", R.drawable.hobby_tv);
        initialHobbyMap.put("Reading books", R.drawable.hobby_read);
        initialHobbyMap.put("Playing piano", R.drawable.hobby_piano);
        initialHobbyMap.put("Drawing", R.drawable.hobby_draw);
        initialHobbyMap.put("Taking bath", R.drawable.hobby_bath);
        initialHobbyMap.put("Meditation", R.drawable.hobby_meditation);
        initialHobbyMap.put("Fake hobby 1", R.drawable.hobby_cooking);
        initialHobbyMap.put("Fake hobby 2", R.drawable.hobby_cooking);
        initialHobbyMap.put("Fake hobby 3", R.drawable.hobby_cooking);
        initialHobbyMap.put("Fake hobby 4", R.drawable.hobby_cooking);
        initialHobbyMap.put("Fake hobby 5", R.drawable.hobby_cooking);
        initialHobbyMap.put("Fake hobby 6", R.drawable.hobby_cooking);
        initialHobbyMap.put("Fake hobby 7", R.drawable.hobby_cooking);
        initialHobbyMap.put("Fake hobby 8", R.drawable.hobby_cooking);
        initialHobbyMap.put("Fake hobby 9", R.drawable.hobby_cooking);
        initialHobbyMap.put("Fake hobby 10", R.drawable.hobby_cooking);

        for (String hobbyName: initialHobbyMap.keySet()) {
            Hobby hobby = new Hobby();
            hobby.setName(hobbyName);
            hobby.setImageDrawableId(initialHobbyMap.get(hobbyName));
            hobby.setIsVisible(true);
            hobby.setSelectedTimes(0);
            hobbyDao.insert(hobby);
        }
    }

    public static int getHobbyMaxSelectedTimes (HobbyDao hobbyDao) {
        return hobbyDao.queryBuilder().where(
                new WhereCondition.StringCondition(
                        "SELECTED_TIMES=(SELECT MAX(SELECTED_TIMES) FROM HOBBY)"))
                .build().list().get(0).getSelectedTimes();
    }
}
