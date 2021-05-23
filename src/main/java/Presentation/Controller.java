package Presentation;

import Domain.Catalog.Production;
import Domain.Facade;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Controller implements Initializable {
    public Button bSearch;
    public Text tLogProd;
    public Text tLogAdmin;
    public Text tLogUser;
    public Button bAddProduction;
    public Button bAddCredit;
    public Button bsignOut;
    public HBox hBoxSignIn;
    public VBox vBoxSignIn;
    public Button bDark;
    public ImageView darkBackground;
    public AnchorPane newMember;
    public TextField userName;
    public PasswordField userPassword;
    public Label label;
    public SplitMenuButton logInd;
    public ImageView lightCredit;
    public ImageView darkCredit;
    public TableColumn<Production, Integer> tID;
    public TableColumn<Production, String> tTitel;
    public TableColumn<Production, String> tDescription;
    public TableColumn <Production, String>tReleaseDate;
    public TableView<Production> tableView;
    public VBox vBoxLogIn;
    public VBox vAddProduction;
    public TextField tpTitel;
    public TextField tpBeskrivelse;
    public TextField tpReleaseDate;
    public AnchorPane mainPane;
    public TextField searchField;
    private Facade facade = new Facade();
    ObservableList<Production> productions;

    //configure the table
    @FXML private TableView<Production> productionTableView;
    @FXML private TableColumn<Production, Integer> pIDColumn;
    @FXML private TableColumn<Production, String> pTitelColumn;
    @FXML private TableColumn<Production, String> pDescriptionColumn;
    @FXML private TableColumn<Production, String> pReleaseDateColumn;

    //These instance variables are used to create new Person objects
    @FXML private TextField pIDTextfield;
    @FXML private TextField pTitelTextField;
    @FXML private TextField pDescriptionTextField;
    @FXML private TextField pReleaseDateTextField;



    public void signIn(ActionEvent actionEvent) throws IOException {
        if (userName.getText().equals("producer") && userPassword.getText().equals("1234")) {
            tLogProd.setVisible(true);
            hBoxSignIn.setVisible(false);
            logInd.setVisible(false);
            vBoxSignIn.setVisible(false);
            bAddProduction.setVisible(true);
            bAddCredit.setVisible(true);
            bsignOut.setVisible(true);
            //HUndeprutter lugter ik
        }
        if (userName.getText().equals("systemadmin") && userPassword.getText().equals("5678")) {
            tLogAdmin.setVisible(true);
            hBoxSignIn.setVisible(false);
            logInd.setVisible(false);
            vBoxSignIn.setVisible(false);
            bAddProduction.setVisible(true);
            bAddCredit.setVisible(true);
            bsignOut.setVisible(true);
        }
        if (userName.getText().equals("bruger") && userPassword.getText().equals("91011")) {
            tLogUser.setVisible(true);
            hBoxSignIn.setVisible(false);
            logInd.setVisible(false);
            vBoxSignIn.setVisible(false);
            bsignOut.setVisible(true);
        } else {
            label.setVisible(true);
            label.setText("Forkert brugernavn eller adgangskode");
        }
    }

    public void signOutP(ActionEvent actionEvent) throws IOException {
        hBoxSignIn.setVisible(true);
        logInd.setVisible(true);
        vBoxSignIn.setVisible(true);
        bAddProduction.setVisible(false);
        bAddCredit.setVisible(false);
        tLogProd.setVisible(false);
        tLogAdmin.setVisible(false);
        tLogUser.setVisible(false);
        label.setVisible(false);
        bsignOut.setVisible(false);
        vAddProduction.setVisible(false);
    }


    public void bSearchP(ActionEvent actionEvent) throws IOException {
        Parent rootS1 = FXMLLoader.load(getClass().getResource("SearchView.fxml"));
        Stage primaryS1 = new Stage();
        primaryS1.setTitle(("Catalog"));
        primaryS1.setScene(new Scene(rootS1, 406, 418));
        primaryS1.setResizable(false);
        primaryS1.show();
    }


    public void addProduction(ActionEvent actionEvent) {
    }

    ;

    public void logoClick(ActionEvent actionEvent) {
        vBoxSignIn.setVisible(false);
        newMember.setVisible(false);
    }

    public void actionOne(ActionEvent actionEvent) {
        vBoxSignIn.setVisible(true);
        newMember.setVisible(false);
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
        vBoxSignIn.setVisible(false);
    }

    public void viewAllProductions() {
        updateList();
    }


    public void bDark(ActionEvent actionEvent){
            if (darkBackground.isVisible() == true){
                darkBackground.setVisible(false);
                darkCredit.setVisible(false);
                lightCredit.setVisible(true);

            }else{
                darkBackground.setVisible(true);
                darkCredit.setVisible(true);
                lightCredit.setVisible(false);

            }
    }
    public void addProductionButton(){
        vAddProduction.setVisible(true);
        vAddProduction.toFront();
    }
    public void closeAddProductionWindow(){
        vAddProduction.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initializing tableview
        productionTableView = new TableView<Production>(getProduction());

        // this makes it possible to doubleClick a row
        productionTableView.setRowFactory(tv -> {
            TableRow<Production> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Production rowData = row.getItem();

                    System.out.println("Double click on: "+rowData.getProductionID());
                }
            });
            return row ;
        });

        pIDColumn = new TableColumn<>("productionID");
        pTitelColumn = new TableColumn<>("productionName");
        pDescriptionColumn = new TableColumn<>("description");
        pReleaseDateColumn = new TableColumn<>("releaseDate");

        pIDColumn.setText("ID");
        pTitelColumn.setText("Titel");
        pDescriptionColumn.setText("Description");
        pReleaseDateColumn.setText("Release Date");
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

        productionTableView.getColumns().addAll(pIDColumn,pTitelColumn,pDescriptionColumn,pReleaseDateColumn);
        mainPane.getChildren().addAll(productionTableView);

        FilteredList<Production> filteredData = new FilteredList<>(FXCollections.observableList(productions));
        productionTableView.setItems(filteredData);

        searchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(createPredicate(newValue))
        );


    }

    private Predicate<Production> createPredicate(String searchText){
        return Production -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(Production, searchText);
        };
    }

    private boolean searchFindsOrder(Production production, String searchText){
        return (production.getProductionName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(production.getProductionID()).toString().equals(searchText.toLowerCase());
    }



    public ObservableList<Production>  getProduction()
    {
        productions = FXCollections.observableArrayList();

    facade.updateCatalog();

    updateList();

        return productions;
    }




    public void updateList(){
        productions.clear();
        for(Production p : facade.getCatalog().getProductionList().values()) {
            productions.add(p);
        }
    }

    public void bCreateProduction(ActionEvent actionEvent) {
        String releaseDate = tpReleaseDate.getText();
        String title = tpTitel.getText();
        String description = tpBeskrivelse.getText();
        facade.addProduction(releaseDate,title,description);

        updateList();

    }


}

