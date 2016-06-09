package cardGame.model;

import cardGame.model.Card.Suit;

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
public class Card implements Comparable
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