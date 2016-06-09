package CST338FinalProject;
/*
 * Subject: CST338 
 * Student: Clarence Mitchell
 * Assignment: Final Project 
 * ClassName: GuiMarker.java
 * 
 *
 * Description
 * Class GuiMarker sub-controller for the Marker class
 * It is used to transport the Icons
 * 
 */
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class GUIMarker
{
   //
   //  ----- Private Attributes  ---
   //
   
   private static final char EMPTY_MARK = 'E';

   //Holds a Marker icon for each valid board square.
   private static Icon[] iconMarkers = new ImageIcon[3];
   private static Icon iconBlank;
   private static boolean iconsLoaded = false;
   private static String iconFolderPath = "./images";
   
   //*************************************************
   //*
   //* Constructors
   //*
   //*************************************************
   /* Marker()
    * In: Nothing
    * Out: Nothing
    * Description: This is a default constructor that takes no values. 
    *              It will create a empty Value.
    *              
    */
   public GUIMarker()
   {
      //Load all of the mark icons if they haven't been already.
      if (!GUIMarker.iconsLoaded)
         GUIMarker.loadMarkIcons();

   } // end Marker()

   //*************************************************
   //*
   //* public class methods
   //*
   //*************************************************

   /* 
    * Icon getIcon
    * In: A symbol value that the caller wants an image for
    * Out: An Icon containing the image
    * 
    */
   public static Icon getIcon(char symbol)
   {
      //Load all of the mark icons if they haven't been already.
      if (!GUIMarker.iconsLoaded)
      {
         GUIMarker.loadMarkIcons();
      }
         
      
      //return the appropriate mark icon.
      return iconMarkers[valueAsInt(symbol)];
   } // end getIcon()
   
   /* 
    * void loadMarkIcons
    * In: Nothing
    * Out: Nothing
    * Loads all of the marker icons from the images directory. If the images
    * directory is not in the right place, then the user is prompted to
    * select a directory where the images can be found.
    * 
    */
   private static void loadMarkIcons()
   {
      //If the images folder doesn't exist,
      if (!(new File(GUIMarker.iconFolderPath).exists()))
      {
         //Prompt the user for a valid image folder.
         JOptionPane.showMessageDialog(null, "By deafult ./images/ is "
            + "used to store card icon images, but ./images/ does not "
            + "exist. Press OK to select the folder where card icon "
            + "images are stored. Press cancel in the forthcoming dialog "
            + "window to exit this program.");
         JFileChooser chooser = new JFileChooser(".");
         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         chooser.setMultiSelectionEnabled(false);
         chooser.showDialog(null, "Select");
         File selectedFile = chooser.getSelectedFile();
         
         //Exit the program if a valid image folder is not provided.
         if (selectedFile == null)
            System.exit(0);
         
         GUIMarker.iconFolderPath = selectedFile.getPath();
         System.out.println(iconFolderPath);
      } // end if

      //Load each of the cards into the appropriate position in the
      //iconCards array.
      for (int m = 0; m < Marker.validMarkerValues.length; m++)
      {
             //If a card cannot be loaded, tell the user and exit the
            //application.
            if (!new File(iconFolderPath + "/" + Marker.validMarkerValues[m]
               + ".gif").exists())
            {
               JOptionPane.showMessageDialog(null, Marker.validMarkerValues[m]
                  + ".gif could not be found in the icon"
                  + " folder. Program execution will now stop.");
               System.exit(0);
            } // end if
            
            iconMarkers[m] = new ImageIcon(iconFolderPath + "/"
               + Marker.validMarkerValues[m] + ".gif");

      } // end for loop m
      
      //Load the blank icon. 
      iconBlank =  new ImageIcon(iconFolderPath + "/E.gif");
      
      //Make sure this function is not called again.
      GUIMarker.iconsLoaded = true; 
   } // end loadCardIcons()
   
   /* 
    * int valueAsInt
    * In: A marker object
    * Out: An integer representing the value in icon marker 
    * 
    */
   private static int valueAsInt(char symbol)
   {
      String values = new String(Marker.validMarkerValues);
      int returnValue = values.indexOf(symbol);
      
      if (returnValue < 0)
      {
         returnValue = values.indexOf(EMPTY_MARK);
      }
      return returnValue;
   } // end valueAsInt()
   
   
   /* 
    * Icon getBlankIcon
    * In: Nothing
    * Out: An Icon object containing a blank marker.
    * 
    */
   public Icon getBlankIcon()
   {
      //Load all of the icons if they have not been already.
      if (!GUIMarker.iconsLoaded)
         GUIMarker.loadMarkIcons();
      
      return GUIMarker.iconBlank;
   } // end getBackCardIcon()
} // end class GUICard

