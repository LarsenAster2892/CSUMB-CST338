package c3spresso;
import java.util.*;

import c3spresso.Card.Suit;
import c3spresso.Card.Value;

import java.lang.Math;
import java.text.NumberFormat;

/**
 * Subject: CST338 
 * Team: Cubed Expresso
 *       Chris Smith
 *       Clarence Mitchell
 *       Daniel Cadwell
 * Assignment: 3  - Phase 2
 * ClassName: Assig3Tester.java
 * 
 *
 * Description
 * This driver executes the following test phases:
 * 
 * Phase 1 - The Card Class
  * This Phases does the following:
 *   1. Creates 3 Card objects (2 valid, 1 invalid).
 *   2. Displays the 3 Cards
 *   3. Makes a valid card invalid, and invalid valid
 *   4. Displays the 3 Cards again
 * 
 * Phase 2 - The Hand Class
 * This Phases does the following:
 *   1. Creates 5 cards objects.
 *   2. Creates 1 hand object.
 *   3. Loops taking the cards in maximum cards are reached
 *   4. Displays the hand
 *   5. Inspect for a valid card (within 1 to maximum cards)
 *   6. Inspect for a invalid card (not in 1 to maximum card)
 *   7. Loops playing a card until hand is empty (while displaying each card)
 *   8. Displays the hand (should be empty) 
 * 
 * Phase 3 - The Deck Class
 * This Phases does the following:
 *   1. Creates a Deck object with TWO packs of cards (52 x 2) (not shuffled).
 *   2. Loops and deals all the Cards in the deck until it is empty.
 *      These are displayed to the console, not to a hand 
 *   3. The Deck is reset
 *   4. The Deck is shuffled
 *   5. Loops and deals all the Cards in the deck until it is empty.
 *      These are displayed to the console, not to a hand 
 *   7. Creates a Deck object with ONE packs of cards (not shuffled).
 *   8. Loops and deals all the Cards in the deck until it is empty.
 *      These are displayed to the console, not to a hand 
 *   9. The Deck is reset
 *  10. The Deck is shuffled
 *  11. Loops and deals all the Cards in the deck until it is empty.
 *      These are displayed to the console, not to a hand 
 * 
 * Phase 4 - The Deck Class and The Hand Classes
 * This Phases does the following:
 *   1. Get player hands from the user (from 1 to 10)
 *   2. Creates a Deck object with ONE packs of cards (not shuffled).
 *   3. Loops and deals all the Cards in the deck to the hands
 *      until it is empty.
 *   4. Display all the Hands
 *   5. The Deck is reset
 *   6. The Deck is shuffled
 *   7. Loops and deals all the Cards in the deck to the hands
 *      until it is empty.
 *   8. Display all the Hands
*/

//*************************************************************
//
// main class Assig2 Definition
//
//
//*************************************************************


public class Assig3Tester

{

   public static void main(String[] args)
   {
      int handsToPlay = 0;
      Hand[] handsOfCards;
      /**
       ************************************************************
       * 
       * Phase 1: Test The Card Class
       * 
       *********************************************************** 
       */
      System.out.println();
      System.out.println("Welcome to Assignment 3 Test Runs! \n");
      System.out.println("*******************************************\n");
      System.out.println(); 
      System.out.println("Phase 1: The Card Class \n");
        
      //
      // Step 1. Create 3 cards (2 valid, 1 invalid)
      //
      System.out.println(" -- Creating Cards \n\n");
      Card testCard1 = new Card('A', Suit.diamonds);
      Card testCard2 = new Card('3', Suit.hearts );
      Card testCard3 = new Card('1', Suit.spades);
      
      //
      // Step 2. Print 3 Cards
      //
      System.out.println(" -- Printing Cards \n\n");
      System.out.println(testCard1.toString() + "\n");
      System.out.println(testCard2.toString() + "\n");
      System.out.println(testCard3.toString() + "\n");
  
      // 
      // Step 3. Make a good card bad, and bad one good
      //
      System.out.println(" -- Good to Bad, Bad to Good \n\n");
      testCard2.set('0', Suit.clubs);
      testCard3.set('K', Suit.hearts);
 
      //
      // Step 4. Print 3 Cards again
      //
      System.out.println(" -- Printing Updated Cards \n\n");
      System.out.println(testCard1.toString() + "\n");
      System.out.println(testCard2.toString() + "\n");
      System.out.println(testCard3.toString() + "\n");
     
      //
      // Step 4. Print 3 Cards again
      //
      System.out.println();
      System.out.println("-- End of Phase 1 \n");
      System.out.println("*******************************************\n");
      System.out.println(); 
      
      /**
       ************************************************************
       * 
       * Phase 2: Test The Hand Class
       * 
       *********************************************************** 
       */
      System.out.println();
      System.out.println("*******************************************\n");
      System.out.println(); 
      System.out.println("Phase 2: The Hand Class \n");

      //
      // Step 1. Create 5 cards (reusing 3 from phase 1)
      //
      System.out.println(" -- Creating Cards \n");
      
      //
      // reuse the old 3
      Card[] cardArray = new Card[5];
      
      testCard1.set('3', Suit.clubs); 
      testCard2.set('T', Suit.clubs); 
      testCard3.set('9', Suit.hearts); 
      //
      // define two new cards
      Card testCard4 = new Card('Q', Suit.hearts );
      Card testCard5 = new Card('K', Suit.diamonds);
      //
      // Assign to array (shallow copy cause cards exist not changing)
      //
        cardArray[0] = testCard1;
        cardArray[1] = testCard2;
        cardArray[2] = testCard3;
        cardArray[3] = testCard4;
        cardArray[4] = testCard5;


      //
      // Create a Hand Object
      Hand testHand = new Hand();
      
      //
      // Step 2. loop through and take cards until hand full
      //         note varying card 
      //
      int handLoop = 0; 
      while (testHand.takeCard(cardArray[handLoop]))
      {
         //
         // Get to the next card by increment the wrap within 5 (modulus)
         handLoop = ++handLoop % 5;
      }
      System.out.println(); 
      System.out.println("Hand full\n");
      System.out.println("number of cards = " + testHand.getNumCards()); 

      
      //
      // Step 3. Display the hand loop through and take cards until hand full
      //         note varying card and formatted for 80 columns
      //
      System.out.println("After Deal \nHand = (");
      System.out.println(testHand.toString());
      System.out.println(")\n");  
      
      //
      // Step 4. Test inspectCard()
      //         Legal and illegal
      //
      System.out.println("---Testing inspectCard()\n");     
      // First legal
      System.out.println(testHand.inspectCard(8).toString() + "\n");
      // now illegal
      System.out.println(testHand.inspectCard(58).toString() + "\n");
   
      //
      // Step 5. Play each card in a loop until the hand is empty
      //         Displaying each card
      //
      System.out.println("---Playing Cards");     
      Card returnCard; 
      returnCard = testHand.playCard();
      while (returnCard != null)
      {
         //
         // 
         System.out.println("Playing " + returnCard.toString() + "\n");
         returnCard = testHand.playCard();
      }
      
      //
      // Step 6. Display empty hand
      //
      System.out.print("After playing all cardsl \nHand = (");
      System.out.print(testHand.toString());
      System.out.println(" )\n");  

      /**
       ************************************************************
       * 
       * Phase 3: Test The Deck Class
       * 
       *********************************************************** 
       */
      
      Deck myDeck;
      int numPacks = 2;
      int i;

      // Testing overloaded Deck(int numPacks), unshuffled
      myDeck = new Deck(numPacks);
      System.out.println("2 Decks, unshuffled:");
      for ( i = 0; i < Deck.cards.length; i++)
      {
         System.out.print(Deck.cards[i] + " / ");
         if (i % 5 == 0)
         {
            System.out.println();
         } // end if
      } // end for

      System.out.println("\n");

      // Testing overloaded Deck(int numPacks), shuffled
      myDeck = new Deck(numPacks);
      System.out.println("2 Decks, shuffled:");
      myDeck.shuffle(1000);
      for ( i = 0; i < Deck.cards.length; i++)
      {
         System.out.print(Deck.cards[i] + " / ");
         if (i % 5 == 0)
         {
            System.out.println();
         } // end if
      } // end for

      System.out.println("\n");
      System.out.println("\n");

      // Testing one Deck(), unshuffled
      myDeck = new Deck();
      System.out.println("1 Deck, unshuffled:");
      for ( i = 0; i < Deck.cards.length; i++)
      {
         System.out.print(Deck.cards[i] + " / ");
         if (i % 5 == 0)
         {
            System.out.println();
         } // end if
      } // end for

      System.out.println("\n");

      // Testing one Deck(), shuffled
      myDeck = new Deck();
      System.out.println("1 Deck, shuffled:");
      myDeck.shuffle(1000);
      for ( i = 0; i < Deck.cards.length; i++)
      {
         System.out.print(Deck.cards[i] + " / ");
         if (i % 5 == 0)
         {
            System.out.println();
         } // end if
      }// end for
      
      
  
      
      /**
       ************************************************************
       * 
       * Phase 4: Test The Deck and Hand Classes
       * 
       *********************************************************** 
       */

      System.out.println();
      System.out.println("*******************************************\n");
      System.out.println(); 
      System.out.println("Phase 4: The Deck & Hand Classes \n");

      //
      // Step 1. Get the number of hands and create Hand objects array
      //
      handsToPlay = getNumHands();
      handsOfCards = new Hand[handsToPlay];
      myDeck = new Deck();
      int cardsPerPlayer = Deck.MAX_CARDS / handsToPlay;
      
      System.out.println();
      System.out.println("1 Deck, unshuffled, passed to " + handsToPlay
         + " players:\n");

      for (int j = 1; j <= handsToPlay; j++)
      {
         System.out.print("Hand ");
         System.out.print(j);
         System.out.println(" = (");
         for (int k = 0; k < cardsPerPlayer; k++)
         {
            System.out.print(Deck.cards[k] + " / ");
            if (k % 5 == 0)
            {
               System.out.println();
            }
         }
         System.out.println(")\n");
      }

      System.out.println();
      System.out.println("1 Deck, shuffled, passed to " + handsToPlay
         + " players:\n");
      myDeck.shuffle(750);
      for (int j = 1; j <= handsToPlay; j++)
      {
         System.out.print("Hand ");
         System.out.print(j);
         System.out.println(" = (");
         for (int k = 0; k < cardsPerPlayer; k++)
         {
            System.out.print(Deck.cards[k] + " / ");
            if (k % 5 == 0)
            {
               System.out.println();
            }
         }
         System.out.println(")\n");
      }
      
      System.out.println();
      System.out.println("-- End of Phase 4 \n");
      System.out.println("*******************************************\n");
      System.out.println(); 

      
      
   } // ends main()

   
   /**************************************
    * getNumHands 
    *   Gets number of hands to play from user  
    * 
    * Return Value: 
    *    int number of hands user entered 
    * 
    * PARAMETERS:
    *   (n/a)
    *
    * Precondition : 
    *     N/a
    *          
    * Postcondition: 
    *    The value entered by the user 
    ****************************************/

   public static int getNumHands()
   {
      // Initialize scanner for user input through keyboard.
      Scanner keyboard = new Scanner(System.in);
      int numHands;
      
      System.out.print("How many hands (1 - 10, please): ");

      numHands = keyboard.nextInt();

      while ((numHands < 1) || (numHands > 10))
      {
         System.out.print("Invalid number of Hands\n" 
            + "Please enter number hands  (between 1 - 10): ");
   
         numHands = keyboard.nextInt();
      } // end while

      return numHands;
   } // ends getNumHands() 

   
} // ends class Assig2








/********************* Output *******************************
 * 
 *  
 *  

  
 ************************************************************/
