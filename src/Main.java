import controllers.GameController;
import models.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to my game!!");
        System.out.println("Enjoy playing Tic Tac Toe");

        Scanner input = new Scanner(System.in);
        GameController gameController = new GameController();

//        System.out.println("Enter the game dimension : ");
//        int dimension = input.nextInt();

        // HardCoding for now.
        int dimension = 3;
        List<Player> playerList = List.of(
                new Player("John",new Symbol('X'), PlayerType.HUMAN),
                new Bot("AsianBOT",new Symbol('O'),PlayerType.BOT)
        );

        Game game = gameController.startGame(dimension,playerList);
//        gameController.printBoard(game);

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);
            gameController.makeMove(game);
        }
        if (!gameController.checkState(game).equals(GameState.ENDED)) {
            game.setGameState(GameState.DRAW);
            System.out.println("This Game is a draw");
        }
        else {
            System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
        }
    }
}
