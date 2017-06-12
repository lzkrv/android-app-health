package com.healthtracking.ui.activities;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.healthtracking.R;
import com.healthtracking.data.HealthLevel;
import com.healthtracking.ui.MainActivity;
import com.healthtracking.ui.common.DatePickerFragment;
import com.healthtracking.ui.common.TimePickerFragment;

import static com.healthtracking.Utils.getCurrentDateAndTime;

public class AddHealthActivity extends AppCompatActivity {
    //TODO: store time as int, not as two Strings

    private LinearLayout healthLevelSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health);

        healthLevelSelector = (LinearLayout)findViewById(R.id.health_level_selector);

        // TODO: allow user to select default health level
        addHealthLevels(healthLevelSelector);
        selectHealthLevel(HealthLevel.GOOD);

        // TODO: store categories in DB
        // TODO: allow user to modify categories
        String[] testContent = {"Nothing special", "Sport Injury", "Stomack problems", "...add more types"};
        Spinner spinner = (Spinner) findViewById(R.id.bad_health_reason_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, testContent);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        String[] dateAndTime = getCurrentDateAndTime();
        TextView datePlaceholder = (TextView) findViewById(R.id.date_placeholder);
        datePlaceholder.setText(dateAndTime[0]);
        TextView timePlaceholder = (TextView) findViewById(R.id.time_placeholder);
        timePlaceholder.setText(dateAndTime[1]);
    }

    public void submitHealthPoint(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
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

    private void addHealthLevels(final LinearLayout layout) {
        for (final HealthLevel healthLevel : HealthLevel.values()) {
            final ImageView healthLevelImage = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    (int) getResources().getDimension(R.dimen.health_level_icon_size),
                    (int) getResources().getDimension(R.dimen.health_level_icon_size)
            );
            healthLevelImage.setId(healthLevel.ordinal());
            healthLevelImage.setLayoutParams(layoutParams);
            healthLevelImage.setImageDrawable(ContextCompat.getDrawable(this, healthLevel.getImage()));
            healthLevelImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectHealthLevel(healthLevel);
                }
            });
            layout.addView(healthLevelImage);
        }
    }

    private void selectHealthLevel(HealthLevel healthLevel) {
        final int childCount = healthLevelSelector.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) healthLevelSelector.getChildAt(i);
            if (imageView.getId() == healthLevel.ordinal()) {
                imageView.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.colorForSelectedImage));
            } else {
                imageView.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.normalBackgroundColor));
            }
        }
        if(healthLevel == HealthLevel.POOR || healthLevel == HealthLevel.UNHEALTHY) {
            changeBadHealthReasonElementsState(true);
        } else {
            changeBadHealthReasonElementsState(false);
        }
    }

    private void changeBadHealthReasonElementsState(boolean turnOn) {
        LinearLayout.LayoutParams layoutParams = turnOn ? new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) :
                new LinearLayout.LayoutParams(
                        (int) getResources().getDimension(R.dimen.empty_margin),
                        (int) getResources().getDimension(R.dimen.empty_margin));
        if (turnOn) {
            layoutParams.setMargins(
                    (int) getResources().getDimension(R.dimen.empty_margin),
                    (int) getResources().getDimension(R.dimen.margin_between_elements),
                    (int) getResources().getDimension(R.dimen.empty_margin),
                    (int) getResources().getDimension(R.dimen.empty_margin));
        }
        int visibility = turnOn ? View.VISIBLE : View.INVISIBLE;
        findViewById(R.id.bad_health_reason_spinner).setLayoutParams(layoutParams);
        findViewById(R.id.bad_health_reason_spinner).setVisibility(visibility);
        findViewById(R.id.bad_health_reason_text).setLayoutParams(layoutParams);
        findViewById(R.id.bad_health_reason_text).setVisibility(visibility);
    }
}
