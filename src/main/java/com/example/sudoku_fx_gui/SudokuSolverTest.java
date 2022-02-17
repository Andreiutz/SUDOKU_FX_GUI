package com.example.sudoku_fx_gui;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuSolverTest {


    @Test
    public void testValidBoard() {
        SudokuSolver solver = new SudokuSolver();
        SudokuBoard board = new SudokuBoard();
        board.set(0, 0, 9);
        board.set(7, 7, 5);
        assertTrue(solver.valid(board, 0, 2, 2));
        assertFalse(solver.valid(board, 1, 2, 9));
        assertFalse(solver.valid(board, 7, 2, 5));
        assertFalse(solver.valid(board, 0, 5, 9));
        assertTrue(solver.valid(board, 0, 1, 3));
    }

    /*
    Test Board
            Valid input
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','4'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
     */

    private void setupTestBoard(SudokuBoard board) {
            Integer[][] matrix = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                                  {6, 0, 0, 1, 9, 5, 0, 0, 0},
                                  {0, 9, 8, 0, 0, 0, 0, 6, 0},
                                  {8, 0, 0, 0, 6, 0, 0, 0, 3},
                                  {4, 0, 0, 8, 0, 3, 0, 0, 1},
                                  {7, 0, 0, 0, 2, 0, 0, 0, 6},
                                  {0, 6, 0, 0, 0, 0, 2, 8, 4},
                                  {0, 0, 0, 4, 1, 9, 0, 0, 5},
                                  {0, 0, 0, 0, 8, 0, 0, 7, 9}};

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board.set(i, j, matrix[i][j]);
                }
            }

    }

    @Test
    public void testSolve() {
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new SudokuSolver();
        setupTestBoard(board);
        SudokuBoard invalid_board = new SudokuBoard(board);
        invalid_board.set(0, 2, 3);
        assertFalse(solver.solve(invalid_board));
        assertTrue(solver.solve(board));
    }




}
