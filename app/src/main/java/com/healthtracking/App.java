package com.healthtracking;

import android.app.Application;

import com.healthtracking.data.DaoMaster;
import com.healthtracking.data.DaoSession;
import com.healthtracking.data.HobbyDao;
import com.healthtracking.data.SportDao;

import org.greenrobot.greendao.database.Database;

import static com.healthtracking.data.Hobby.initHobbyTable;
import static com.healthtracking.data.Sport.initSportTable;

/**
 * Created by larisa on 6/17/17.
 */

public class App extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "log-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        SportDao sportDao = daoSession.getSportDao();
        if (sportDao.queryBuilder().build().list().size() == 0) {
            initSportTable(sportDao);
        }

        HobbyDao hobbyDao = daoSession.getHobbyDao();
        if (hobbyDao.queryBuilder().build().list().size() == 0) {
            initHobbyTable(hobbyDao);
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
