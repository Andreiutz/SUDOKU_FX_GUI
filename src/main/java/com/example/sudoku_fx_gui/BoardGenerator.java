package com.example.sudoku_fx_gui;

import java.util.Random;

public class BoardGenerator {

    private final Random random = new Random();
    private final SudokuBoard board = new SudokuBoard();
    private final SudokuSolver solver = new SudokuSolver();

    public void generateBoard(Integer[][] int_board) {
        solver.solve(board);
        for (int index = 1; index <= 30; index++) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            int_board[row][col] = board.get(row, col);
        }

    }

}
