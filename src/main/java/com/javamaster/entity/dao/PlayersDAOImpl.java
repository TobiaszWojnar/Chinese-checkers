package com.javamaster.entity.dao;

import com.javamaster.entity.Players;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayersDAOImpl implements PlayersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public PlayersDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Players p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }

    public int getPlayer_id(String login) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT player_id FROM players WHERE login LIKE :login");
        query.setParameter("login", login);
        int id = (int) query.uniqueResult();
        session.close();
        return id;
    }

    @SuppressWarnings("unchecked")
    public List<String> getPlayerLogins(String gameId) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT login FROM players " +
                "JOIN games_players gp on players.player_id = gp.player " +
                "JOIN games g on g.game_id = gp.game WHERE game_id = :gameId");
        query.setParameter("gameId", gameId);
        List<String> logins = query.list();
        session.close();
        return logins;
    }
}
