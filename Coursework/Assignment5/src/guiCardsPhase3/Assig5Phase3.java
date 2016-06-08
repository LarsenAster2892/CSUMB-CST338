package guiCardsPhase3;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import guiCardsPhase3.Deck;
import guiCardsPhase3.Card;
import guiCardsPhase3.Card.Suit;

import java.awt.event.*;
import java.io.File;
import java.lang.Comparable;
import javax.swing.JOptionPane;

/*
 * Subject: CST338 
 * Team: Cubed Expresso
 *       Chris Smith
 *       Clarence Mitchell
 *       Daniel Cadwell
 * Assignment: 5, Phase 3 - GUI Cards
 * ClassName: Assig5Phase3.java
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
//   Base Class Assig5Phase3 Definition   //
//                                        //
//****************************************//

public class Assig5Phase3
{
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;
   static int numPacksPerDeck = 1;
   static int numJokersPerPack = 0;
   static int numUnusedCardsPerPack = 0;
   static Card[] unusedCardsPerPack = null;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
   static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
   static Card[] player1Winnings = new Card[NUM_CARDS_PER_HAND*2];
   static Card[] player2Winnings = new Card[NUM_CARDS_PER_HAND*2];
   static int[] cardPointValues = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
   static JLabel statusText = new JLabel("");
   static CardListener listener = new CardListener();
   static CardGameFramework highCardGame;
   static CardTable table;
   static final String PLAYER1_TEXT = "Computer", PLAYER2_TEXT = "You";
   //private static int computerCard = -1;

   public static void main(String[] args)
   {
      table = new CardTable("High Card Game", NUM_CARDS_PER_HAND, NUM_PLAYERS);

      initGame();
      table.setVisible(true);
   } // end main()
   
   public static void initGame()
   {
      player1Winnings = new Card[NUM_CARDS_PER_HAND * 2];
      player2Winnings = new Card[NUM_CARDS_PER_HAND * 2];
      
      //
      //   Reset the entire table to default settings
      //
      table.pnlComputerHand.removeAll();
      table.pnlPlayedCards.removeAll();
      table.pnlPlayerText.removeAll();
      table.pnlStatusText.removeAll();
      table.pnlHumanHand.removeAll();
      
      //
      //   Create the framework for the High Card game
      //
      highCardGame = new CardGameFramework(numPacksPerDeck, numJokersPerPack,
         numUnusedCardsPerPack, unusedCardsPerPack, NUM_PLAYERS,
         NUM_CARDS_PER_HAND);
      
      highCardGame.deal();
      highCardGame.getHand(1).sort();
      
      for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      {
         //
         //   Get and set Icons for computer hand and add to JLabel
         //
         computerLabels[i] = new JLabel();
         computerLabels[i].setIcon(GUICard.getBackCardIcon());
         
         //
         //   Get and set Icons for player hand and add to JLabel
         //
         humanLabels[i] = new JLabel();
         humanLabels[i].setIcon(
               GUICard.getIcon(highCardGame.getHand(1).inspectCard(i)));
         humanLabels[i].setMaximumSize(new Dimension(0, 0));
         humanLabels[i].addMouseListener(listener);
         
         //
         //   Add the computer and player hands (JLabels) to the table
         //
         table.pnlComputerHand.add(computerLabels[i]);
         table.pnlHumanHand.add(humanLabels[i]);
      } // end for loop
      
      //
      //   Create, add and format text to the playing area of the table
      //
      playLabelText[0] = new JLabel(PLAYER1_TEXT + ": 0");
      playLabelText[1] = new JLabel(PLAYER2_TEXT + ": 0");
      playLabelText[0].setHorizontalAlignment(JLabel.CENTER);
      playLabelText[0].setVerticalAlignment(JLabel.TOP);
      playLabelText[1].setHorizontalAlignment(JLabel.CENTER);
      playLabelText[1].setVerticalAlignment(JLabel.TOP);
      table.pnlPlayerText.add(playLabelText[0]);
      table.pnlPlayerText.add(playLabelText[1]);
      statusText.setHorizontalAlignment(JLabel.CENTER);
      table.pnlStatusText.add(statusText);
      statusText.removeMouseListener(listener);
      statusText.setText("");
      statusText.setBorder(null);
      
      table.pnlHumanHand.revalidate();
      table.pnlHumanHand.repaint();
      table.pnlComputerHand.revalidate();
      table.pnlComputerHand.repaint();
      table.pnlPlayArea.revalidate();
      table.pnlPlayArea.repaint();
   } // end initGame()
   
   static int getCardPointValue(Card card)
   {
      String values = new String(Card.validCardValues);
      
      if(card.errorFlag)
         return -1;
      
      return cardPointValues[values.indexOf(card.getValue())];
   } // end getCardPointValue()
   
   static int getComputerCard(Card playerCard)
   {
      Card possibleCard = null;
      int cardPosition = 0;
      boolean hasHigherCard = false;
      
      for (int i = 0; i < highCardGame.getHand(0).getNumCards(); i++)
      {
         if (playerCard.compareTo(highCardGame.getHand(0).inspectCard(i)) < 0)
         {
            //The computer has a higher card.
            if (possibleCard != null)
            {
               //If this card is lower than the possible card, but can still
               //beat the player, then replace possible card.
               if (possibleCard.compareTo(highCardGame.getHand(0).
                  inspectCard(i)) > 0)
               {
                  possibleCard = new Card(highCardGame.getHand(0).
                     inspectCard(i));
                  cardPosition = i;
               } // end if possibleCard.compareTo
            }
            else
            {
               possibleCard = new Card(highCardGame.getHand(0).inspectCard(i));
               hasHigherCard = true;
               cardPosition = i;
            } // end if-else possibleCard
         } // end if playerCard
      } // end for loop
      
      if (!hasHigherCard)
      {
         //If the computer does not have a card that can beat the player, then
         //feed the lowest card to the player.
         for (int i = 0; i < highCardGame.getHand(0).getNumCards(); i++)
         {
            if(playerCard.compareTo(highCardGame.getHand(0).
               inspectCard(i)) >= 0)
            {
               if(possibleCard != null)
               {
                  if(possibleCard.compareTo(highCardGame.getHand(0).
                     inspectCard(i)) > 0)
                  {
                     possibleCard = new Card(highCardGame.getHand(0).
                        inspectCard(i));
                     cardPosition = i;
                  } // end if possibleCard.compareTo
               }
               else
               {
                  possibleCard = highCardGame.getHand(0).inspectCard(i);
                  cardPosition = i;
               } // end if-else possibleCard
            }
         } // end for loop
      } // end if !hasHigherCard

      return cardPosition;
   } // end getComputerCard()
   
   static int calculateScore(Card[] winnings)
   {
      int score = 0;
      
      for (Card card : winnings)
         if(card != null)
            score++;
         else
            break;
      
      return score;
   } // end calculateScore()
   
   static void removeLabel(JLabel[] labels, JLabel label)
   {
      boolean moveBack = false;
      
      for (int i = 0; i < labels.length; i++)
      {
         if (labels[i] == label)
         {
            labels[i] = null;
            moveBack = true;
         }
         else if (moveBack)
         {
            labels[i - 1] = labels[i];
            labels[i] = null;
         } // end if-else
      } // end for loop
   } // end removeLabel()
   
   static void addToWinnings(Card[] winnings, Card... cards)
   {
      for (int i = 0; i < cards.length; i++)
         for(int j = 0; j < winnings.length; j++)
            if(winnings[j] == null)
            {
               winnings[j] = new Card(cards[i]);
               break;
            } // end if
   } // end addToWinnings()
   
   static void playCard(int handPosition)
   {
      //Clear out the previous play
      table.pnlPlayedCards.removeAll();

      Card playerCard = highCardGame.getHand(1).inspectCard(handPosition);
      int computerHandPosition = getComputerCard(playerCard);
      Card computerCard = highCardGame.getHand(0).
         inspectCard(computerHandPosition);
      
      JLabel computerCardLabel = new JLabel();
      computerCardLabel.setIcon(GUICard.getIcon(computerCard));
      computerCardLabel.setHorizontalAlignment(JLabel.CENTER);
      computerCardLabel.setVerticalAlignment(JLabel.BOTTOM);

      table.pnlHumanHand.remove(humanLabels[handPosition]);
      table.pnlComputerHand.remove(computerLabels[computerHandPosition]);
      highCardGame.getHand(0).playCard(computerHandPosition);
      highCardGame.getHand(1).playCard(handPosition);

      computerLabels[0].setHorizontalAlignment(JLabel.CENTER);
      humanLabels[handPosition].setHorizontalAlignment(JLabel.CENTER);
      humanLabels[handPosition].setVerticalAlignment((JLabel.BOTTOM));

      table.pnlPlayedCards.add(computerCardLabel);
      table.pnlPlayedCards.add(humanLabels[handPosition]);
      humanLabels[handPosition].removeMouseListener(listener);
      humanLabels[handPosition].setBorder(null);
      
      removeLabel(humanLabels, humanLabels[handPosition]);
      removeLabel(computerLabels, computerLabels[computerHandPosition]);

      if (playerCard.compareTo(computerCard) < 0)
      {
         addToWinnings(player1Winnings, computerCard, playerCard);
         statusText.setText("Computer wins...");
      }
      else if (playerCard.compareTo(computerCard) > 0)
      {
         addToWinnings(player2Winnings, computerCard, playerCard);
         statusText.setText("You win!");
      }
      else
         statusText.setText("Draw! The cards have been discarded.");
      
      playLabelText[0].setText(PLAYER1_TEXT + ": "
         + calculateScore(player1Winnings));
      playLabelText[1].setText(PLAYER2_TEXT + ":"
         + calculateScore(player2Winnings));

      if (highCardGame.getHand(0).getNumCards() == 0)
      {
         //The game is over.
         if (calculateScore(player1Winnings) > calculateScore(player2Winnings))
         {
            statusText.setText("Computer wins the game...");
         }
         else if (calculateScore(player1Winnings) <
            calculateScore(player2Winnings))
         {
            statusText.setText("You win the game!");
         }
         else
         {
            statusText.setText("The game ended in a draw.");
         }
         
         statusText.setText(statusText.getText()
            + " Click here to play again!");
         statusText.addMouseListener(listener);
      } // end if highCardGame

      table.pnlHumanHand.revalidate();
      table.pnlHumanHand.repaint();
      table.pnlComputerHand.revalidate();
      table.pnlComputerHand.repaint();
      table.pnlPlayArea.revalidate();
      table.pnlPlayArea.repaint();
   } // end playCard()
} // end base class Assig5Phase3


//***********************************//
//                                   //
//   CardListener Class Definition   //
//                                   //
//***********************************//
class CardListener implements MouseListener
{
   public void mouseEntered(MouseEvent e)
   {
      JLabel source = (JLabel)e.getSource();
      LineBorder border = new LineBorder(new Color(0, 0, 255), 2);
      source.setBorder(border);
   } // end mouseEntered()
   
   public void mouseExited(MouseEvent e)
   {
      JLabel source = (JLabel)e.getSource();
      source.setBorder(null);
   } // end mouseExited()
   
   public void mouseClicked(MouseEvent e)
   {
      JLabel source = (JLabel)e.getSource();
      
      for (int i = 0; i < Assig5Phase3.humanLabels.length; i++)
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


//****************************************//
//                                        //
//   CardGameFramework Class Definition   //
//                                        //
//****************************************//
class CardGameFramework
{
   private static final int MAX_PLAYERS = 50;

   private int numPlayers;
   private int numPacks;              // # standard 52-card packs per deck
                                      // ignoring jokers or unused cards
   private int numJokersPerPack;      // if 2 per pack & 3 packs per deck, get 6
   private int numUnusedCardsPerPack; // # cards removed from each pack
   private int numCardsPerHand;       // # cards to deal each player
   private Deck deck;                 // holds the initial full deck and gets
                                      // smaller (usually) during play
   private Hand[] hand;               // one Hand for each player
   private Card[] unusedCardsPerPack; // an array holding the cards not used
                                      // in the game.  e.g. pinnacle does not
                                      // use cards 2-8 of any suit

   public CardGameFramework( int numPacks, int numJokersPerPack,
      int numUnusedCardsPerPack,  Card[] unusedCardsPerPack,
      int numPlayers, int numCardsPerHand)
   {
      int k;

      // filter bad values
      if (numPacks < 1 || numPacks > 6)
         numPacks = 1;
      if (numJokersPerPack < 0 || numJokersPerPack > 4)
         numJokersPerPack = 0;
      if (numUnusedCardsPerPack < 0 || numUnusedCardsPerPack > 50) //  > 1 card
         numUnusedCardsPerPack = 0;
      if (numPlayers < 1 || numPlayers > MAX_PLAYERS)
         numPlayers = 4;
      // one of many ways to assure at least one full deal to all players
      if (numCardsPerHand < 1 ||
         numCardsPerHand >  numPacks * (52 - numUnusedCardsPerPack)
         / numPlayers )
         numCardsPerHand = numPacks * (52 - numUnusedCardsPerPack) / numPlayers;

      // allocate
      this.unusedCardsPerPack = new Card[numUnusedCardsPerPack];
      this.hand = new Hand[numPlayers];
      for (k = 0; k < numPlayers; k++)
         this.hand[k] = new Hand();
      deck = new Deck(numPacks);

      // assign to members
      this.numPacks = numPacks;
      this.numJokersPerPack = numJokersPerPack;
      this.numUnusedCardsPerPack = numUnusedCardsPerPack;
      this.numPlayers = numPlayers;
      this.numCardsPerHand = numCardsPerHand;
      for (k = 0; k < numUnusedCardsPerPack; k++)
         this.unusedCardsPerPack[k] = unusedCardsPerPack[k];

      // prepare deck and shuffle
      newGame();
   } // end overloaded CardGameFramework()

   // constructor default for game like bridge
   public CardGameFramework()
   {
      this(1, 0, 0, null, 4, 13);
   } // end CardGameFramework()

   public Hand getHand(int k)
   {
      // hands start from 0 like arrays

      // on error return automatic empty hand
      if (k < 0 || k >= numPlayers)
         return new Hand();

      return hand[k];
   } // end getHand()

   public Card getCardFromDeck()
   {
      return deck.dealCard();
   } // end getCardFromDeck()

   public int getNumCardsRemainingInDeck()
   {
      return deck.getNumCards();
   } // end getNumCardsRemainingInDeck()

   public void newGame()
   {
      int k, j;

      // clear the hands
      for (k = 0; k < numPlayers; k++)
         hand[k].resetHand();

      // restock the deck
      deck.init(numPacks);

      // remove unused cards
      for (k = 0; k < numUnusedCardsPerPack; k++)
         deck.removeCard(unusedCardsPerPack[k]);

      // add jokers
      for (k = 0; k < numPacks; k++)
         for ( j = 0; j < numJokersPerPack; j++)
            deck.addCard(new Card('X', Card.Suit.values()[j]));

      // shuffle the cards
      deck.shuffle();
   } // end newGame()

   public boolean deal()
   {
      // returns false if not enough cards, but deals what it can
      int k, j;
      boolean enoughCards;

      // clear all hands
      for (j = 0; j < numPlayers; j++)
         hand[j].resetHand();

      enoughCards = true;
      
      for (k = 0; k < numCardsPerHand && enoughCards ; k++)
      {
         for (j = 0; j < numPlayers; j++)
            if (deck.getNumCards() > 0)
               hand[j].takeCard(deck.dealCard());
            else
            {
               enoughCards = false;
               break;
            } // end if-else
      } // end for loop 

      return enoughCards;
   } // end deal()

   void sortHands()
   {
      int k;

      for (k = 0; k < numPlayers; k++)
         hand[k].sort();
   } // end sortHands()
} // end class CardGameFramework


//******************************//
//                              //
//   GUICard Class Definition   //
//                              //
//******************************//
class GUICard
{
   private static Icon[][] iconCards = new ImageIcon[14][4];
   private static Icon iconBack;
   static boolean iconsLoaded = false;
   static final char[] VALID_SUITS = {'C', 'D', 'H', 'S'};
   private static String iconFolderPath = "./images";
   
   public static Icon getIcon(Card card)
   {
      if(!GUICard.iconsLoaded)
         GUICard.loadCardIcons();
      
      return iconCards[valueAsInt(card)][suitAsInt(card)];
   } // end getIcon()
   
   private static void loadCardIcons()
   {
      if(!(new File(GUICard.iconFolderPath).exists()))
      {
         JOptionPane.showMessageDialog(null, "By deafult ../images/ is used to"
            + " store card icon images, but ../images/ does not exist. Press OK"
            + " to select the folder where card icon images are stored. Press"
            + " cancel in the forthcoming dialog window to exit this program.");
         JFileChooser chooser = new JFileChooser(".");
         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         chooser.setMultiSelectionEnabled(false);
         chooser.showDialog(null, "Select");
         File selectedFile = chooser.getSelectedFile();
         if(selectedFile == null)
            System.exit(0);
         GUICard.iconFolderPath = selectedFile.getPath();
         System.out.println(iconFolderPath);
      } // end if
      for (int i = 0; i < Card.validCardValues.length; i++)
         for (int j = 0; j < VALID_SUITS.length; j++)
         {
            if (!new File(iconFolderPath + "/" + Card.validCardValues[i]
               + VALID_SUITS[j] + ".gif").exists())
            {
               JOptionPane.showMessageDialog(null, Card.validCardValues[i]
                  + VALID_SUITS[j] + ".gif could not be found in the icon"
                  + " folder. Program execution will now stop.");
               System.exit(0);
            } // end if
            
            iconCards[i][j] = new ImageIcon(iconFolderPath + "/"
               + Card.validCardValues[i] + VALID_SUITS[j] + ".gif");
         } // end for loop j
      
      iconBack = new ImageIcon(iconFolderPath + "/BK.gif");
      GUICard.iconsLoaded = true;
   } // end loadCardIcons()
   
   private static int valueAsInt(Card card)
   {
      String values = new String(Card.validCardValues);
      
      return values.indexOf(card.getValue());
   } // end valueAsInt()
   
   private static int suitAsInt(Card card)
   {
      return card.getSuit().ordinal();
   } // end suitAsInt()
   
   public static Icon getBackCardIcon()
   {
      if(!GUICard.iconsLoaded)
         GUICard.loadCardIcons();
      
      return GUICard.iconBack;
   } // end getBackCardIcon()
} // end class GUICard


//********************************//
//                                //
//   CardTable Class Definition   //
//                                //
//********************************//
class CardTable extends JFrame
{
   static final int MAX_CARDS_PER_HAND = 56;
   static final int MAX_PLAYERS = 2; // for now, we only allow 2 person games
   private int numCardsPerHand;
   private int numPlayers;

   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea, pnlPlayedCards,
      pnlPlayerText, pnlStatusText;

   //
   //   Default constructor
   //
   public CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      super();

      if(numCardsPerHand < 0 || numCardsPerHand > CardTable.MAX_CARDS_PER_HAND)
         this.numCardsPerHand = 20;

      this.numCardsPerHand = numCardsPerHand;
      if(numPlayers < 2 || numPlayers > CardTable.MAX_PLAYERS)
         this.numPlayers = numPlayers;
      if(title == null)
         title = "";
      
      //
      //   Establish main frame in which program will run
      //
      this.setTitle(title);
      this.setSize(800, 600);
      this.setMinimumSize(new Dimension(800, 600));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      BorderLayout layout = new BorderLayout();
      this.setLayout(layout);

      //
      //   Creates and formats JPanel for computer's hand
      //
      FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
      TitledBorder border = new TitledBorder("Computer Hand");
      pnlComputerHand = new JPanel();
      pnlComputerHand.setLayout(flowLayout);
      pnlComputerHand.setPreferredSize(new Dimension((int)this.getMinimumSize().
         getWidth()-50, 105));
      JScrollPane scrollComputerHand = new JScrollPane(pnlComputerHand);
      scrollComputerHand.setVerticalScrollBarPolicy(JScrollPane.
         VERTICAL_SCROLLBAR_NEVER);
      scrollComputerHand.setBorder(border);
      this.add(scrollComputerHand, BorderLayout.NORTH);

      //
      //   Creates and formats JPanel for playing area
      //
      border = new TitledBorder("Playing Area");
      GridLayout gridLayoutCardsArea = new GridLayout(1, 2);
      GridLayout gridLayoutStatusArea = new GridLayout(1, 1);
      pnlPlayArea = new JPanel();
      pnlPlayArea.setBorder(border);
      
      layout = new BorderLayout();
      pnlPlayArea.setLayout(layout);
      pnlPlayedCards = new JPanel();
      pnlPlayedCards.setLayout(gridLayoutCardsArea);
      pnlPlayerText = new JPanel();
      pnlPlayerText.setLayout(gridLayoutCardsArea);
      pnlStatusText = new JPanel();
      pnlStatusText.setLayout(gridLayoutStatusArea);
      pnlPlayedCards.setPreferredSize(new Dimension((int)this.getMinimumSize().
         getWidth()-50, 150));
      pnlPlayerText.setPreferredSize(new Dimension(100, 30));
      pnlStatusText.setPreferredSize(new Dimension(100, 30));
      pnlPlayArea.add(pnlPlayedCards, BorderLayout.NORTH);
      pnlPlayArea.add(pnlPlayerText, BorderLayout.CENTER);
      pnlPlayArea.add(pnlStatusText, BorderLayout.SOUTH);
      this.add(pnlPlayArea, BorderLayout.CENTER);
      
      //
      //   Creates and formats JPanel for human's hand
      //
      border = new TitledBorder("Human Hand");
      pnlHumanHand = new JPanel();
      pnlHumanHand.setLayout(flowLayout);
      pnlHumanHand.setPreferredSize(new Dimension((int)this.getMinimumSize().
         getWidth()-50, 105));
      JScrollPane scrollHumanHand = new JScrollPane(pnlHumanHand);
      scrollHumanHand.setVerticalScrollBarPolicy(JScrollPane.
         VERTICAL_SCROLLBAR_NEVER);
      scrollHumanHand.setBorder(border);
      this.add(scrollHumanHand, BorderLayout.SOUTH);
   } // end CardTable()
} // end class CardTable


/*
 * 
 * ClassName: Card.java
 * 
 * Description
 * This class is part of group of classes that represent parts of a card game 
 * This is the card class that represents the a playing card
 * 
 * 
 */

//*************************************************************
//
//  card Class Definition
//
//
//*************************************************************
class Card implements Comparable
{
   //
   //  ----- Enumerators  ---
   //

   //The four standard suits are supported.
   public enum Suit{clubs, diamonds, hearts, spades};
    
   //
   //  ----- Private Attributes  ---
   //
   private char value;
   private Suit suit;
 
   // Note: 
   //   errorFlag is set to true if the user tries to create or set 
   //   a card's value to one that is not in the validCardValues array.
   //   This will cause the card's toString() method to indicate 
   //   that the card is invalid.
   boolean errorFlag;
    
    
    //validCardValues holds values that a card is allowed to be.
    public static char[] validCardValues = 
       {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X'};
    public static char[] validCardSuits = {'C', 'D', 'H', 'S'};
    public static char[] valueRanks = validCardValues;
    
        
    // ***************************************
    // 
    //    --- constructors
    //
    // ***************************************
    
    //
    //default constructor
    //   Card()
    //   This is a default constructor that takes no values. 
    //   It will create an Ace of Spades.
    public Card()
    {
        this.set('A', Suit.spades);
    }
    
    //
    // value, suit constructor
    //   Card(char value, Suit suit)
    //   This constructor creates a card using the passed value and suit 
    //      
    public Card(char value, Suit suit)
    {
       this.set(value, suit);
    }
 
    //
    // card constructor
    //    Card(Card card))
    //   This constructor creates a card using the passed card object 
    //      
    public Card(Card card)
    {
       this.set(card.value, card.suit);
    }
    // ***************************************
    // 
    //    standard methods
    //
    // ***************************************
    
    //
    /**************************************
     * compareTo
     *    used to compare two like objects using only value (not suit)
     *    
     * Return Value: 
     *    int -1 if passed object is greater than this object
     *         0 if passed object is equal to this object 
     *         1 if passed object is less than this object
     * 
     * PARAMETERS:
     *   object - to be compared
     *
     ****************************************/

    public int compareTo(Object testObj)
    {
       int returnValue = 1;
       
       //
       // first check if we are comparing same type of object
       //
       if(testObj.getClass() != this.getClass())
       {
          returnValue = 1;
       }
       else
       {
          //
          // cast object and compare
          Card cardObj = (Card)testObj;
          String strRanks = new String(valueRanks);
          
          if(strRanks.indexOf(cardObj.getValue()) < 0)
          {
             returnValue = 1;
          }  
          else if(strRanks.indexOf(cardObj.getValue()) < 
                strRanks.indexOf(this.getValue()))
          {
             returnValue = 1;
          } 
          else if(strRanks.indexOf(cardObj.getValue()) == 
                strRanks.indexOf(this.getValue()))
          {
             returnValue = 0;
          } 
          else if(strRanks.indexOf(cardObj.getValue()) > 
          strRanks.indexOf(this.getValue()))
          {
             returnValue = -1;  
          } // end if else if
       } // end if getClass
       
        return returnValue;
        
    } // end compareTo
    
    /**************************************
     * equals
     *    compares to passed card object to see it is equal to this  
     *    (same value and suit0
     *    
     * Return Value: 
     *    boolean - true if equal
     *            - false if not equal
     * 
     * PARAMETERS:
     *   Card  - card object to compares
     *
     ****************************************/

    public boolean equals(Card cardObj)
    {
       if((this.getValue() == cardObj.getValue()) && 
             (this.getSuit() == cardObj.getSuit()))
       {
          return true;
       } // end if

      return false;
       
    } // end equals
    
    // convert class to string for display 
    //
    /**************************************
     * toString
     *    returns the card's value to the caller in String form.
     *    
     * Return Value: 
     *    string representing class data
     * 
     * PARAMETERS:
     *   none
     *
     ****************************************/
      public String toString()
      {
         if(this.errorFlag == true)
            return "[INVALID CARD]";
        else
            return this.value + " of " + suit.toString();
         
      } // end toString

   
    // ***************************************
    // 
    //    --- Accessors and Mutators  -----
    //
    // ***************************************
    //
    //------ Accessors -------------
    //
    
    
    //  Accessor for private numCards
    //
    // getValue()
    // accessor for the card's value.
    //
    public char getValue()
    {
        return value;
    }
 
    // getSuit()
    // accessor for the card's suit.
    //
    public Suit getSuit()
    {
        return this.suit;
    }

    //
    //------ Mutators -------------
    //
    //
    // 
    // set(char, Suit)
    // This set's the card's suit and value, if they are valid. Otherwise,
    // it sets the card's errorFlag to true. 
    public boolean set(char value, Suit suit)
    {
       boolean returnValue = false;
       
       if(Card.isValid(value, suit)) 
        {
            this.errorFlag = false;
            this.value = value;
            this.suit = suit;
            returnValue = true;
        } 
        else
        {
            this.errorFlag = true;
            returnValue = false;
        } // end if
       
       return returnValue;
    }

    
    /**************************************
     * arraySort
     *    used to sort the card array in ascending order
     *    
     * Return Value: 
     *    none
     * 
     * PARAMETERS:
     *   Card[] - card array to be sorted
     *   int    - size of array
     *
     ****************************************/
    static void arraySort(Card[] cards, int arraySize)
    {
        boolean swapped = false;
        //
        // Standard bubble sort with swap indicator for early termination
        //
        do {
            swapped = false;
            for(int i = 1; i < arraySize; i++)
            {
                if(cards[i-1].compareTo(cards[i]) > 0)
                {
                    Card tmpCard = new Card(cards[i-1]);
                    cards[i-1] = new Card(cards[i]);
                    cards[i] = new Card(tmpCard);
                    swapped = true;
                } //end if
            }// end for
        } while(swapped);
    } // end arrarySort

    /**************************************
     * isValid
     *    This function determines whether the value passed to it is a valid
     *    value for a card. It checks the value against the valid values stored
     *    in Card.validCardValues.
     *    
     * Return Value: 
     *    boolean - true if valid
     *              false if invalid
     * 
     * PARAMETERS:
     *   char - value for card
     *   Suit - Suit for card
     *
     ****************************************/
    private static boolean isValid(char value, Suit suit)
    {
       boolean returnValue = false;
       
       for(char validValue : Card.validCardValues)
       {
          if(String.valueOf(validValue).toLowerCase().equals(
                String.valueOf(value).toLowerCase()))
            {
               returnValue = true;
            }// end if
       }
        return returnValue;
    }// end  isValid
    
} // end Card Class


/*
 * 
 * ClassName: Hand.java
 * 
 * Description
 * This class is part of group of classes that represent parts of a card game 
 * This is the players Hand class that represents the cards held by a single
 * player.
 * 
 * 
 */


//*************************************************************
// 
//   hand Class Definition
//
//
//*************************************************************

class Hand 
{
   //
   //  ----- Constants  ---
   //
   public static final int MAX_CARDS = 50;        // max cards per hand 
   //
   //  ----- Private Attributes  ---
   //
   private Card[] myCards = new Card[MAX_CARDS];
   int numCards = 0;

   
   // ***************************************
   // 
   //    --- constructors
   //
   // ***************************************
   
   //default constructor
    public Hand()
    {
    }
    
    // ***************************************
    // 
    //    standard methods
    //
    // ***************************************

    //  Convert class to string for display
    //
    /**************************************
     * toString
     *    outputs class data for display
     *    
     * Return Value: 
     *    string representing class data
     * 
     * PARAMETERS:
     *   none
     ****************************************/
     public String toString()
     {
        String handString = "( ";
        
        for(int i = 0; i <  this.numCards; i++)
        {
            handString += this.myCards[i].toString();
            if(i != this.numCards - 1)
                handString += ", ";
        } // end for
        handString += " )";
        
        return handString;
      
     } // end toString

     
     // ***************************************
     // 
     //    --- Accessors and Mutators  -----
     //
     // ***************************************
     //
     //------ Accessors -------------
     //
     
     
     //  Accessor for private numCards
     //
     public int getNumCards()
     {
        
        return this.numCards;
        
     } // end getString1
     
    /**************************************
     * resetHand()
     *    sets the hand to its default state, containing no cards
     *     
     * Return Value: 
     *    none
     * 
     * PARAMETERS:
     *   none
     *
     ****************************************/
    public void resetHand()
    {
        this.myCards = new Card[MAX_CARDS];
        this.numCards = 0;
    } // end resetHand
   
    /**************************************
     * Sort
     *    used to sort the card array in ascending order
     *     
     * Return Value: 
     *    none
     * 
     * PARAMETERS:
     *   Card[] - card array to be sorted
     *   int    - size of array
     *
     ****************************************/

    void sort()
    {
        Card.arraySort(this.myCards, numCards);
    }
   
    /**************************************
     * takeCard
     *    takes a Card object and places a copy of that object into the hand.
     *     
     * Return Value: 
     *    boolean - true if successful
     *            - false if not successful
     *            
     * PARAMETERS:
     *   Card  - card to add to hand

     *
     ****************************************/
    public boolean takeCard(Card card)
    {
        if(this.numCards >= MAX_CARDS)
        {
           return false;           
        }

        else 
        {
            this.myCards[numCards] = new Card(card);
            this.numCards++;
            return true;
        }
    }
    /**************************************
     * playCard
     *    plays a card in the hand by creating
     *    a copy of the first card in the hand
     *    and returns it to the caller.
     *     
     * Return Value: 
     *    card - true if successful
     *            - false if not successful
     *            
     * PARAMETERS:
     *   none

     *
     ****************************************/
    public Card playCard()
    {
        Card card = this.myCards[this.numCards-1];
        this.myCards[this.numCards-1] = null;
        this.numCards--;
        return card;
    }
   
    public Card playCard(int k)
    {
       if (k >= this.numCards || k < 0)
       {
          return new Card('0', Card.Suit.spades);
       }   
       else
       {
          Card card = new Card(this.myCards[k]);
          
          for(int i = k+1; i < this.numCards; i++)
          {
             this.myCards[i - 1] = this.myCards[i];
             this.myCards[i] = null;
          } // end for loop
          this.numCards--;
          
          return card;
       } // end if-else
    } // end playCard()

    /**************************************
     * inspectCard
     *    Accessor for individual card 
     *    
     * Return Value: 
     *    Card - card at location or error card
     * 
     * PARAMETERS:
     *   int k - cardNumber
     *
     ****************************************/
    public Card inspectCard(int k)
    {
        if(k >= this.numCards || k < 0)
            return new Card('0', Card.Suit.spades);
        else
            return new Card(this.myCards[k]);
    } // end inspectCard
}// Class Hand


/*
 * 
 * ClassName: Deck.java
 * 
 * Description
 * This class is part of group of classes that represent parts of a card game 
 * This is the Deck class that represents the deck(s) of cards used.
 * 
 */
//***************************************************//
//                                                   //
//   Deck Class                                      //
//   Sibling class, can be used by any other class   //
//                                                   //
//***************************************************//

class Deck
{
   //
   //  ----- Constants  ---
   //
    public static final short MAX_CARDS_IN_PACK = 56;
    public static final short MAX_PACKS = 6;
    public static final short MAX_CARDS = MAX_PACKS * MAX_CARDS_IN_PACK;
    
    //
    //  ----- Private Attributes  ---
    //

    //The masterPack is a pack of cards that the cards in the deck are built
    // off of. It contains one card for each value/suit combination. 
    // This is static, as it does not change per object instantiated.
    //
    //
    private static Card[] masterPack = new Card[MAX_CARDS_IN_PACK];
    private Card[] cards; //The cards in the object's deck. 
    private int topCard; //The position of the card on the top of the deck.
    private int numPacks; //The deck can consist of multiple packs of cards.
   
    // ***************************************
    // 
    //    --- constructors
    //
    // ***************************************
    //
    //
    public Deck() 
    {
       this.allocateMasterPack();
       this.init(1);
    } // end default constructor

    public Deck(int numPacks) 
    {
        //Build the master pack.
        this.allocateMasterPack();
        
        //If the user wants more packs than are available, give them the max.
        if (numPacks > Deck.MAX_PACKS)
        {
           this.init(Deck.MAX_PACKS);
        }
        else if (numPacks < 1)
        {
           this.init(1); 
        }    
        else
        {
           //Otherwise, build the deck with the specified number of packs.
           this.init(numPacks);     
        } // end if
    } // end constructor
    // ***************************************
    // 
    //    --- Accessors and Mutators  -----
    //
    // ***************************************
    //
    //------ Accessors -------------
    //

    public int getNumCards()
    {
        return this.topCard;
    }

    //
    // 
    //
    public int getTopCard() 
    {
        return this.topCard;
    }

    /**************************************
     * init
     *    set/resets the deck using the masterpack 
     *    
     * Return Value: 
     *    none
     * 
     * PARAMETERS:
     *   none
     *
     ****************************************/
    public void init(int numPacks) 
    {
        //Initialize the cards array.
        this.cards = new Card[numPacks * Deck.MAX_CARDS_IN_PACK];
        
        //Until the total number of cards are reached, 
        // keep adding cards from the master pack.
        for (int i = 0; i < numPacks * Deck.MAX_CARDS_IN_PACK; i++) 
        {
            this.cards[i] = this.masterPack[i % Deck.MAX_CARDS_IN_PACK];
        } // end for
        
        //Set the top card to the last card allocated.
        this.topCard = numPacks * Deck.MAX_CARDS_IN_PACK;
    } // end init

    /**************************************
     * shuffle
     *    reorders cards in deck
     *    This uses a Fisher-Yates shuffle to shuffle 
     * Return Value: 
     *    none
     * 
     * PARAMETERS:
     *   int - number of times to shuffle
     *
     * 
     ****************************************/

    public void shuffle() 
    {
       //Beginning with the top card, decrement i until i is 0.
       for (int i = this.topCard - 1; i >= 0; i--) 
       {
          // Store the card at i, since it will be overwritten.
          Card tmpCard = this.cards[i];
          
          // Choose a random card position from within the deck.
          int randomPosition = (int) (Math.random() * (this.topCard - 1));
          
          // Take the card from the random position 
          // and store it in the ith position.
          this.cards[i] = this.cards[randomPosition];
          
          // Take the card from the ith position, 
          // and put it into the randomly chosen position.
          this.cards[randomPosition] = tmpCard;
          //The cards have now been swapped.
        } // end for
    } // end shuffle
   
    /**************************************
     * dealCard
     *    removes a card and outputs it
     *    
     * Return Value: 
     *    card output
     * 
     * PARAMETERS:
     *   none
     *
     ****************************************/
    public Card dealCard() 
    {
       //Return an invalid card if there are no cards in the deck.
       if (this.topCard < 0)
       {
          return new Card('0', Card.Suit.spades);
       }
       else 
       {
          //Create a copy of the card on the top of the deck.
          Card card = new Card(this.cards[this.topCard - 1]);
            
          //Set the actual card on the top of the deck to null, to destroy it.
          this.cards[this.topCard - 1] = null;
          
          //The topCard is now one less than it was.
          this.topCard--;
          
          //return the copy.
          return card;
        } // end if
    } // end dealCard
    
    /**********************************************
    * inspectCard
    *    Accessor for individual card 
    *    
    * Return Value: 
    *    Card - card at location or error card
    * 
    * PARAMETERS:
    *   int k - cardNumber
    *
    *    
    *********************************************/
    public Card inspectCard(int k) 
    {
        //If k is invalid, return an invalid card.
        if (k >= this.topCard || k < 0)
        {
           return new Card('0', Card.Suit.spades);
        }      
        else
        {
           //Otherwise, return a copy of the card in position k.
           return new Card(this.cards[k]);
          
        } // end if
    } // end inspectCard
   
    /***********************************************************
     * allocateMasterPack                                      
     *    Determines whether masterPack exists                 
     *    If not, it populates masterPack                      
     *                                                         
     * Return Value:                                          
     *    n/a                                                  
     *                                                         
     * PARAMETERS:                                             
     *    n/a                                                  
     *
     ***********************************************************/
    private static void allocateMasterPack() 
    {
       // If Deck.masterPack is null, then it needs to be filled, 
       // otherwise, nothing needs to be done.
        if (Deck.masterPack != null) 
        {
           //For each suit, fill the masterPack with each valid card value from that suit.
           for (int i = 0; i < Card.Suit.values().length; i++) 
           {
              for (int j = 0; j < Card.validCardValues.length; j++) 
              {
                 Deck.masterPack[i * Card.validCardValues.length + j] = 
                       new Card(Card.validCardValues[j], Card.Suit.values()[i]);
                } // end for j
            } // end for i
        } // end fi
    } // end allocateMasterPack

    /***********************************************************
     * addCard                                      
     *    put the card on the top of the deck                                    
     *                                                         
     * Return Value:                                          
     *   boolean true if successful
     *           false if not successful                                               
     *                                                         
     * PARAMETERS:                                             
     *    card to be added                                                  
     *
     ***********************************************************/

    public boolean addCard(Card card)
    {
        int cardCount = 0;
        for(Card cardInDeck : this.cards)
        {
           if(cardInDeck.equals(card))
           {
              cardCount++;
           } // end if
        } // end for
        
        if((cardCount >= this.numPacks) || 
              (this.topCard >= this.MAX_CARDS))
        {
           return false;
        } // end if
            
        this.topCard++;
        this.cards[topCard - 1] = new Card(card);
        return true;
    } // end addCard
    
    /**************************************
     * removeCard
     *    removes a card and outputs it
     *    
     * Return Value: 
     *   boolean true if successful
     *           false if not successful
     * 
     * PARAMETERS:
     *   card to be removed
     *
     * 
     ****************************************/
   public boolean removeCard(Card card)
    {
        for(int i = 0; i < this.cards.length; i++)
        {
           if(this.cards[i].equals(card))
           {
              this.cards[i] = new Card(this.cards[topCard - 1]);
              this.topCard--;
              return true;
              
           } // end if         
        } // end for
        
        return false;
    } // end removeCard
   
    /**************************************
     * Sort
     *    used to sort the card array in ascending order
     *     
     * Return Value: 
     *    none
     * 
     * PARAMETERS:
     *   Card[] - card array to be sorted
     *   Card   - top card 
     *
     ****************************************/
   public void sort()
    {
        Card.arraySort(this.cards, this.topCard);
    }  // end sort
} // end Deck