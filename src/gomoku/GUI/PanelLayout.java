package gomoku.GUI;

import javafx.scene.Group;
import javafx.scene.Parent;

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
    
    private final int numOfRows;
    private final int numOfColumns;
    private final int gridInterval;
    
    private Desk desk;

    public PanelLayout() {
        this.numOfRows = 10;
        this.numOfColumns = 10;
        this.gridInterval = 50;
        
        desk = new Desk(numOfRows, numOfColumns, gridInterval);
        
        this.root = desk;
    }   //panel()

    public Parent getRoot() {
        return root;
    }   //getRoot()           
    
}   //PanelLayout
