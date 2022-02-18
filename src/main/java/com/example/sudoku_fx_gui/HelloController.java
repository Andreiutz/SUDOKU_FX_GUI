package com.example.sudoku_fx_gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HelloController {



    private final TextField[][] textFields = new TextField[9][9];
    private final Integer[][] initialBoard = new Integer[9][9];

    private Button solveButton;
    private Button resetButton;

    public void setUpGrid(GridPane grid) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5, 5, 5, 5));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField t = new TextField();
                t.setAlignment(Pos.CENTER);
                textFields[i][j] = t;

                t.setMinWidth(40);
                t.setPrefWidth(40);
                t.setMaxWidth(40);
                int id = i*10+j;
                t.setId(""+id);

                t.setMinHeight(40);
                t.setPrefHeight(40);
                t.setMaxHeight(40);

                t.setStyle("-fx-background-color: white;");
                t.setFont(Font.font("", FontWeight.BOLD, 20));
                t.setOnKeyTyped(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        boolean validInput = true;
                        Integer val;
                        try {
                            val = Integer.valueOf(t.getText().trim());

                            if (val < 1 || val > 9) validInput = false;
                        } catch (NumberFormatException ex) {
                            if (!t.getText().trim().equals("")) {
                                validInput = false;
                            }
                        }
                        if (!validInput) {
                            t.setStyle("-fx-background-color: red");
                            t.setText("");
                        } else {
                            if (t.getText().trim().equals("")) {
                                t.setStyle("-fx-background-color: white");
                            }
                            else if (leadsToSolution()) {
                                t.setStyle("-fx-background-color: lightgreen");
                                //check if board completed
                                if (checkForCompleteBoard()) finalEffect();
                            } else {
                                t.setStyle("-fx-background-color: red");
                            }
                        }
                    }
                });

                t.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        //it has i and j coordinates
                        //draw the 3x3 square it belongs to

                        if (t.isEditable()) {
                            drawRelatedField(t);
                        }
                    }
                });

                grid.add(t, j, i);
            }
        }



        //Reset Button Setup
        setUpResetButton(grid);

        //Solve Button Setup
        setupSolveButton(grid);



        generateBoard();

    }




    private void drawRelatedField(TextField t) {
        int id = Integer.parseInt(t.getId().toString());
        int x = id / 10;
        int y = id % 10;
        int xStart = x / 3 * 3;
        int yStart = y / 3 * 3;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == x || y == j || (i >= xStart && i <= xStart+2 && j >= yStart && j <= yStart+2)) {
                    textFields[i][j].setStyle("-fx-background-color: #E8E8E8");
                } else {
                    textFields[i][j].setStyle("-fx-background-color: white");
                }
            }
        }
    }


    private boolean checkForCompleteBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (textFields[i][j].getText().trim().equals("")) return false;
            }
        }
        return true;
    }

    private void finalEffect()  {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    textFields[i][j].setStyle("-fx-background-color: lightgreen");
                }
            }

    }


    private void setUpResetButton(GridPane grid) {
        resetButton = new Button("Reset");
        HBox hBox3 = new HBox(5);
        hBox3.getChildren().add(resetButton);
        hBox3.setAlignment(Pos.CENTER);
        grid.add(hBox3, 11, 3, 3, 1);

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                initializeTextFields();
            }
        });
    }



    private void setupSolveButton(GridPane grid) {
        //Solve Button Setup
        solveButton = new Button("Solve");
        HBox hBox2 = new HBox(5);
        hBox2.getChildren().add(solveButton);
        hBox2.setAlignment(Pos.CENTER);
        grid.add(hBox2, 11, 5, 3, 1);

        solveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                SudokuSolver solver = new SudokuSolver();
                SudokuBoard uncompletedBoard = new SudokuBoard();

                //printInternalBoard(textFields);
                boolean validBoard = true;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        Integer val;
                        try {
                            val = Integer.valueOf(textFields[i][j].getText().trim());
                            uncompletedBoard.set(i, j, val);
                            if (val < 1 || val > 9) validBoard = false;
                        } catch (NumberFormatException ex) {
                            val = 0;
                            if (!textFields[i][j].getText().trim().equals("")) {
                                validBoard = false;
                            }
                        }
                    }
                }

                SudokuBoard solution = new SudokuBoard(uncompletedBoard);

                boolean validSolution = solver.consistent(solution) && solver.solve(solution) && validBoard;

                //Update UI
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {

                        if (textFields[i][j].getText().trim().equals("")) {
                            if (!validSolution) {
                                textFields[i][j].setStyle("-fx-background-color: red");
                            } else {
                                textFields[i][j].setStyle("-fx-background-color: lightgreen");
                                textFields[i][j].setText("" + solution.get(i, j));
                            }
                        } else {
                            textFields[i][j].setStyle("-fx-background-color: white");
                        }

                    }
                }

            }
        });
    }


    private boolean leadsToSolution() {

        SudokuSolver solver = new SudokuSolver();
        SudokuBoard board = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    Integer val = Integer.valueOf(textFields[i][j].getText().trim());
                    board.set(i, j, val);
                } catch (NumberFormatException ex){
                    //pass
                }
            }
        }
        return solver.consistent(board) && solver.solve(board);
    }


    private void generateBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                initialBoard[i][j] = 0;
            }
        }
        BoardGenerator generator = new BoardGenerator();
        generator.generateBoard(initialBoard);
        initializeTextFields();
    }

    private void initializeTextFields() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (initialBoard[i][j] != 0) {
                    textFields[i][j].setText(initialBoard[i][j].toString());
                    textFields[i][j].setEditable(false);
                } else {
                    textFields[i][j].setText("");
                }
                textFields[i][j].setStyle("-fx-background-color: white");
            }
        }
    }
}