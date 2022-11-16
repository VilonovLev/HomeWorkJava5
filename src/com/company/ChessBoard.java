package com.company;

import java.util.Arrays;
import java.util.Random;

public class ChessBoard {
    Random random = new Random();
    private int countQueens = 0;
    private String[][] arrayField = new String[8][8];
    private String cage = "\u29C8";
    private String queen = "Q";
    private String attackCage = "\u2BD0";

    public ChessBoard() {
        setBoardToStart();
    }

    public void setBoardToStart() {
        for (int i = 0; i < 8; i++) {
            Arrays.fill(arrayField[i], cage);
        }
    }

    public String getBoardStatus() {
        String result = "";
        for (int i = 0; i < arrayField.length; i++) {
            result += (i + 1) + " ";
            for (int j = 0; j < arrayField.length; j++) {
                result += " " + arrayField[i][j];
            }
            result += "\n";
        }
        return result;
    }

    public void addQeuun(int x, int y) {
        for (int i = 0; i < arrayField.length; i++) {
            arrayField[y][i] = attackCage;
            arrayField[i][x] = attackCage;
            if (x + i < arrayField.length && y + i < arrayField.length)
                arrayField[y + i][x + i] = attackCage;
            if (x - i >= 0 && y - i >= 0)
                arrayField[y - i][x - i] = attackCage;
            if (x + i < arrayField.length && y - i >= 0)
                arrayField[y - i][x + i] = attackCage;
            if (x - i >= 0 && y + i < arrayField.length)
                arrayField[y + i][x - i] = attackCage;
        }
        arrayField[y][x] = queen;
        countQueens++;
    }

    public boolean isAcceptableMove(int x, int y) {
        return arrayField[y][x] == cage;
    }

    public boolean isThereMoves() {
        for (String[] line : arrayField) {
            for (String ell : line) {
                if (ell.equals(cage)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String searchSolution() {
        while (isThereMoves()) {
            int x, y;
            do {
                x = random.nextInt(0, 8);
                y = random.nextInt(0, 8);
            } while (!isAcceptableMove(x, y));
            addQeuun(x, y);
            if (!isThereMoves() && countQueens < 8) {
                countQueens = 0;
                setBoardToStart();
            }
        }
        return getBoardStatus();
    }
}
