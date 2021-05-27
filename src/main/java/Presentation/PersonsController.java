package Presentation;

import Domain.Catalog.Person;
import Domain.Creditmanager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class PersonsController implements Initializable {

    public TableView<Person> personsTV;
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
    public Button bSletPerson;
    public Button bFejlOK;
    public TitledPane tFejl;
    private Person selectedPerson = null;
    public TitledPane bBekræftSlet;
    public Button cancelledPopUp1;
    Informationholder informationholder = Informationholder.getInstance();
    private Creditmanager creditmanager = Creditmanager.getInstance();
    ObservableList<Person> persons = FXCollections.observableArrayList(creditmanager.viewPersons());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choosePerson();

        if (informationholder.getUser().getRole().equals("Producer")) {
            bSletPerson.setVisible(false);
            bSletPerson.toBack();
        }


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

        FilteredList<Person> filteredData = new FilteredList<>(persons);
        personsTV.setItems(filteredData);

        searchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(searchForPerson(newValue))
        );
    }

    private void choosePerson() {
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
                if (row.selectedProperty() != null) {
                    selectedPerson = row.getItem();
                }
            });
            return row;
        });
    }

    public void bPConfirmchanges() {
        if (informationholder.getPerson() != null && informationholder.getProduction() != null) {
            if (!tPersonRole.getText().isEmpty()) {
                creditmanager.addCredit(informationholder.getProduction().getProductionID(), informationholder.getPerson().getuID(), tPersonRole.getText());
            } else System.out.println("Empty textfield");
        }

    }

    private Predicate<Person> searchForPerson(String searchText) {
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

    public void createPerson() {
        tAddPerson.setVisible(true);
        tAddPerson.toFront();
    }

    public void hideAddPersonWindow() {
        tAddPerson.setVisible(false);
        tAddPerson.toBack();
    }

    public void closePersonWindow(ActionEvent event) {
        informationholder.setPerson(null);
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

    public void acceptCreatePerson() {
        //Todo Add information parse to DB
        if (creditmanager.addPerson(pMail.getText(), pFirstName.getText(), pLastName.getText(), Integer.valueOf(pPhone.getText()), pBeskrivelse.getText()) != -1) {
            hideAddPersonWindow();

        }
        confirmPopUp.toBack();
        confirmPopUp.setVisible(false);
        tAddPerson.setVisible(false);
        tAddPerson.toBack();
        updateList();

    }

    public void cancelledPopUp() {
        confirmPopUp.setVisible(false);
        confirmPopUp.toBack();
    }

    public void addPerson() {

        confirmPopUp.toFront();
        confirmPopUp.setVisible(true);
    }

    public void acceptCreateCredit(ActionEvent event) {
        //Todo Add information parse to DB
        bPConfirmchanges();
        tPersonName.setText(informationholder.getPerson().getfName());
        tpOpretKreditering.setVisible(false);
        tpOpretKreditering.toBack();
        confirmPopUp1.setVisible(false);
        confirmPopUp1.toBack();
        int productionID = informationholder.getProduction().getProductionID();
        informationholder.setProduction(creditmanager.getCatalog().getProduction(productionID));

        Parent productionViewParent = null;
        try {
            productionViewParent = FXMLLoader.load(getClass().getResource("Production.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene productionViewScene = new Scene(productionViewParent, 838, 540);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(productionViewScene);
        window.show();
    }

    public void updateList(){
        persons.clear();
        for (Person p : creditmanager.viewPersons()){
            persons.add(p);
        }


    }

    public void cancelOpretKreditering() {
        tpOpretKreditering.setVisible(false);
        tpOpretKreditering.toBack();
    }

    public void addCredit() {
        confirmPopUp1.setVisible(true);
        confirmPopUp1.toFront();
    }

    public void cancelConfirm() {
        confirmPopUp1.setVisible(false);
        confirmPopUp1.toBack();
    }

    public void setbFejlOK() {
        tFejl.setVisible(false);
        tFejl.toBack();
    }

    public void deletePerson() {
        if (selectedPerson != null) {
            bBekræftSlet.setVisible(true);
            bBekræftSlet.toFront();
        } else
            tFejl.setVisible(true);
        tFejl.toFront();

    }

    public void acceptDeletePerson(ActionEvent event) {
        if (creditmanager.deletePerson(selectedPerson.getuID())) {
            annullerSletConfirm();
            updateList();
        } else {
            System.out.println("something went wrong");
        }
    }

    public void annullerSletConfirm() {
        bBekræftSlet.setVisible(false);
        bBekræftSlet.toBack();
    }


}