package com.healthtracking.data;

import com.healthtracking.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.HashMap;

/**
 * Created by larisa on 6/11/17.
 */

@Entity
public class Sport {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String name;

    private int imageDrawableId;

    private boolean isVisible;

    private int selectedTimes;

    @Generated(hash = 364628063)
    public Sport(Long id, @NotNull String name, int imageDrawableId,
            boolean isVisible, int selectedTimes) {
        this.id = id;
        this.name = name;
        this.imageDrawableId = imageDrawableId;
        this.isVisible = isVisible;
        this.selectedTimes = selectedTimes;
    }

    @Generated(hash = 2004282459)
    public Sport() {
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

    public static void initSportTable(SportDao sportDao) {
        HashMap<String, Integer> initialSportsMap = new HashMap<String, Integer>();
        initialSportsMap.put("Cycling", R.drawable.sport_cycling);
        initialSportsMap.put("Running", R.drawable.sport_running);
        initialSportsMap.put("Soccer", R.drawable.sport_soccer);
        initialSportsMap.put("Badminton", R.drawable.sport_badminton);
        initialSportsMap.put("CrossFit", R.drawable.sport_crossfit);
        initialSportsMap.put("Swimming", R.drawable.sport_swimming);
        initialSportsMap.put("Baseball", R.drawable.sport_baseball);
        initialSportsMap.put("Skiing", R.drawable.sport_skiing);
        initialSportsMap.put("Dancing", R.drawable.sport_dancing);
        initialSportsMap.put("Surfing", R.drawable.sport_surfing);
        initialSportsMap.put("Hiking", R.drawable.sport_hiking);
        initialSportsMap.put("Fake sport 5", R.drawable.sport_base);
        initialSportsMap.put("Fake sport 6", R.drawable.sport_base);
        initialSportsMap.put("Fake sport 7", R.drawable.sport_base);
        initialSportsMap.put("Fake sport 8", R.drawable.sport_base);
        initialSportsMap.put("Fake sport 9", R.drawable.sport_base);
        initialSportsMap.put("Fake sport 10", R.drawable.sport_base);

        for (String sportName: initialSportsMap.keySet()) {
            Sport sport = new Sport();
            sport.setName(sportName);
            sport.setImageDrawableId(initialSportsMap.get(sportName));
            sport.setIsVisible(true);
            sport.setSelectedTimes(0);
            sportDao.insert(sport);
        }
    }

    public static int getMaxSelectedTimes (SportDao sportDao) {
        return sportDao.queryBuilder().where(
                new WhereCondition.StringCondition(
                        "SELECTED_TIMES=(SELECT MAX(SELECTED_TIMES) FROM SPORT)"))
                .build().list().get(0).getSelectedTimes();
    }

    public int getSelectedTimes() {
        return this.selectedTimes;
    }

    public void setSelectedTimes(int selectedTimes) {
        this.selectedTimes = selectedTimes;
    }
}
