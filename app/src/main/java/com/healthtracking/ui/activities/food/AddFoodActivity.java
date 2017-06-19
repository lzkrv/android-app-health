package com.healthtracking.ui.activities.food;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.healthtracking.App;
import com.healthtracking.R;
import com.healthtracking.data.Log;
import com.healthtracking.data.LogDao;
import com.healthtracking.data.LogFood;
import com.healthtracking.data.LogFoodDao;
import com.healthtracking.ui.MainActivity;
import com.healthtracking.ui.common.DatePickerFragment;
import com.healthtracking.ui.common.TimePickerFragment;

import static com.healthtracking.Utils.initDateAndTimeFields;

public class AddFoodActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private AlertDialog alertDialog;

    private int chosenMealType = -1;
    private int chosenMealReason = -1;
    private int chosenHungerLevelBefore = -1;
    private int chosenHungerLevelAfter = -1;

    LogDao logDao;
    LogFoodDao logFoodDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        logDao = ((App) getApplication()).getDaoSession().getLogDao();
        logFoodDao = ((App) getApplication()).getDaoSession().getLogFoodDao();

        initDateAndTimeFields(this);
    }

    public void selectMealKind(View view) {
        showAlertDialog(
                view,
                new CharSequence[] {"Breakfast", "Lunch", "Dinner", "Perekus"},
                R.id.meal_kind,
                "chosenMealType"
        );
    }

    public void selectMealReason(View view) {
        showAlertDialog(
                view,
                new CharSequence[] {"Hunger", "Emotional - Event", "Emotional - Sadness", "Emotional - Guilt"},
                R.id.meal_reason,
                "chosenMealReason"
        );
    }

    public void selectHungerLevelBefore(View view) {
        showAlertDialog(
                view,
                new CharSequence[] {"Starving", "Hungry", "Nor hungry nor full", "Full", "Bloated"},
                R.id.hunger_level_before,
                "chosenHungerLevelBefore"
        );
    }

    public void selectHungerLevelAfter(View view) {
        showAlertDialog(
                view,
                new CharSequence[] {"Starving", "Hungry", "Nor hungry nor full", "Full", "Bloated"},
                R.id.hunger_level_after,
                "chosenHungerLevelAfter"
        );
    }

    private void showAlertDialog(final View view, final CharSequence[] items,
                                 final int elementId, final String varToPopulate) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int n) {
                TextView textView = (TextView) view.findViewById(elementId);
                textView.setText(items[n]);

                //save n to a variable
                switch (varToPopulate) {
                    case "chosenMealType":
                        chosenMealType = n;
                        break;
                    case "chosenMealReason":
                        chosenMealReason = n;
                        break;
                    case "chosenHungerLevelBefore":
                        chosenHungerLevelBefore = n;
                        break;
                    case "chosenHungerLevelAfter":
                        chosenHungerLevelAfter = n;
                        break;
                }

                alertDialog.dismiss();
            }
        });
        adb.setTitle("What meal did you have?");
        alertDialog = adb.show();
    }

    public void changeDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void changeTime(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void submitFoodRecord(View view) {
        EditText foodEaten = (EditText) findViewById(R.id.food_eaten);

        LogFood logFood = new LogFood();
        logFood.setFoodEaten(foodEaten.getText().toString());
        logFood.setMealKind(chosenMealType);
        logFood.setMealReason(chosenMealReason);
        logFood.setHungerLevelBefore(chosenHungerLevelBefore);
        logFood.setHungerLevelAfter(chosenHungerLevelAfter);
        logFoodDao.insert(logFood);

        Log log = new Log();
        log.setLogFood(logFood);
        log.setTimestamp(sharedPref.getLong(this.getString(R.string.current_time), 0));
        logDao.insert(log);

        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);

        finish();
    }
}
