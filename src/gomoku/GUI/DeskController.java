/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.GUI;

import gomoku.Gomoku;
import gomoku.util.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * Controller to control the background game business logic of desk.
 *
 * @author allen
 */
public class DeskController {
    private final Gomoku mainApp;
    private final Desk desk;

    private WinnerHighlighter winnerHighlighter;
    private ChessmanHighlighter chessmanHighlighter;

    private final int deskNumOfRows;
    private final int deskNumOfColumns;

    /*
     *  Both these two variables are maps for chessman and coordinate, respectively.
     *  The first one is an observable so we add listeners to listen changes to it.
     */
    private final ObservableIntegerArray chessmanCoordinateArray;    // Chessman -> Integer
    private final HashMap<Integer, Chessman> chessmanCoordinateMap; // Integer -> Chessman

    // The list to record the coordinate of winning chessmen.
    private final ArrayList<Integer> winningChessmanList;

    private int round;
    private Player offensivePlayer;
    
    public DeskController(Desk desk, Gomoku mainApp) {
        this.mainApp = mainApp;
        this.desk = desk;

        this.deskNumOfRows = desk.getNumOfRows();
        this.deskNumOfColumns = desk.getNumOfColumns();

        this.round = 0;

        chessmanCoordinateArray = FXCollections.observableIntegerArray();
        // Assure that the array has no preset value.
        chessmanCoordinateArray.clear();
        chessmanCoordinateArray.resize((deskNumOfRows + 1) * (deskNumOfColumns + 1));

        chessmanCoordinateMap = new HashMap<>();
        winningChessmanList = new ArrayList<>();

        // Add listeners to connect background data and UI operations.
        chessmanCoordinateArray.addListener((ObservableIntegerArray observable, boolean sizeChanged, int from, int to) -> {

            desk.setInactive();
            // Game continues.
            if (isGameEnd(from) == false) {

                desk.setActive();

                // A new round begins.
                roundInc();
                desk.setChessman(getRoundPlayer());

            } else {
                /*
                 * The game ends.
                 */
                // Highlight the winning chessmen.
                highlightWinningChessman();

                // Highlight the winner to declare the result.
                hightlightWinner(
                        (chessmanCoordinateArray.get(from) == Player.PLAYER_A.getId()) ?
                                Player.PLAYER_A : Player.PLAYER_B);
            }
        });
    }   //DeskController()

    /**
     * Check if this game is end.
     *
     * @param coordinate the coordinate where the new chessman was laid.
     * @return {@code true} for end, {@code false} for not.
     */
    private boolean isGameEnd(int coordinate) {
        // Set the upper bound and lower bound of the coordinates.
        int coordinateUpperBound = deskNumOfRows * (deskNumOfColumns + 1);
        int coordinateLowerBound = 0;

        int valueOfCoordinate = chessmanCoordinateArray.get(coordinate);

        /*
         * Of the same row.
         */
        winningChessmanList.clear();
        winningChessmanList.add(coordinate);
        // Right check.
        for (int i = 1;i < 5;i ++) {
            // Same row and same value.
            if ((coordinate + i <= coordinateUpperBound) && (((coordinate + i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    chessmanCoordinateArray.get(coordinate + i) == valueOfCoordinate) {
                winningChessmanList.add(coordinate + i);
            } else {
                break;
            }
        }

        // Left check.
        for (int i = 1;i < 5;i ++) {
            // Same row and same value.
            if ((coordinate - i >= coordinateLowerBound) && (((coordinate - i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    chessmanCoordinateArray.get(coordinate - i) == valueOfCoordinate) {
                winningChessmanList.add(coordinate - i);
            } else {
                break;
            }
        }
        if (winningChessmanList.size() >= 5)
            return true;

        /*
         * Of the same column.
         */
        winningChessmanList.clear();
        winningChessmanList.add(coordinate);
        // Down check.
        for (int i = 1;i < 5;i ++) {
            // Same column and same value.
            if (((coordinate + i * (deskNumOfColumns + 1)) <= coordinateUpperBound) &&
                    chessmanCoordinateArray.get(coordinate + i * (deskNumOfColumns + 1)) == valueOfCoordinate) {
                winningChessmanList.add(coordinate + i * (deskNumOfColumns + 1));
            } else {
                break;
            }
        }

        // Up check.
        for (int i = 1;i < 5;i ++) {
            // Same column and same value.
            if (((coordinate - i * (deskNumOfColumns + 1)) >= coordinateLowerBound) &&
                    chessmanCoordinateArray.get(coordinate - i * (deskNumOfColumns + 1)) == valueOfCoordinate) {
                winningChessmanList.add(coordinate - i * (deskNumOfColumns + 1));
            } else {
                break;
            }
        }
        if (winningChessmanList.size() >= 5)
            return true;


        /*
         * Of the same principal diagonal.
         */
        winningChessmanList.clear();
        winningChessmanList.add(coordinate);
        // Right-down check.
        for (int i = 1;i < 5;i ++) {
            // Same principal diagonal and same value.
            if ((((coordinate + i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    ((coordinate + i * (deskNumOfColumns + 1) + i) <= coordinateUpperBound) &&
                    chessmanCoordinateArray.get(coordinate + i * (deskNumOfColumns + 1) + i) == valueOfCoordinate) {

                winningChessmanList.add(coordinate + i * (deskNumOfColumns + 1) + i);
            } else {
                break;
            }
        }

        // Left-up check.
        for (int i = 1;i < 5;i ++) {
            // Same principal diagonal and same value.
            if ((((coordinate - i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    ((coordinate - i * (deskNumOfColumns + 1) - i) >= coordinateLowerBound) &&
                    chessmanCoordinateArray.get(coordinate - i * (deskNumOfColumns + 1) - i) == valueOfCoordinate) {
                winningChessmanList.add(coordinate - i * (deskNumOfColumns + 1) - i);
            } else {
                break;
            }
        }
        if (winningChessmanList.size() >= 5)
            return true;


        /*
         * Of the same deputy diagonal.
         */
        winningChessmanList.clear();
        winningChessmanList.add(coordinate);
        // Left-down check.
        for (int i = 1;i < 5;i ++) {
            // Same deputy diagonal and same value.
            if ((((coordinate - i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    ((coordinate + i * (deskNumOfColumns + 1) - i) <= coordinateUpperBound) &&
                    chessmanCoordinateArray.get(coordinate + i * (deskNumOfColumns + 1) - i) == valueOfCoordinate) {

                winningChessmanList.add(coordinate + i * (deskNumOfColumns + 1) - i);
            } else {
                break;
            }
        }

        // Right-up check.
        for (int i = 1;i < 5;i ++) {
            // Same deputy diagonal and same value.
            if ((((coordinate + i) / (deskNumOfColumns + 1)) == (coordinate / (deskNumOfColumns + 1))) &&
                    ((coordinate - i * (deskNumOfColumns + 1) + i) >= coordinateLowerBound) &&
                    chessmanCoordinateArray.get(coordinate - i * (deskNumOfColumns + 1) + i) == valueOfCoordinate) {
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

    /**
     * Highlight the winning chessmen.
     */
    private void highlightWinningChessman() {
        // The list to record the instances of winning chessmen.
        ArrayList<Chessman> highlightedChessmanRenderList = new ArrayList<>();

        for (int coordinate : winningChessmanList) {
            highlightedChessmanRenderList.add(chessmanCoordinateMap.get(coordinate));
        }

        chessmanHighlighter = new ChessmanHighlighter(highlightedChessmanRenderList);
        chessmanHighlighter.run();
    }   //highlightWinningChessman()

    /**
     * Highlight the winner(declare the result).
     * @param winner The player wins the game.
     */
    private void hightlightWinner(Player winner) {
        winnerHighlighter = new WinnerHighlighter(winner, mainApp);
        winnerHighlighter.run();
    }   //hightlightWinner()

    /**
     *
     * @param coordinate the coordinate the chessman laid.
     * @return the state whether the chessman is able to be laid there.
     */
    public boolean isSettable(int coordinate) {
        if (chessmanCoordinateArray.get(coordinate) == 0)
            return true;
        return false;
    }   //isSettable()

    public ObservableIntegerArray getChessmanCoordinateArray() {
        return chessmanCoordinateArray;
    }   //getChessmanCoordinateArray()

    public HashMap<Integer, Chessman> getChessmanCoordinateMap() {
        return chessmanCoordinateMap;
    }   //getChessmanCoordinateMap()

    /**
     * 
     * @param offensivePlayer value varies between 1 and 2 which represents the player respectively.
     */
    public void setOffensivePlayer(Player offensivePlayer) {
        this.offensivePlayer = offensivePlayer;
        desk.setActive();
        
        round ++;
        desk.setChessman(offensivePlayer);
    }   //offensivePlayer()
    
    /**
     * 
     * @return The player who are supposed to lay the chessman in this round.
     */
    public Player getRoundPlayer() {
        if (round % 2 != 0) {
            return offensivePlayer;
        } else {
            return (offensivePlayer.switchPlayer());
        }
    }   //getRoundPlayer()

    public Player getOffensivePlayer() { return offensivePlayer; }   //getOffensivePlayer()

    public Desk getDesk() {
        return desk;
    }   //getDesk()
    
    public void roundInc() {
        round ++;
    }   //roundInc()
}   //DeskController
