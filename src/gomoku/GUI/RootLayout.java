package gomoku.GUI;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author allen
 */
public class RootLayout {
        
    private final Parent root;
    
    private final int numOfRows_Desk;
    private final int numOfColumns_Desk;
    private final int gridInterval_Desk;
    
    private Desk desk;

    public RootLayout() {
        this.numOfRows_Desk = 9;
        this.numOfColumns_Desk = 9;
        this.gridInterval_Desk = 50;
        
        desk = new Desk(numOfRows_Desk, numOfColumns_Desk, gridInterval_Desk);
                
        this.root = desk;
        
        desk.setChessman(0);        
                
    }   //panel()
        

    public Parent getRoot() {
        return root;
    }   //getRoot()              
    
}   //RootLayout
