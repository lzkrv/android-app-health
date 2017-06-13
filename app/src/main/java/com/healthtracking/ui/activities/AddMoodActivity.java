package com.healthtracking.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.healthtracking.R;
import com.healthtracking.data.MoodLevel;
import com.healthtracking.ui.MainActivity;
import com.healthtracking.ui.common.DatePickerFragment;
import com.healthtracking.ui.common.TimePickerFragment;

import static com.healthtracking.Utils.initDateAndTimeFields;

public class AddMoodActivity extends AppCompatActivity {
    private LinearLayout moodLevelSelector;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        updateMoodLevel();
        setSpinnerForMoodReasons();
        initDateAndTimeFields(this);
    }

    public void submitMoodPoint(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
        editor.remove(getString(R.string.current_time));
        editor.commit();
        finish();
    }

    public void changeDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void changeTime(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private void addMoodLevels(final LinearLayout layout) {
        for (final MoodLevel moodLevel : MoodLevel.values()) {
            final ImageView healthLevelImage = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    (int) getResources().getDimension(R.dimen.health_level_icon_size),
                    (int) getResources().getDimension(R.dimen.health_level_icon_size)
            );
            healthLevelImage.setId(moodLevel.ordinal());
            healthLevelImage.setLayoutParams(layoutParams);
            healthLevelImage.setImageDrawable(ContextCompat.getDrawable(this, moodLevel.getImage()));
            healthLevelImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectMoodLevel(moodLevel);
                }
            });
            layout.addView(healthLevelImage);
        }
    }

    private void selectMoodLevel(MoodLevel moodLevel) {
        final int childCount = moodLevelSelector.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) moodLevelSelector.getChildAt(i);
            if (imageView.getId() == moodLevel.ordinal()) {
                imageView.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.colorForSelectedImage));
            } else {
                imageView.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.normalBackgroundColor));
            }
        }
    }

    private void updateMoodLevel() {
        moodLevelSelector = (LinearLayout)findViewById(R.id.mood_level_selector);
        // TODO: allow user to select default health level in settings
        addMoodLevels(moodLevelSelector);
        selectMoodLevel(MoodLevel.GOOD);
    }

    private void setSpinnerForMoodReasons() {
        // TODO: store categories in DB
        // TODO: allow user to modify categories
        String[] testContent = {"Nothing special", "Work related", "Family related",
                "Personal stuff", "...add more types"};
        Spinner spinner = (Spinner) findViewById(R.id.mood_reason_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, testContent);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
