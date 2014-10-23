/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * The desk displaying the game.
 * 
 * @author allen
 */
public class Desk extends Pane {
    
    private final static double padSpace = 20.0f;
    
    private final int numOfRows;
    private final int numOfColumns;
    private final int gridInterval;
    
    private final double deskWidth;
    private final double deskHeight;
    
    private Path grid = null;
    
    public Desk(int numOfRows, int numOfColumns, int gridInterval) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.gridInterval = gridInterval;
                                
        this.deskWidth = ((double) (numOfColumns * gridInterval)) + padSpace * 2;
        this.deskHeight = ((double) (numOfRows * gridInterval)) + padSpace * 2;
                
        this.setPrefSize(deskWidth, deskHeight);
        this.setMaxSize(deskWidth, deskHeight);
        this.autosize();
        
        grid = gridGenerate();
        
        this.getChildren().add(grid);
    }   //Desk()

    /**
     * 
     * @return The Grid to lay the chess on.
     */
    private Path gridGenerate() {
       Path grid = new Path();
       
       // Create horizontal grid lines.
       for (int row = 0;row <= numOfRows; row ++) {
           grid.getElements().addAll(
                   new MoveTo(padSpace, padSpace + row * gridInterval),
                   new LineTo(padSpace + numOfColumns * gridInterval, padSpace + row * gridInterval)
           );           
       }
       
       // Create vertical grid lines.
       for (int col = 0;col <= numOfColumns;col ++) {
           grid.getElements().addAll(
                   new MoveTo(padSpace + col * gridInterval, padSpace),
                   new LineTo(padSpace + col * gridInterval, padSpace + numOfRows * gridInterval)
           );
       }
        
       return grid;
    }   //gridGenerate()                       
    
}   //Desk
