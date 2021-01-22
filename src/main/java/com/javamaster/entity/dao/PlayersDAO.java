package com.javamaster.entity.dao;

import com.javamaster.entity.Players;

public interface PlayersDAO {

    public void save(Players p);

    public int getPlayer_id(String login);
}
