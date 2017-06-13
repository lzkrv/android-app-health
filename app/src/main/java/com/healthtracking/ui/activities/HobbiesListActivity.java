package com.healthtracking.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.healthtracking.R;
import com.healthtracking.data.FakeHobbyProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class HobbiesListActivity extends AppCompatActivity {

    ListView hobbyListView ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby_list);

        hobbyListView = (ListView) findViewById(R.id.hobbies_list);


        final HobbyArrayAdapter adapter = new HobbyArrayAdapter(this, FakeHobbyProvider.getInstance().findAll());
        hobbyListView.setAdapter(adapter);
        hobbyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.chooseActivity(((RelativeLayout)view).getChildAt(0));
            }
        });
    }

    public void addNewHobby(View view) {
        Intent intent = new Intent(view.getContext(),AddNewHobbyActivity.class);
        startActivity(intent);
    }
}
