package gomoku;

import gomoku.GUI.Desk;
import gomoku.GUI.DeskController;
import gomoku.GUI.StartBoard;
import gomoku.util.Player;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author allen
 */
public class RootLayoutController {
    private final Gomoku mainApp;
    private DeskController deskController;

    public RootLayoutController(Gomoku mainApp) {
        this.mainApp = mainApp;
    }   //panel()

    public void work() {
        showOnStage(mainApp.getPrimaryStage(), setNewScene(createStartBoardLayout()));
    }   //work()

    private Parent createStartBoardLayout() {
        double width_StartBoard = 600;                  // Width of StartBoard.
        double height_StartBoard = 400;                 // Height of StartBoard.

        StartBoard startBoard = new StartBoard(width_StartBoard, height_StartBoard, mainApp, this);
        return startBoard;
    }   //createStartBoardLayout()

    private Parent createDeskLayout() {
        int numOfRows_Desk = 9;                         // Number of row grid lines in Desk, NOT row grid.
        int numOfColumns_Desk = 9;                      // Number of column grid lines in Desk, NOT column grid.
        int gridInterval_Desk = 50;                     // Value of grid interval in Desk.

        Desk desk = new Desk(numOfRows_Desk, numOfColumns_Desk, gridInterval_Desk, mainApp, this);
        deskController = desk.getDeskController();

        return desk;
    }   //createDeskLayout()

    /**
     * Configure a new scene with a set of provided layout.
     *
     * @param parent  The root element of a set of layout.
     * @return A new Scene instance.
     */
    protected Scene setNewScene(Parent parent) {
        Scene scene = new Scene(parent);
        return scene;
    }   //setNewScene()

    /**
     * Show the stage.
     *
     * @param stage The Stage to show the scene.
     * @param scene The Scene to be show.
     */
    protected void showOnStage(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.show();
    }   //showOnStage()

    /**
     * Switch to the next scene specified.
     *
     * @param sceneName The name of the next scene switch to.
     */
    public void switchToScene(String sceneName) {
        // Switch to Desk scene.
        if (sceneName.equalsIgnoreCase("Desk")) {
            showOnStage(mainApp.getPrimaryStage(), setNewScene(createDeskLayout()));

            if (mainApp.isServer()) {
                deskController.setOffensivePlayer(Player.PLAYER_A);
            }

        }

    }   //switchToScene()

}   //RootLayoutController
