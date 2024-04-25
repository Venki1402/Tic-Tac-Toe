package controllers;

import models.Game;
import models.GameState;
import models.Player;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players){
        // TODO
        // Verify if the game is valid.
        // If 2 players have chose the same symbol, throw an Exception.

        return new Game(dimension,players);
    }

    public void makeMove(Game game){
        return;
    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void prinBoard(Game game){
        game.printBoard();
    }

}
