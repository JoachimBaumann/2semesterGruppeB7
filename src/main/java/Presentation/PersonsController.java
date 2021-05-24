package Presentation;

import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Domain.Facade;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonsController implements Initializable {
    public TableView personsTV;
    public TitledPane tAddPerson;
    public TitledPane confirmPopUp;
    public AnchorPane tpOpretKreditering;
    public TextField pFirstName;
    public TextField pLastName;
    public TextField pMail;
    public TextField pPhone;
    public TextField pBeskrivelse;
    public TableColumn uIDcolumn;
    public TableColumn fnameColumn;
    public TableColumn lNameColumn;
    public TableColumn mailColumn;
    public TableColumn phoneColumn;
    public TableColumn beskrivelsecolumn;
    Informationholder informationholder = Informationholder.getInstance();
    private Facade facade = new Facade();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personsTV.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Person rowData = row.getItem();
                    informationholder.setPerson(rowData);
                }
            });
            return row;
        });

        /*setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Production, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Production, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getProductionID());
            }

        });


         */

    }

    public void showAddPersonWindow(){
        tAddPerson.setVisible(true);
        tAddPerson.toFront();
    }

    public void hideAddPersonWindow(){
        tAddPerson.setVisible(false);
        tAddPerson.toBack();
    }

    public void closePersonWindow(ActionEvent event){
        Parent producerViewParent = null;
        try {
            producerViewParent = FXMLLoader.load(getClass().getResource("producer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene producerViewScene = new Scene(producerViewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(producerViewScene);
        window.show();
    }
    public void bConfirmedClicked(){
        //Todo Add information parse to DB
        confirmPopUp.toBack();
        confirmPopUp.setVisible(false);
        tAddPerson.setVisible(false);
        tAddPerson.toBack();
        

    }
    public void cancelledPopUp(){
        confirmPopUp.setVisible(false);
        confirmPopUp.toBack();
    }
    public void updatePersons(){
        facade.addPerson(pMail.getText(),pFirstName.getText(),pLastName.getText(),Integer.valueOf(pPhone.getText()),pBeskrivelse.getText());
        confirmPopUp.toFront();
        confirmPopUp.setVisible(true);
    }
    public void acceptOpretKreditering(){
        tpOpretKreditering.setVisible(false);
        tpOpretKreditering.toBack();
    }
    public void cancelOpretKreditering(){
        tpOpretKreditering.setVisible(false);
        tpOpretKreditering.toBack();
    }
}
