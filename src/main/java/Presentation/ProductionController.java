package Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductionController implements Initializable {
    public Text tProductionTitle;
    public Text tProductionReleaseDate;
    public Text tProductionID;
    public Text tProduktionBeskrivelse;
    public TableView CreditTV;
    public TextArea BeskrivelseTextArea;
    public TextField TitleTextField;
    public TextField ReleaseDateTextField;
    public TextField ProductionIDTextField;
    public TitledPane confirmPopUp;
    public Button bConfirmed;
    public Button bDenyChanges;
    private Informationholder informationholder = Informationholder.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTextFields();
        confirmPopUp.setVisible(false);
        confirmPopUp.toBack();
    }
    public void cancelProduction(ActionEvent event) throws IOException {
    Parent producerViewParent = FXMLLoader.load(getClass().getResource("producer.fxml"));
    Scene producerViewScene = new Scene(producerViewParent);

    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

    window.setScene(producerViewScene);
    window.show();
    }
    public void updateProductions(){
        confirmPopUp.toFront();
        confirmPopUp.setVisible(true);
    }
    public void bConfirmedClicked(){
        confirmPopUp.toBack();
        confirmPopUp.setVisible(false);

        updateTextFields();

    }
    public void cancelledPopUp(){
        confirmPopUp.setVisible(false);
        confirmPopUp.toBack();
    }
    public void updateTextFields(){
        tProductionTitle.setText(informationholder.getProduction().getProductionName());
        TitleTextField.setText(informationholder.getProduction().getProductionName());
        tProductionReleaseDate.setText(informationholder.getProduction().getReleaseDate());
        ReleaseDateTextField.setText(informationholder.getProduction().getReleaseDate());
        tProductionID.setText(String.valueOf(informationholder.getProduction().getProductionID()));
        ProductionIDTextField.setText(String.valueOf(informationholder.getProduction().getProductionID()));
        tProduktionBeskrivelse.setText(informationholder.getProduction().getDescription());
        BeskrivelseTextArea.setText(informationholder.getProduction().getDescription());
    }

        public void bAddCredit(ActionEvent event2) throws IOException {
            Parent personsViewParent = FXMLLoader.load(getClass().getResource("Persons.fxml"));
            Scene personsViewScene = new Scene(personsViewParent);

            Stage window = (Stage) ((Node)event2.getSource()).getScene().getWindow();

            window.setScene(personsViewScene);
            window.show();   
    }

}

