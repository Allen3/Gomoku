package gomoku;

import gomoku.GUI.Desk;
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
    
    private final int numOfRows_Desk;
    private final int numOfColumns_Desk;
    private final int gridInterval_Desk;
            
    private final Desk desk;            

    public RootLayout(Gomoku mainApp) {
        this.mainApp = mainApp;

        this.numOfRows_Desk = 9;
        this.numOfColumns_Desk = 9;
        this.gridInterval_Desk = 50;                
        
        desk = new Desk(numOfRows_Desk, numOfColumns_Desk, gridInterval_Desk, mainApp);
                
        this.root = desk;      
        
        desk.getDeskController().setOffensivePlayer(Player.PLAYER_A);
    }   //panel()
        

    public Parent getRoot() {
        return root;
    }   //getRoot()              
    
}   //RootLayout
