/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * The highlighter to highlight the winning chessmen.
 *
 * @author allen
 */
public class ChessmanHighlighter {
    private final ArrayList<Chessman> highlightChessmanList;

    public ChessmanHighlighter(ArrayList<Chessman> highlightChessmanList) {
        this.highlightChessmanList = highlightChessmanList;
    }   //ChessmanHighlighter()

    /**
     * Use Fade Transition to highlight the winning chessmen.
     */
    public void run() {
        for (final Chessman chessman : highlightChessmanList) {
            setFadeTransition(chessman).play();
        }
    }   //run()

    private FadeTransition setFadeTransition(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), node);
        fadeTransition.setToValue(0.3);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(4);

        return fadeTransition;
    }   //setFadeTransition()

}   //ChessmanHighLighter
