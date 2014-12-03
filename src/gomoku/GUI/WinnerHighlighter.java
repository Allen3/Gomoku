package gomoku.GUI;

import gomoku.Gomoku;
import gomoku.util.Player;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 * The highlighter to highlight the winner.
 *
 * @author allen
 */
public class WinnerHighlighter {
    private final Gomoku mainApp;

    private final Player winner;

    private final Stage winnerHighlightStage;

    private ScaleTransition scaleTransition;

    public WinnerHighlighter(Player winner, Gomoku mainApp) {
        this.winner = winner;
        this.mainApp = mainApp;

        winnerHighlightStage = new Stage();
        winnerHighlightStage.setTitle("Result");
        winnerHighlightStage.initStyle(StageStyle.TRANSPARENT);
        winnerHighlightStage.initModality(Modality.WINDOW_MODAL);
        winnerHighlightStage.initOwner(mainApp.getPrimaryStage());

        // Enable press Esc to close this stage.
        winnerHighlightStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                winnerHighlightStage.close();
            }
        });

        Parent highLighterRoot = rootInit();

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

        Text winningInformation = setWinningInformation();
        Text windowClosePrompt = setWindowClosePrompt();

        borderPane.setCenter(winningInformation);
        borderPane.setBottom(windowClosePrompt);

        return borderPane;
    }   //rootInit();

    /**
     * Set the prompt text about closing the window and the decoration on it.
     *
     * @return The prompt text about closing this window.
     */
    private Text setWindowClosePrompt() {
        Text text = new Text("Press `Esc` to close this window");
        //TO-DO
        //Decorations...
        return text;
    }   //setWindowClosePrompt()

    /**
     * Set the winning information text and decoration on it.
     *
     * @return The winning information text.
     */
    private Text setWinningInformation() {
        Text text = new Text(winner.toString() + " wins!");
        setScaleTransition(text);
        //TO-DO
        //Decorations...
        return text;
    }   //setWinningInformation()

    /**
     * Set Scale Transition on.
     * @param node
     */
    private void setScaleTransition(Node node) {
        scaleTransition = new ScaleTransition(Duration.millis(2000), node);
        scaleTransition.setByX(1.5f);
        scaleTransition.setByY(1.5f);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
    }   //setScaleTransition()

    public void run() {
        scaleTransition.play();
        winnerHighlightStage.show();
        //scaleTransition.play();
    }   //run()

}   //WinnerHighlighter
