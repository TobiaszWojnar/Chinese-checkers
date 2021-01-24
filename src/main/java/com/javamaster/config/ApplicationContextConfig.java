package com.javamaster.config;

import com.javamaster.entity.Games;
import com.javamaster.entity.Moves;
import com.javamaster.entity.Players;
import com.javamaster.entity.dao.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.javamaster.entity")
@EnableTransactionManagement
public class ApplicationContextConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ChineseCheckers");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

        sessionBuilder.addAnnotatedClasses(Games.class, Moves.class, Players.class);

        return sessionBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean(name = "gamesDao")
    public GamesDAO getGamesDAO(SessionFactory sessionFactory) {
        return new GamesDAOImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "movesDao")
    public MovesDAO getMovesDAO(SessionFactory sessionFactory) {
        return new MovesDAOImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "playersDao")
    public PlayersDAO getPlayersDAO(SessionFactory sessionFactory) {
        return new PlayersDAOImpl(sessionFactory);
    }
}