package com.healthtracking.ui.activities.sport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtracking.App;
import com.healthtracking.R;
import com.healthtracking.data.Sport;
import com.healthtracking.data.SportDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.healthtracking.data.Sport.getMaxSelectedTimes;

public class AddNewSportActivity extends AppCompatActivity {

    private LinearLayout imageParent;
    private int selectedSportImageId = -1;

    private SportDao sportDao;

    private static List<Integer> images = new ArrayList<>(Arrays.asList(
            R.drawable.sport_base,
            R.drawable.sport_hiking,
            R.drawable.sport_surfing,
            R.drawable.sport_dancing,
            R.drawable.sport_skiing,
            R.drawable.sport_baseball,
            R.drawable.sport_swimming,
            R.drawable.sport_crossfit,
            R.drawable.sport_badminton,
            R.drawable.sport_soccer,
            R.drawable.sport_running,
            R.drawable.sport_cycling));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sport_activity);

        sportDao = ((App) getApplication()).getDaoSession().getSportDao();

        imageParent = (LinearLayout) findViewById(R.id.parent_for_all_sport_images);
        for (final int imageId: images) {
            final ImageView sportImage = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    (int) getResources().getDimension(R.dimen.image_in_list_size),
                    (int) getResources().getDimension(R.dimen.image_in_list_size)
            );
            sportImage.setId(imageId);
            sportImage.setLayoutParams(layoutParams);
            sportImage.setImageDrawable(ContextCompat.getDrawable(this, imageId));
            sportImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectSportImage(imageId);
                }
            });
            imageParent.addView(sportImage);
        }
    }

    public void addNewSport(View view) {
        TextView newSportDescription = (TextView)findViewById(R.id.new_sport_name);
        if (newSportDescription.getText() == null ||
                newSportDescription.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please provide name for the sport.", Toast.LENGTH_LONG).show();
            return;
        }
        String newSportName = newSportDescription.getText().toString();

        List<Sport> sportsWithName = sportDao.queryBuilder()
                .where(SportDao.Properties.Name.eq(newSportName))
                .build().list();

        if (sportsWithName.size() == 0) {
            // create new sport
            Sport newSport = new Sport();
            newSport.setName(newSportName);
            newSport.setImageDrawableId(selectedSportImageId == -1 ? R.drawable.sport_base : selectedSportImageId);
            newSport.setIsVisible(true);
            newSport.setSelectedTimes(getMaxSelectedTimes(sportDao) + 1);
            sportDao.insert(newSport);
        } else {
            // make existing sport visible again
            Sport oldSport = sportsWithName.get(0);
            oldSport.setIsVisible(true);
            oldSport.setSelectedTimes(getMaxSelectedTimes(sportDao) + 1);
            sportDao.insertOrReplace(oldSport);
        }

        Intent intent = new Intent(view.getContext(), SportsListActivity.class);
        startActivity(intent);
        finish();
    }

    private void selectSportImage(int imageId) {
        selectedSportImageId = imageId;
        final int childCount = imageParent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) imageParent.getChildAt(i);
            if (imageView.getId() == imageId) {
                imageView.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.colorForSelectedImage));
            } else {
                imageView.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.normalBackgroundColor));
            }
        }
    }
}
