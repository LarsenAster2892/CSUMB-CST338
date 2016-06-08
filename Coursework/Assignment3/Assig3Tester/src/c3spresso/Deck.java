package c3spresso;

import java.util.*;
import java.lang.Math;

import c3spresso.Card;
import c3spresso.Card.Value;
import c3spresso.Card.Suit;

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
   public static final int MAX_CARDS = 52;

   //
   //  ----- Private Attributes  ---
   //
   private static Card[] masterPack = new Card[MAX_CARDS];
   private static boolean masterDeckInitialized = false;
   private int topCard;
   private int numPacks;
   
   public static Card[] cards;
 
   // ***************************************
   // 
   //    --- constructors
   //
   // ***************************************
   //
   // Default Constructor
   //
   public Deck()
   {
      allocateMasterPack();
      this.numPacks = 1;
      init(numPacks);
      //topCard = 0;
   } // end Deck()


   //
   // Overloaded Constructor
   //
   public Deck(int numPacks)
   {
      allocateMasterPack();
      this.numPacks = numPacks;

     init(numPacks);

   } // end Deck(int numPacks)
   
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
    * Precondition : 
    *    masterpack exist and is initialized
    *    numPacks has a valid value
    *          
    * Postcondition: 
    *    New deck is created using the masterpack
    * 
    ****************************************/
 

   public void init(int numPacks)
   {
      int counter = 0;
      cards = new Card[numPacks * MAX_CARDS];
      for (int loopNumPacks = 0; loopNumPacks < numPacks; loopNumPacks++)
      {
         for (int loopMasterCard = 0; loopMasterCard < MAX_CARDS; 
               loopMasterCard++)
         {
            cards[counter++] = masterPack[loopMasterCard];
         } // end for loopMasterCard
      }  // end for loopNumPacks

      this.topCard = numPacks * MAX_CARDS;

   } // end init()
   
   // ***************************************
   // 
   //    --- Accessors and Mutators  -----
   //
   // ***************************************
   //
   //------ Accessors -------------
   //

   public int getTopCard()
   {
      return this.topCard;
   } // end getTopCard()

   

   
   /**************************************
    * shuffle
    *    reorders cards in deck
    *    
    * Return Value: 
    *    none
    * 
    * PARAMETERS:
    *   int - number of times to shuffle
    *
    * Precondition : 
    *   cards is defined and has objects
    *          
    * Postcondition: 
    *   cards has been reordered
    * 
    ****************************************/

   public void shuffle(int shuffleNum)
   {
      int i, j, k;

      for (k = 0; k < shuffleNum; k++)
      {
         i = (int) (Math.random() * topCard);
         j = (int) (Math.random() * topCard);

         Card card = cards[i];
         cards[i] = cards[j];
         cards [j] = card;
      } // end for

      //topCard = MAX_CARDS; // Current card to deal from top.
   }
   
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
    * Precondition : 
    *   cards is defined and has objects
    *   topCard has value 
    *          
    * Postcondition: 
    *   cards has one card less
    *   topCard is decremented
    * 
    ****************************************/
   public Card dealCard()
   {
      Card returnCard = null;
      int cardIndex = 0;
      //Card card;
      //topCard = getTopCard();

      if (topCard > 0)
      {
         returnCard = new Card();
         cardIndex = topCard - 1;
         returnCard.set(cards[cardIndex].getValue(), 
               cards[cardIndex].getSuit());
        --topCard;
      }
      else
      {
         System.out.println("Deck is out of cards!");
      } // end if
      
      return returnCard;
   } // end dealCard()

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
    * Precondition : 
    *    k has valid value
    *    handCards is define 
    *          
    * Postcondition: 
    *    returnCard is define with value
    *    
    *********************************************/

   public Card inspectCard(int k)
   {
      Card card;

      if ((k < 1) && (k > topCard))
      {
         card = new Card('0', Card.Suit.spades);
      }
      else
      {
         card = cards[k - 1];
      } // end if

      return card;
   } // end inspectCard()

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
    * Precondition : 
    *    k has valid value
    *    handCards is define 
    *          
    * Postcondition: 
    *    returnCard is define with value
    *    
   ***********************************************************/

   private static void allocateMasterPack()
   {
      //
      //   check if the masterdeck as already created, if not make it
      //
      if (!masterDeckInitialized)
      {
         int counter = 0;

         for (Suit s : Suit.values())
         {
            for (Value v : Value.values())
            {
               masterPack[counter++] = new Card(v, s);
            } // end for Value
         }// end for Suit
         
         //
         // now set flag so we will not create it again
         masterDeckInitialized = true;
      }// end if
   } // end allocateMasterPack()
} // end class Deck

