package guiCardsPhase2;
/**************************
 * 
 *  Import Statements
 * 
 **************************
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import guiCardsPhase2.Card;

import java.lang.Comparable;

/**
* Subject: CST338 
* Team: Cubed Expresso
*       Chris Smith
*       Clarence Mitchell
*       Daniel Cadwell
* Assignment: 5  - Phase 2
* ClassName: Assig5Phase2r.java
* 
*
* Description
* This driver executes phase 2 which 
*  1. Defines the CardTable and GUICard classess
*  2. Encapsulates the layout and Icons into the CardTable and GUICard classes 
* 
* 
*/

//*************************************************************
//
//main class Assig5Phase2 Definition
//
//
//*************************************************************

public class Assig5Phase2 
{
    //
    // Constants
    //   
    static int NUM_CARDS_PER_HAND = 7;
    static int NUM_PLAYERS = 2;
    static final String PLAYER1_TEXT = "Computer", PLAYER2_TEXT = "You";
    //
    //  ----- Private Attributes  ---
    //
   
    static JLabel statusText = new JLabel("");
    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
    static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
    
    
    public static void main(String[] args)
    {
       //
       // Create the empty table
       //
        
        CardTable table = 
              new CardTable("CardTable",NUM_CARDS_PER_HAND, NUM_PLAYERS);
        //
        //  build computer and human card hands in memory
        //
         for(int i = 0; i < NUM_CARDS_PER_HAND; i++)
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
            humanLabels[i].setIcon(GUICard.getIcon(generateRandomCard()));
            humanLabels[i].setMaximumSize(new Dimension(0, 0));
         
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

      statusText.setText("");
      statusText.setBorder(null);
        
          
      table.setVisible(true);
      //
      // Set Computer and Player labels
      //
      for(int i = 0; i < NUM_PLAYERS; i++)
      {
         //
         playedCardLabels[i] = new JLabel();
         playedCardLabels[i].setIcon(GUICard.getIcon(generateRandomCard()));
         playLabelText[i] = new JLabel();
         if(i == 0)
             playLabelText[i].setText("Computer");
         else
             playLabelText[i].setText("You");
         playedCardLabels[i].setHorizontalAlignment(JLabel.CENTER);
         table.pnlPlayArea.add(playedCardLabels[i]);
      } // end for
        
        
      for(JLabel label : playLabelText)
      {
         //
         label.setHorizontalAlignment(JLabel.CENTER);
         table.pnlPlayArea.add(label);
         
      }
        
      table.setVisible(true);
    } // end main
    //*****************************************************
    //
    //  Helper routines
    //
    //****************************************************
    //
    //  -- Generates random Card
    //
    static Card generateRandomCard(){
        Card card = new Card();
        int value = (int)(Math.random()*(Card.validCardValues.length - 1));
        int suit = (int)(Math.random()*(Card.validCardSuits.length - 1));
        card.set(Card.validCardValues[value], Card.Suit.values()[suit]);
        return card;
    }
}

/*
 * 
 * ClassName: GUICard
 * 
 * Description
 * manages the reading and building of the card imageIcons 
 * 
 * 
 */

//*************************************************************
//
//  GUICard Class Definition
//
//
//*************************************************************

class GUICard
{
   //
   //  ----- Private Attributes  ---
   //
   private static Icon[][] iconCards = new ImageIcon[14][4];
   private static Icon iconBack = new ImageIcon("./images/BK.gif");
   static boolean iconsLoaded = false;
   static final char[] VALID_SUITS = {'C', 'D', 'H', 'S'};
   // ***************************************
   // 
   //    --- Accessors and Mutators  -----
   //
   // ***************************************
   //
   //------ Accessors -------------
   //
   //  Accessor for card Icon
   //
  
   public static Icon getIcon(Card card)
   {
        if(!GUICard.iconsLoaded)
        {
           GUICard.loadCardIcons();           
        }

        return iconCards[valueAsInt(card)][suitAsInt(card)];
    } // end getIcon
   
   //
   //  Accessor for card value
   // 
   private static int valueAsInt(Card card)
   {
       String values = new String(Card.validCardValues);
       return values.indexOf(card.getValue());
   } // end valueAsInt
   
   //
   //  Accessor for card suit
   // 
   private static int suitAsInt(Card card)
   {
       return card.getSuit().ordinal();
   } // end suitAInt
   
   //
   //  Accessor for card Icon
   // 
   public static Icon getBackCardIcon()
   {
       return GUICard.iconBack;
   } // need getBackCardIcon
   
   /**************************************
    * loadCardIcons
    *    build icons from file names
    *    
    * Return Value: 
    *    none
    * 
    * PARAMETERS:
    *   none
    *
    ****************************************/
    private static void loadCardIcons()
    {
       
       for(int i = 0; i < Card.validCardValues.length; i++)
       {
          for(int j = 0; j < VALID_SUITS.length; j++)
          {
             iconCards[i][j] = 
             new ImageIcon("./images/" + Card.validCardValues[i] +
                   VALID_SUITS[j] + ".gif");
              
          } // end for
          
        } // end for
        
        GUICard.iconsLoaded = true;
        
    } // end loadCardIcon
    
    
    /**************************************
     * valueAsInt
     *    build icons from file names
     *    
     * Return Value: 
     *    none
     * 
     * PARAMETERS:
     *   none
     *
     ****************************************/
    
} // end class GUICard
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
 * ClassName: CardTable.java
 * 
 * Description
 * This class is part of group of classes that represent parts of a card game 
 * This is the card class that represents the a playing card
 * 
 * 
 */

//*************************************************************
//
//  CardTable Class Definition
//
//
//*************************************************************


class CardTable extends JFrame 
{
   //
   //  ----- Constants  ---
   //

   static final int MAX_CARDS_PER_HAND = 56;
   static final int MAX_PLAYERS = 2;
   //
   //  ----- Private Attributes  ---
   //
   private int numCardsPerHand;
   private int numPlayers;
   //
   //  ----- Public Attributes  ---
   //
   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea, pnlPlayedCards,
      pnlPlayerText, pnlStatusText;
    
   // ***************************************
   // 
   //    --- constructors
   //
   // ***************************************
   
   public CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      //
      //  call base class
      super();
      //
      // valid input values
      //
      if((numCardsPerHand < 0) || 
            (numCardsPerHand > CardTable.MAX_CARDS_PER_HAND))
      {
         this.numCardsPerHand = 20;
      }
            
      this.numCardsPerHand = numCardsPerHand;
        
      if((numPlayers < 2) || 
            (numPlayers > CardTable.MAX_PLAYERS))
      {
         this.numPlayers = numPlayers; 
      }
           
      if(title == null)
      {
         title = ""; 
      }
      //
      // build layouts and panels
      //
      this.setTitle(title);
      this.setSize(800, 600);
      this.setMinimumSize(new Dimension(800, 600));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      BorderLayout layout = new BorderLayout();
      this.setLayout(layout);

      FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
      TitledBorder border = new TitledBorder("Computer Hand");
      pnlComputerHand = new JPanel();
      pnlComputerHand.setLayout(flowLayout);
      pnlComputerHand.setPreferredSize(
            new Dimension((int)this.getMinimumSize().getWidth()-50, 115));
      JScrollPane scrollComputerHand = new JScrollPane(pnlComputerHand);
      scrollComputerHand.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_NEVER);
      scrollComputerHand.setBorder(border);
      this.add(scrollComputerHand, BorderLayout.NORTH);

      border = new TitledBorder("Playing Area");
      GridLayout gridLayoutCardsArea = new GridLayout(1, 2);
      GridLayout gridLayoutStatusArea = new GridLayout(1, 1);
      pnlPlayArea = new JPanel();
      pnlPlayArea.setBorder(border);

      //GridLayout gridLayout = new GridLayout(2, 2);


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
      
//      pnlPlayArea.setLayout(gridLayout);
//      this.add(pnlPlayArea, BorderLayout.CENTER);
//
//      border = new TitledBorder("Human Hand");
//      pnlHumanHand = new JPanel();
//      pnlHumanHand.setLayout(flowLayout);
//      pnlHumanHand.setPreferredSize(
//            new Dimension((int)this.getMinimumSize().getWidth()-50, 115));
//      JScrollPane scrollHumanHand = new JScrollPane(pnlHumanHand);
//      scrollHumanHand.setVerticalScrollBarPolicy(
//            JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//      scrollHumanHand.setBorder(border);
//      this.add(scrollHumanHand, BorderLayout.SOUTH);

   } // end of constructor
}  // end CardTable

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