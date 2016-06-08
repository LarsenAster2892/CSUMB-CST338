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
   public static final int MAX_CARDS = 52;
   private static Card[] masterPack = new Card[MAX_CARDS];
   public static Card[] cards;
   private int topCard;
   private int numPacks;

   /********************************************
    * Default Constructors:                    *
    *    Used to                               *
    ********************************************/

   //
   // Overloaded Constructor
   //
   public Deck(int numPacks)
   {
      this.numPacks = numPacks;

     init(numPacks);

   } // end Deck(int numPacks)
   
   
   //
   // Default Constructor
   //
   public Deck()
   {
      allocateMasterPack();
      this.numPacks = 1;
      init(numPacks);
      topCard = 0;
   }

   public void init(int numPacks)
   {
      int counter = 0;
      cards = new Card[numPacks * MAX_CARDS];

      for (Suit s : Suit.values())
      {
         for (Value v : Value.values())
         {
            cards[counter++] = new Card(v, s);
         }
      }

      topCard = 0;
   } // end init()

   public void shuffle(int shuffleNum)
   {
      int i, j, k;

      for (k = 0; k < shuffleNum; k++)
      {
         i = (int) (Math.random() * MAX_CARDS);
         j = (int) (Math.random() * MAX_CARDS);

         Card card = cards[i];
         cards[i] = cards[j];
         cards [j] = card;
      }

      topCard = 0; // Current card to deal from top.
   }

   public Card dealCard()
   {
      //Card card;
      topCard = getTopCard();

      if (topCard < MAX_CARDS)
      {
         return cards[topCard++];
      }
      else
      {
         System.out.println("Deck is out of cards!");

         return null;  // Error;
      }
   } // end dealCard()

   /***********************************************
    * Accessors:                                  *
    *    Used to get...                           *
    ***********************************************/

   public int getTopCard()
   {
      return topCard;
   } // end getTopCard()

   public Card inspectCard(int k)
   {
      Card card;

      if (k > topCard)
      {
         card = new Card('0', Card.Suit.spades);
      }
      else
      {
         card = cards[k];
      }

      return card;
   } // end inspectCard()

   /***********************************************************
    * allocateMasterPack                                      *
    *    Determines whether masterPack exists                 *
    *    If not, it populates masterPack                      *
    *                                                         *
    * Return Value:                                           *
    *    n/a                                                  *
    *                                                         *
    * PARAMETERS:                                             *
    *    n/a                                                  *
    ***********************************************************/

   private static void allocateMasterPack()
   {
      int counter = 0;

      for (Suit s : Suit.values())
      {
         for (Value v : Value.values())
         {
            masterPack[counter++] = new Card(v, s);
         }
      }
   } // end allocateMasterPack()
} // end class Deck

