package com.javamaster.service;

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
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

import static com.javamaster.model.GameStatus.*;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(NewGame newGame) {
        Game game = new Game();
        GameInstance gameInstance = new GameInstance(newGame.getNumOfPlayers(),
                newGame.getRuleset(), newGame.getBoardType());
        game.setBoard(gameInstance.getBoard().getBoard());
        game.setGameId(UUID.randomUUID().toString());
        gameInstance.setGameId(UUID.randomUUID().toString());
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


        game.getPlayers().add(newPlayer);
        gameInstance.addPlayer(newPlayer);
        if (game.getPlayers().full()) {
            game.setStatus(IN_PROGRESS);
            gameInstance.setCurrentPlayer(new Random().nextInt(gameInstance.getNumOfPlayers()));
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

        GameStorage.getInstance().setGame(game);
        return game;
    }
}
