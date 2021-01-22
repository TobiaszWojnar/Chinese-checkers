package com.javamaster.entity.dao;

import com.javamaster.entity.Moves;

import java.util.List;

public interface MovesDAO {

    public void save(Moves p);

    public List<Moves> list(String game);
}
