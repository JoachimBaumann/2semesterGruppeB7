package Presentation;

import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Domain.Facade;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class PersonsController implements Initializable {
    public TableView personsTV;
    public TitledPane tAddPerson;
    public TitledPane confirmPopUp;
    public TitledPane tpOpretKreditering;
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
    public TextField tPersonName;
    public TextField searchField;
    public TextField tPersonRole;
    public TitledPane confirmPopUp1;

    Informationholder informationholder = Informationholder.getInstance();
    private Facade facade = Facade.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facade.updateCatalog();
        personsTV.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    tpOpretKreditering.toFront();
                    Person rowData = row.getItem();
                    informationholder.setPerson(rowData);
                    if (informationholder.getProduction() != null) {
                        tpOpretKreditering.setVisible(true);
                        tPersonName.setText(rowData.getfName() + " " + rowData.getlName());
                    }
                }
            });
            return row;
        });


        uIDcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Person, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getuID());
            }
        });


        fnameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getfName());
            }
        });
        lNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getlName());
            }
        });
        mailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMail());
            }
        });
        phoneColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPhoneNumber());
            }
        });
        beskrivelsecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDescription());
            }
        });

        FilteredList<Person> filteredData = new FilteredList<>(FXCollections.observableList(getPersons()));
        personsTV.setItems(filteredData);

        searchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(createPredicate(newValue))
        );
    }

    public void bPConfirmchanges() {
        if (informationholder.getPerson() != null && informationholder.getProduction() != null) {
            if (!tPersonRole.getText().isEmpty()) {
                facade.addCredit(informationholder.getProduction().getProductionID(), informationholder.getPerson().getuID(), tPersonRole.getText());
            } else System.out.println("Emty textfield");
        }

    }

    private Predicate<Person> createPredicate(String searchText) {
        return Person -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(Person, searchText);
        };
    }

    private boolean searchFindsOrder(Person person, String searchText) {
        return (person.getfName().toLowerCase().contains(searchText.toLowerCase()))
                || (person.getlName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(person.getuID()).toString().equals(searchText.toLowerCase());
    }

    public void showAddPersonWindow() {
        tAddPerson.setVisible(true);
        tAddPerson.toFront();
    }

    public void hideAddPersonWindow() {
        tAddPerson.setVisible(false);
        tAddPerson.toBack();
    }

    public void closePersonWindow(ActionEvent event) {
        Parent producerViewParent = null;
        try {
            producerViewParent = FXMLLoader.load(getClass().getResource("producer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene producerViewScene = new Scene(producerViewParent, 838, 540);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(producerViewScene);
        window.show();
    }

    public void bConfirmedClicked() {
        //Todo Add information parse to DB
        if (facade.addPerson(pMail.getText(), pFirstName.getText(), pLastName.getText(), Integer.valueOf(pPhone.getText()), pBeskrivelse.getText()) != -1) {
            hideAddPersonWindow();
        }
        confirmPopUp.toBack();
        confirmPopUp.setVisible(false);
        tAddPerson.setVisible(false);
        tAddPerson.toBack();


    }

    public void cancelledPopUp() {
        confirmPopUp.setVisible(false);
        confirmPopUp.toBack();
    }

    public void updatePersons() {

        confirmPopUp.toFront();
        confirmPopUp.setVisible(true);
    }

    public void acceptOpretKreditering() {
        //Todo Add information parse to DB
        bPConfirmchanges();
        tPersonName.setText(informationholder.getPerson().getfName());
        tpOpretKreditering.setVisible(false);
        tpOpretKreditering.toBack();
        confirmPopUp1.setVisible(false);
        confirmPopUp1.toBack();
        facade.updateCatalog();
    }

    public void cancelOpretKreditering() {
        tpOpretKreditering.setVisible(false);
        tpOpretKreditering.toBack();
    }

    public void opretKreditering() {
        confirmPopUp1.setVisible(true);
        confirmPopUp1.toFront();
    }

    public void cancelConfirm() {
        confirmPopUp1.setVisible(false);
        confirmPopUp1.toBack();
    }

    public ObservableList<Person> getPersons() {
        ArrayList<Person> tempList = new ArrayList<>();

        for (Person p : facade.getCatalog().getPersons().values()) {
            tempList.add(p);
        }

        ObservableList observableList = FXCollections.observableArrayList(tempList);
        return observableList;

    }
}
