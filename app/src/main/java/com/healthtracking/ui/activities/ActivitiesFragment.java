package com.healthtracking.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.healthtracking.App;
import com.healthtracking.R;
import com.healthtracking.data.ActionType;
import com.healthtracking.data.Log;
import com.healthtracking.data.LogDao;

import org.greenrobot.greendao.query.Query;


public class ActivitiesFragment extends Fragment {

    private Boolean isFabOpen = false;
    private FloatingActionButton fabAdd,fabFood,fabSport, fabHealth, fabMood, fabHobby;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    private ListView logListView;

    private LogDao logDao;

    public ActivitiesFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_fragment1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        fabAdd = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });

        fabFood = getFloatingButton(getContext(), ActionType.FOOD);
        fabSport = getFloatingButton(getContext(), ActionType.SPORT);
        fabMood = getFloatingButton(getContext(), ActionType.MOOD);
        fabHealth = getFloatingButton(getContext(), ActionType.HEALTH);
        fabHobby = getFloatingButton(getContext(), ActionType.HOBBY);

        CoordinatorLayout cl = (CoordinatorLayout) view.findViewById(R.id.fragment1);
        cl.addView(fabFood);
        cl.addView(fabSport);
        cl.addView(fabMood);
        cl.addView(fabHealth);
        cl.addView(fabHobby);

        fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_backward);



        logListView = (ListView) view.findViewById(R.id.log_list);

        logDao = ((App)getActivity().getApplication()).getDaoSession().getLogDao();

        Query<Log> logQuery = logDao.queryBuilder()
                .orderDesc(LogDao.Properties.Timestamp)
                .build();

        final LogArrayAdapter adapter = new LogArrayAdapter(getContext(), logQuery.list());
        logListView.setAdapter(adapter);

        return view;
    }

    private FloatingActionButton getFloatingButton(Context context, final ActionType actionType) {
        FloatingActionButton fab = new FloatingActionButton(context);

        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.WRAP_CONTENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.END;

        if(getActivity().getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            layoutParams.bottomMargin = (int) getResources().getDimension(R.dimen.fabs_init_margin)
                    + ((int) getResources().getDimension(R.dimen.fabs_next_margin)
                        * actionType.ordinal());
            layoutParams.rightMargin = (int) getResources().getDimension(R.dimen.fab_margin);
        } else {
            layoutParams.bottomMargin = (int) getResources().getDimension(R.dimen.fab_margin);
            layoutParams.rightMargin = (int) getResources().getDimension(R.dimen.fabs_init_margin)
                    + ((int) getResources().getDimension(R.dimen.fabs_next_margin)
                    * actionType.ordinal());
        }

        fab.setLayoutParams(layoutParams);

        fab.setCompatElevation(getResources().getDimension(R.dimen.fab_elevation));
        fab.setTranslationZ(R.dimen.fab_pressedTranslationZ);
        fab.setVisibility(View.INVISIBLE);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(actionType.getColor())));
        fab.setImageDrawable(ContextCompat.getDrawable(context, actionType.getImage()));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                Intent intent = new Intent(getActivity(), actionType.getActivity());
                startActivity(intent);
            }
        });

        return fab;
    }

    private void animateFAB(){

        if(isFabOpen){

            fabAdd.startAnimation(rotate_backward);

            fabFood.startAnimation(fab_close);
            fabSport.startAnimation(fab_close);
            fabHealth.startAnimation(fab_close);
            fabMood.startAnimation(fab_close);
            fabHobby.startAnimation(fab_close);

            fabFood.setClickable(false);
            fabSport.setClickable(false);
            fabHealth.setClickable(false);
            fabMood.setClickable(false);
            fabHobby.setClickable(false);

            isFabOpen = false;

        } else {

            fabAdd.startAnimation(rotate_forward);

            fabFood.startAnimation(fab_open);
            fabSport.startAnimation(fab_open);
            fabHealth.startAnimation(fab_open);
            fabMood.startAnimation(fab_open);
            fabHobby.startAnimation(fab_open);

            fabFood.setClickable(true);
            fabSport.setClickable(true);
            fabHealth.setClickable(true);
            fabMood.setClickable(true);
            fabHobby.setClickable(true);

            isFabOpen = true;
        }
    }
}
