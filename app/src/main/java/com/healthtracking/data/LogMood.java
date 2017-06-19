package com.healthtracking.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by larisa on 6/18/17.
 */

@Entity
public class LogMood {
    @Id(autoincrement = true)
    private Long id;

    private int moodLevel;

    private Long moodReasonId;

    @ToOne(joinProperty = "moodReasonId")
    private MoodReason moodReason;

    private String details;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1583462865)
    private transient LogMoodDao myDao;

    @Generated(hash = 925332213)
    public LogMood(Long id, int moodLevel, Long moodReasonId, String details) {
        this.id = id;
        this.moodLevel = moodLevel;
        this.moodReasonId = moodReasonId;
        this.details = details;
    }

    @Generated(hash = 762747791)
    public LogMood() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMoodLevel() {
        return this.moodLevel;
    }

    public void setMoodLevel(int moodLevel) {
        this.moodLevel = moodLevel;
    }

    public Long getMoodReasonId() {
        return this.moodReasonId;
    }

    public void setMoodReasonId(Long moodReasonId) {
        this.moodReasonId = moodReasonId;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Generated(hash = 1156469004)
    private transient Long moodReason__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 700088057)
    public MoodReason getMoodReason() {
        Long __key = this.moodReasonId;
        if (moodReason__resolvedKey == null
                || !moodReason__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MoodReasonDao targetDao = daoSession.getMoodReasonDao();
            MoodReason moodReasonNew = targetDao.load(__key);
            synchronized (this) {
                moodReason = moodReasonNew;
                moodReason__resolvedKey = __key;
            }
        }
        return moodReason;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 466496699)
    public void setMoodReason(MoodReason moodReason) {
        synchronized (this) {
            this.moodReason = moodReason;
            moodReasonId = moodReason == null ? null : moodReason.getId();
            moodReason__resolvedKey = moodReasonId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2111355346)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLogMoodDao() : null;
    }
}
