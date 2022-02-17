package com.example.sudoku_fx_gui;

import java.util.Objects;

public class SudokuSolver {

    private Integer zero = 0;

    public void solveSudoku(SudokuBoard board) {
        solve(board);
    }

    //Solves the board, returns true if possible, false instead
    public boolean solve(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Objects.equals(board.get(i, j), zero)) {
                    for (Integer c = 1; c <= 9; c++) {
                        if (valid(board, i, j, c)) {
                            board.set(i, j, c);
                            if (solve(board)) {
                                return true;
                            } else {
                                board.set(i, j, zero);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    //Verifies if certain cell can take the c char
    public boolean valid(SudokuBoard board, int row, int col, Integer c) {
        for (int i = 0; i < 9; i++) {
            if (Objects.equals(board.get(row, i), c)) return false;
            if (Objects.equals(board.get(i, col), c)) return false;
            if (Objects.equals(board.get(row / 3 * 3 + i / 3, col / 3 * 3 + i % 3), c)) return false;
        }
        return  true;
    }


}
