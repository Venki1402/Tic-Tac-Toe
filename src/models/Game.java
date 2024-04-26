package models;

import exceptions.InvalidMoveException;
import strategies.WinningAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextPlayerIndex;
    private WinningAlgorithm winningAlgorithm;

    public Game(int dimension,List<Player> players){
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerIndex = 0;
        this.winningAlgorithm = new WinningAlgorithm();
    }

    public Game(Board board, List<Player> players, List<Move> moves, GameState gameState, Player winner, int nextPlayerIndex) {
         this.board = board;
        this.players = players;
        this.moves = moves;
        this.gameState = gameState;
        this.winner = winner;
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void printBoard(){
        this.board.printBoard();
    }

    public void makeMove(){
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println(currentPlayer.getName() + " : Please make your move");
        Move move = currentPlayer.makeMove(board);

        if (!isValidMove(move)) throw new InvalidMoveException("Dear player "+currentPlayer.getName()+" The move that you are trying to make is Invalid. Please try something else!");

        int r = move.getCell().getRow();
        int c = move.getCell().getCol();

        Cell cellToChange = board.getBoard().get(r).get(c);
        cellToChange.setPlayer(currentPlayer);
        cellToChange.setCellState(CellState.FILLED);

        Move finalMove = new Move(cellToChange,currentPlayer);
        moves.add(finalMove);
        nextPlayerIndex = (nextPlayerIndex + 1)%players.size();

        // Game over ?
        if (winningAlgorithm.checkWinner(board,finalMove)){
            gameState = GameState.ENDED;
            winner = currentPlayer;
        }
    }

    public boolean isValidMove(Move move){
        int r = move.getCell().getRow();
        int c = move.getCell().getCol();
        if (r<0 || c<0 || r>=board.getSize() || c>=board.getSize()) return false;
        return board.getBoard().get(r).get(c).getCellState().equals(CellState.EMPTY);
    }

}
