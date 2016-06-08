package c3spresso;
import java.util.*;

import c3spresso.Card.Suit;
import c3spresso.Card.Value;

import java.lang.Math;
import java.text.NumberFormat;
// *****************************************************************
//
//  Class files follow the output at the end of program 
//
// ******************************************************************
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
      //
      // Used for testing objects
      //
      int handsToPlay = 0;
      //int numCardInDeck = 0;
      
      //
      //  Class objects used in test
      //
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

      System.out.println();
      System.out.println("-- End of Phase 2 \n");
      System.out.println("*******************************************\n");
      System.out.println(); 

      /**
       ************************************************************
       * 
       * Phase 3: Test The Deck Class
       * 
       *********************************************************** 
       */
      //
      //  Create variables and objects needed for phase
      //
      System.out.println();
      System.out.println("*******************************************\n");
      System.out.println(); 
      System.out.println("Phase 3: The Deck \n");

      int numPacks = 2;
      int i;
      int numDeckCards = 0;
      //
      // Objects needed
      Deck myDeck;
     
      //
      // --- Step 1 Create 2 decks unshuffled then print
      //
      // Testing overloaded Deck(int numPacks), unshuffled
      myDeck = new Deck(numPacks);
      
      System.out.println("2 Decks, unshuffled:");
      
      numDeckCards = myDeck.getTopCard();
      //
      //  Loop through and print the cards
      //
      for ( i = 0; i < numDeckCards; i++)
      {
         System.out.print(myDeck.dealCard() + " / ");
         if (i % 5 == 0)
         {
            System.out.println();
         } // end if
      } // end for

      System.out.println("\n");
      //
      // --- Step 2 Create 2 decks shuffled then print
      //
      //
      // Testing overloaded Deck(int numPacks), shuffled
      //
      myDeck.init(numPacks);
      
      System.out.println("2 Decks, shuffled:");
      myDeck.shuffle(1000);

      numDeckCards = myDeck.getTopCard();
      
      for ( i = 0; i < numDeckCards; i++)
      {
         System.out.print(myDeck.dealCard() + " / ");
         if (i % 5 == 0)
         {
            System.out.println();
         } // end if
      } // end for

      System.out.println("\n");
      System.out.println("\n");

      //
      // --- Step 3 Create 1 deck unshuffled then print
      //
      // Testing one Deck(), unshuffled
      myDeck = new Deck();
      
      System.out.println("1 Deck, unshuffled:");
  
      numDeckCards = myDeck.getTopCard();
      
      for ( i = 0; i < numDeckCards; i++)
      {
         System.out.print(myDeck.dealCard() + " / ");
         if (i % 5 == 0)
         {
            System.out.println();
         } // end if
      } // end for

      System.out.println("\n");

      //
      // --- Step 4 Create 1 deck shuffled then print
      //
      //
      // Testing one Deck(), shuffled
      myDeck = new Deck();
      System.out.println("1 Deck, shuffled:");
      myDeck.shuffle(1000);
      
      
      numDeckCards = myDeck.getTopCard();
      
      for ( i = 0; i < numDeckCards; i++)
      {
         System.out.print(myDeck.dealCard() + " / ");
         if (i % 5 == 0)
         {
            System.out.println();
         } // end if
      } // end for
      

      System.out.println();
      System.out.println("-- End of Phase 3 \n");
      System.out.println("*******************************************\n");
      System.out.println();      
  
      
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
      // initial variables used in the phase
      //
      int currHand = 0;

      //
      // Step 1. Get the number of hands and create Hand objects array
      //
      handsToPlay = getNumHands();
      handsOfCards = new Hand[handsToPlay];
      
      //
      //  Initialize the hands
      // 
      for (int loopHands = 0; loopHands < handsToPlay; loopHands++ )
      {
         System.out.println("Creating hand number:" + loopHands);
         handsOfCards[loopHands] =  new Hand();
      }
    
      //
      // Now create new deck and initial
      //
      
      myDeck = new Deck();
      
      
      int numCardInDeck = 0;
      numCardInDeck = myDeck.getTopCard();
      //
      // Step 2. Deal the deck
      //
      
      System.out.println();
      System.out.println("1 Deck, unshuffled, passed to " + handsToPlay
         + " players:\n");
      
      //
      //  Deal to hands..
      //
      for (int loopDeck = 0; loopDeck < numCardInDeck; loopDeck++)
      {
         handsOfCards[currHand].takeCard(myDeck.dealCard());
         currHand = (++currHand) %  handsToPlay;
      }
      
      //
      //  Print the hands out
      //
      for (int loopHands = 0; loopHands < handsToPlay; loopHands++)
      {
         System.out.print("Hand = (");
         System.out.print(handsOfCards[loopHands] + ") \n ");
         System.out.println("\n");
       }
      System.out.println("\n");
      System.out.println();
      
      //
      // reset the deck and the hands.
      //
      myDeck.init(1);
      for (int loopHands = 0; loopHands < handsToPlay; loopHands++ )
      {
         handsOfCards[loopHands].resetHand();
      }
      
      //
      //  now shuffle the deck and redeal to the hands
      //
     
      System.out.println("1 Deck, shuffled, passed to " + handsToPlay
         + " players:\n");
      myDeck.shuffle(750);

      currHand = 0;
 
      //
      //  Deal to hands..
      //
     
      for (int loopDeck = 0; loopDeck < numCardInDeck; loopDeck++)
      {
          handsOfCards[currHand].takeCard(myDeck.dealCard());
         currHand = (++currHand) %  handsToPlay;
      }
           
      //
      //  Print the hands out
      //      
      for (int loopHands = 0; loopHands < handsToPlay; loopHands++)
      {
         System.out.print("Hand = (");
         System.out.print(handsOfCards[loopHands] + ") \n ");
         System.out.println("\n");
       }
      System.out.println("\n");
      
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

Welcome to Assignment 3 Test Runs! 

*******************************************


Phase 1: The Card Class 

 -- Creating Cards 


 -- Printing Cards 


ace diamonds

three hearts

[INVALID]

 -- Good to Bad, Bad to Good 


 -- Printing Updated Cards 


ace diamonds

[INVALID]

king hearts


-- End of Phase 1 

*******************************************



*******************************************


Phase 2: The Hand Class 

 -- Creating Cards 


Hand full

number of cards = 50
After Deal 
Hand = (
 three clubs, ten clubs, nine hearts, queen hearts, king diamonds, three clubs,
 ten clubs, nine hearts, queen hearts, king diamonds, three clubs, ten clubs,
 nine hearts, queen hearts, king diamonds, three clubs, ten clubs, nine hearts,
 queen hearts, king diamonds, three clubs, ten clubs, nine hearts, queen hearts,
 king diamonds, three clubs, ten clubs, nine hearts, queen hearts, king diamonds,
 three clubs, ten clubs, nine hearts, queen hearts, king diamonds, three clubs,
 ten clubs, nine hearts, queen hearts, king diamonds, three clubs, ten clubs,
 nine hearts, queen hearts, king diamonds, three clubs, ten clubs, nine hearts,
 queen hearts, king diamonds
)

---Testing inspectCard()

nine hearts

[INVALID]

---Playing Cards
Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

Playing king diamonds

Playing queen hearts

Playing nine hearts

Playing ten clubs

Playing three clubs

After playing all cardsl 
Hand = ( )


-- End of Phase 2 

*******************************************



*******************************************


Phase 3: The Deck 

2 Decks, unshuffled:
king spades / 
queen spades / jack spades / ten spades / nine spades / eight spades / 
seven spades / six spades / five spades / four spades / three spades / 
two spades / ace spades / king hearts / queen hearts / jack hearts / 
ten hearts / nine hearts / eight hearts / seven hearts / six hearts / 
five hearts / four hearts / three hearts / two hearts / ace hearts / 
king diamonds / queen diamonds / jack diamonds / ten diamonds / nine diamonds / 
eight diamonds / seven diamonds / six diamonds / five diamonds / four diamonds / 
three diamonds / two diamonds / ace diamonds / king clubs / queen clubs / 
jack clubs / ten clubs / nine clubs / eight clubs / seven clubs / 
six clubs / five clubs / four clubs / three clubs / two clubs / 
ace clubs / king spades / queen spades / jack spades / ten spades / 
nine spades / eight spades / seven spades / six spades / five spades / 
four spades / three spades / two spades / ace spades / king hearts / 
queen hearts / jack hearts / ten hearts / nine hearts / eight hearts / 
seven hearts / six hearts / five hearts / four hearts / three hearts / 
two hearts / ace hearts / king diamonds / queen diamonds / jack diamonds / 
ten diamonds / nine diamonds / eight diamonds / seven diamonds / six diamonds / 
five diamonds / four diamonds / three diamonds / two diamonds / ace diamonds / 
king clubs / queen clubs / jack clubs / ten clubs / nine clubs / 
eight clubs / seven clubs / six clubs / five clubs / four clubs / 
three clubs / two clubs / ace clubs / 

2 Decks, shuffled:
ace hearts / 
jack clubs / three diamonds / ace clubs / ten clubs / two spades / 
ace hearts / queen diamonds / three hearts / king clubs / queen spades / 
five hearts / four diamonds / five diamonds / five clubs / king spades / 
queen hearts / eight clubs / king spades / eight spades / four clubs / 
seven hearts / eight clubs / seven hearts / ace diamonds / six spades / 
king hearts / three diamonds / jack spades / eight hearts / king diamonds / 
eight diamonds / seven spades / ten hearts / two spades / four spades / 
three spades / jack hearts / five diamonds / seven diamonds / king clubs / 
four hearts / ten spades / four spades / queen spades / queen hearts / 
six spades / nine hearts / four diamonds / nine diamonds / seven clubs / 
seven spades / six diamonds / king hearts / jack spades / six hearts / 
eight diamonds / ace spades / nine hearts / five hearts / four hearts / 
queen diamonds / king diamonds / queen clubs / ace clubs / eight spades / 
queen clubs / five spades / ten diamonds / six hearts / two clubs / 
nine spades / ten diamonds / three clubs / two diamonds / three clubs / 
eight hearts / nine clubs / nine clubs / ten spades / jack diamonds / 
seven diamonds / two diamonds / six diamonds / ten clubs / two clubs / 
jack hearts / jack clubs / jack diamonds / ten hearts / seven clubs / 
five spades / three spades / four clubs / nine spades / six clubs / 
two hearts / six clubs / five clubs / ace diamonds / nine diamonds / 
ace spades / three hearts / two hearts / 



1 Deck, unshuffled:
king spades / 
queen spades / jack spades / ten spades / nine spades / eight spades / 
seven spades / six spades / five spades / four spades / three spades / 
two spades / ace spades / king hearts / queen hearts / jack hearts / 
ten hearts / nine hearts / eight hearts / seven hearts / six hearts / 
five hearts / four hearts / three hearts / two hearts / ace hearts / 
king diamonds / queen diamonds / jack diamonds / ten diamonds / nine diamonds / 
eight diamonds / seven diamonds / six diamonds / five diamonds / four diamonds / 
three diamonds / two diamonds / ace diamonds / king clubs / queen clubs / 
jack clubs / ten clubs / nine clubs / eight clubs / seven clubs / 
six clubs / five clubs / four clubs / three clubs / two clubs / 
ace clubs / 

1 Deck, shuffled:
nine diamonds / 
nine clubs / queen diamonds / ten hearts / six hearts / two diamonds / 
two hearts / eight spades / two clubs / queen hearts / seven spades / 
king spades / five spades / three spades / ten clubs / queen clubs / 
eight diamonds / king hearts / king clubs / five diamonds / two spades / 
ace spades / four clubs / six clubs / jack clubs / three hearts / 
five hearts / ace diamonds / ace clubs / three clubs / six spades / 
four diamonds / jack diamonds / ace hearts / nine spades / nine hearts / 
five clubs / queen spades / king diamonds / six diamonds / jack spades / 
seven hearts / seven clubs / eight hearts / three diamonds / eight clubs / 
ten diamonds / four spades / four hearts / ten spades / jack hearts / 
seven diamonds / 
-- End of Phase 3 

*******************************************



*******************************************


Phase 4: The Deck & Hand Classes 

How many hands (1 - 10, please): 4
Creating hand number:0
Creating hand number:1
Creating hand number:2
Creating hand number:3

1 Deck, unshuffled, passed to 4 players:

Hand = ( king spades, nine spades, five spades, ace spades, ten hearts, six hearts,
 two hearts, jack diamonds, seven diamonds, three diamonds, queen clubs, eight clubs,
 four clubs) 
 

Hand = ( queen spades, eight spades, four spades, king hearts, nine hearts, five hearts,
 ace hearts, ten diamonds, six diamonds, two diamonds, jack clubs, seven clubs,
 three clubs) 
 

Hand = ( jack spades, seven spades, three spades, queen hearts, eight hearts, four hearts,
 king diamonds, nine diamonds, five diamonds, ace diamonds, ten clubs, six clubs,
 two clubs) 
 

Hand = ( ten spades, six spades, two spades, jack hearts, seven hearts, three hearts,
 queen diamonds, eight diamonds, four diamonds, king clubs, nine clubs, five clubs,
 ace clubs) 
 




1 Deck, shuffled, passed to 4 players:

Hand = ( five hearts, three spades, nine hearts, five diamonds, nine clubs, eight clubs,
 ten spades, ace hearts, queen hearts, ten hearts, ten clubs, eight hearts,
 four hearts) 
 

Hand = ( queen spades, two spades, ace spades, six diamonds, ten diamonds, three clubs,
 five clubs, three diamonds, four spades, ace clubs, two hearts, three hearts,
 king clubs) 
 

Hand = ( six spades, king hearts, two diamonds, seven spades, two clubs, seven hearts,
 jack clubs, nine spades, king diamonds, queen diamonds, four clubs, eight diamonds,
 four diamonds) 
 

Hand = ( six clubs, ace diamonds, nine diamonds, jack hearts, six hearts, five spades,
 queen clubs, seven diamonds, king spades, eight spades, seven clubs, jack diamonds,
 jack spades) 
 




-- End of Phase 4 

*******************************************
 ************************************************************/
