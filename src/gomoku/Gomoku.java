/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import gomoku.business.Client;
import gomoku.business.Server;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author allen
 */
public class Gomoku extends Application {
    private Stage primaryStage;

    private boolean isServer;
    private boolean isClient;
    private Server server;
    private Client client;
    
    private final RootLayoutController rootLayoutController;

    public Gomoku() {
        isServer = false;
        isClient = false;

        server = null;
        client = null;

        rootLayoutController = new RootLayoutController(this);
    }   //Gomoku()
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gomoku");
        rootLayoutController.work();
    }   //start()

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   //main()

    public Stage getPrimaryStage() {
        return primaryStage;
    }   //getPrimaryStage()

    public boolean isServer() {
        return isServer;
    }   //isServer()

    public boolean isClient() {
        return isClient;
    }   //isClient()

    public void setServer(Server server) {
        this.server = server;
        this.isServer = true;
    }   //setServer()

    public void setClient(Client client) {
        this.client = client;
        this.isClient = true;
    }   //setClient()

    public Server getServer() {
        return server;
    }   //getServer()

    public Client getClient() {
        return client;
    }   //getClient()
}   //Gomoku
