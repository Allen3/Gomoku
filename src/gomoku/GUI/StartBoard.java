package gomoku.GUI;

import gomoku.Gomoku;
import gomoku.RootLayoutController;
import gomoku.business.Client;
import gomoku.business.Server;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by allen on 12/10/14.
 */
public class StartBoard extends AnchorPane {
    private final Gomoku mainApp;
    private final RootLayoutController rootLayoutController;

    private final double width;
    private final double height;

    public StartBoard(double width, double height, Gomoku mainApp, RootLayoutController rootLayoutController) {
        this.mainApp = mainApp;
        this.rootLayoutController = rootLayoutController;

        this.width = width;
        this.height = height;

        this.setHeight(height);
        this.setWidth(width);

        this.getChildren().addAll(
                createUpperBorderPane(),
                createSeparator(),
                createLowerAnchorPane()
        );

        this.getChildren().add(createButton("Client"));
        this.getChildren().add(createButton("Server"));
    }   //StartBoard()

    /**
     * Create the upper BorderPane to load the text.
     *
     * @return The BorderPane instance.
     */
    private BorderPane createUpperBorderPane() {
        BorderPane borderPane = new BorderPane();

        // Set the layout relationship with its parent.
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);
        AnchorPane.setBottomAnchor(borderPane, 250.0);

        // Add text to the center.
        Text text = createText("Please choose a role");
        borderPane.setCenter(text);

        return borderPane;
    }   //createUpperBorderPane()

    /**
     * Create a Separator to separate the upper and lower BorderPane.
     *
     * @return The Separator instance.
     */
    private Separator createSeparator() {
        Separator separator = new Separator(Orientation.HORIZONTAL);

        // Set the layout relationship with its parent.
        AnchorPane.setLeftAnchor(separator, 0.0);
        AnchorPane.setRightAnchor(separator, 0.0);
        AnchorPane.setTopAnchor(separator, 150.0);
        AnchorPane.setBottomAnchor(separator, 250.0);

        return separator;
    }   //createSeparator()

    /**
     * Create the lower BorderPane to load the buttons.
     *
     * @return The BorderPane instance.
     */
    private AnchorPane createLowerAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();

        // Set the layout relationship with its parent.
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 150.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);

        Button serverButton = createButton("Server");
        Button clientButton = createButton("Client");

        // Set the two buttons layout.
        AnchorPane.setLeftAnchor(serverButton, 100.0);
        AnchorPane.setTopAnchor(serverButton, 100.0);
        AnchorPane.setRightAnchor(clientButton, 100.0);
        AnchorPane.setBottomAnchor(clientButton, 100.0);
        anchorPane.getChildren().addAll(serverButton, clientButton);

        return anchorPane;
    }   //createLowerAnchorPane()

    /**
     * Create Button instance and add decorations on it.
     *
     * @param name The name of the Button.
     * @return The Button instance.
     */
    private Button createButton(String name) {
        Button button = new Button(name);
        
        // Bind actions to the button.
        button.setOnAction((ActionEvent actionEvent) -> {
            if (name.equalsIgnoreCase("Server")) {
                try {
                    mainApp.setServer(new Server());
                    mainApp.getServer().bind(8080);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }   //try-catch
            } else {
                try {
                    mainApp.setClient(new Client());
                    mainApp.getClient().connect(8080, "192.168.19.22");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }   //try-catch
            }
            rootLayoutController.switchToScene("Desk");
        });

        return button;
    }   //createButton()

    /**
     * Create Text instance and add decorations on it.
     *
     * @param content   The content of the text.
     * @return  The Text instance.
     */
    private Text createText(String content) {
        Text text = new Text(content);
        text.setFont(new Font(36));
        return text;
    }   //createText()

}   //StartBoard
