/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import static gomoku.GUI.Desk.PADSPACE;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 * The chess to be settled on the Desk.
 * 
 * @author allen
 */
public class Chess extends Circle {
    private final Desk desk;
    
    private final int numOfRows;
    private final int numOfColumns;
    private final int gridInterval;
    
    private final double deskWidth;
    private final double deskHeight;

    public Chess(Desk desk, double radius) {
        this.desk = desk;
        
        this.numOfRows = desk.numOfRows;
        this.numOfColumns = desk.numOfColumns;
        this.gridInterval = desk.gridInterval;
        this.deskWidth = desk.deskWidth;
        this.deskHeight = desk.deskHeight;
        
        this.setRadius(radius);
        
        // Hide the cursor.
        this.setCursor(Cursor.NONE);
        
        this.setOnMouseMoved((MouseEvent mouseEvent) -> {
            double newTranslateX = mouseEvent.getSceneX();
            double newTranslateY = mouseEvent.getSceneY();
            
            this.setTranslateX(newTranslateX);
            this.setTranslateY(newTranslateY);
                        
        });                
                
        this.setOnMouseReleased((MouseEvent mouseEvent) -> {
            double newTranslateX = mouseEvent.getSceneX();
            double newTranslateY = mouseEvent.getSceneY();
            
            int col = (int) ((newTranslateX - PADSPACE) / gridInterval);
            int row = (int) ((newTranslateY - PADSPACE) / gridInterval);                        
            
            Point2D target = checkAround(newTranslateX, newTranslateY , row, col);
            
            if (target != null) {
                this.setTranslateX(target.getX());
                this.setTranslateY(target.getY());
                
                this.setDisable(true);
            }   
            
        });
        
    }   //Chess()
    
    /**
     * 
     * @param newTranslateX 
     * @param newTranslateY
     * @param row 
     * @param col
     * @return 
     */
    private Point2D checkAround(double newTranslateX, double newTranslateY, int row, int col) {
        
        Point2D selfPoint = new Point2D(Desk.PADSPACE + col * gridInterval, Desk.PADSPACE + row * gridInterval);
        Point2D rightPoint = new Point2D(Desk.PADSPACE + gridInterval + col * gridInterval, Desk.PADSPACE + row * gridInterval);
        Point2D downPoint = new Point2D(Desk.PADSPACE + col * gridInterval , Desk.PADSPACE + gridInterval + row * gridInterval);
        Point2D rightDownPoint = new Point2D(Desk.PADSPACE + gridInterval + col * gridInterval,
                Desk.PADSPACE + gridInterval + row * gridInterval);
        
        double minDistance = 9999.0f;
        Point2D minDistancePoint = null;
        
        for (final Point2D point2D : new Point2D[] {selfPoint, rightPoint, downPoint, rightDownPoint}) {
            if (point2D.getX() + Desk.PADSPACE <= deskWidth && 
                    point2D.getY() + Desk.PADSPACE <= deskHeight) {
                
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
    
    
}   //Chess
