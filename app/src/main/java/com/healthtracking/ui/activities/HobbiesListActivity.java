package com.healthtracking.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.healthtracking.App;
import com.healthtracking.R;
import com.healthtracking.data.Hobby;
import com.healthtracking.data.HobbyDao;

import org.greenrobot.greendao.query.Query;

public class HobbiesListActivity extends AppCompatActivity {

    ListView hobbyListView ;

    private HobbyDao hobbyDao;
    private Query<Hobby> hobbyQuery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby_list);

        hobbyListView = (ListView) findViewById(R.id.hobbies_list);

        hobbyDao = ((App) getApplication()).getDaoSession().getHobbyDao();
        hobbyQuery = hobbyDao.queryBuilder()
                .where(HobbyDao.Properties.IsVisible.eq(true))
                .orderDesc(HobbyDao.Properties.SelectedTimes)
                .orderAsc(HobbyDao.Properties.Name)
                .build();


        final HobbyArrayAdapter adapter = new HobbyArrayAdapter(this, hobbyQuery.list());
        hobbyListView.setAdapter(adapter);
        hobbyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.chooseActivity(position);
            }
        });
    }

    public void addNewHobby(View view) {
        Intent intent = new Intent(view.getContext(),AddNewHobbyActivity.class);
        startActivity(intent);
    }
}
