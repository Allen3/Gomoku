/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import gomoku.Gomoku;
import gomoku.RootLayoutController;
import gomoku.util.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * The desk displaying the game.
 * 
 * @author allen
 */
public class Desk extends Pane {
    private final Gomoku mainApp;
    private final RootLayoutController rootLayoutController;
    private final DeskController deskController;
    
    protected final static double PADSPACE = 50.0f;
    protected final static double CHESSMANRADIUS = 20.0f;
    protected final static double UNIVERSAL_INDEXLABELBIAS = 30.0f;
    protected final static double ROW_INDEXLABELBIAS = 4.0f;
    protected final static double COL_INDEXLABELBIAS = 8.0f;
            
    protected final int numOfRows;
    protected final int numOfColumns;
    protected final int gridInterval;
    
    protected final double deskWidth;
    protected final double deskHeight;
    
    private Path grid = null;
    private ObservableList<Label> indexLabelList = null;

    /**
     * This constructor will instantiate Desk, and help instantiate a new DeskController.
     *
     * @param numOfRows The number of row grid lines.
     * @param numOfColumns The number of column grid lines.
     * @param gridInterval The value of grid interval.
     * @param mainApp The Gomoku mainApp reference.
     */
    public Desk(int numOfRows, int numOfColumns, int gridInterval, Gomoku mainApp, RootLayoutController rootLayoutController) {
        this.mainApp = mainApp;
        this.rootLayoutController = rootLayoutController;

        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.gridInterval = gridInterval;

        // Calculate the size of this desk and then set.
        this.deskWidth = ((double) (numOfColumns * gridInterval)) + PADSPACE * 2;   //600.0f
        this.deskHeight = ((double) (numOfRows * gridInterval)) + PADSPACE * 2;     //600.0f
                
        this.setPrefSize(deskWidth, deskHeight);
        this.setMaxSize(deskWidth, deskHeight);
        this.autosize();

        // Add the grid lines to the desk.
        grid = gridGenerate();
        this.getChildren().add(grid);

        indexLabelList = indexLabelListGenerate();
        this.getChildren().addAll(indexLabelList);

        // deskController should be instantiated after the Desk instantiated.
        deskController = new DeskController(this, mainApp);
    }   //Desk()

    private ObservableList<Label> indexLabelListGenerate() {
        indexLabelList = FXCollections.observableArrayList();

        // Create vertical index labels.
        for (int row = 0;row <= numOfRows;row ++) {
            Label rowLabel = new Label(Integer.toString(row));
            rowLabel.setTranslateX(PADSPACE - UNIVERSAL_INDEXLABELBIAS);
            rowLabel.setTranslateY(PADSPACE + row * gridInterval - COL_INDEXLABELBIAS);
            indexLabelList.add(rowLabel);
        }

        // Create horizontal index labels.
        for (int col = 0;col <= numOfRows;col ++) {
            Label colLabel = new Label(Integer.toString(col));
            colLabel.setTranslateX(PADSPACE + col * gridInterval - ROW_INDEXLABELBIAS);
            colLabel.setTranslateY(PADSPACE - UNIVERSAL_INDEXLABELBIAS);
            indexLabelList.add(colLabel);
        }

        return indexLabelList;
    }   //indexLabelListGenerate()

    /**
     * 
     * @return The Grid to lay the chess on.
     */
    private Path gridGenerate() {
       Path grid = new Path();
       
       // Create horizontal grid lines.
       for (int row = 0;row <= numOfRows;row ++) {
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
     * Set the desk as an active state.
     */
    public void setActive() {
        this.setDisable(false);
        //this.setEffect(new DropShadow());
        //this.toFront();
    }   //setActive()
    
    /**
     * Set the chess as an inactive state.
     */
    public void setInactive() {
        this.setDisable(true);
        //this.setEffect(null);
        //this.toBack();
    }   //setInactive()

    /**
     * Set the actions of putting new chessman to the desk.
     * @param player The player who's turn to lay the new chessman.
     */
    public void setChessman(Player player) {
        // Add a new chessman to the desk.
        Chessman chessman = new Chessman(CHESSMANRADIUS, player);
        this.getChildren().add(chessman);                

        // Set the cursor hovered on the desk when a new chessman is ready to be laid.
        this.setCursor(Cursor.NONE);

        // Set the chessman follow the mouse.
        this.setOnMouseMoved((MouseEvent mouseEvent) -> {
            double newTranslateX = mouseEvent.getSceneX();
            double newTranslateY = mouseEvent.getSceneY();
            
            chessman.setTranslateX(newTranslateX);
            chessman.setTranslateY(newTranslateY);
                        
        });                

        // Set the chessman laid at the point where grid lines cross and make some changes to the background data correspondingly.
        this.setOnMouseReleased((MouseEvent mouseEvent) -> {
            double newTranslateX = mouseEvent.getSceneX();
            double newTranslateY = mouseEvent.getSceneY();
            
            int col = (int) ((newTranslateX - PADSPACE) / gridInterval);
            int row = (int) ((newTranslateY - PADSPACE) / gridInterval);

            // Get the grid line point where the chessman to be laid.
            Point2D targetPos = checkAround(newTranslateX, newTranslateY , col, row);

            // Check the new position and make some changes to background data if possible.
            if (targetPos != null) {
                int coordinate = (int) (targetPos.getY() * (numOfColumns + 1) + targetPos.getX());
                
                if (deskController.isSettable(coordinate) == true) {
                    
                    chessman.setTranslateX(targetPos.getX() * gridInterval + PADSPACE);
                    chessman.setTranslateY(targetPos.getY() * gridInterval + PADSPACE);
                                        
                    deskController.getChessmanCoordinateMap().put(coordinate, chessman);
                    
                    // We should make the change to observable variable at last.
                    deskController.getChessmanCoordinateArray().set(coordinate, player.getId());
                }
            }   
            
        });            
    }   //setChessman()            
    
    /**
     * Check the point where the new chessman to be laid more accurately.
     * In other words, make the chessman finally laid at grid lines, instead of the intervals of grid lines.
     *
     * @param newTranslateX 
     * @param newTranslateY
     * @param row 
     * @param col
     * @return The position(column and row) where the chessman should lay, {@code null} indicates that the chessman
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
    
    public int getNumOfRows() {
        return numOfRows;
    }   //getNumOfRows()

    public int getNumOfColumns() {
        return numOfColumns;
    }   //getNumOfColumns()
    
    public DeskController getDeskController() {
        return deskController;
    }   //getDeskController()
}   //Desk
