package cardGame.view;

import javax.swing.JPanel;



import cardGame.controller.cardGameAppController;

public class cardTablePanel extends JPanel
{
   private cardGameAppController baseController;
   
   public cardTablePanel(cardGameAppController baseController)
   {
      this.baseController = baseController;
      
   }
}
