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

    public Judger(DeskController deskController, int numOfRows, int numOfColumns) {
        this.deskController = deskController;
        
        observableIntegerArray = FXCollections.observableIntegerArray();    
        
        // Assure that the array has no preset value.
        observableIntegerArray.clear();
        observableIntegerArray.resize((numOfRows + 1) * (numOfColumns + 1));
//TEST
        System.out.println("numOfRows = " + numOfRows + " numOfColumns = " + numOfColumns);
        
        /*
        observableIntegerArray.addListener((Observable observable) -> {
//TEST
            System.out.println(observable.toString());
            
            deskController.getDesk().setInactive();
                                    
            //game continues
            if (judge(observable) == 0) {                
                deskController.getDesk().setActive();
                
                // A new round begins.
                deskController.roundInc();
                deskController.getDesk().setChessman(deskController.getRoundPlayer());
                                                
            } else {
                System.out.println("Player " + judge(observable) + " wins!");
            }
            
        });
        */
                
        observableIntegerArray.addListener((ObservableIntegerArray observable, boolean sizeChanged, int from, int to) -> {
            System.out.println("from = " + from + " to = " + to);
            
            deskController.getDesk().setInactive();
                                    
            //game continues
            if (judge(observable) == 0) {                
                deskController.getDesk().setActive();
                
                // A new round begins.
                deskController.roundInc();
                deskController.getDesk().setChessman(deskController.getRoundPlayer());
                                                
            } else {
                System.out.println("Player " + judge(observable) + " wins!");
            }
        });        
    }   //Judger()

    private int judge(Observable observableIntegerArray) {
        
        // TO-do
        return 0;
        
        
    }   //judge()
    
    public ObservableIntegerArray getObservableIntegerArray() {
        return observableIntegerArray;
    }   //getObservableIntegerArray()
               
}   //Judger
