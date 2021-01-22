package com.javamaster.service;

import com.javamaster.entity.Games;
import com.javamaster.entity.Moves;
import com.javamaster.entity.Players;
import com.javamaster.entity.dao.GamesDAOImpl;
import com.javamaster.entity.dao.MovesDAOImpl;
import com.javamaster.entity.dao.PlayersDAOImpl;
import com.javamaster.exception.InvalidGameException;
import com.javamaster.exception.InvalidParamException;
import com.javamaster.exception.NotFoundException;
import com.javamaster.model.*;
import com.javamaster.model.board.IntPoint;
import com.javamaster.model.chineseCheckers.GameInstance;
import com.javamaster.model.chineseCheckers.datastructures.PlayerList;
import com.javamaster.storage.GameInstanceStorage;
import com.javamaster.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static com.javamaster.model.GameStatus.*;

@Service
@AllArgsConstructor
public class GameService {

    @Autowired
    GamesDAOImpl gamesDAO;
    @Autowired
    PlayersDAOImpl playersDAO;
    @Autowired
    MovesDAOImpl movesDAO;

    public Game createGame(NewGame newGame) {
        Game game = new Game();
        newGame.getPlayer().setAlive(true);
        GameInstance gameInstance = new GameInstance(newGame.getNumOfPlayers(),
                newGame.getRuleset(), newGame.getBoardType());
        game.setBoard(gameInstance.getBoard().getBoard());
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        game.setGameId(uuid.toString());
        gameInstance.setGameId(uuid.toString());
        game.setPlayers(new PlayerList(newGame.getNumOfPlayers()));
        game.getPlayers().add(newGame.getPlayer());
        gameInstance.addPlayer(newGame.getPlayer());
        game.setStatus(NEW);
        GameStorage.getInstance().setGame(game);
        GameInstanceStorage.getInstance().setGameInstance(gameInstance);
        return game;
    }

    public Game connectToGame(Player newPlayer, String gameId) throws InvalidParamException {
        if (!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }
        Game game = GameStorage.getInstance().getGames().get(gameId);
        GameInstance gameInstance = GameInstanceStorage.getInstance().getGames().get(gameId);


        newPlayer.setAlive(true);
        game.getPlayers().add(newPlayer);
        gameInstance.addPlayer(newPlayer);
        if (game.getPlayers().full()) {
            game.setStatus(IN_PROGRESS);
            gameInstance.setCurrentPlayer(new Random().nextInt(gameInstance.getNumOfPlayers()) + 1);
            Set<Players> players = new HashSet<>();
            for (Player player : game.getPlayers().getMap().values()) {
                Players p = new Players();
                p.setLogin(player.getLogin());
                playersDAO.save(p);
                players.add(p);
                game.setCurrent(Field.valueOf("Player"+gameInstance.getCurrentPlayer()));
            }
            Games games = new Games();
            games.setGame_id(gameId);
            games.setNumOfPlayers(gameInstance.getNumOfPlayers());
            games.setVariant(gameInstance.getVariant());
            games.setBoardType(gameInstance.getBoardType());
            games.setStarting_player(gameInstance.getCurrentPlayer());
            games.setBegan(new Timestamp(System.currentTimeMillis()));
            games.setPlayers(players);
            gamesDAO.save(games);
        }
        GameStorage.getInstance().setGame(game);
        GameInstanceStorage.getInstance().setGameInstance(gameInstance);
        return game;
    }


    public Game gamePlay(GamePlay gamePlay) throws NotFoundException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())) {
            throw new NotFoundException("Game not found");
        }

        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());
        GameInstance gameInstance = GameInstanceStorage.getInstance().getGames().get(gamePlay.getGameId());
        if (game.getStatus().equals(FINISHED)) {
            throw new InvalidGameException("Game is already finished");
        }

        switch (gamePlay.getMessage()) {
            case "SELECT":
                if (gamePlay.getType().getValue() == gameInstance.getCurrentPlayer()) {
                    int x = gamePlay.getCoordinateX();
                    int y = gamePlay.getCoordinateY();
                    gameInstance.setChosen(new IntPoint(x, y));
                    gameInstance.getLogic().highlightPossible(x, y,
                            Field.valueOf("Player" + gameInstance.getCurrentPlayer()));
                    game.setBoard(gameInstance.getBoard().getBoard());
                }
                break;
            case "DESELECT":
                if (gamePlay.getType().getValue() == gameInstance.getCurrentPlayer()) {
                    int x = gamePlay.getCoordinateX();
                    int y = gamePlay.getCoordinateY();
                    gameInstance.getLogic().deselect(x, y,
                            Field.valueOf("Player" + gameInstance.getCurrentPlayer()));
                    gameInstance.setChosen(null);
                    game.setBoard(gameInstance.getBoard().getBoard());
                }
                break;
            case "MOVE":
                if (gamePlay.getType().getValue() == gameInstance.getCurrentPlayer()) {
                    int x = gamePlay.getCoordinateX();
                    int y = gamePlay.getCoordinateY();
                    gameInstance.getLogic().move(x, y, gameInstance.getChosen().getX(),
                            gameInstance.getChosen().getY(),
                            Field.valueOf("Player" + gameInstance.getCurrentPlayer()));

                    Moves move = new Moves();
                    move.setGame(game.getGameId());
                    move.setMove_id(gameInstance.getMoveCount());
                    move.setPlayer(playersDAO.getPlayer_id(gamePlay.getLogin()));
                    move.setFrom_x(gameInstance.getChosen().getX());
                    move.setFrom_y(gameInstance.getChosen().getY());
                    move.setTo_x(x);
                    move.setTo_y(y);

                    movesDAO.save(move);

                    gameInstance.setChosen(null);
                    boolean winner = gameInstance.checkIfWinner();
                    if (winner) {
                        game.setWinner(game.getPlayers().getMap().get(gameInstance.getCurrentPlayer()));
                        game.getPlayers().getMap().get(gameInstance.getCurrentPlayer()).setAlive(false);
                    }
                    gameInstance.endTurn();
                    game.setBoard(gameInstance.getBoard().getBoard());
                }
                break;
            case "ENDTURN":
                if (gamePlay.getType().getValue() == gameInstance.getCurrentPlayer()) {
                    gameInstance.endTurn();
                }
            case "SKIP":
                if (gamePlay.getType().getValue() == gameInstance.getCurrentPlayer()) {
                    if (gameInstance.getChosen() != null) {
                        gameInstance.getLogic().deselect(gameInstance.getChosen().getX(),
                                gameInstance.getChosen().getY(),
                                Field.valueOf("Player" + gameInstance.getCurrentPlayer()));
                        gameInstance.setChosen(null);
                        game.setBoard(gameInstance.getBoard().getBoard());
                    }
                    gameInstance.endTurn();
                }
                break;
            case "RESIGN":
            case "CLOSE":
                game.setResigned(game.getPlayers().getMap().get(gamePlay.getType().getValue()));
                gameInstance.kill(gamePlay.getType().getValue());
                game.getPlayers().getMap().get(gamePlay.getType().getValue()).setAlive(false);
                if (gamePlay.getType().getValue() == gameInstance.getCurrentPlayer()) {
                    gameInstance.endTurn();
                }
                break;
        }
        game.setCurrent(Field.valueOf("Player"+gameInstance.getCurrentPlayer()));
        GameStorage.getInstance().setGame(game);
        return game;
    }
}