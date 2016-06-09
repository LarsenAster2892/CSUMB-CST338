package cardGame.model;

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

public class Hand 
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

