package com.javamaster.entity.dao;

import com.javamaster.entity.MoveId;import com.javamaster.entity.Moves;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovesDAOImpl implements MovesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public MovesDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Moves p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Moves> list(String game) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT * FROM moves WHERE game = :game")
                .addEntity(Moves.class)
                .setParameter("game",game);
        List<Moves> moves = (List<Moves>) query.list();
        session.close();
        return moves;
    }
}
