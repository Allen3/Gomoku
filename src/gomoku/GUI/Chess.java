/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author Allen Young
 */
public class Chess extends Circle {
    private static double RADIUS = 23.0f;
    
    private final int numOfRows;
    private final int numOfColumns;
    private final int gridInterval;
    private final Paint filledColor;

    public Chess(int numOfRows, int numOfColumns, int gridInterval, 
            Paint filledColor) {
        
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.gridInterval = gridInterval;
        this.filledColor = filledColor;
        this.setRadius(RADIUS);
        
        //TO-DO        
        
    }   //Chess()
                           
}   //Chess
