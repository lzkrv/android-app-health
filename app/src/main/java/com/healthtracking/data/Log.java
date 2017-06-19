package com.healthtracking.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by larisa on 6/18/17.
 */

@Entity
public class Log {
    @Id(autoincrement = true)
    private Long id;

    private long timestamp;

    private long logFoodId;

    @ToOne(joinProperty = "logFoodId")
    private LogFood logFood;

    private long logHealthId;

    @ToOne(joinProperty = "logHealthId")
    private LogHealth logHealth;

    private long logHobbyId;

    @ToOne(joinProperty = "logHobbyId")
    private LogHobby logHobby;

    private long logMoodId;

    @ToOne(joinProperty = "logMoodId")
    private LogMood logMood;

    private long logSportId;

    @ToOne(joinProperty = "logSportId")
    private LogSport logSport;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1363441965)
    private transient LogDao myDao;

    @Generated(hash = 1149685055)
    public Log(Long id, long timestamp, long logFoodId, long logHealthId,
            long logHobbyId, long logMoodId, long logSportId) {
        this.id = id;
        this.timestamp = timestamp;
        this.logFoodId = logFoodId;
        this.logHealthId = logHealthId;
        this.logHobbyId = logHobbyId;
        this.logMoodId = logMoodId;
        this.logSportId = logSportId;
    }

    @Generated(hash = 1364647056)
    public Log() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getLogFoodId() {
        return this.logFoodId;
    }

    public void setLogFoodId(long logFoodId) {
        this.logFoodId = logFoodId;
    }

    public long getLogHealthId() {
        return this.logHealthId;
    }

    public void setLogHealthId(long logHealthId) {
        this.logHealthId = logHealthId;
    }

    public long getLogHobbyId() {
        return this.logHobbyId;
    }

    public void setLogHobbyId(long logHobbyId) {
        this.logHobbyId = logHobbyId;
    }

    public long getLogMoodId() {
        return this.logMoodId;
    }

    public void setLogMoodId(long logMoodId) {
        this.logMoodId = logMoodId;
    }

    public long getLogSportId() {
        return this.logSportId;
    }

    public void setLogSportId(long logSportId) {
        this.logSportId = logSportId;
    }

    @Generated(hash = 387948654)
    private transient Long logFood__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 780253185)
    public LogFood getLogFood() {
        long __key = this.logFoodId;
        if (logFood__resolvedKey == null || !logFood__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LogFoodDao targetDao = daoSession.getLogFoodDao();
            LogFood logFoodNew = targetDao.load(__key);
            synchronized (this) {
                logFood = logFoodNew;
                logFood__resolvedKey = __key;
            }
        }
        return logFood;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 941249490)
    public void setLogFood(@NotNull LogFood logFood) {
        if (logFood == null) {
            throw new DaoException(
                    "To-one property 'logFoodId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.logFood = logFood;
            logFoodId = logFood.getId();
            logFood__resolvedKey = logFoodId;
        }
    }

    @Generated(hash = 325063353)
    private transient Long logHealth__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 727834938)
    public LogHealth getLogHealth() {
        long __key = this.logHealthId;
        if (logHealth__resolvedKey == null
                || !logHealth__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LogHealthDao targetDao = daoSession.getLogHealthDao();
            LogHealth logHealthNew = targetDao.load(__key);
            synchronized (this) {
                logHealth = logHealthNew;
                logHealth__resolvedKey = __key;
            }
        }
        return logHealth;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1585291532)
    public void setLogHealth(@NotNull LogHealth logHealth) {
        if (logHealth == null) {
            throw new DaoException(
                    "To-one property 'logHealthId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.logHealth = logHealth;
            logHealthId = logHealth.getId();
            logHealth__resolvedKey = logHealthId;
        }
    }

    @Generated(hash = 2033488482)
    private transient Long logHobby__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1685548271)
    public LogHobby getLogHobby() {
        long __key = this.logHobbyId;
        if (logHobby__resolvedKey == null || !logHobby__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LogHobbyDao targetDao = daoSession.getLogHobbyDao();
            LogHobby logHobbyNew = targetDao.load(__key);
            synchronized (this) {
                logHobby = logHobbyNew;
                logHobby__resolvedKey = __key;
            }
        }
        return logHobby;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 484616328)
    public void setLogHobby(@NotNull LogHobby logHobby) {
        if (logHobby == null) {
            throw new DaoException(
                    "To-one property 'logHobbyId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.logHobby = logHobby;
            logHobbyId = logHobby.getId();
            logHobby__resolvedKey = logHobbyId;
        }
    }

    @Generated(hash = 1118056215)
    private transient Long logMood__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2056311092)
    public LogMood getLogMood() {
        long __key = this.logMoodId;
        if (logMood__resolvedKey == null || !logMood__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LogMoodDao targetDao = daoSession.getLogMoodDao();
            LogMood logMoodNew = targetDao.load(__key);
            synchronized (this) {
                logMood = logMoodNew;
                logMood__resolvedKey = __key;
            }
        }
        return logMood;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 312348324)
    public void setLogMood(@NotNull LogMood logMood) {
        if (logMood == null) {
            throw new DaoException(
                    "To-one property 'logMoodId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.logMood = logMood;
            logMoodId = logMood.getId();
            logMood__resolvedKey = logMoodId;
        }
    }

    @Generated(hash = 1734200999)
    private transient Long logSport__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1378885521)
    public LogSport getLogSport() {
        long __key = this.logSportId;
        if (logSport__resolvedKey == null || !logSport__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LogSportDao targetDao = daoSession.getLogSportDao();
            LogSport logSportNew = targetDao.load(__key);
            synchronized (this) {
                logSport = logSportNew;
                logSport__resolvedKey = __key;
            }
        }
        return logSport;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 503901284)
    public void setLogSport(@NotNull LogSport logSport) {
        if (logSport == null) {
            throw new DaoException(
                    "To-one property 'logSportId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.logSport = logSport;
            logSportId = logSport.getId();
            logSport__resolvedKey = logSportId;
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
    @Generated(hash = 1531216752)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLogDao() : null;
    }
}
