package com.healthtracking.ui.activities;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtracking.R;
import com.healthtracking.data.FakeHobbyProvider;
import com.healthtracking.data.Hobby;
import com.healthtracking.ui.MainActivity;
import com.healthtracking.ui.common.DatePickerFragment;
import com.healthtracking.ui.common.TimePickerFragment;

import static com.healthtracking.Utils.getCurrentDateAndTime;

public class AddHobbyActivity extends AppCompatActivity {
    //TODO: make this stateless (save time & length of activity to shared preferences)
    //TODO: store time as int, not as two Strings

    private boolean hobbySelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hobby);

        int selectedActivityId = getIntent().getIntExtra("SELECTED_ACTIVITY_ID", -1);
        if(selectedActivityId != -1) {
            Hobby hobby = FakeHobbyProvider.getInstance().getHobbyById(selectedActivityId);
            if (hobby == null) {
                Toast.makeText(getApplicationContext(),
                        "Can't find hobby with id " + selectedActivityId, Toast.LENGTH_LONG).show();
            } else {
                ImageView selectedHobbyImg = (ImageView) findViewById(R.id.select_hobby_img);
                TextView selectedHobbyText = (TextView) findViewById(R.id.select_hobby_text);
                selectedHobbyImg.setImageResource(hobby.getImageDrawableId());
                selectedHobbyImg.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.normalBackgroundColor)
                );
                selectedHobbyText.setText(hobby.getName());
                hobbySelected = true;
            }
        }

        String[] dateAndTime = getCurrentDateAndTime();
        TextView datePlaceholder = (TextView) findViewById(R.id.date_placeholder);
        datePlaceholder.setText(dateAndTime[0]);
        TextView timePlaceholder = (TextView) findViewById(R.id.time_placeholder);
        timePlaceholder.setText(dateAndTime[1]);
    }

    public void selectHobbyFromList(View view) {
        Intent intent = new Intent(view.getContext(), HobbiesListActivity.class);
        startActivity(intent);
        finish();
    }

    public void submitHobbyActivity(View view) {
        if (!hobbySelected) {
            ImageView selectedHobbyImg = (ImageView) findViewById(R.id.select_hobby_img);
            selectedHobbyImg.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.alertBackgroundColor)
            );
            Toast.makeText(getApplicationContext(),
                    "Please choose your activity", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void changeDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void changeTime(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
