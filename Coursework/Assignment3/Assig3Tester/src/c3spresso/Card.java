package c3spresso;


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

public class Card
{
   //
   //  ----- Enumerators  ---
   //

   public enum Suit {clubs, diamonds, hearts, spades};
   public enum Value {ace, two, three, four, five, six, seven, eight,
      nine, ten, jack, queen, king};

   //
   //  ----- Private Attributes  ---
   //
   private Value value;            
   private Suit suit;
   boolean errorFlag;   

   // ***************************************
   // 
   //    --- constructors
   //
   // ***************************************
   
   // default constructor
   //
   public Card()
   {
      suit = Suit.spades;
      value = Value.ace;
      
   } // end constructor

   // set value and suit constructor
   //
   public Card(char v, Suit s)
   {
      this.set(v, s);

   } // end constructor

   // set value and suit constructor
   //
   public Card(Value v, Suit s)
   {
      this.set(v, s);

   } // end constructor

   
   // ***************************************
   // 
   //    standard methods
   //
   // ***************************************
   
   // convert class to string for display 
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
      if(errorFlag)
         return "[INVALID]";
      else
         return value + " " + suit;
      
   } // end toString

   // ***************************************
   // 
   //    --- Accessors and Mutators  -----
   //
   // ***************************************
   //
   //------ Accessors -------------
   //
   //
   public Suit getSuit()
   {
      
      return suit;
      
   } // end getSuit


   public Value getValue()
   {
      
      return value;
      
   } // end getValue 

   public Boolean getErrorFlag()
   {
      
      return errorFlag;
      
   } // end getErrorFlag

   //
   //------ Mutators -------------
   //
   //
   //
   //------ Mutator to set value and suit using char and enum
   //       (overloaded)
   public Boolean set(char v, Suit s)
   {
      errorFlag = false;
      //
      // validate and assign Suit 
      //
      switch (s)
      {
      case clubs:
      case diamonds:
      case hearts:
      case spades:
         this.suit = s;
         break;
         
      default:
         this.suit = s;
         errorFlag = true;
         break;   
      }// end switch s
      
      
      //
      // validate and assign value
      //
      switch (v)
      {
      case 'A':
         this.value = Value.ace;
         break;
      case '2':
         this.value = Value.two;
         break;
      case '3':
         this.value = Value.three;
         break;
      case '4':
         this.value = Value.four;
         break;
      case '5':
         this.value = Value.five;
         break;
      case '6':
         this.value = Value.six;
         break;
      case '7':
         this.value = Value.seven;
         break;
      case '8':
         this.value = Value.eight;
         break;
      case '9':
         this.value = Value.nine;
         break;
      case 'T':
         this.value = Value.ten;
         break;
      case 'J':
         this.value = Value.jack;
         break;
      case 'Q':
         this.value = Value.queen;
         break;
      case 'K':
         this.value = Value.king;
         break;
         
      default:
         errorFlag = true;
         break;  
      } // switch v
      
      return errorFlag;
   } // end set (char, enum)

   //
   //------ Mutator to set value and suit using enums
   //       (overloaded)
   public Boolean set(Value v, Suit s)
   {
      errorFlag = false;

      switch (s)
      {
      case clubs:
      case diamonds:
      case hearts:
      case spades:
         this.suit = s;
         break;
      
      default:
         this.suit = s;
         errorFlag = true;
         break;   
      } // end switch

      switch (v)   
      {
      case ace:
      case two:
      case three:
      case four:
      case five:
      case six:
      case seven:
      case eight:
      case nine:
      case ten:
      case jack:
      case queen:
      case king:
         this.value = v;
         break;


      default:
         errorFlag = true;
         break;  
      } // end switch

      return errorFlag;
   } // end set (enum, enum)



}  // end card

