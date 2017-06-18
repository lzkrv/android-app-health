package com.healthtracking.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.healthtracking.R;
import com.healthtracking.data.FakeSportProvider;

public class SportsListActivity extends AppCompatActivity {

    ListView sportListView ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_list);

        sportListView = (ListView) findViewById(R.id.sports_list);


        final SportArrayAdapter adapter = new SportArrayAdapter(this, FakeSportProvider.getInstance().findAll());
        sportListView.setAdapter(adapter);
        sportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.chooseActivity(((RelativeLayout)view).getChildAt(0));
            }
        });
    }

    public void addNewSport(View view) {
        Intent intent = new Intent(view.getContext(),AddNewSportActivity.class);
        startActivity(intent);
    }
}
