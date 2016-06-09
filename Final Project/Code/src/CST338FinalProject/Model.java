package CST338FinalProject;
/*
 * Subject: CST338 
 * Student: Clarence Mitchell
 * Assignment: Final Project 
 * ClassName: Model.java
 * 
 *
 * Description
 * This final project is a Tic-Tac-Toe game using the Model-View-Controller
 * Design Pattern.  Based on the following YouTube Video..
 * 
 * https://youtu.be/dTVVa2gfht8?list=PLhIoD5GG1FOtFptRxTwqWAGDCF8HDiPTk
 * 
 * It utilizes some of the GUI techniques from the class
 * such as JFrames, JPanels, Labels, Icons, etc.
 * 
 */

import java.awt.Point;

public class Model implements ModelInterface 
{
   char[][] board = new char[3][3];
   View view;
   char player = 'X';

   public Model(View view){
      this.view = view;
      reset();
   }

   // runs through all the chars on the board and sets them to 0
   private void reset() 
   {
      for(int i = 0; i < 3; i++){
         for(int j = 0; j < 3; j++){
            board[i][j] = 0;
         }
      }
   }

   @Override
   public void movePlayer(Point location) 
   {
      // if the board is empty at the selected location, a move is made
      if(board[location.x][location.y] == 0){
         makeMove(player, location);
      }
   }

   private void makeMove(char player, Point position)
   {
      board[position.x][position.y] = player;

      // tells the view that a move has been made
      view.playerMove(position.y+3*position.x, player);
      checkWin();
      checkTie();
      changePlayer();
   }

   //the checkWin method goes through all the possible ways for a win and calls the 
   //winnerFound method, if one of them is forfilled
   private void checkWin()
   {
      if(board[0][0] != 0 && board[0][0] == board[1][1] && 
            board[1][1] == board[2][2])
      {
         winnerFound(board[0][0]);
      }
      else if(board[0][2] != 0 && board[0][2] == board[1][1] && 
            board[1][1] == board[2][0])
      {
         winnerFound(board[0][2]);
      }
      else{
         for(int i = 0; i <3; i++)
         {
            if(board[i][0] != 0 && board[i][0] == board[i][1] 
                  && board[i][1] == board[i][2])
            {
               winnerFound(board[i][0]);
               break;
            }
            if(board[0][i] != 0 && board[0][i] == board[1][i] && 
                  board[1][i] == board[2][i])
            {
               winnerFound(board[0][i]);
               break;
            }
         }
      }
   }

   //if a winner is found, the model alerts the view and resets the model
   private void winnerFound(char winner)
   {
      view.gameWon(player);
      reset();
   }

   // the checkTie method checks if all the locations on the board are filled
   private void checkTie()
   {

      for(int i = 0; i < 3; i++)
      {
         for(int j = 0; j < 3; j++)
         {
            if(board[i][j] == 0)
               return;
         }
      }
      view.gameTie();
      reset();
   }

   // changes the current player
   private void changePlayer()
   {
      if(this.player == 'O')
      {
         this.player = 'X';
      }
      else
         this.player = 'O';          
   }
}

