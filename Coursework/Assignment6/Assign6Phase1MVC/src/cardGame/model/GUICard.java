package cardGame.model;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

//******************************//
////
//GUICard Class Definition   //
////
//******************************//
public class GUICard
{
   private static Icon[][] iconCards = new ImageIcon[14][4];
   private static Icon iconBack;
   static boolean iconsLoaded = false;
   static final char[] VALID_SUITS = {'C', 'D', 'H', 'S'};
   private static String iconFolderPath = "./images";

   public static Icon getIcon(Card card)
   {
      if(!GUICard.iconsLoaded)
         GUICard.loadCardIcons();

      return iconCards[valueAsInt(card)][suitAsInt(card)];
   } // end getIcon()

   private static void loadCardIcons()
   {
      if(!(new File(GUICard.iconFolderPath).exists()))
      {
         JOptionPane.showMessageDialog(null, "By deafult ../images/ is used to"
               + " store card icon images, but ../images/ does not exist. Press OK"
               + " to select the folder where card icon images are stored. Press"
               + " cancel in the forthcoming dialog window to exit this program.");

         JFileChooser chooser = new JFileChooser(".");
         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         chooser.setMultiSelectionEnabled(false);
         chooser.showDialog(null, "Select");
         File selectedFile = chooser.getSelectedFile();

         if(selectedFile == null)
            System.exit(0);

         GUICard.iconFolderPath = selectedFile.getPath();
         System.out.println(iconFolderPath);
      } // end if

      for (int i = 0; i < Card.validCardValues.length; i++)
      {
         for (int j = 0; j < VALID_SUITS.length; j++)
         {
            if (!new File(iconFolderPath + "/" + Card.validCardValues[i]
                  + VALID_SUITS[j] + ".gif").exists())
            {
               JOptionPane.showMessageDialog(null, Card.validCardValues[i]
                     + VALID_SUITS[j] + ".gif could not be found in the icon"
                     + " folder. Program execution will now stop.");
               System.exit(0);
            } // end if

            iconCards[i][j] = new ImageIcon(iconFolderPath + "/"
                  + Card.validCardValues[i] + VALID_SUITS[j] + ".gif");
         } // end for loop j
      } // end for loop i

      iconBack = new ImageIcon(iconFolderPath + "/BK.gif");
      GUICard.iconsLoaded = true;
   } // end loadCardIcons()

   private static int valueAsInt(Card card)
   {
      String values = new String(Card.validCardValues);

      return values.indexOf(card.getValue());
   } // end valueAsInt()

   private static int suitAsInt(Card card)
   {
      return card.getSuit().ordinal();
   } // end suitAsInt()

   public static Icon getBackCardIcon()
   {
      if(!GUICard.iconsLoaded)
      {
         GUICard.loadCardIcons();       
      }

      return GUICard.iconBack;
   } // end getBackCardIcon()
} // end class GUICard

