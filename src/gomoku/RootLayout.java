package gomoku;

import gomoku.GUI.Desk;
import gomoku.GUI.DeskController;
import gomoku.util.Player;
import javafx.scene.Parent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author allen
 */
public class RootLayout {
    private final Gomoku mainApp;

    private final Parent root;
    private final Desk desk;
    private final DeskController deskController;

    private final int numOfRows_Desk;       // Number of row grid lines, NOT row grid.
    private final int numOfColumns_Desk;    // Number of column grid lines, NOT column grid.
    private final int gridInterval_Desk;    // Value of grid interval.

    public RootLayout(Gomoku mainApp) {
        this.mainApp = mainApp;

        this.numOfRows_Desk = 9;
        this.numOfColumns_Desk = 9;
        this.gridInterval_Desk = 50;                
        
        desk = new Desk(numOfRows_Desk, numOfColumns_Desk, gridInterval_Desk, mainApp);
                
        this.root = desk;
        deskController = desk.getDeskController();

        deskController.setOffensivePlayer(Player.PLAYER_A);
    }   //panel()
        

    public Parent getRoot() {
        return root;
    }   //getRoot()              
    
}   //RootLayout
