/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.business;

import gomoku.GUI.Desk;
import gomoku.RootLayout;

/**
 *
 * @author allen
 */
public class DeskController {
    private final Judger judger;        
    
    private final Desk desk;
    
    private int round;
    private int offensivePlayer;    
    
    public DeskController(Desk desk) {        
        this.desk = desk;
        
        // Should be assigned here as desk finish being initializing.
        judger = new Judger(this, desk.getNumOfRows(), desk.getNumOfColumns());
                
        
        round = 0;        
    }   //DeskController()
            
    /**
     * 
     * @param player value varies between 1 and 2 which represents the player respectively.
     */
    public void setOffensivePlayer(int offensivePlayer) {
        this.offensivePlayer = offensivePlayer;
        desk.setActive();
        
        round ++;
        desk.setChessman(offensivePlayer);
    }   //offensivePlayer()
    
    /**
     * 
     * @return The player who are supposed to lay the chessman in this round.
     */
    public int getRoundPlayer() {
        if (round % 2 == 0) {
            return offensivePlayer;
        } else {
            return (3 - offensivePlayer);
        }
    }   //getRoundPlayer()
    
    public Judger getJudger() {
        return judger;
    }   //getJudger()

    public Desk getDesk() {
        return desk;
    }   //getDesk()
    
    public void roundInc() {
        round ++;
    }   //roundInc()
}   //DeskController
