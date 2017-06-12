package com.healthtracking.ui.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtracking.R;
import com.healthtracking.data.FakeHobbyProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddNewHobbyActivity extends AppCompatActivity {

    private LinearLayout imageParent;
    private int selectedHobbyImageId = -1;

    private static List<Integer> images = new ArrayList<>(Arrays.asList(
            R.drawable.hobby,
            R.drawable.hobby_bath,
            R.drawable.hobby_cooking,
            R.drawable.hobby_draw,
            R.drawable.hobby_meditation,
            R.drawable.hobby_piano,
            R.drawable.hobby_read,
            R.drawable.hobby_tv,
            R.drawable.health,
            R.drawable.mood,
            R.drawable.food,
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_hobby_activity);

        imageParent = (LinearLayout) findViewById(R.id.parent_for_all_hobby_images);
        for (final int imageId: images) {
            final ImageView hobbyImage = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    (int) getResources().getDimension(R.dimen.image_in_list_size),
                    (int) getResources().getDimension(R.dimen.image_in_list_size)
            );
            hobbyImage.setId(imageId);
            hobbyImage.setLayoutParams(layoutParams);
            hobbyImage.setImageDrawable(ContextCompat.getDrawable(this, imageId));
            hobbyImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectHobbyImage(imageId);
                }
            });
            imageParent.addView(hobbyImage);
        }
    }

    public void addNewHobby(View view) {
        TextView newHobbyDescription = (TextView)findViewById(R.id.new_hobby_name);
        if (newHobbyDescription.getText() == null ||
                newHobbyDescription.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please provide name for the hobby.", Toast.LENGTH_LONG).show();
            return;
        }

        if (selectedHobbyImageId == -1) {
            FakeHobbyProvider.getInstance().insertHobby(
                    newHobbyDescription.getText().toString());
        } else {
            FakeHobbyProvider.getInstance().insertHobby(
                    newHobbyDescription.getText().toString(), selectedHobbyImageId);
        }

        Intent intent = new Intent(view.getContext(), HobbiesListActivity.class);
        startActivity(intent);
        finish();
    }

    private void selectHobbyImage(int imageId) {
        selectedHobbyImageId = imageId;
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
