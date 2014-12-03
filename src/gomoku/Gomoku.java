/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author allen
 */
public class Gomoku extends Application {
    private Stage primaryStage;
    
    private final RootLayout rootLayout;

    public Gomoku() {        
        rootLayout = new RootLayout(this);
    }   //Gomoku()
    
    @Override
    public void start(Stage primaryStage) {        
        this.primaryStage = primaryStage;

        Scene scene = new Scene(rootLayout.getRoot());
        
        primaryStage.setTitle("Gomoku");
        primaryStage.setScene(scene);
        primaryStage.show();
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
}   //Gomoku
