/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
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
    
    private final static double PADSPACE = 20.0f;
    
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
                                
        this.deskWidth = ((double) (numOfColumns * gridInterval)) + PADSPACE * 2;
        this.deskHeight = ((double) (numOfRows * gridInterval)) + PADSPACE * 2;
                
        this.setPrefSize(deskWidth, deskHeight);
        this.setMaxSize(deskWidth, deskHeight);
        this.autosize();
        
        grid = gridGenerate();
        
        this.getChildren().add(grid);       
        
//TEST        
        setChess(0);
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

    /**
     * Set chess on desk.
     */
    public void setChess(int player) {                                
        // Create the chess.
        Circle chess = new Circle();
        chess.setRadius(20.0f);        
        
        this.getChildren().add(chess);        
        
        // Hide the cursor.
        this.setCursor(Cursor.NONE);
        
        this.setOnMouseMoved((MouseEvent mouseEvent) -> {
            double newTranslateX = mouseEvent.getSceneX();
            double newTranslateY = mouseEvent.getSceneY();
            
            chess.setTranslateX(newTranslateX);
            chess.setTranslateY(newTranslateY);
        });
                
        this.setOnMouseReleased((MouseEvent mouseEvent) -> {
            double newTranslateX = mouseEvent.getSceneX();
            double newTranslateY = mouseEvent.getSceneY();
            
            int col = (int) ((newTranslateX - PADSPACE) / gridInterval);
            int row = (int) ((newTranslateY - PADSPACE) / gridInterval);                        
            
            Point2D target = checkAround(newTranslateX, newTranslateY , row, col);
            
            if (target != null) {
                chess.setTranslateX(target.getX());
                chess.setTranslateY(target.getY());
                
                chess.setDisable(true);
            }   
            
        });
        
    }   //setChess()

    /**
     * 
     * @param newTranslateX 
     * @param newTranslateY
     * @param row 
     * @param col
     * @return 
     */
    private Point2D checkAround(double newTranslateX, double newTranslateY, int row, int col) {
        
        Point2D selfPoint = new Point2D(PADSPACE + col * gridInterval, PADSPACE + row * gridInterval);
        Point2D rightPoint = new Point2D(PADSPACE + gridInterval + col * gridInterval, PADSPACE + row * gridInterval);
        Point2D downPoint = new Point2D(PADSPACE + col * gridInterval , PADSPACE + gridInterval + row * gridInterval);
        Point2D rightDownPoint = new Point2D(PADSPACE + gridInterval + col * gridInterval,
                PADSPACE + gridInterval + row * gridInterval);
        
        double minDistance = 9999.0f;
        Point2D minDistancePoint = null;
        
        for (final Point2D point2D : new Point2D[] {selfPoint, rightPoint, downPoint, rightDownPoint}) {
            if (point2D.getX() + PADSPACE <= deskWidth && 
                    point2D.getY() + PADSPACE <= deskHeight) {
                
                double distance = point2D.distance(newTranslateX, newTranslateY);
                if (distance < minDistance) {
                    minDistance = distance;
                    minDistancePoint = point2D;                    
                }                                
            }
                        
        }   
        
        //To-do
        // set a min-range distance to mimic an action of attach.
        
        return minDistancePoint;        
    }   //checkAround
   
    
}   //Desk
