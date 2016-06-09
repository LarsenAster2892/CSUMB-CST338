package CST338FinalProject;
/*
 * Subject: CST338 
 * Student: Clarence Mitchell
 * Assignment: Final Project 
 * ClassName: ViewInterface.java
 * 
 *
 * Description
 * Interface for view -- 
 * 
 */
public interface ViewInterface 
{
   public void playerMove(int position, char player);
   public void gameTie();
   public void gameWon(char winner);
}
