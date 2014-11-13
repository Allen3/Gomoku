/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.business;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;

/**
 * The back-end class to judge the state of the game.
 * @author allen
 */
public class Judger {        
    private DeskController deskController;
    
    private final ObservableIntegerArray observableIntegerArray;    
    
    private final int deskNumOfRows;
    private final int deskNumOfColumns;

    public Judger(DeskController deskController, int numOfRows, int numOfColumns) {
        this.deskController = deskController;
                        
        observableIntegerArray = FXCollections.observableIntegerArray();    
        
        this.deskNumOfRows = numOfRows;
        this.deskNumOfColumns = numOfColumns;
        
        // Assure that the array has no preset value.
        observableIntegerArray.clear();
        observableIntegerArray.resize((numOfRows + 1) * (numOfColumns + 1));
//TEST
        System.out.println("numOfRows = " + numOfRows + " numOfColumns = " + numOfColumns);                
                
        observableIntegerArray.addListener((ObservableIntegerArray observable, boolean sizeChanged, int from, int to) -> {
//TEST         
            System.out.println("from = " + from + " to = " + to);
            
            deskController.getDesk().setInactive();
                                    
            //game continues
            int judgeResult = judge(from);
            if (judgeResult == 0) {                
                deskController.getDesk().setActive();
                
                // A new round begins.
                deskController.roundInc();
                deskController.getDesk().setChessman(deskController.getRoundPlayer());
                                                
            } else {
                System.out.println("Player " + judgeResult + " wins!");
            }
        });        
    }   //Judger()
    
    /**
     * 
     * @param coordinate the coordinate to be judged.
     * @return the player ID for the winner, 0 for game going on.
     */
    private int judge(int coordinate) {        
        int valueOfCoordinate = observableIntegerArray.get(coordinate);
        
        int countOfSameChessman = 1;        
        // Right check.
        for (int i = 1;i < 5;i ++) {
            // Same row and same value.
            if ((((coordinate + i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    observableIntegerArray.get(coordinate - i) == valueOfCoordinate) {
                countOfSameChessman ++;
            } else {
                break;
            }            
        }
        
        // Left check.
        for (int i = 1;i < 5;i ++) {
            // Same row and same vlaue.
            if ((((coordinate - i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    observableIntegerArray.get(coordinate + i) == valueOfCoordinate) {
                countOfSameChessman ++;
            } else {
                break;
            }
        }        
        if (countOfSameChessman >= 5)
            return valueOfCoordinate;
        
        countOfSameChessman = 1;
        // Down check.
        for (int i = 1;i < 5;i ++) {
            // Same column and same value.
            if (((coordinate + i * (deskNumOfColumns + 1)) <= (deskNumOfRows * (deskNumOfRows + 1))) &&
                    observableIntegerArray.get(coordinate + i * (deskNumOfColumns + 1)) == valueOfCoordinate) {
                countOfSameChessman ++;
            } else {
                break;
            }
        }
        
        // Up check.
        for (int i = 1;i < 5;i ++) {
            // Same column and same value.
            if (((coordinate - i * (deskNumOfColumns + 1)) >= 0) &&
                    observableIntegerArray.get(coordinate - i * (deskNumOfColumns + 1)) == valueOfCoordinate) {
                countOfSameChessman ++;
            } else {
                break;
            }
        }
        if (countOfSameChessman >= 5)
            return valueOfCoordinate;
        
        countOfSameChessman = 1;
        // Right-down check.
        for (int i = 1;i < 5;i ++) {
            //To-do
        }
        
        // Left-up check.
        for (int i = 1;i < 5;i ++) {
            //To-do
        }
        if (countOfSameChessman >= 5)
            return valueOfCoordinate;
        
        
        // Game continues, nobody wins.
        return 0;                
    }   //judge()
    
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
               
}   //Judger
