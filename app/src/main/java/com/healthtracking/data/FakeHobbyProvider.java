package com.healthtracking.data;

import com.healthtracking.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeHobbyProvider implements HobbyDAO {
    private static FakeHobbyProvider fakeHobbyProvider;

    private static Hobby hobby1 = new Hobby(0, "Cooking", R.drawable.hobby_cooking);
    private static Hobby hobby2 = new Hobby(1, "Watching TV", R.drawable.hobby_tv);
    private static Hobby hobby3 = new Hobby(2, "Reading books", R.drawable.hobby_read);
    private static Hobby hobby4 = new Hobby(3, "Playing piano", R.drawable.hobby_piano);
    private static Hobby hobby5 = new Hobby(4, "Drawing", R.drawable.hobby_draw);
    private static Hobby hobby6 = new Hobby(5, "Taking bath", R.drawable.hobby_bath);
    private static Hobby hobby7 = new Hobby(6, "Meditation", R.drawable.hobby_meditation);
    private static Hobby hobby8 = new Hobby(7, "Fake hobby 1", R.drawable.hobby_cooking);
    private static Hobby hobby9 = new Hobby(8, "Fake hobby 2", R.drawable.hobby_cooking);
    private static Hobby hobby10 = new Hobby(9, "Fake hobby 3", R.drawable.hobby_cooking);
    private static Hobby hobby11 = new Hobby(10, "Fake hobby 4", R.drawable.hobby_cooking);
    private static Hobby hobby12 = new Hobby(11, "Fake hobby 5", R.drawable.hobby_cooking);
    private static Hobby hobby13 = new Hobby(12, "Fake hobby 6", R.drawable.hobby_cooking);
    private static Hobby hobby14 = new Hobby(13, "Fake hobby 7", R.drawable.hobby_cooking);
    private static Hobby hobby15 = new Hobby(14, "Fake hobby 8", R.drawable.hobby_cooking);
    private static Hobby hobby16 = new Hobby(15, "Fake hobby 9", R.drawable.hobby_cooking);
    private static Hobby hobby17 = new Hobby(16, "Fake hobby 10", R.drawable.hobby_cooking);

    private static List<Hobby> hobbies = new ArrayList<>(Arrays.asList(
            hobby1, hobby2, hobby3, hobby4, hobby5, hobby6, hobby7, hobby8, hobby9, hobby10,
            hobby11, hobby12, hobby13, hobby14, hobby15, hobby16, hobby17));

    private FakeHobbyProvider() {}

    public static FakeHobbyProvider getInstance() {
        if (fakeHobbyProvider == null) {
            fakeHobbyProvider = new FakeHobbyProvider();
        }
        return fakeHobbyProvider;
    }

    public List<Hobby> findAll() {
        return hobbies;
    }

    public boolean deleteHobby(int hobbyId) {
        int index = -1;
        for (int i=0; i < hobbies.size(); i++) {
            if (hobbies.get(i).getId() == hobbyId) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        hobbies.remove(index);
        return true;
    }

    public Hobby getHobbyById(int hobbyId) {
        Hobby hobby = null;
        for (int i=0; i < hobbies.size(); i++) {
            if (hobbies.get(i).getId() == hobbyId) {
                hobby = hobbies.get(i);
                break;
            }
        }
        return hobby;
    }

    public boolean insertHobby(String hobbyName) {
        Hobby hobby = new Hobby(
                hobbies.get(hobbies.size() - 1).getId() + 1,
                hobbyName,
                R.drawable.hobby_cooking);
        hobbies.add(hobby);
        return true;
    }

    public boolean insertHobby(String hobbyName, int imageId) {
        Hobby hobby = new Hobby(
                hobbies.get(hobbies.size() - 1).getId() + 1,
                hobbyName,
                imageId);
        hobbies.add(hobby);
        return true;
    }
}
