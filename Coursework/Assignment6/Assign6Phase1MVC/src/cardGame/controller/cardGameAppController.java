package cardGame.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import cardGame.view.cardTableFrame;
//import guiCardsPhase3.Assig5Phase3;
import cardGame.model.*;


public class cardGameAppController
{
   //
   //  Define constants
   //
   static final int NUM_CARDS_PER_HAND = 7;
   static final int NUM_PLAYERS = 2;
   static final int numPacksPerDeck = 1;
   static final int numJokersPerPack = 0;
   static final String GAME_TITLE = "High Card Game";

   static public cardTableFrame cardTable;
   static Card[] player1Winnings = new Card[NUM_CARDS_PER_HAND * 2];
   static Card[] player2Winnings = new Card[NUM_CARDS_PER_HAND * 2];

   private CardListener cardTableListener;
   
   public void start()
   {
      cardTable = new cardTableFrame(this, GAME_TITLE, NUM_CARDS_PER_HAND, 
            NUM_PLAYERS);
      cardTableListener = new CardListener();
   }
   

    //***********************************//
    //                                    //
    //    CardListener Class Definition   //
    //                                    //
    //***********************************//
    class CardListener implements MouseListener
    {
      // private 
      // CardListener(cardTableFrame cardTable)
      // {
      //    
      // }
       public void mouseEntered(MouseEvent e)
       {
          cardGameAppController.cardTable.selectCard((JLabel)e.getSource());
       } // end mouseEntered()
       
       public void mouseExited(MouseEvent e)
       {
          cardGameAppController.cardTable.deselectCard((JLabel)e.getSource());
       } // end mouseExited()
       
       public void mouseClicked(MouseEvent e)
       {
          int cardPosition = 0;
          JLabel source = (JLabel)e.getSource();
          //
          // check if click status label and process
          //
          if (source.getName() == cardTable.getStatusCommand())
          {
             
          }
          else
          {
             cardPosition = cardTable.locatePlayerCard((JLabel)e.getSource());
             if (cardPosition >= 0)
             {
                playCard(cardPosition);
             }
             else
             {
                Assig5Phase3.initGame();
             }
             
          }

          
          for (int i = 0; i < cardTable.humanLabels.length; i++)
             if (Assig5Phase3.humanLabels[i] == source)
             {
                Assig5Phase3.playCard(i);
                break;
             }
          
          if (Assig5Phase3.statusText == source)
          {
             Assig5Phase3.initGame();
          }
       } // end mouseClicked()
       
       public void mouseReleased(MouseEvent e)
       {
   
       } // end mouseReleased()
       
       public void mousePressed(MouseEvent e)
       {
   
       } // end mousePressed()
    } // end class CardListener

}
