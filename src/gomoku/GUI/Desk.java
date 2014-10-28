/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * The desk displaying the game.
 * 
 * @author allen
 */
public class Desk extends Pane {
    
    protected final static double PADSPACE = 50.0f;
    protected final static double CHESSMANRADIUS = 20.0f;
            
    protected final int numOfRows;
    protected final int numOfColumns;
    protected final int gridInterval;
    
    protected final double deskWidth;
    protected final double deskHeight;
    
    private Path grid = null;
        
    private final ObservableIntegerArray observableIntegerArray;
    
    public Desk(int numOfRows, int numOfColumns, int gridInterval) {
        observableIntegerArray = FXCollections.observableIntegerArray();    
        observableIntegerArray.resize((numOfRows + 1) * (numOfColumns + 1));
        
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.gridInterval = gridInterval;
                                
        this.deskWidth = ((double) (numOfColumns * gridInterval)) + PADSPACE * 2;   //600.0f
        this.deskHeight = ((double) (numOfRows * gridInterval)) + PADSPACE * 2;     //600.0f
                
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
    
    
    public void setChessman(int player) {
        Circle chessman = new Circle();
        this.getChildren().add(chessman);
        
        chessman.setRadius(CHESSMANRADIUS);
        
        this.setCursor(Cursor.NONE);
        
        this.setOnMouseMoved((MouseEvent mouseEvent) -> {
            double newTranslateX = mouseEvent.getSceneX();
            double newTranslateY = mouseEvent.getSceneY();
            
            chessman.setTranslateX(newTranslateX);
            chessman.setTranslateY(newTranslateY);
                        
        });                
                
        this.setOnMouseReleased((MouseEvent mouseEvent) -> {
            double newTranslateX = mouseEvent.getSceneX();
            double newTranslateY = mouseEvent.getSceneY();
            
            int col = (int) ((newTranslateX - PADSPACE) / gridInterval);
            int row = (int) ((newTranslateY - PADSPACE) / gridInterval);                                                         
            
            Point2D targetPos = checkAround(newTranslateX, newTranslateY , col, row);
            
            if (targetPos != null) {
                chessman.setTranslateX(targetPos.getX() * gridInterval + PADSPACE);
                chessman.setTranslateY(targetPos.getY() * gridInterval + PADSPACE);
                     
                observableIntegerArray.set(row * (numOfColumns + 1) + col, 1);                
                //this.setDisable(true);                
            }   
            
        });
        
        
        
    }   //setChessman()
    
    /**
     * 
     * @param newTranslateX 
     * @param newTranslateY
     * @param row 
     * @param col
     * @return The position(column and row) where the chessman should lay, null indicates that the chessman
     * was not attached to any position.
     */
    private Point2D checkAround(double newTranslateX, double newTranslateY, int col, int row) {
                                       
        Point2D selfPos = new Point2D(col, row);
        Point2D rightPos = new Point2D(col + 1, row);
        Point2D downPos = new Point2D(col, row + 1);
        Point2D rightDownPos = new Point2D(col + 1, row + 1);
        
        double minDistance = 9999.9f;
        Point2D minDistancePos = null;
        
        for (final Point2D pos : new Point2D[] {selfPos, rightPos, downPos, rightDownPos}) {
            Point2D point = new Point2D(pos.getX() * gridInterval + PADSPACE, 
                    pos.getY() * gridInterval + PADSPACE);
            if ((point.getX() + PADSPACE <= deskWidth) && 
                    (point.getY() + PADSPACE <= deskHeight)) {
                
                double distance = point.distance(newTranslateX, newTranslateY);
                if (distance < minDistance) {
                    minDistance = distance;
                    minDistancePos = pos;
                }
                
            }
        }   
        
        //To-do
        // set a min-range distance to mimic an action of attach.
        
        return minDistancePos;        
    }   //checkAround
}   //Desk
