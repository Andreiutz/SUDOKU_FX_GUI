package com.example.sudoku_fx_gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SudokuSolver {

    private Integer zero = 0;
    private List<Integer> values;

    public SudokuSolver() {
        values = new ArrayList<>();
        for (Integer i = 1; i <= 9; i++) values.add(i);
        Collections.shuffle(values);
    }

    public void solveSudoku(SudokuBoard board) {

        solve(board);
    }

    //Solves the board, returns true if possible, false instead
    public boolean solve(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Objects.equals(board.get(i, j), zero)) {
                    for (Integer c : values) {
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

    public boolean consistent(SudokuBoard board) {
        //Checks every cell and verifies if the uncompleted board can lead to a solution
        for (int index = 0; index < 9; index++) {
            if (!checkRow(board, index) || !checkCol(board, index) || !checkSquare(board, index/3, index%3)) return false;
        }
        return true;
    }


    private boolean checkRow(SudokuBoard board, int rowIndex) {
        int[] digits = {0,0,0,0,0,0,0,0,0,0,0,0}; //contains the frequence for each digit after execution
        for (int i = 0; i < 9; i++) {
            if (board.get(rowIndex, i) != 0) {
                digits[board.get(rowIndex, i)]++;
                if (digits[board.get(rowIndex, i)] > 1) {
                    return false;//at least a digit appears twice
                }
            }
        }
        return true;
    }



    private boolean checkCol(SudokuBoard board, int colIndex) {
        int[] digits = {0,0,0,0,0,0,0,0,0,0,0,0}; //contains the frequence for each digit after execution
        for (int i = 0; i < 9; i++) {
            if (board.get(i, colIndex) != 0) {
                digits[board.get(i, colIndex)]++;
                if (digits[board.get(i, colIndex)] > 1) {
                    return false;//at least a digit appears twice
                }
            }
        }
        return true;
    }

    private boolean checkSquare(SudokuBoard board, int i, int j) {
        int[] digits = {0,0,0,0,0,0,0,0,0,0,0,0}; //contains the frequence for each digit after execution
        int iStart = i/3*3;
        int jStart = j/3*3;
        for (int x = iStart; x < iStart+3; x++) {
            for (int y = jStart; y < jStart+3; y++) {
                if (board.get(x, y) != 0) {
                    digits[board.get(x, y)]++;
                    if (digits[board.get(x, y)] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

