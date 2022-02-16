package com.example.sudoku_fx_gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

     TextField[][] textFields = new TextField[9][9];

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        GridPane grid = new GridPane();
        stage.setWidth(800);
        stage.setHeight(500);

        grid.setAlignment(Pos.CENTER);
        stage.setResizable(false);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField t = new TextField();
                t.setAlignment(Pos.CENTER);
                textFields[i][j] = t;
                t.setMinWidth(30);
                t.setPrefWidth(30);
                t.setMaxWidth(30);

                t.setMinHeight(30);
                t.setPrefHeight(30);
                t.setMaxHeight(30);

                grid.add(t, j, i);
            }
        }

        //Clear Button Setup
        setupClearButton(grid);


        //Solve Button Setup
        setupSolveButton(grid);


        Scene scene = new Scene(grid, 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void setupClearButton(GridPane grid) {
        //Clear Button Setup
        Button clearButton = new Button("Clear");
        HBox hBox = new HBox(10);
        hBox.getChildren().add(clearButton);
        hBox.setAlignment(Pos.BOTTOM_LEFT);
        grid.add(hBox, 0, 9, 3, 1);

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        textFields[i][j].setText("");
                        textFields[i][j].setStyle("-fx-background-color: white");
                    }
                }
            }
        });
    }

    private void setupSolveButton(GridPane grid) {
        //Solve Button Setup
        Button solveButton = new Button("Solve");
        HBox hBox2 = new HBox(10);
        hBox2.getChildren().add(solveButton);
        hBox2.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(hBox2, 7, 9, 3, 1);

        solveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        textFields[i][j].setText("");
                        textFields[i][j].setStyle("-fx-background-color: grey");
                    }
                }
            }
        });
    }

}