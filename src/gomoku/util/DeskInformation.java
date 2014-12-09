package gomoku.util;

import gomoku.GUI.Chessman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * POJO class to carry desk information.
 *
 * Created by allen on 12/9/14.
 */
public class DeskInformation implements Serializable{
    private static final long serialVersionUID = 3L;    //Set the serial version UID explicitly.

    private final int round;
    private final Player roundPlayer;

    private final ArrayList<Integer> chessmanCoordinateArray;
    private final HashMap<Integer, Chessman> chessmanCoordinateMap;

    public DeskInformation(int round, Player roundPlayer, ArrayList<Integer> chessmanCoordinateArray, HashMap<Integer, Chessman> chessmanCoordinateMap) {
        this.round = round;
        this.roundPlayer = roundPlayer;
        this.chessmanCoordinateArray = chessmanCoordinateArray;
        this.chessmanCoordinateMap = chessmanCoordinateMap;
    }   //DeskInformation()

    public int getRound() {
        return round;
    }   //getRound()

    public Player getRoundPlayer() {
        return roundPlayer;
    }   //getRoundPlayer()

    public ArrayList<Integer> getArrayList() {
        return chessmanCoordinateArray;
    }   //getArrayList()

    public HashMap<Integer, Chessman> getChessmanCoordinateMap() {
        return chessmanCoordinateMap;
    }   //getChessmanCoordintaeMap()
}   //DeskInformation
