package CST338FinalProject;
/*
 * Subject: CST338 
 * Student: Clarence Mitchell
 * Assignment: Final Project 
 * ClassName: Controller.java
 * 
 *
 * Description
 * Interface for view -- 
 * 
 */
import java.awt.Point;

public class Controller 
{
    Model model;
    private boolean whosGo=false; // SET PLAYER O TO GO FIRST

    // default constructor
    public Controller()
    {
    }
    
    // setting up the controller with a model
    public void setup(Model model)
    {
        this.model = model;
    }
    
    // when a potential move has been made, the controller sends 
    // this information to the model
    public void playermove(Point point)
    {
        model.movePlayer(point);
    } 
} // end Class Controller
