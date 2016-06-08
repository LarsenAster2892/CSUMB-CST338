package guiCardsPhase1;
import javax.swing.*;
import java.awt.*;

/*
 * Subject: CST338 
 * Team: Cubed Expresso
 *       Chris Smith
 *       Clarence Mitchell
 *       Daniel Cadwell
 * Assignment: 5, Phase 1 - GUI Cards
 * ClassName: Assig5Phase1.java
 * 
 *
 * Description
 * Load all 57 card gif images (52 regular cards, 4 Jokers, 1 back-of-card) into
 * Image Icons, then add Icons to JLabels. The JLabels will then be added to the
 * JFrame to be displayed to the user via GUI. User should see all 57 cards
 * displayed in order.
 * 
 */

//****************************************//
//                                        //
//   Base Class Assig5Phase1 Definition   //
//                                        //
//****************************************//
public class Assig5Phase1
{

   static final int NUM_CARD_IMAGES = 57;
   static final String cardValues="A23456789TJKQX";
   static final String cardSuits="CDHS";
   
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];
   
   static void loadCardIcons()
   {
      //
      //   Indices for loops
      //
      int iconIndex = 0;
      
      //
      //   Loop through each suit and each value and load icon image
      //
      //
      for (char suitChar : cardSuits.toCharArray())
      {
         for (char valueChar : cardValues.toCharArray())
         {
            icon[iconIndex++] = new ImageIcon("images/" + valueChar 
               + suitChar + ".gif");
         } // end for loop valueIndex
      } // end for loop suitIndex

      //
      //   Load last image for the card back
      //
      icon[iconIndex] = new ImageIcon("images/BK.gif" );
      
   } // end loadCardIcons()
   
   
 //*************************************************************
   //
   // main class Assig5Phase1 Definition
   //
   //
   //*************************************************************
   
 
   public static void main(String[] args)
   {
      int k;
      
      loadCardIcons();
      
      //
      //   Establish main frame in which program will run
      //
      JFrame myWindowFrame = new JFrame("The c3spresso Card Table");
      myWindowFrame.setSize(1150, 650);
      myWindowFrame.setLocationRelativeTo(null);
      myWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //
      //   Establish layout for content
      //
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);
      myWindowFrame.setLayout(layout);
      
      //
      //   Create labels
      //
      JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
      for (k = 0; k < NUM_CARD_IMAGES; k++)
      {
         labels[k] = new JLabel(icon[k]);
      }
      
      //
      //   Add labels to frame
      //
      for (k = 0; k < NUM_CARD_IMAGES; k++)
      {
         myWindowFrame.add(labels[k]);
      }
      
      //
      //   Shows everything to the user
      //
      myWindowFrame.setVisible(true);

   } // end main()
} // end base class Assig5Phase1