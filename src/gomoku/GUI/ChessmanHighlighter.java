/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

/**
 *
 * @author allen
 */
public class ChessmanHighlighter {
    private final ArrayList<Chessman> highlightedChessmanList;

    public ChessmanHighlighter(ArrayList<Chessman> highlightedChessmanList) {
        this.highlightedChessmanList = highlightedChessmanList;
    }   //ChessmanHighlighter()
           
    public void handleHighlighting() {
        for (final Chessman chessman : highlightedChessmanList) {
            
//TEST
            System.out.println(chessman);
            
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), chessman);
            fadeTransition.setToValue(0.3);
            fadeTransition.setAutoReverse(true);            
            
            fadeTransition.play();
        }
                
    }   //handleHighlighting()
    
    
    
}   //ChessmanHighLighter
