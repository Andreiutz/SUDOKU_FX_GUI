package com.example.sudoku_fx_gui;

public class SudokuSolver {
    
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    //Solves the board, returns true if possible, false instead
    public boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (valid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
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
    public boolean valid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;
            if (board[i][col] == c) return false;
            if (board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == c) return false;
        }
        return  true;
    }

}
