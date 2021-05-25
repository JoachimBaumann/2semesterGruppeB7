package Presentation;

import Domain.Catalog.Credit;
import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Domain.Facade;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductionController implements Initializable {
    public Text tProductionTitle;
    public Text tProductionReleaseDate;
    public Text tProductionID;
    public Text tProduktionBeskrivelse;
    public TextArea BeskrivelseTextArea;
    public TextField TitleTextField;
    public TextField ReleaseDateTextField;
    public TextField ProductionIDTextField;
    public TitledPane confirmPopUp;
    public Button bConfirmed;
    public Button bDenyChanges;
    private final Informationholder informationholder = Informationholder.getInstance();
    public Button bRediger;
    public VBox vboxRediger;
    public Button bTilføjKreditering;
    private Facade facade = Facade.getInstance();
    public TableView CreditTV;

    public TableColumn creditIDColumn;
    public TableColumn jobroleColumn;
    public TableColumn firstNameColumn;
    public TableColumn lastNameColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTextFields();
        confirmPopUp.setVisible(false);
        confirmPopUp.toBack();
        checkRole();
        CreditTV.setItems(getCredit());


        creditIDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Credit, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Credit, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCreditID());
            }

        });
        jobroleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Credit, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Credit, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getJobTitle());
            }
        });
        firstNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Credit, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Credit, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPerson(p.getValue().getPersonID()).getfName());
            }
        });
        lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Credit, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Credit, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPerson(p.getValue().getPersonID()).getlName());
            }
        });
    }

    public ObservableList<Credit> getCredit() {
        ArrayList<Credit> tempList = new ArrayList<>();
        for (Credit c : informationholder.getProduction().getCreditList().values()) {
            tempList.add(c);
        }

        ObservableList observableList = FXCollections.observableArrayList(tempList);
        return observableList;

    }



    public void cancelProduction(ActionEvent event) throws IOException {
        Parent producerViewParent = FXMLLoader.load(getClass().getResource("producer.fxml"));
        Scene producerViewScene = new Scene(producerViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(producerViewScene);
        informationholder.setProduction(null);
        window.show();
    }

    public void updateProductions() {
        confirmPopUp.toFront();
        confirmPopUp.setVisible(true);
    }

    public void bConfirmedClicked() {
        Production production = informationholder.getProduction();
        if (production != null) {
            if (!TitleTextField.getText().isEmpty() && !ReleaseDateTextField.getText().isEmpty() && !ProductionIDTextField.getText().isEmpty() && !BeskrivelseTextArea.getText().isEmpty()) {
                if(!facade.updateProduction(informationholder.getProduction().getProductionID(), ReleaseDateTextField.getText(), TitleTextField.getText(), BeskrivelseTextArea.getText())){
                    System.out.println("something went wrong");
                }
            }
        }
        facade.updateCatalog();
        int temp = informationholder.getProduction().getProductionID();
        informationholder.setProduction(facade.getCatalog().getProduction(temp));
        confirmPopUp.toBack();
        confirmPopUp.setVisible(false);
        vboxRediger.setVisible(false);
        vboxRediger.toBack();
        updateTextFields();
    }

    public void cancelledPopUp() {
        confirmPopUp.setVisible(false);
        confirmPopUp.toBack();
    }

    public void updateTextFields() {
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

        Stage window = (Stage) ((Node) event2.getSource()).getScene().getWindow();

        window.setScene(personsViewScene);
        window.show();
    }
    public void vBoxRedigerToggle(){
        vboxRediger.setVisible(true);
        vboxRediger.toFront();
    }
    public void checkRole(){

        if(informationholder.getUser() != null) {

            if (informationholder.getUser().getRole().equals("Systemadministrator")) {
                bRediger.setVisible(true);
                bConfirmed.setVisible(true);
                bTilføjKreditering.setVisible(true);
            } else if (informationholder.getUser().getRole().equals("Producer")) {
                bRediger.setVisible(true);
                bConfirmed.setVisible(true);
                bTilføjKreditering.setVisible(true);
            } else if (informationholder.getUser().getRole().equals("Bruger") || informationholder.getUser() == null) {
                bRediger.setVisible(false);
                bConfirmed.setVisible(false);
                bTilføjKreditering.setVisible(false);
                vboxRediger.setVisible(false);
                vboxRediger.toBack();
            }
            else{
                bRediger.setVisible(false);
                bConfirmed.setVisible(false);
                vboxRediger.setVisible(false);
                vboxRediger.toBack();
                bTilføjKreditering.setVisible(false);
            }
        }

    }

}

