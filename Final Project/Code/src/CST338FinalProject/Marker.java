package CST338FinalProject;
/*
 * Subject: CST338 
 * Student: Clarence Mitchell
 * Assignment: Final Project 
 * ClassName: Marker.java
 * 
 *
 * Description
 * Class Marker represents a mark on the tic-tac-toe board
 * It has private members to hold the value. It also
 * has methods to validate and set these data members.
 * 
 */

public class Marker  implements Comparable
{
   //
   //  Enumerators
   //
   // The three standard values are supported.
   public enum Marks {emptyValue, XValue, OValue};

   //
   //  ----- Private Attributes  ---
   //

   private int value;
   private Marks marker;

   //
   //  ----- Public Attributes  ---
   //
   //validCardValues holds values that a card is allowed to be.
   public static char[] validMarkerValues = {'E', 'X', 'O'};
   public static char[] valueRanks = validMarkerValues;


   //*************************************************
   //*
   //* Constructors
   //*
   //*************************************************
   /* 
    * Marker()
    * In: Nothing
    * Out: Nothing
    * Description: This is a default constructor that takes no values. 
    *              It will create a empty Value.
    *              
    */
   public Marker()
   {
      this.set(Marks.emptyValue);
   } // end Marker()

   /* 
    * Marker(Marks)
    * In: A char representing the mark value, a
    * Out: Nothing
    * Description: This is a constructor that takes a value for the Marker
    *              This will create a Marker of the specified value
    *              
    */
   
   public Marker(Marks mark)
   {
      this.set(mark);
      String strRanks = new String(valueRanks);
      value = strRanks.indexOf(this.getValue());

   } // end Marker(mark)


   //*************************************************
   //*
   //* assessor and mutators
   //*
   //*************************************************

   //
   // -- Assessors
   //
   /* 
    * char getValue()
    * In: Nothing
    * Out: A char holding the card's value.
    * Description: This is an assessor for the card's value.
    * 
    */
   public char getValue() 
   {
      return validMarkerValues[value];
   } // end getValue

   /* 
    * Marks getMark()
    * In: Nothing
    * Out: A mark value.
    * Description: This is an assessor for the Marker's value.
    * 
    */
  public Marks getMark()
   {
      return this.marker;
   } // end getMark()

   //
   // --- Mutators
   //
   /* 
    * boolean set(mark)
    * In: a mark to be set
    * Out: none
    * Description: This set's the Maker's value
    * 
    */
   public void set(Marks mark)
   {
      this.marker = mark;
   } // end set()

   //*************************************************
   //*
   //* public class methods
   //*
   //*************************************************

   /* 
    * CompareTo 
    * In: An object
    * Out: An int indicating if the object is less than, greater than, or equal
    *      to the object performing the comparison.
    *      
    */
   public int compareTo(Object t)
   {
      Marker m = (Marker) t;

      if (t.getClass() != this.getClass())
         return 1;

      String strRanks = new String(valueRanks);
      if (strRanks.indexOf(m.getValue()) < 0)
         return 1;
      if (strRanks.indexOf(m.getValue()) < strRanks.indexOf(this.getValue()))
         return 1;
      if (strRanks.indexOf(m.getValue()) == strRanks.indexOf(this.getValue()))
         return 0;
      if (strRanks.indexOf(m.getValue()) > strRanks.indexOf(this.getValue()))
         return -1;
      return 1;
   } // end compareTo()

   /* 
    * String toString()
    * In: Nothing
    * Out: A String object containing the value of mark,
    * Description: This returns the Makrs's value to the caller in String form.
    * 
    */
   public String toString()
   {
      String returnValue = "";

      if (marker != Marks.emptyValue)
      {
         returnValue =  String.valueOf(this.getValue());
      } // end if


      return returnValue;
   } // end toString()

   /* 
    * boolean equals
    * In: A Marker object
    * Out: A boolean value. True if the Markers are equal, false if otherwise.
    * 
    */
   public boolean equals(Marker m)
   {
      if (this.getValue() == m.getValue())
         return true;

      return false;
   } // end equals()

} // end Marker Class
