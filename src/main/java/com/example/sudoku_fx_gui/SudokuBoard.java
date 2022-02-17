package com.example.sudoku_fx_gui;

public class SudokuBoard {

    private Integer[][] board = new Integer[9][9];

    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    public SudokuBoard(SudokuBoard copy) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] =copy.get(i, j);
            }
        }
    }

    public Integer get(int i, int j) {
        return this.board[i][j];
    }

    public void set(int i, int j, Integer val) {
        this.board[i][j] = val;
    }

}
