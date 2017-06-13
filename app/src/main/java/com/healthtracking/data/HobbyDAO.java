package com.healthtracking.data;

import java.util.List;

/**
 * Created by larisa on 6/11/17.
 */

public interface HobbyDAO {
    List<Hobby> findAll();

    Hobby getHobbyById(int id);

    boolean insertHobby(String hobbyName);
    boolean insertHobby(String hobbyName, int imageId);
    boolean deleteHobby(int hobbyId);
}
