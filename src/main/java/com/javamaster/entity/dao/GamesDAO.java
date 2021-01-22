package com.javamaster.entity.dao;

import com.javamaster.entity.Games;

import java.util.List;

public interface GamesDAO {

    public void save(Games p);
    public List<Games> list(String login);
}
