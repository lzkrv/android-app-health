package com.healthtracking.ui.activities;

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

import com.healthtracking.R;
import com.healthtracking.data.FakeHobbyProvider;
import com.healthtracking.data.Hobby;
import com.healthtracking.ui.MainActivity;
import com.healthtracking.ui.common.DatePickerFragment;
import com.healthtracking.ui.common.TimePickerFragment;

import static com.healthtracking.Utils.initDateAndTimeFields;

public class AddHobbyActivity extends AppCompatActivity {
    private boolean hobbySelected = false;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hobby);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        updateSelectedHobby();
        updateActivityLength();
        initDateAndTimeFields(this);
    }

    public void selectHobbyFromList(View view) {
        EditText activityLengthField = (EditText) findViewById(R.id.activity_time_minutes);
        String activityLengthText = activityLengthField.getText().toString();
        editor.putString(getString(R.string.hobby_length), activityLengthText);
        editor.commit();

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
            editor.remove(getString(R.string.hobby_time));
            editor.remove(getString(R.string.hobby_length));
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

    private void updateSelectedHobby() {
        int selectedActivityId = getIntent().getIntExtra("SELECTED_ACTIVITY_ID", -1);
        if(selectedActivityId != -1) {
            Hobby hobby = FakeHobbyProvider.getInstance().getHobbyById(selectedActivityId);
            if (hobby == null) {
                Toast.makeText(getApplicationContext(),
                        "Can't find hobby with id " + selectedActivityId, Toast.LENGTH_LONG).show();
            } else {
                ImageView selectHobbyImg = (ImageView) findViewById(R.id.select_hobby_img);
                TextView selectedHobbyText = (TextView) findViewById(R.id.select_hobby_text);
                selectHobbyImg.setImageResource(hobby.getImageDrawableId());
                selectHobbyImg.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.normalBackgroundColor)
                );
                ImageView selectedHobbyImg = (ImageView) findViewById(R.id.selected_hobby_img);
                selectedHobbyImg.setImageResource(hobby.getImageDrawableId());
                selectedHobbyText.setText(hobby.getName());
                hobbySelected = true;
            }
        }
    }

    private void updateActivityLength() {
        String activityLength = sharedPref.getString(getString(R.string.hobby_length), null);
        if (activityLength != null) {
            EditText activityLengthField = (EditText) findViewById(R.id.activity_time_minutes);
            activityLengthField.setText(activityLength);
        }
    }
}
