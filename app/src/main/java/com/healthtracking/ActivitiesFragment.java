package com.healthtracking;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivitiesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment {
    //???
    private OnFragmentInteractionListener mListener;

    private Boolean isFabOpen = false;
    private FloatingActionButton fabAdd,fabFood,fabSport, fabHealth, fabMood;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }


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

        CoordinatorLayout cl = (CoordinatorLayout) view.findViewById(R.id.fragment1);
        cl.addView(fabFood);
        cl.addView(fabSport);
        cl.addView(fabMood);
        cl.addView(fabHealth);

        fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_backward);

        return view;
    }

    private FloatingActionButton getFloatingButton(Context context, ActionType actionType) {
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
        fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.p3_plus));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        return fab;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void animateFAB(){

        if(isFabOpen){

            fabAdd.startAnimation(rotate_backward);

            fabFood.startAnimation(fab_close);
            fabSport.startAnimation(fab_close);
            fabHealth.startAnimation(fab_close);
            fabMood.startAnimation(fab_close);


            fabFood.setClickable(false);
            fabSport.setClickable(false);
            fabHealth.setClickable(false);
            fabMood.setClickable(false);

            isFabOpen = false;

        } else {

            fabAdd.startAnimation(rotate_forward);

            fabFood.startAnimation(fab_open);
            fabSport.startAnimation(fab_open);
            fabHealth.startAnimation(fab_open);
            fabMood.startAnimation(fab_open);

            fabFood.setClickable(true);
            fabSport.setClickable(true);
            fabHealth.setClickable(true);
            fabMood.setClickable(true);

            isFabOpen = true;
        }
    }
}
