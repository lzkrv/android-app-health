package com.healthtracking.ui.activities.sport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtracking.App;
import com.healthtracking.R;
import com.healthtracking.data.Sport;
import com.healthtracking.data.SportDao;
import com.healthtracking.ui.MainActivity;
import com.healthtracking.ui.common.DatePickerFragment;
import com.healthtracking.ui.common.TimePickerFragment;

import java.util.List;

import static com.healthtracking.Utils.initDateAndTimeFields;

public class AddSportActivity extends AppCompatActivity {

    private boolean sportSelected = false;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private SportDao sportDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sport);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        sportDao = ((App) getApplication()).getDaoSession().getSportDao();

        updateSelectedSport();
        updateActivityLength();
        initDateAndTimeFields(this);
    }

    public void selectSportFromList(View view) {
        EditText activityLengthField = (EditText) findViewById(R.id.activity_time_minutes);
        String activityLengthText = activityLengthField.getText().toString();
        editor.putString(getString(R.string.sport_length), activityLengthText);
        editor.commit();

        Intent intent = new Intent(view.getContext(), SportsListActivity.class);
        startActivity(intent);
        finish();
    }

    public void submitSportActivity(View view) {
        if (!sportSelected) {
            ImageView selectedSportImg = (ImageView) findViewById(R.id.select_sport_img);
            selectedSportImg.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.alertBackgroundColor)
            );
            Toast.makeText(getApplicationContext(),
                    "Please choose your activity", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
            editor.remove(getString(R.string.sport_time));
            editor.remove(getString(R.string.sport_length));
            editor.remove(getString(R.string.current_time));
            editor.commit();
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

    private void updateSelectedSport() {
        long selectedActivityId = getIntent().getLongExtra("SELECTED_ACTIVITY_ID", -1);
        if(selectedActivityId != -1) {
            List<Sport> theSport = sportDao.queryBuilder()
                    .where(SportDao.Properties.Id.eq(selectedActivityId)).build().list();
            if (theSport.size() == 0) {
                Toast.makeText(getApplicationContext(),
                        "Can't find sport with id " + selectedActivityId, Toast.LENGTH_LONG).show();
            } else {
                ImageView selectSportImg = (ImageView) findViewById(R.id.select_sport_img);
                TextView selectedSportText = (TextView) findViewById(R.id.select_sport_text);
                selectSportImg.setImageResource(theSport.get(0).getImageDrawableId());
                selectSportImg.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.normalBackgroundColor)
                );
                ImageView selectedSportImg = (ImageView) findViewById(R.id.selected_sport_img);
                selectedSportImg.setImageResource(theSport.get(0).getImageDrawableId());
                selectedSportText.setText(theSport.get(0).getName());
                sportSelected = true;
            }
        }
    }

    private void updateActivityLength() {
        String activityLength = sharedPref.getString(getString(R.string.sport_length), null);
        if (activityLength != null) {
            EditText activityLengthField = (EditText) findViewById(R.id.activity_time_minutes);
            activityLengthField.setText(activityLength);
        }
    }
}
