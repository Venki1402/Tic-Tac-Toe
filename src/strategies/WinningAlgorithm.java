package strategies;

import models.Board;
import models.Move;

import java.util.HashMap;

public class WinningAlgorithm {

    HashMap<Integer, HashMap<Character, Integer>> rowMaps = new HashMap<>();
    HashMap<Integer, HashMap<Character, Integer>> colMaps = new HashMap<>();
    HashMap<Character, Integer> leftDiagonalMap = new HashMap<>();
    HashMap<Character, Integer> rightDiagonalMap = new HashMap<>();

    public boolean checkWinner(Board board, Move move) {

        int r = move.getCell().getRow();
        int c = move.getCell().getCol();
        Character character = move.getPlayer().getSymbol().getaChar();

        //ROW
        if (!rowMaps.containsKey(r)) {
            rowMaps.put(r, new HashMap<>());
        }
        HashMap<Character, Integer> currRowMap = rowMaps.get(r);

        if (!currRowMap.containsKey(character)) {
            currRowMap.put(character, 1);
        } else {
            currRowMap.put(character, currRowMap.get(character) + 1);
        }

        if (currRowMap.get(character) == board.getSize()) {
            return true;
        }

        //COL
        if (!colMaps.containsKey(c)) {
            colMaps.put(c, new HashMap<>());
        }
        HashMap<Character, Integer> currColMap = colMaps.get(c);

        if (!currColMap.containsKey(character)) {
            currColMap.put(character, 1);
        } else {
            currColMap.put(character, currColMap.get(character) + 1);
        }

        if (currColMap.get(character) == board.getSize()) {
            return true;
        }

        //Left Diagonal
        if (r == c) {
            if (!leftDiagonalMap.containsKey(character)) {
                leftDiagonalMap.put(character, 1);
            } else {
                leftDiagonalMap.put(character, leftDiagonalMap.get(character) + 1);
            }

            if (leftDiagonalMap.get(character) == board.getSize()) {
                return true;
            }
        }

        //Right Diagonal
        if (r + c == board.getSize() - 1) {
            if (!rightDiagonalMap.containsKey(character)) {
                rightDiagonalMap.put(character, 1);
            } else {
                rightDiagonalMap.put(character, rightDiagonalMap.get(character) + 1);
            }

            if (rightDiagonalMap.get(character) == board.getSize()) {
                return true;
            }
        }

        return false;
    }
}
