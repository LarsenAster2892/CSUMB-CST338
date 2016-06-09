package cardGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import cardGame.controller.cardGameAppController;
import cardGame.controller.cardGameAppController.*;
import cardGame.model.*;

//import guiCardsPhase3.CardTable;

public class cardTableFrame extends JFrame
{
   static final int MAX_CARDS_PER_HAND = 56;
   static final int MAX_PLAYERS = 2; // for now, we only allow 2 person games
   private int numCardsPerHand = 20;
   

   private int numPlayers = 2;
   private String title;
   private cardGameAppController baseController;
   
   //
   // GUI Items
   //
   private cardTablePanel pnlComputerHand, 
      pnlHumanHand, 
      pnlPlayArea, 
      pnlPlayedCards,
      pnlPlayerText, 
      pnlStatusText;
 
   //
   // Card Label Arrays
   //
   static JLabel[] computerLabels;
   static JLabel[] humanLabels;
   static JLabel[] playedCardLabels;
   static JLabel[] playLabelText;
   
   //
   //  Status Message Label
   //
   static JLabel statusText = new JLabel("");

   //
   //
   //
   static baseController.CardListener playerCardListener;
   
   //*******************************************
   //
   //  Assessors and Mutators
   //
   //*******************************************
   //
   //  -- Assessors
   //
   public int getNumCardsPerHand()
   {
      return numCardsPerHand;
   }

   public int getNumPlayers()
   {
      return numPlayers;
   }
   
   public String getStatusCommand()
   {
      return statusText.getName();
   }
   //
   //  -- Mutators
   //
   
   public boolean setComputerCard(int cardPosition, Icon cardIcon)
   {
      boolean returnValue = true;
      
      //
      // Set card in location to cardIcon
      if ((cardPosition < 1) || (cardPosition > numCardsPerHand))
      {
         returnValue = false;
      }
      else
      {
         computerLabels[cardPosition - 1] = new JLabel();
         computerLabels[cardPosition - 1].setIcon(cardIcon);
      }
      
      return returnValue;
   }
   
   
   public boolean setHumanCard(int cardPosition, Icon cardIcon)
   {
      boolean returnValue = true;
      
      //
      // Set card in location to cardIcon
      if ((cardPosition < 1) || (cardPosition > numCardsPerHand))
      {
         returnValue = false;
      }
      else
      {
         humanLabels[cardPosition - 1] = new JLabel();
         humanLabels[cardPosition - 1].setIcon(cardIcon);
         humanLabels[cardPosition - 1].setMaximumSize(new Dimension(0,0));
         
      }
      
      return returnValue;
   }

   //
   // Default constructor
   //
   public cardTableFrame(cardGameAppController baseController,
         String title, int numCardsPerHand, int numPlayers)
   {
      //
      // Execute base class constructor
      //
      super();
      
      //
      // Validate input set to default if invalid
      //
      if((numCardsPerHand < 0) || 
            (numCardsPerHand > cardTableFrame.MAX_CARDS_PER_HAND))
      {
         this.numCardsPerHand = 20;     
      }
      else
      {
         this.numPlayers = numPlayers;
      }
   
   
      if((numPlayers < 2) || (numPlayers > cardTableFrame.MAX_PLAYERS))
      {
         this.numPlayers = 2;
      }
      else
      {
         this.numPlayers = numPlayers;  
      }
      
      if(title == null)
      {
         this.title = "";
      }
      else
      {
         this.title = title;
      }
      
      //this.numCardsPerHand = numCardsPerHand;
      //this.numPlayers = numPlayers;
      //
      //  Define Label Arrays
      //
      computerLabels = new JLabel[this.numCardsPerHand];
      humanLabels = new JLabel[this.numCardsPerHand];
      playedCardLabels = new JLabel[this.numPlayers];
      playLabelText = new JLabel[this.numPlayers];
      
      //
      //      Establish main frame in which program will run
      //
      setupFrame();
      
      //
      //      Creates and formats JPanel for computer's hand
      //
      setupComputerHand();
      
   
      //
      //      Creates and formats JPanel for playing area
      //
      setupPlayingArea();
   
      //
      //      Creates and formats JPanel for human's hand
      //
      setupHumanHand();
      this.setVisible(true);
   } // end CardTable() constructor   
   
   
  
   //****************************************************************
   //
   //  Helper functions
   //
   //***************************************************************
   /**
    *
    * ---- Setup the main panel frame
    * 
    */
   
   private void setupFrame()
   {
      //this.setContentPane(pnlPlayArea);
      this.setTitle(this.title);
      this.setSize(800, 600);
      this.setMinimumSize(new Dimension(800, 600));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      BorderLayout layout = new BorderLayout();
      this.setLayout(layout);
      
   }

   
   
   /**
    *******************************************************
    *
    * ---- Setup the Computer's Hand
    *
    *******************************************************
    */

   private void setupComputerHand()
   {
      pnlComputerHand = new cardTablePanel(baseController);
      //this.setContentPane(pnlComputerHand);
      
      FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
      TitledBorder border = new TitledBorder("Computer Hand");
      
      
      //pnlComputerHand = new JPanel();
      pnlComputerHand.setLayout(flowLayout);
      pnlComputerHand.setPreferredSize(
            new Dimension((int)this.getMinimumSize().getWidth()-50, 105));
      //
      // setup scroll bar
      //
      JScrollPane scrollComputerHand = new JScrollPane(pnlComputerHand);
      scrollComputerHand.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_NEVER);
      scrollComputerHand.setBorder(border);
      this.add(scrollComputerHand, BorderLayout.NORTH);   
   }
  
   /**
    *******************************************************
    *
    * ---- Setup the Human's Hand
    *
    *******************************************************
    */
   
   private void setupHumanHand()
   {
      TitledBorder border = new TitledBorder("Human Hand");
      //BorderLayout layout = new BorderLayout();
      FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
      //this.setContentPane(ctpnlComputerHand);
      
      
      //FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
      pnlHumanHand = new cardTablePanel(baseController);
      pnlHumanHand.setLayout(flowLayout);
      
      pnlHumanHand.setPreferredSize(
            new Dimension((int)this.getMinimumSize().getWidth()-50, 115));
      
      JScrollPane scrollHumanHand = new JScrollPane(pnlHumanHand);
      scrollHumanHand.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_NEVER);
      scrollHumanHand.setBorder(border);
      this.add(scrollHumanHand, BorderLayout.SOUTH);

   }
   
   /**
    *******************************************************
    *
    * ---- Setup the Playing Area
    *
    *******************************************************
    */
   
   private void setupPlayingArea()
   {
      //FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
      TitledBorder border = new TitledBorder("Playing Area");
      BorderLayout layout = new BorderLayout();
      
      //
      //      Creates and formats JPanel for playing area
      //

      //
      // Set Play are border and Layout
      //
      pnlPlayArea = new cardTablePanel(baseController);
      pnlPlayArea.setBorder(border);
      pnlPlayArea.setLayout(layout);
      
      //
      //  Define card and status areas
      //
      GridLayout gridLayoutCardsArea = new GridLayout(1, 2);
      GridLayout gridLayoutStatusArea = new GridLayout(1, 1);
      
      pnlPlayedCards = new cardTablePanel(baseController);
      pnlPlayedCards.setLayout(gridLayoutCardsArea);
      pnlPlayedCards.setPreferredSize(new Dimension((int)this.getMinimumSize().
      getWidth()-50, 150));
      
      pnlPlayerText = new cardTablePanel(baseController);
      pnlPlayerText.setLayout(gridLayoutCardsArea);
      pnlPlayerText.setPreferredSize(new Dimension(100, 30));
      
      pnlStatusText = new cardTablePanel(baseController);
      pnlStatusText.setLayout(gridLayoutStatusArea);
      pnlStatusText.setPreferredSize(new Dimension(100, 30));
      
      //
      //   Add card and status areas to play area
      //
      pnlPlayArea.add(pnlPlayedCards, BorderLayout.NORTH);
      pnlPlayArea.add(pnlPlayerText, BorderLayout.CENTER);
      pnlPlayArea.add(pnlStatusText, BorderLayout.SOUTH);
      this.add(pnlPlayArea, BorderLayout.CENTER);
      
   }
   
   
   //***************************************************
   //*
   //* Class Methods
   //*
   //***************************************************
   
   public void initializeGameBoard()
   {
      resetCardTable();
      
   }

   public void resetCardTable()
   {
      pnlComputerHand.removeAll();
      pnlHumanHand.removeAll();
      pnlPlayedCards.removeAll();
      pnlPlayerText.removeAll();
      pnlStatusText.removeAll();
   }

   
   public void addCardListener(MouseListener listenForMouse)
   {
      
   }
   
   public void selectCard(JLabel source)
   {
      //JLabel source = (JLabel)e.getSource();
      LineBorder border = new LineBorder(new Color(235, 212, 040), 3);
      source.setBorder(border);

   }
   
   public void deselectCard(JLabel source)
   {

      source.setBorder(null);

   }
   
   public int locatePlayerCard(JLabel source)
   {
      int returnLocation = -1;
      
      for (int i = 0; i < humanLabels.length; i++)
         if (humanLabels[i] == source)
         {
            returnLocation = i;
            break;
         }
      
      return returnLocation;
   }
/*   public static void main(String[] args)
   {
      // TODO Auto-generated method stub

   }
*/
   
   
} // end class CardTable
