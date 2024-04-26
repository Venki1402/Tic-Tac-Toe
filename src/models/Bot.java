package models;

import java.util.List;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, Symbol symbol, PlayerType playerType) {
        super(name, symbol, playerType);
    }

    public Bot(String name, Symbol symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        // We are going to find the first empty cell and then fill it.
        for (List<Cell> row : board.getBoard()){
            for (Cell cell : row){
                if (cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell,this);
                }
            }
        }
        return null;
    }
}
