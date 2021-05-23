package Presentation;

import Domain.Catalog.Production;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductionController implements Initializable {

    public TextField pTilte;
    public TextField pReleaseDate;
    public TextField pProductionID;
    private Informationholder informationholder = Informationholder.getInstance();
    private Production production = informationholder.getProduction();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    pTilte.setText(production.getProductionName());
    pReleaseDate.setText(production.getReleaseDate());
    pProductionID.setText(String.valueOf(production.getProductionID()));
    }

public void cancelProduction(ActionEvent event) throws IOException {
    Parent producerViewParent = FXMLLoader.load(getClass().getResource("producer.fxml"));
    Scene producerViewScene = new Scene(producerViewParent);

    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

    window.setScene(producerViewScene);
    window.show();
    }
}

