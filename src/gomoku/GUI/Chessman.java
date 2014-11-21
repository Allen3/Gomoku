/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import gomoku.util.Player;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author Allen Young
 */
public class Chessman extends Circle {
    private final double radius;        
    private final Player player;
    
    private final Paint filledColor;

    public Chessman(double radius, Player player) {
        this.radius = radius;   
        this.player = player;
                
        this.setRadius(radius);
        
        // Set different colors for different players.
        if (player == Player.PLAYER_A) {            
            filledColor = Color.rgb(0, 0, 0);
        } else if (player == Player.PLAYER_B) {
            filledColor = Color.rgb(91, 127, 255);
        } else
            filledColor = null;
        this.setFill(filledColor);
    }   //Chessman()

    public Player getPlayer() {
        return player;
    }   //getPlayer()                                   
}   //Chessman
