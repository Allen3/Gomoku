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
public class PanelLayout {
        
    private final Parent root;
    
    private final int numOfRows_Desk;
    private final int numOfColumns_Desk;
    private final int gridInterval_Desk;
    
    private Desk desk;

    public PanelLayout() {
        this.numOfRows_Desk = 10;
        this.numOfColumns_Desk = 10;
        this.gridInterval_Desk = 50;
        
        desk = new Desk(numOfRows_Desk, numOfColumns_Desk, gridInterval_Desk);
                
        this.root = desk;
                
    }   //panel()
        

    public Parent getRoot() {
        return root;
    }   //getRoot()              
    
}   //PanelLayout
