package com.healthtracking.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by larisa on 6/18/17.
 */

@Entity
public class MoodReason {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String reason;

    private boolean isVisible;

    private int selectedTimes;

    @Generated(hash = 396394496)
    public MoodReason(Long id, @NotNull String reason, boolean isVisible,
            int selectedTimes) {
        this.id = id;
        this.reason = reason;
        this.isVisible = isVisible;
        this.selectedTimes = selectedTimes;
    }

    @Generated(hash = 550654952)
    public MoodReason() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
}
