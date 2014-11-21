/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.business;

import gomoku.util.Player;
import gomoku.GUI.Chessman;
import gomoku.GUI.ChessmanHighlighter;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;

/** 
 * 
 * @author allen
 * 
 * The back-end class to judge the state of the game.
 */
public class Judger {        
    private DeskController deskController;
    
    private ChessmanHighlighter chessmanHighlighter;
    
    private final ObservableIntegerArray observableIntegerArray;
    private final HashMap<Integer, Chessman> chessmanCoordinateMap;
    private final ArrayList<Integer> winningChessmanList;
    
    private final int deskNumOfRows;
    private final int deskNumOfColumns;

    public Judger(DeskController deskController, int numOfRows, int numOfColumns) {
        this.deskController = deskController;
                        
        observableIntegerArray = FXCollections.observableIntegerArray();
        chessmanCoordinateMap = new HashMap<Integer, Chessman>();
        winningChessmanList = new ArrayList<Integer>();
        
        this.deskNumOfRows = numOfRows;
        this.deskNumOfColumns = numOfColumns;
        
        // Assure that the array has no preset value.
        observableIntegerArray.clear();
        observableIntegerArray.resize((numOfRows + 1) * (numOfColumns + 1));
                
        observableIntegerArray.addListener((ObservableIntegerArray observable, boolean sizeChanged, int from, int to) -> {
            
            deskController.getDesk().setInactive();
            //game continues            
            if (isGameEnd(from) == false) {

                deskController.getDesk().setActive();
                
                // A new round begins.
                deskController.roundInc();
                deskController.getDesk().setChessman(deskController.getRoundPlayer());
                                                
            } else {                                
                highlightWinningChessman();
                System.out.println("Player " + (observableIntegerArray.get(from)) + " wins!");
            }
        });        
    }   //Judger()   
    
    /**
     * 
     * @param coordinate the coordinate where the new chessman was laid.
     * @return true{@code} for end, false{@code} for not.
     */
    private boolean isGameEnd(int coordinate) {
        int coordinateMaxBound = deskNumOfRows * (deskNumOfColumns + 1);
        int coordinateMinBound = 0;
        
        int valueOfCoordinate = observableIntegerArray.get(coordinate);
        
        // Of the same row.        
        winningChessmanList.clear();
        winningChessmanList.add(coordinate);
        // Right check.
        for (int i = 1;i < 5;i ++) {            
            // Same row and same value.
            if ((coordinate + i <= coordinateMaxBound) && (((coordinate + i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) && 
                    observableIntegerArray.get(coordinate + i) == valueOfCoordinate) {                
                winningChessmanList.add(coordinate + i);                
            } else {
                break;
            }            
        }
        
        // Left check.
        for (int i = 1;i < 5;i ++) {
            // Same row and same vlaue.
            if ((coordinate - i >= coordinateMinBound) && (((coordinate - i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    observableIntegerArray.get(coordinate - i) == valueOfCoordinate) {
                winningChessmanList.add(coordinate - i);
            } else {
                break;
            }
        }
        if (winningChessmanList.size() >= 5)
            return true;
        
        
        // Of the same column.
        winningChessmanList.clear();
        winningChessmanList.add(coordinate);
        // Down check.
        for (int i = 1;i < 5;i ++) {
            // Same column and same value.
            if (((coordinate + i * (deskNumOfColumns + 1)) <= coordinateMaxBound) &&
                    observableIntegerArray.get(coordinate + i * (deskNumOfColumns + 1)) == valueOfCoordinate) {
                winningChessmanList.add(coordinate + i * (deskNumOfColumns + 1));
            } else {
                break;
            }
        }
        
        // Up check.
        for (int i = 1;i < 5;i ++) {
            // Same column and same value.
            if (((coordinate - i * (deskNumOfColumns + 1)) >= coordinateMinBound) &&
                    observableIntegerArray.get(coordinate - i * (deskNumOfColumns + 1)) == valueOfCoordinate) {
                winningChessmanList.add(coordinate - i * (deskNumOfColumns + 1));
            } else {
                break;
            }
        }
        if (winningChessmanList.size() >= 5)
            return true;
        
        
        // Of the same principal diagonal.
        winningChessmanList.clear();
        winningChessmanList.add(coordinate);
        // Right-down check.
        for (int i = 1;i < 5;i ++) {           
            // Same principal diagonal and same value.
            if ((((coordinate + i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    ((coordinate + i * (deskNumOfColumns + 1) + i) <= coordinateMaxBound) && 
                    observableIntegerArray.get(coordinate + i * (deskNumOfColumns + 1) + i) == valueOfCoordinate) {
                
                winningChessmanList.add(coordinate + i * (deskNumOfColumns + 1) + i);
            } else {
                break;
            }            
        }
        
        // Left-up check.
        for (int i = 1;i < 5;i ++) {
            // Same principal diagonal and same value.
            if ((((coordinate - i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    ((coordinate - i * (deskNumOfColumns + 1) - i) >= coordinateMinBound) &&
                    observableIntegerArray.get(coordinate - i * (deskNumOfColumns + 1) - i) == valueOfCoordinate) {
                winningChessmanList.add(coordinate - i * (deskNumOfColumns + 1) - i);
            } else {
                break;
            }
        }
        if (winningChessmanList.size() >= 5)
            return true;
        
        
       //Of the same deputy diagonal.
       winningChessmanList.clear();
       winningChessmanList.add(coordinate);
       // Left-down check.
       for (int i = 1;i < 5;i ++) {           
            // Same deputy diagonal and same value.
            if ((((coordinate - i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    ((coordinate + i * (deskNumOfColumns + 1) - i) <= coordinateMaxBound) && 
                    observableIntegerArray.get(coordinate + i * (deskNumOfColumns + 1) - i) == valueOfCoordinate) {
                
                winningChessmanList.add(coordinate + i * (deskNumOfColumns + 1) - i);
            } else {
                break;
            }            
        }
       
       // Right-up check.
        for (int i = 1;i < 5;i ++) {
            // Same deputy diagonal and same value.
            if ((((coordinate + i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    ((coordinate - i * (deskNumOfColumns + 1) + i) >= coordinateMinBound) &&
                    observableIntegerArray.get(coordinate - i * (deskNumOfColumns + 1) + i) == valueOfCoordinate) {
                winningChessmanList.add(coordinate - i * (deskNumOfColumns + 1) + i);
            } else {
                break;
            }
        }
        if (winningChessmanList.size() >= 5)
            return true;
        
        // Game continues, nobody wins.
        return false;               
    }   //isGameEnd()
        
    private void highlightWinningChessman() {           
        ArrayList<Chessman> highlightedChessmanRenderList = new ArrayList<Chessman>();
        for (int coordinate : winningChessmanList) {        

            highlightedChessmanRenderList.add(chessmanCoordinateMap.get(coordinate));     
        }
                
        chessmanHighlighter = new ChessmanHighlighter(highlightedChessmanRenderList);
        chessmanHighlighter.handleHighlighting();        
    }   //highlightWinningChessman()
    
    /**
     * 
     * @param coordinate the coordinate the chessman laid.
     * @return the state whether the chessman is able to be laid there.
     */
    public boolean isSettable(int coordinate) {
        if (observableIntegerArray.get(coordinate) == 0)
            return true;
        return false;
    }   //isSettable()
    
    public ObservableIntegerArray getObservableIntegerArray() {
        return observableIntegerArray;
    }   //getObservableIntegerArray()

    public HashMap<Integer, Chessman> getChessmanCoordinateMap() {
        return chessmanCoordinateMap;
    }   //getChessmanCoordinateMap()                   
   
}   //Judger
