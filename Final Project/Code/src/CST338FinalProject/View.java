package CST338FinalProject;
/*
 * Subject: CST338 
 * Student: Clarence Mitchell
 * Assignment: Final Project 
 * ClassName: View.java
 * 
 *
 * Description
 * Class View is the View part of the Model-View-Controller
 * It is used to display data to the users
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class View extends JFrame implements MouseListener, ViewInterface 
{
   private static final Color COLOR_BLUE = new Color(0, 0, 255);
   //
   //  ----- Public Attributes  ---
   //

   public static final int NUM_ROWS = 3;
   public static final int NUM_COLS = 3;

   //
   //  ----- Private Attributes  ---
   //

   private GUIMarker gameMarkers;

   private Controller controller;
   private JPanel panel;
   //
   // Dynamic array. This was covered in chapter 14 of book, it is sweet.
   //
   ArrayList<JLabel> board = new ArrayList<JLabel>();

   private JLabel [][] board2 = new JLabel[NUM_ROWS][NUM_COLS];

   private JPanel messagePanel;
   private JLabel topText;


   //*************************************************
   //*
   //* Constructors
   //*
   //*************************************************
   /* 
    * View(Controller controller)
    * In: Controller
    * Out: Nothing
    * Description: This is the only constructor. 
    *              It takes a link to the main driver for the controller
    *              This is so the controller can decide what to do about
    *              user clicks
    *              
    */

   public View(Controller controller)
   {
      super();
      this.controller = controller;
      this.gameMarkers = new GUIMarker();
      setupGUI();      
   } // end View constructor


   //*****************************************************
   // 
   //  Helper methods
   //
   //*****************************************************
   /* 
    * void setupGUI()
    * In: Nothing
    * Out: none
    * Description: setups the board for playing
    * 
    */

   private void setupGUI()
   {
      createFrame();
      createMessagePanel();
      createPanel();
      createBoard();

      //Adds the panel to the frame
      this.getContentPane().add(panel);
      panel.addMouseListener(this);
   } // end setupGUI
   /* 
    * void createFrame()
    * In: Nothing
    * Out: none
    * Description: creates the fraem
    * 
    */

   private void createFrame()
   {
      setSize(400,400);
      setTitle("Tic-Tac-Toe");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // centers the game window and adds a borderlayout to the frame
      setLocationRelativeTo(null);
      setLayout(new BorderLayout());

   } // end createFrame


   /* 
    * void createMessagePanel()
    * In: Nothing
    * Out: none
    * Description: sets up the top message panel
    * 
    */
   private void createMessagePanel()
   {
      messagePanel = new JPanel();
      topText = new JLabel("X start");
      messagePanel.add(topText);

      // sets the location of the messagepanel to be NORTH
      this.getContentPane().add(messagePanel, BorderLayout.NORTH);
   }// createMessagePanel

   /* 
    * void void createPanel()
    * In: Nothing
    * Out: none
    * Description: sets up the  panel
    * 
    */
   private void createPanel()
   {
      panel = new JPanel();

      // sets the layout of the panel to be a grid (3x3 board)
      panel.setLayout(new GridLayout(3, 3, -1, -1));
   } // end createPanel


   /* 
    * void void createBoard()
    * In: Nothing
    * Out: none
    * Description: draws the board
    * 
    */
   private void createBoard()
   {
      // adds 9 JLabels to the panel, to simulate the 3x3 board
      for(int loopCell = 0; loopCell < 9; loopCell++)
      {
         JLabel symbol = new JLabel();
         symbol.setIcon(gameMarkers.getBlankIcon());
         symbol.setSize(new Dimension(400/3,400/3));
         symbol.setHorizontalAlignment(JLabel.CENTER);
         symbol.setBorder(
               BorderFactory.createMatteBorder(2, 2, 2, 2,Color.BLUE));

         // adds the JLabels to both the board arraylist and the gridpanel
         board.add(symbol);
         panel.add(symbol);
      } // end loopCell
   }


   /* 
    * void playerMove(int position, char player)
    * In: position and player moving
    * Out: none
    * Description: marks players move
    * 
    */
   @Override
   public void playerMove(int position, char player) 
   {
      drawSymbol(position, player);
      setCurrentPlayer(player);
   } // end playerMove

   /* 
    * void drawSymbol(int index, char symbol)
    * In: board location and symbol to place
    * Out: none
    * Description:updates the selected JLabel with the symbol of the player
    * 
    */
   private void drawSymbol(int index, char symbol)
   {
      //board.get(index).setText("" +symbol);
      JLabel temp = (JLabel) board.get(index);
      temp.setIcon(gameMarkers.getIcon(symbol));
      //temp.setText("" +symbol);
      // board2.
   } // end drawSymol

   /* 
    * void setCurrentPlayer(char player)
    * In: determines which player to play
    * Out: none
    * Description: updates the top text panel
    * 
    */
   private void setCurrentPlayer(char player)
   {
      if(player == 'O'){
         topText.setText("X's turn");
      }
      else{
         topText.setText("O's turn");
      }
   }

   /* 
    * void gameTie() 
    * In: none
    * Out: none
    * Description: displays message for tie game
    * 
    */

   public void gameTie() 
   {
      giveMessage("Game Tie", "nobody won");
      reset();
   }

   /* 
    * void gameWon(char winner) 
    * In: winnder
    * Out: none
    * Description: displays message for winner
    * 
    */
   public void gameWon(char winner) 
   {
      giveMessage("Game done", winner + " won!");
      reset();

   }


   /* 
    * void gameWon(char winner) 
    * In: winnder
    * Out: none
    * Description: the giveMessage method prints a string to the top text panel
    * 
    */
   private void giveMessage(String header, String message)
   {
      JOptionPane.showMessageDialog(this,
            message,
            header,
            JOptionPane.PLAIN_MESSAGE);
   } // end giveMessage

   /* 
    * void reset() 
    * In: none
    * Out: none
    * Description: reset the board by setting the text on all the board labels
    * 
    */
   private void reset() 
   {
      for(JLabel label : board)
      {
         label.setIcon(gameMarkers.getBlankIcon());
      }
      panel.repaint();
   }

   //
   // Mouse listeners
   //
   @Override
   public void mouseClicked(MouseEvent e) 
   {

   } // end mouseClicked

   @Override
   public void mouseEntered(MouseEvent e)
   {
   } // end mouseEntered()
   
   @Override
   public void mouseExited(MouseEvent e)
   {
   } // end mouseExited()

   @Override
   public void mousePressed(MouseEvent e) 
   {

   } // end mousePressed

   @Override
   public void mouseReleased(MouseEvent e) 
   {
      //the loop runs through all the JLabels of the board
      for(int i = 0; i < board.size(); i++)
      {   

         //if one of the JLabels bounds contains the point where the mouse click 
         //was released, passes the information to the controller with the  
         //playermove method
         if(board.get(i).getBounds().contains(e.getPoint()))
         {
            controller.playermove(new Point(i/3, i%3));
         }
      }
   } // end mouseReleased

} // end Class View
