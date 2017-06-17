package com.healthtracking.data;

import java.util.List;

/**
 * Created by larisa on 6/11/17.
 */

public interface SportDAO {
    List<Sport> findAll();

    Sport getSportById(int id);

    boolean insertSport(String sportName);
    boolean insertSport(String sportName, int imageId);
    boolean deleteSport(int sportId);
}
