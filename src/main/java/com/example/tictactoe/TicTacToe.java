package com.example.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TicTacToe extends Application {
    private Button button [][] = new Button[3][3];
    private Label PlayerXScoreLabel, PlayerOScoreLabel;
    private boolean playerXTurn = true;
    private int playerXscore = 0, playerOscore = 0;
    private BorderPane createContent(){
        BorderPane root = new BorderPane();


        //title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size : 28px; -fx-font-weight : bold;");
        root.setTop(titleLabel);


        //game bord
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int i = 0; i < 3; i++) {
           for(int j=0; j < 3; j++){
               Button button1 = new Button();
               button1.setPrefSize(100,100);
               button1.setStyle("-fx-font-size : 28px; -fx-font-weight : bold;");
               button1.setOnAction(actionEvent -> buttonClicked(button1));
               button[i][j] = button1;
               gridPane.add(button1,j,i);
           }
        }
        root.setCenter(gridPane);


        //score
        HBox scoreBoard = new HBox(20);
        PlayerXScoreLabel = new Label("Player X : 0");
        PlayerXScoreLabel.setStyle("-fx-font-size : 15px; -fx-font-weight : bold;");
        PlayerOScoreLabel = new Label("Player O : 0");
        PlayerOScoreLabel.setStyle("-fx-font-size : 15px; -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(PlayerXScoreLabel,PlayerOScoreLabel);
        root.setBottom(scoreBoard);
        return root;
    }



    //button clicked function
    private void buttonClicked(Button button){
        if(button.getText().equals("")) {
            if (playerXTurn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            playerXTurn = !playerXTurn;

            checkWinner();
        }
        return;
    }

    //winner function
    private void checkWinner() {
        //row
        for (int row = 0; row < 3; row++) {
            if (button[row][0].getText().equals(button[row][1].getText())
                    && button[row][1].getText().equals(button[row][2].getText())
                    && !button[row][0].getText().isEmpty()) {

                //we will have a winner
                String winner = button[row][0].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
        //col
        for (int col = 0; col < 3; col++) {
            if (button[0][col].getText().equals(button[1][col].getText())
                    && button[1][col].getText().equals(button[2][col].getText())
                    && !button[0][col].getText().isEmpty()) {

                //we will have a winner
                String winner = button[0][col].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
        //digonal

        //1st diagonal
        if (button[0][0].getText().equals(button[1][1].getText())
                && button[1][1].getText().equals(button[2][2].getText())
                && !button[0][0].getText().isEmpty()) {

            //we will have a winner
            String winner = button[0][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;

        }
        //2nd diagona
        if (button[2][0].getText().equals(button[1][1].getText())
                && button[1][1].getText().equals(button[0][2].getText())
                && !button[2][0].getText().isEmpty()) {

            //we will have a winner
            String winner = button[2][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;

        }
        //tie

        boolean tie= true;
        for (Button row[] : button){
            for(Button button : row){
                if(button.getText().isEmpty()){
                    tie = false;
                    break;
                }
            }
        }
        if(tie){
            showTieDialog();
            resetBoard();
        }
    }
    private void showWinnerDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("The Winner is " + winner + " Great Work ! Keep It Up");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void showTieDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("sorry ! This mathch is Tie !!!");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXscore++;
            PlayerXScoreLabel.setText("Player X : "+ playerXscore);
        }else{
            playerOscore++;
            PlayerOScoreLabel.setText("Player O : "+ playerOscore);
        }
    }

        private void resetBoard(){
        for(Button row[] : button){
            for(Button button:row){
                button.setText("");
            }
        }
        }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("tic tac toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}