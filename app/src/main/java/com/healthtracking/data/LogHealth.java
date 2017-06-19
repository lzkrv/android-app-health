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
public class LogHealth {
    @Id(autoincrement = true)
    private Long id;

    private int healthLevel;

    private Long healthReasonId;

    @ToOne(joinProperty = "healthReasonId")
    private HealthReason healthReason;

    private String details;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1815104470)
    private transient LogHealthDao myDao;

    @Generated(hash = 14899972)
    public LogHealth(Long id, int healthLevel, Long healthReasonId,
            String details) {
        this.id = id;
        this.healthLevel = healthLevel;
        this.healthReasonId = healthReasonId;
        this.details = details;
    }

    @Generated(hash = 1678317066)
    public LogHealth() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHealthLevel() {
        return this.healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public Long getHealthReasonId() {
        return this.healthReasonId;
    }

    public void setHealthReasonId(Long healthReasonId) {
        this.healthReasonId = healthReasonId;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Generated(hash = 307285835)
    private transient Long healthReason__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1899428250)
    public HealthReason getHealthReason() {
        Long __key = this.healthReasonId;
        if (healthReason__resolvedKey == null
                || !healthReason__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HealthReasonDao targetDao = daoSession.getHealthReasonDao();
            HealthReason healthReasonNew = targetDao.load(__key);
            synchronized (this) {
                healthReason = healthReasonNew;
                healthReason__resolvedKey = __key;
            }
        }
        return healthReason;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1676410302)
    public void setHealthReason(HealthReason healthReason) {
        synchronized (this) {
            this.healthReason = healthReason;
            healthReasonId = healthReason == null ? null : healthReason.getId();
            healthReason__resolvedKey = healthReasonId;
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
    @Generated(hash = 2076650925)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLogHealthDao() : null;
    }
}
