package CST338FinalProject;
/*
 * Subject: CST338 
 * Student: Clarence Mitchell
 * Assignment: Final Project 
 * ClassName: TicTacToe.java
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


//*************************************************************
//
//  main class TicTacToe Definition
//
//
//*************************************************************

public class TicTacToe {
   
   public static void main(String[] args)
   {
      //
      // --- Create the Model, View and Controller
      //
       Controller controller = new Controller();
       View view = new View(controller);
       Model model = new Model(view);
       controller.setup(model);
       //
       // Show view
       //
       view.setVisible(true);
   }
}
