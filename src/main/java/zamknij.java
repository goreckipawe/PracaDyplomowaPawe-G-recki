
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Paweł on 2016-05-29.
 */
public class zamknij implements Initializable {

    @FXML
    private Button zamknij;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zamknij.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
    }
}