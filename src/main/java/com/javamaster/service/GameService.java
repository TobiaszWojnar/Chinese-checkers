package com.javamaster.service;

import com.javamaster.entity.Games;
import com.javamaster.entity.Moves;
import com.javamaster.entity.Players;
import com.javamaster.entity.dao.GamesDAO;
import com.javamaster.entity.dao.GamesDAOImpl;
import com.javamaster.entity.dao.MovesDAOImpl;
import com.javamaster.entity.dao.PlayersDAOImpl;
import com.javamaster.exception.InvalidGameException;
import com.javamaster.exception.InvalidParamException;
import com.javamaster.exception.NotFoundException;
import com.javamaster.model.*;
import com.javamaster.model.board.IntPoint;
import com.javamaster.model.chineseCheckers.GameInstance;
import com.javamaster.model.chineseCheckers.ReplayInstance;
import com.javamaster.model.chineseCheckers.datastructures.PlayerList;
import com.javamaster.storage.GameInstanceStorage;
import com.javamaster.storage.GameStorage;
import com.javamaster.storage.ReplayInstanceStorage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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
        game.setPlayer(Field.Player1);
        game.setPlayerList(new ArrayList<>(game.getPlayers().getMap().values()));
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
        game.setPlayer(Field.valueOf("Player" + game.getPlayers().getIndex()));
        game.setPlayerList(new ArrayList<>(game.getPlayers().getMap().values()));
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
                    gameInstance.incMoveCount();
                    Moves move = new Moves();
                    move.setGame(game.getGameId());
                    move.setMove_id(gameInstance.getMoveCount());
                    move.setPlayer(gameInstance.getCurrentPlayer());
                    move.setFrom_x(gameInstance.getChosen().getX());
                    move.setFrom_y(gameInstance.getChosen().getY());
                    move.setTo_x(x);
                    move.setTo_y(y);

                    movesDAO.save(move);

                    gameInstance.setChosen(null);
                    boolean winner = gameInstance.checkIfWinner();
                    if (winner) {
                        game.getWinners().add(game.getPlayers().getMap().get(gameInstance.getCurrentPlayer()));
                        game.getPlayers().getMap().get(gameInstance.getCurrentPlayer()).setAlive(false);
                    }
                    gameInstance.endTurn();
                    if (game.getPlayers().last()) {
                        game.getWinners().add(game.getPlayers().getMap().get(gameInstance.getCurrentPlayer()));
                        game.getPlayers().getMap().get(gameInstance.getCurrentPlayer()).setAlive(false);
                        game.setStatus(FINISHED);
                    }
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
                    if (game.getPlayers().last()) {
                        game.getWinners().add(game.getPlayers().getMap().get(gameInstance.getCurrentPlayer()));
                        game.getPlayers().getMap().get(gameInstance.getCurrentPlayer()).setAlive(false);
                        game.setStatus(FINISHED);
                    }
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
        game.setPlayerList(new ArrayList<>(game.getPlayers().getMap().values()));
        game.setCurrent(Field.valueOf("Player"+gameInstance.getCurrentPlayer()));
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game startReplay(String gameId) {
        Games games = gamesDAO.getGame(gameId);
        List<Moves> moves = movesDAO.list(gameId);
        Game game = new Game();
        game.setGameId(gameId);
        List<String> logins = playersDAO.getPlayerLogins(gameId);
        PlayerList players = new PlayerList(games.getNumOfPlayers());
        for (String login : logins) {
            Player player = new Player();
            player.setLogin(login);
            player.setAlive(true);
            players.add(player);
        }
        ReplayInstance replayInstance = new ReplayInstance(gameId,
                games.getNumOfPlayers(), games.getVariant(),
                games.getBoardType(), players, moves);
        game.setBoard(replayInstance.getBoard().getBoard());
        game.setCurrent(Field.valueOf("Player" + replayInstance.getCurrentPlayer()));
        game.setPlayers(players);
        ReplayInstanceStorage.getInstance().setReplay(replayInstance);
        return game;
    }

    public Game replay(String gameId, boolean forward) {
        ReplayInstance replayInstance = ReplayInstanceStorage.getInstance().getReplays().get(gameId);
        Game game = new Game();
        replayInstance.move(forward);
        game.setPlayers(replayInstance.getPlayers());
        game.setCurrent(Field.valueOf("Player" + replayInstance.getCurrentPlayer()));
        game.setBoard(replayInstance.getBoard().getBoard());
        game.setGameId(gameId);
        return game;
    }
}