package com.healthtracking.data;

import com.healthtracking.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeSportProvider implements SportDAO {
    private static FakeSportProvider fakeSportProvider;

    private static Sport sport1 = new Sport(0, "Cycling", R.drawable.sport_cycling);
    private static Sport sport2 = new Sport(1, "Running", R.drawable.sport_running);
    private static Sport sport3 = new Sport(2, "Soccer", R.drawable.sport_soccer);
    private static Sport sport4 = new Sport(3, "Badminton", R.drawable.sport_badminton);
    private static Sport sport5 = new Sport(4, "CrossFit", R.drawable.sport_crossfit);
    private static Sport sport6 = new Sport(5, "Swimming", R.drawable.sport_swimming);
    private static Sport sport7 = new Sport(6, "Baseball", R.drawable.sport_baseball);
    private static Sport sport8 = new Sport(7, "Skiing", R.drawable.sport_skiing);
    private static Sport sport9 = new Sport(8, "Dancing", R.drawable.sport_dancing);
    private static Sport sport10 = new Sport(9, "Surfing3", R.drawable.sport_surfing);
    private static Sport sport11 = new Sport(10, "Hiking", R.drawable.sport_hiking);
    private static Sport sport12 = new Sport(11, "Fake sport 5", R.drawable.sport_base);
    private static Sport sport13 = new Sport(12, "Fake sport 6", R.drawable.sport_base);
    private static Sport sport14 = new Sport(13, "Fake sport 7", R.drawable.sport_base);
    private static Sport sport15 = new Sport(14, "Fake sport 8", R.drawable.sport_base);
    private static Sport sport16 = new Sport(15, "Fake sport 9", R.drawable.sport_base);
    private static Sport sport17 = new Sport(16, "Fake sport 10", R.drawable.sport_base);

    private static List<Sport> sports = new ArrayList<>(Arrays.asList(
            sport1, sport2, sport3, sport4, sport5, sport6, sport7, sport8, sport9, sport10,
            sport11, sport12, sport13, sport14, sport15, sport16, sport17));

    private FakeSportProvider() {}

    public static FakeSportProvider getInstance() {
        if (fakeSportProvider == null) {
            fakeSportProvider = new FakeSportProvider();
        }
        return fakeSportProvider;
    }

    public List<Sport> findAll() {
        return sports;
    }

    public boolean deleteSport(int sportId) {
        int index = -1;
        for (int i = 0; i < sports.size(); i++) {
            if (sports.get(i).getId() == sportId) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        sports.remove(index);
        return true;
    }

    public Sport getSportById(int sportId) {
        Sport sport = null;
        for (int i = 0; i < sports.size(); i++) {
            if (sports.get(i).getId() == sportId) {
                sport = sports.get(i);
                break;
            }
        }
        return sport;
    }

    public boolean insertSport(String sportName) {
        Sport sport = new Sport(
                sports.get(sports.size() - 1).getId() + 1,
                sportName,
                R.drawable.sport_base);
        sports.add(sport);
        return true;
    }

    public boolean insertSport(String sportName, int imageId) {
        Sport sport = new Sport(
                sports.get(sports.size() - 1).getId() + 1,
                sportName,
                imageId);
        sports.add(sport);
        return true;
    }
}
