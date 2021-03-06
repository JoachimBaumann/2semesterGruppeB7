package Presentation;

import Domain.Catalog.Production;
import Domain.Creditmanager;
import Domain.Users.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ProducerController implements Initializable {
    public Button bSearch;
    public Text tLogProd;


    public Button bAddProduction;
    public Button bAddCredit;
    public Button bsignOut;
    public Button bDark;
    public ImageView darkBackground;
    public AnchorPane newMember;
    public TextField userName;
    public PasswordField userPassword;
    public SplitMenuButton logInd;
    public ImageView lightCredit;
    public ImageView darkCredit;
    public TableColumn<Production, Integer> tID;
    public TableColumn<Production, String> tTitel;
    public TableColumn<Production, String> tDescription;
    public TableColumn<Production, String> tReleaseDate;
    public TableView<Production> tableView;
    public VBox vBoxLogIn;
    public TitledPane TAddProduction;
    public TextField tpTitel;
    public TextField tpBeskrivelse;
    public DatePicker tpReleaseDate;
    public AnchorPane mainPane;
    public TextField searchField;
    public TitledPane tBoxLogIn;
    public Text tPassword;
    public TitledPane confirmPopUp;
    public Button cancelledPopUp;
    public Button bFjernProduktion;
    public TitledPane tBekræftSletProduktion;
    public TitledPane tFejlProduktion;
    private Creditmanager creditmanager = Creditmanager.getInstance();
    ObservableList<Production> productions = FXCollections.observableArrayList(creditmanager.viewProductions());
    private Informationholder informationholder = Informationholder.getInstance();
    private Production selectedProduction = null;


    //configure the table
    @FXML
    private TableView<Production> productionTableView;
    @FXML
    private TableColumn<Production, Integer> pIDColumn;
    @FXML
    private TableColumn<Production, String> pTitelColumn;
    @FXML
    private TableColumn<Production, String> pDescriptionColumn;
    @FXML
    private TableColumn<Production, String> pReleaseDateColumn;


    public void signIn() {
        if (informationholder.getUser().getRole().equals("Producer")) {
            tLogProd.setVisible(true);
            tLogProd.setText("Du er logget ind som: " + informationholder.getUser().getRole());
            tBoxLogIn.setVisible(false);
            logInd.setVisible(false);
            tBoxLogIn.setVisible(false);
            bAddProduction.setVisible(true);
            bAddCredit.setVisible(true);
            bsignOut.setVisible(true);
            tBoxLogIn.toBack();
            //HUndeprutter lugter ik
        }
        if (informationholder.getUser().getRole().equals("Systemadministrator")) {
            tLogProd.setVisible(true);
            tLogProd.setText("Du er logget ind som: " + informationholder.getUser().getRole());
            tBoxLogIn.setVisible(false);
            logInd.setVisible(false);
            tBoxLogIn.setVisible(false);
            bAddProduction.setVisible(true);
            bAddCredit.setVisible(true);
            bsignOut.setVisible(true);
            tBoxLogIn.toBack();
            bFjernProduktion.setVisible(true);
        }
        if (informationholder.getUser().getRole().equals("Bruger")) {
            logInd.setVisible(false);
            tLogProd.setText("Du er logget ind som: " + informationholder.getUser().getRole());
            tLogProd.setVisible(true);
            bsignOut.setVisible(true);
            tBoxLogIn.toBack();
            tBoxLogIn.setVisible(false);
        } else {
            tPassword.setVisible(true);
        }
    }

    public void signOutP(ActionEvent actionEvent) throws IOException {
        tBoxLogIn.setVisible(false);
        logInd.setVisible(true);
        bAddProduction.setVisible(false);
        bAddCredit.setVisible(false);
        tLogProd.setVisible(false);
        tPassword.setVisible(false);
        bsignOut.setVisible(false);
        TAddProduction.setVisible(false);
        bFjernProduktion.setVisible(false);
        tBoxLogIn.toBack();
        informationholder.setUser(null);

    }


    // incomming spagetticode
    public void login() {
        if (userName != null && userPassword != null) {
            User user = creditmanager.login(userName.getText(), userPassword.getText());

            if (user != null) {
                informationholder.setUser(user);
                signIn();
            } else tPassword.setVisible(true);
        }
    }


    public void openLogin(ActionEvent actionEvent) {
        tBoxLogIn.setVisible(true);
        newMember.setVisible(false);
        tBoxLogIn.toFront();
    }

    public void actionTwo(ActionEvent actionEvent) {
        newMember.setVisible(true);
    }

    public void rCancel(ActionEvent actionEvent) {
        newMember.setVisible(false);
    }

    public void forgotCode(ActionEvent actionEvent) {
        System.out.println("Forkert brugernavn eller adgangskode");
    }

    public void bCancel(ActionEvent actionEvent) {
        tBoxLogIn.setVisible(false);
        tBoxLogIn.toBack();
    }

    public void createProduction() {
        TAddProduction.setVisible(true);
        TAddProduction.toFront();
    }

    public void closeAddProductionWindow() {
        TAddProduction.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initializing tableview
        creditmanager.updateCatalog();
        updateList();
        productionTableView = new TableView<Production>(productions);

        if (informationholder.getUser() != null) {
            signIn();
        }
        // this makes it possible to doubleClick a row
        chooseProduction();

        pIDColumn = new TableColumn<>("productionID");
        pTitelColumn = new TableColumn<>("productionName");
        pDescriptionColumn = new TableColumn<>("description");
        pReleaseDateColumn = new TableColumn<>("releaseDate");

        pIDColumn.setText("ID");
        pTitelColumn.setText("Titel");
        pDescriptionColumn.setText("Beskrivelse");
        pReleaseDateColumn.setText("Udgivelsesdato");
        productionTableView.toBack();
        //placement
        productionTableView.setLayoutX(-2);
        productionTableView.setLayoutY(170);
        productionTableView.setPrefHeight(388);
        productionTableView.setPrefWidth(883);
        //Column Sizes
        pIDColumn.setPrefWidth(53);
        pTitelColumn.setPrefWidth(300);
        pDescriptionColumn.setPrefWidth(370);
        pReleaseDateColumn.setPrefWidth(117);


        pIDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Production, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Production, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getProductionID());
            }

        });
        pTitelColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Production, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Production, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getProductionName());
            }
        });

        pDescriptionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Production, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Production, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDescription());
            }
        });

        pReleaseDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Production, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Production, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getReleaseDate());
            }
        });

        productionTableView.getColumns().addAll(pIDColumn, pTitelColumn, pDescriptionColumn, pReleaseDateColumn);
        mainPane.getChildren().addAll(productionTableView);

        FilteredList<Production> filteredData = new FilteredList<>(productions);
        productionTableView.setItems(filteredData);

        searchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(searchForProduction(newValue))
        );


    }

    private void chooseProduction() {
        productionTableView.setRowFactory(tv -> {
            TableRow<Production> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Production rowData = row.getItem();
                    informationholder.setProduction(rowData);
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
                if (event.getClickCount() == 1) {
                    selectedProduction = row.getItem();
                }
            });
            return row;
        });
    }

    private Predicate<Production> searchForProduction(String searchText) {
        return Production -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(Production, searchText);
        };
    }

    private boolean searchFindsOrder(Production production, String searchText) {
        return (production.getProductionName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(production.getProductionID()).toString().equals(searchText.toLowerCase());
    }


    public void updateList() {

        productions.clear();
        for (Production p : creditmanager.viewProductions()) {
            productions.add(p);
        }
    }

    public void acceptCreateProduction(ActionEvent actionEvent) {
        //TODO Add information parse to database
        String releaseDate = tpReleaseDate.getEditor().getText();
        String title = tpTitel.getText();
        String description = tpBeskrivelse.getText();
        creditmanager.addProduction(releaseDate, title, description);

        updateList();
        confirmPopUp.setVisible(false);
        confirmPopUp.toBack();
        TAddProduction.setVisible(false);
        TAddProduction.toBack();


    }

    public void openPersonWindow(ActionEvent event) {
        Parent personsViewParent = null;
        try {
            personsViewParent = FXMLLoader.load(getClass().getResource("Persons.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene personsViewScene = new Scene(personsViewParent, 838, 540);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(personsViewScene);
        window.show();
    }

    public void addProduction() {
        confirmPopUp.toFront();
        confirmPopUp.setVisible(true);

    }

    public void cancelledPopUp() {
        confirmPopUp.setVisible(false);
        confirmPopUp.toBack();
    }


    public void deleteProduction() {
        if (selectedProduction != null) {
            tBekræftSletProduktion.setVisible(true);
            tBekræftSletProduktion.toFront();
        } else
            tFejlProduktion.setVisible(true);
        tFejlProduktion.toFront();
    }

    public void pFejlProduktionOK() {
        tFejlProduktion.setVisible(false);
        tFejlProduktion.toBack();
    }

    public void pProduktionSletAnnuller() {
        tBekræftSletProduktion.setVisible(false);
        tBekræftSletProduktion.toBack();
    }

    public void acceptDeleteProduction(ActionEvent event) {
        if (creditmanager.deleteProduction(selectedProduction.getProductionID())) {
            pProduktionSletAnnuller();
            updateList();
        }
    }
}

