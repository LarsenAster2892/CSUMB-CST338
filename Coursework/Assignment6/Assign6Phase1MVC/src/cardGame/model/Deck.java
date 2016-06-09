package cardGame.model;

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

public class Deck
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
