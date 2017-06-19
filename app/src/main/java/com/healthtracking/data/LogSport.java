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
public class LogSport {
    @Id(autoincrement = true)
    private Long id;

    private Long sportTypeId;

    @ToOne(joinProperty = "sportTypeId")
    private Sport sportType;

    private int length;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1624089677)
    private transient LogSportDao myDao;

    @Generated(hash = 980378604)
    public LogSport(Long id, Long sportTypeId, int length) {
        this.id = id;
        this.sportTypeId = sportTypeId;
        this.length = length;
    }

    @Generated(hash = 1388370530)
    public LogSport() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSportTypeId() {
        return this.sportTypeId;
    }

    public void setSportTypeId(Long sportTypeId) {
        this.sportTypeId = sportTypeId;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Generated(hash = 1679748660)
    private transient Long sportType__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 849793139)
    public Sport getSportType() {
        Long __key = this.sportTypeId;
        if (sportType__resolvedKey == null
                || !sportType__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SportDao targetDao = daoSession.getSportDao();
            Sport sportTypeNew = targetDao.load(__key);
            synchronized (this) {
                sportType = sportTypeNew;
                sportType__resolvedKey = __key;
            }
        }
        return sportType;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 294932060)
    public void setSportType(Sport sportType) {
        synchronized (this) {
            this.sportType = sportType;
            sportTypeId = sportType == null ? null : sportType.getId();
            sportType__resolvedKey = sportTypeId;
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
    @Generated(hash = 1193298667)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLogSportDao() : null;
    }
}
