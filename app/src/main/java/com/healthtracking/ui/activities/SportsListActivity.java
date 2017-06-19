package com.healthtracking.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.healthtracking.App;
import com.healthtracking.R;
import com.healthtracking.data.Sport;
import com.healthtracking.data.SportDao;

import org.greenrobot.greendao.query.Query;

public class SportsListActivity extends AppCompatActivity {

    ListView sportListView ;

    private SportDao sportDao;
    private Query<Sport> sportsQuery;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_list);

        sportListView = (ListView) findViewById(R.id.sports_list);

        sportDao = ((App) getApplication()).getDaoSession().getSportDao();
        sportsQuery = sportDao.queryBuilder()
                .where(SportDao.Properties.IsVisible.eq(true))
                .orderDesc(SportDao.Properties.SelectedTimes)
                .orderAsc(SportDao.Properties.Name)
                .build();

        final SportArrayAdapter adapter = new SportArrayAdapter(this, sportsQuery.list());
        sportListView.setAdapter(adapter);
        sportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.chooseActivity(position);
            }
        });
    }

    public void addNewSport(View view) {
        Intent intent = new Intent(view.getContext(),AddNewSportActivity.class);
        startActivity(intent);
    }
}
