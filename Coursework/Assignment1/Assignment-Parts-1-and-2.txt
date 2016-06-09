import java.util.Scanner;
/*
 * Subject: CST338 
 * Student: Clarence Mitchell
 * Assignment: 1 
 * ClassName: Assignment1.java
 * 
 *
 * Description
 * This assignment part 1 of 2 and gets the user's first and last name
 * then prints the full name, length, 
 * and the full name as upper case & lower case
 */

public class Assignment1
{
   //
   //  Constants
   //
   public static final char SINGLE_SPACE = ' '; 

   public static void main(String[] args)
   {
      //
      // -- Variables types from import
      //
      Scanner userInput = new Scanner(System.in);
      
      //
      // Standard variable type declaration
      //
      String firstName, lastName, fullName;
      int fullNameLength = 0;
      
      //
      //  Prompt user and get input 
      //
      System.out.println("Enter your First and Last at the each propmpt");
      System.out.println("Please capitalize the first letter of each name");     
      System.out.println();
      
      System.out.print("Enter your first name:");
      firstName = userInput.next();
      
      System.out.print("Enter your last name:");
      lastName = userInput.next();
      
      //
      //  concatenate the first and last name with space and print out
      //  note: SINGLE_SPACE is constant
      //
      fullName = firstName + SINGLE_SPACE + lastName;
      fullNameLength = fullName.length();
      
      System.out.println();
      System.out.print("Your Full Name is : ");
      System.out.println(fullName);
      
      System.out.print("It length is: ");
      System.out.println(fullNameLength);
      
      //
      //  Print out the Upper and Lower case using convert functions
      //
      System.out.print("All UPPERCASE Full Name is : ");
      System.out.println(fullName.toUpperCase());

      System.out.print("All lowercase Full Name is : ");
      System.out.println(fullName.toLowerCase());

   } // End of main

}

/********************* Output *******************************
 * 
 *  Note: the output has been moved over a column below for alignment
 *  
 * ---- Run 1 --- 
 * 
 Enter your First and Last at the each propmpt
 Please capitalize the first letter of each name

 Enter your first name:Clarence
 Enter your last name:Mitchell

 Your Full Name is : Clarence Mitchell
 It length is: 17
 All UPPERCASE Full Name is : CLARENCE MITCHELL
 All lowercase Full Name is : clarence mitchell
 
 *
 * ---- Run 2 ---
 * 
 Enter your First and Last at the each propmpt
 Please capitalize the first letter of each name

 Enter your first name:Bobby
 Enter your last name:Dylan

 Your Full Name is : Bobby Dylan
 It length is: 11
 All UPPERCASE Full Name is : BOBBY DYLAN
 All lowercase Full Name is : bobby dylan

  
 ************************************************************/


import java.util.Scanner;
import java.text.DecimalFormat;
/*
 * Subject: CST338 
 * Student: Clarence Mitchell
 * Assignment: 2 
 * ClassName: Assignment2.java
 * 
 *
 * Description
 * This assignment part 2 of 2.  It prints minimum and maximum range for data
 * value entry then gets the user input for hours spent for the week. 
 * 
 */

public class Assignment2
{
   //
   //  Constants
   //

   public static final double MIN_HOURS = 12; 
   public static final double MAX_HOURS = 20;
  
   
   public static void main(String[] args)
   {
      
      //
      // -- Variables types from import
      //
      Scanner userInput = new Scanner(System.in);
      DecimalFormat hoursFormat = new DecimalFormat("##0.0");
      
      //
      // Standard variable type declaration
      //
      double weeklyHours = 0;
      
      //
      //  Prompt user and get input 
      //
      System.out.println("The weekly hours spent for CST338");     
      System.out.printf("range from %4.2f to %4.2f \n", MIN_HOURS, MAX_HOURS);
      System.out.println();

      System.out.print("Enter your hours spent this week to 3 decimal places:");
      weeklyHours = userInput.nextDouble();
      
      
      //
      //  Output (echo) users input rounding to 1 decimal place
      //     
      System.out.println();
      
      System.out.printf("You said you have spent %s hours this week ",
            hoursFormat.format(weeklyHours));
      System.out.println();
      
 
   } // End of main
     
}

/********************* Output *******************************
 * 
 *  Note: the output has been moved over a column below for alignment
 *  
 * ---- Run 1 --- 
 * 
 The weekly hours spent for CST338
 range from 12.00 to 20.00 

 Enter your hours spent this week to 3 decimal places:18.425

 You said you have spent 18.4 hours this week 
 
 *
 * ---- Run 2 ---
 * 
 The weekly hours spent for CST338
 range from 12.00 to 20.00 

 Enter your hours spent this week to 3 decimal places:12

 You said you have spent 12.0 hours this week 

  
 ************************************************************/

