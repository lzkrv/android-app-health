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
public class LogHobby {
    @Id(autoincrement = true)
    private Long id;

    private Long hobbyTypeId;

    @ToOne(joinProperty = "hobbyTypeId")
    private Hobby hobbyType;

    private int length;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1391123746)
    private transient LogHobbyDao myDao;

    @Generated(hash = 183787881)
    public LogHobby(Long id, Long hobbyTypeId, int length) {
        this.id = id;
        this.hobbyTypeId = hobbyTypeId;
        this.length = length;
    }

    @Generated(hash = 890288771)
    public LogHobby() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHobbyTypeId() {
        return this.hobbyTypeId;
    }

    public void setHobbyTypeId(Long hobbyTypeId) {
        this.hobbyTypeId = hobbyTypeId;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Generated(hash = 1191615375)
    private transient Long hobbyType__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 57786329)
    public Hobby getHobbyType() {
        Long __key = this.hobbyTypeId;
        if (hobbyType__resolvedKey == null
                || !hobbyType__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HobbyDao targetDao = daoSession.getHobbyDao();
            Hobby hobbyTypeNew = targetDao.load(__key);
            synchronized (this) {
                hobbyType = hobbyTypeNew;
                hobbyType__resolvedKey = __key;
            }
        }
        return hobbyType;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 993813148)
    public void setHobbyType(Hobby hobbyType) {
        synchronized (this) {
            this.hobbyType = hobbyType;
            hobbyTypeId = hobbyType == null ? null : hobbyType.getId();
            hobbyType__resolvedKey = hobbyTypeId;
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
    @Generated(hash = 224838335)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLogHobbyDao() : null;
    }
}
