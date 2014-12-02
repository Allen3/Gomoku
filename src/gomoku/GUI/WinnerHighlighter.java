package gomoku.GUI;

import gomoku.Gomoku;
import gomoku.util.Player;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 * Created by allen on 12/2/14.
 */
public class WinnerHighlighter {
    private final Gomoku mainApp;

    private final Player winner;

    private final Stage winnerHighlightStage;

    private ScaleTransition scaleTransition;

    public WinnerHighlighter(Player winner, Gomoku mainApp) {
        this.winner = winner;
        this.mainApp = mainApp;

        Parent highLighterRoot = rootInit();

        winnerHighlightStage = new Stage();
        winnerHighlightStage.setTitle("Result");
        winnerHighlightStage.initStyle(StageStyle.UNDECORATED);
        winnerHighlightStage.initModality(Modality.WINDOW_MODAL);
        winnerHighlightStage.initOwner(mainApp.getPrimaryStage());

        Scene scene = new Scene(highLighterRoot);
        winnerHighlightStage.setScene(scene);
    }   //WinnerHighlighter

    /**
     * Initialize the elements of the scene.
     *
     * @return The top element to show in the scene.
     */
    private Parent rootInit() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(300.0f, 200.0f);

        Text text = new Text(winner.toString() + " wins!");
        setScaleTransition(text);

        borderPane.setCenter(text);

        return borderPane;
    }   //rootInit();

    private void setScaleTransition(Node node) {
        scaleTransition = new ScaleTransition(Duration.millis(2000), node);
        scaleTransition.setByX(2.0f);
        scaleTransition.setByY(2.0f);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
    }   //setScaleTransition()

    public void run() {
        scaleTransition.play();
        winnerHighlightStage.show();
        //scaleTransition.play();
    }   //run()

}   //WinnerHighlighter
