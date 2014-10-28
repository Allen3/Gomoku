/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

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
    
    protected final static double PADSPACE = 20.0f;
    
    protected final int numOfRows;
    protected final int numOfColumns;
    protected final int gridInterval;
    
    protected final double deskWidth;
    protected final double deskHeight;
    
    private Path grid = null;
    
    public Desk(int numOfRows, int numOfColumns, int gridInterval) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.gridInterval = gridInterval;
                                
        this.deskWidth = ((double) (numOfColumns * gridInterval)) + PADSPACE * 2;
        this.deskHeight = ((double) (numOfRows * gridInterval)) + PADSPACE * 2;
                
        this.setPrefSize(deskWidth, deskHeight);
        this.setMaxSize(deskWidth, deskHeight);
        this.autosize();
        
        grid = gridGenerate();
        
        this.getChildren().add(grid);       
        
        this.getChildren().add(new Chess(this, 20.0f));
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
                   new MoveTo(PADSPACE, PADSPACE + row * gridInterval),
                   new LineTo(PADSPACE + numOfColumns * gridInterval, PADSPACE + row * gridInterval)
           );           
       }
       
       // Create vertical grid lines.
       for (int col = 0;col <= numOfColumns;col ++) {
           grid.getElements().addAll(
                   new MoveTo(PADSPACE + col * gridInterval, PADSPACE),
                   new LineTo(PADSPACE + col * gridInterval, PADSPACE + numOfRows * gridInterval)
           );
       }
        
       return grid;
    }   //gridGenerate()                       
      
}   //Desk
