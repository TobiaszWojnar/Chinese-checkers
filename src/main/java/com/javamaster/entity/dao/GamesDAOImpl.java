package com.javamaster.entity.dao;

import com.javamaster.entity.Games;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GamesDAOImpl implements GamesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public GamesDAOImpl() {

    }

    public GamesDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Games p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Games> list(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("CALL listGames(:login)")
                .addEntity(Games.class)
                .setParameter("login", login);
        List<Games> games = query.list();
        session.close();
        return games;
    }

}
