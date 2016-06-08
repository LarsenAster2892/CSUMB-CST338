package c3spresso;
import c3spresso.Card;
import c3spresso.Card.Suit;
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
  private static final char SPACE_VALUE = ' ';   // Make space easier to see 
  private static final char DELIMIT_VALUE = ','; // Make comma easier to see 

  private static final int MAX_PER_LINE = 6;    // max cards per output line
  public static final int MAX_CARDS = 50;        // max cards per hand 


  //
  //  ----- Private Attributes  ---
  //
  private Card[] handCards;
  private int numCards;
  
  // ***************************************
  // 
  //    --- constructors
  //
  // ***************************************
  
  //default constructor
  public Hand()
  {
     //
     // execute helper function call by various places
     this.initializeData();
      
  } // end default constructor

  // ***************************************
  // 
  //    Private Helper methods
  //
  // ***************************************
  /**************************************
   * initializeData
   *    set/resets class data 
   *    
   * Return Value: 
   *    none
   * 
   * PARAMETERS:
   *   none
   *
   * Precondition : 
   *   MAX_CARDS has been defined and initialized
   *          
   * Postcondition: 
   *   numCards is initialized
   *   handCards is defined  
   * 
   ****************************************/

  private void initializeData()
  {
     this.handCards = new Card[MAX_CARDS];
     this.numCards = 0;
     
  } // end intializeDataa

  
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
   *
   * Precondition : 
   *   none
   *          
   * Postcondition: 
   *   string value contains class data representation
   * 
   ****************************************/
 public String toString()
  {
     String returnString = "";

     if (numCards > 0)
     {
        for (int loopCardIndex = 0; loopCardIndex < numCards; loopCardIndex++ )
        {

           //
           //  Note could be 1 statement but separated for visibility  
           //
           returnString += SPACE_VALUE;
           returnString += this.handCards[loopCardIndex].toString();
           returnString += DELIMIT_VALUE;
           
           //
           // Check if reached maximum cards per line, if so add newline 
           // Note by adding 1 to loop index check, avoids 0 having newline
           //
           if ( ((loopCardIndex + 1) % MAX_PER_LINE) == 0 )
           {
              returnString += "\n";
           } // end if
        } // end for
        //
        // Remove the last comma ..
        returnString = returnString.substring(0, returnString.length()-1);
       
     }
     else
     {
        returnString = "";
     }
     //
     // Return value
     return (returnString);
     
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
     
     return numCards;
     
  } // end getString1
  

  //
  //------ Mutators -------------
  //
  //  --- None ------------------
  

  
  // ***************************************
  // 
  //    Class Methods
  //
  // ***************************************

  public boolean equals(Object otherObject)
  {
     boolean returnFlag = false; // define and initialize return
     
     //
     // Check to make sure object is not null and same class
     //
     if ((otherObject != null) && (this.getClass() == otherObject.getClass())) 
     {
        //
        // At this point we have the same object type so create reference
        // then continue to compare
        //
        Hand otherHand = (Hand)otherObject;
        
        //
        //  Same number of cards?
        //
        if (numCards == otherHand.numCards)
        {
           returnFlag = true;   // Change value to be true as default
           //
           // Now check every card for a match...
           //
           for (int loopCardIndex = 0;
                 ((loopCardIndex < numCards) && returnFlag);
                 loopCardIndex++ )
           {       
              //  So here just the next card in the hand, then
              //  use its equals to check if the passed object's card at the 
              //  same spot is equal. 
              //  Note that value returned by the equals is placed into 
              //  the returnFlag.  So if they are not equal the returnFlag
              //  will be false.  This will trigger the exit of the loop,
              //  as the return flag is part of the loop condition
              //
              returnFlag = 
                  handCards[loopCardIndex].equals((inspectCard(loopCardIndex)));
           } // end for
           
        } // end if numcards
     }
     else
     {
        returnFlag = false;
     } // end if otherObject
     
    
     return returnFlag;
  }
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
   * Precondition : 
   *    k has valid value
   *    handCards is define 
   *          
   * Postcondition: 
   *    returnCard is define with value
   *    
   ****************************************/

  public Card inspectCard(int k)
  {
     Card returnCard = new Card(); 
     int cardIndex = k - 1;
     //
     // Determine if k is beyond cards in hand
     if ((k < 0) || (k > this.numCards))
     {
        returnCard.set('V', Suit.spades);
     }
     else
     {
        returnCard.set(this.handCards[cardIndex].getValue(),
              this.handCards[cardIndex].getSuit());
     } // end if
     
     return returnCard;
     
  } // end inspectCard
  

  /**************************************
   * playCard
   *    returns and removes the top card 
   *    
   * Return Value: 
   *    card           - if not empty successful
   *    card (null)    - if empty card is set to null
   * 
   * PARAMETERS:
   *   none
   *
   * Precondition : 
   *   none
   *          
   * Postcondition:
   *  returnCard is defined with value 
   * 
   ****************************************/

  public Card playCard()
  {
     Card returnCard = null;      // Create card to return
     //
     //  if still cards than call inspect to next  
     //  Note numCards is decremented in if statement of at right index 
     //
     if (this.numCards >= 1)
     {
        returnCard = inspectCard(numCards);
        --numCards;
     }
     else
     {
       returnCard = null; 
     } // end if
       
     return returnCard;
     
  } // end playCard

  /**************************************
   * resetHand()
   *    removes all cards from the hand 
   *    
   * Return Value: 
   *    none
   * 
   * PARAMETERS:
   *    none
   *
   * Precondition : 
   *  numCards is defined with value
   *          
   * Postcondition: 
   *  handCards is reset
   * 
   ****************************************/

  public void resetHand()
  {
     
     //
     //  while the fastest way might be setting the array to null
     //  garbage collection that process might happen latter
     //  So this method is used
     //
     for (int loopCardIndex = 0; loopCardIndex < numCards; loopCardIndex++ )
     {       
        //  release each card object 
        //
        this.handCards[loopCardIndex] = null;
     } // end for
     
     initializeData();
     
  } // end resetHand
  
  

  /**************************************
   * takeCard
   *    adds a card to the next available position 
   *    
   * Return Value: 
   *    true  - if card add successful
   *    false - if hand is full
   * 
   * PARAMETERS:
   *    card   - object card to add
   *
   * Precondition : 
   *    MAX_CARDS is defined with value
   *    card input has valid card
   *          
   * Postcondition: 
   *     card input is added to handCards
   *     numCards is incremented
   * 
   ****************************************/

  public boolean takeCard(Card card)
  {
     boolean returnCardAdded = true;  // return value to output
     Card addCard = new Card();       // Create card object to copy into
     
     //
     //  if more room in hand, add card  
     //
     if (this.numCards < MAX_CARDS)
     {
        addCard.set(card.getValue(),card.getSuit());
        handCards[numCards] = addCard;
        returnCardAdded = true;
        numCards++;
     }
     else
     {
        returnCardAdded = false; 
     } // end if
          
     return returnCardAdded;
     
  } // end takeCard

  
} // end Hand class

   
   

