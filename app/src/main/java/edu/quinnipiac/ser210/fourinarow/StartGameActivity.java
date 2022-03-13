package edu.quinnipiac.ser210.fourinarow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class StartGameActivity extends AppCompatActivity implements View.OnClickListener, IGame {

    private Button[][] board = new Button[6][6];
    private boolean playerTurn = true;
    private boolean computerTurn = false;
    private int status;
    private int computerNum = 0;
    private int playerNum = 1;
    private int currentState;
    private Button clearBoard;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        String name = getIntent().getStringExtra("nameKey");
        TextView welcome = (TextView) findViewById(R.id.welcomeView);
        welcome.setText("Welcome " + name + ". Press where you want to place your colors");
        result = (TextView) findViewById(R.id.resultView);
        currentState = IGame.PLAYING;


        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id",getPackageName());
                board[i][j] = findViewById(resID);
                board[i][j].setOnClickListener(this);

            }
        }


        clearBoard = findViewById(R.id.clear_button);
        clearBoard.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                clearBoard();

            }
        });

    }

    @Override
    public void onClick(View view) {
        setMove(playerNum, view);
        setMove(computerNum,getComputerMove());
        currentState = checkForWinner();
        if(currentState == TIE){
            result.setText("Ooopsss! It's a Tie!");
        }
        else if(currentState == RED_WON){
            result.setText("Congratulations! RED won.");
        }
        else if(currentState == BLUE_WON){
            result.setText("Congratulations! BLUE won.");
        }


    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void clearBoard() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j].setText("");
            }
        }


    }

    @Override
    public void setMove(int player, View view) {
        if(((Button)view).getText().toString().isEmpty()){
            if(player == playerNum){
                ((Button) view).setText("B");
            }
            else if(player == computerNum)
                ((Button)view).setText("R");

        }

    }

    @Override
    public View getComputerMove() {
        int movement;
        int row;
        int column;
        Random random = new Random();

        do {
            movement = random.nextInt(36);
            row = movement/6;
            column = movement%6;

        }while(!board[row][column].getText().toString().isEmpty());



        return board[row][column];
    }

    @Override
    public int checkForWinner() {
        int count = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length-3; j++) {
                if (board[i][j].getText().toString().equals("R") && board[i][j+1].getText().toString().equals("R") && board[i][j+2].getText().toString().equals("R") && board[i][j+3].getText().toString().equals("R")) {
                    status = IGame.RED_WON;

                }
                else if (board[i][j].getText().toString().equals("B") && board[i][j+1].getText().toString().equals("B") && board[i][j+2].getText().toString().equals("B") && board[i][j+3].getText().toString().equals("B")) {
                    status = IGame.BLUE_WON;
                }
            }
        }

        for (int i = 0; i < board.length-3; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getText().toString().equals("R") && board[i+1][j].getText().toString().equals("R") && board[i+2][j].getText().toString().equals("R") && board[i+3][j].getText().toString().equals("R")) {
                    status = IGame.RED_WON;
                }
                else if (board[i][j].getText().toString().equals("B") && board[i+1][j].getText().toString().equals("B") && board[i+2][j].getText().toString().equals("B") && board[i+3][j].getText().toString().equals("B")) {
                    status = IGame.BLUE_WON;
                }
            }
        }


        for (int i = 3; i < board.length; i++) {
            for (int j = 0; j < board[0].length-3; j++) {
                if (board[i][j].getText().toString().equals("R") && board[i-1][j+1].getText().toString().equals("R") && board[i-2][j+2].getText().toString().equals("R") && board[i-3][j+3].getText().toString().equals("R")) {
                    status = IGame.RED_WON;
                }
                else if (board[i][j].getText().toString().equals("B") && board[i-1][j+1].getText().toString().equals("B") && board[i-2][j+2].getText().toString().equals("B") && board[i-3][j+3].getText().toString().equals("B")) {
                    status = IGame.BLUE_WON;
                }
            }
        }

        for (int i = 0; i < board.length-3; i++) {
            for (int j = 0; j < board[0].length-3; j++) {
                if (board[i][j].getText().toString().equals("R")&& board[i+1][j+1].getText().toString().equals("R") && board[i+2][j+2].getText().toString().equals("R") && board[i+3][j+3].getText().toString().equals("R") ) {
                    status = IGame.RED_WON;
                }
                else if (board[i][j].getText().toString().equals("B") && board[i+1][j+1].getText().toString().equals("B") && board[i+2][j+2].getText().toString().equals("B") && board[i+3][j+3].getText().toString().equals("B")) {
                    status = IGame.BLUE_WON;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
              if(!board[i][j].getText().toString().isEmpty()){
                  count++;
            }
        }

        if(count == 35 && status != BLUE_WON && status != RED_WON)   {
            status = IGame.TIE;
        }




    }
        return status;


    }



    public void RestartGame(View view) {
        clearBoard();
        currentState = IGame.PLAYING;
        status = currentState;
        result.setText(" ");
    }
}