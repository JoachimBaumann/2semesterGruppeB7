package Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductionController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
public void cancelProduction(ActionEvent event) throws IOException {
    Parent producerViewParent = FXMLLoader.load(getClass().getResource("producer.fxml"));
    Scene producerViewScene = new Scene(producerViewParent);

    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

    window.setScene(producerViewScene);
    window.show();
    }
}

