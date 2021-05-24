package Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    public void updateProductions(){
        tProductionTitle.setText(TitleTextField.getText());
        tProductionReleaseDate.setText(ReleaseDateTextField.getText());
        tProductionID.setText(ProductionIDTextField.getText());
        tProduktionBeskrivelse.setText(BeskrivelseTextArea.getText());
    }

}

