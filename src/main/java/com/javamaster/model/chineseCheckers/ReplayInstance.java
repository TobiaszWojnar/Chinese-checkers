package com.javamaster.model.chineseCheckers;

import com.javamaster.entity.Moves;
import com.javamaster.model.Field;
import com.javamaster.model.board.chineseCheckers.ChineseBoardFactory;
import com.javamaster.model.board.chineseCheckers.ChineseCheckersBoard;
import com.javamaster.model.chineseCheckers.datastructures.PlayerList;

import java.util.List;

public class ReplayInstance {
    private final String gameId;
    private final ChineseCheckersBoard board;
    //private int currentPlayer;
    private final PlayerList players;
    private int moveCount;
    private final List<Moves> moves;

    public ReplayInstance(String gameId, int numOfPlayers, int ruleset, String boardType, PlayerList players,
                          List<Moves> moves) {
        this.gameId = gameId;
        ChineseBoardFactory boardFactory = new ChineseBoardFactory();
        board = boardFactory.getBoard(boardType);
        board.prepareForPlayers(numOfPlayers);
        this.players = players;
        moveCount = 0;
        this.moves = moves;
        //this.currentPlayer = moves.get(0).getPlayer();
    }

    public void move(boolean forward) {
        if (moveCount >= 0 && moves.size() > moveCount) {

            if (forward) {
                Moves move = moves.get(moveCount);
                board.setField(move.getTo_x(), move.getTo_y(),
                        Field.valueOf("Player" + move.getPlayer()));
                board.setField(move.getFrom_x(), move.getFrom_y(),
                        Field.Empty);
                moveCount++;
                //currentPlayer = moves.get(moveCount).getPlayer();
            } else if (moveCount >= 1) {
                moveCount--;
                Moves move = moves.get(moveCount);
                //currentPlayer = move.getPlayer();
                board.setField(move.getTo_x(), move.getTo_y(),
                        Field.Empty);
                board.setField(move.getFrom_x(), move.getFrom_y(),
                        Field.valueOf("Player" + move.getPlayer()));
            }
        }
    }

    public String getGameId() {
        return gameId;
    }

/*
    public int getCurrentPlayer() {
        return currentPlayer;
    }
*/

    public PlayerList getPlayers() {
        return players;
    }

    public ChineseCheckersBoard getBoard() {
        return board;
    }
}
