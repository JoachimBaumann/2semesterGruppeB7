package Presentation;

import Domain.Catalog.Production;
import Domain.Facade;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private Facade facade = new Facade();


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

    ObservableList<Production> observableList = FXCollections.observableArrayList(facade.viewProductions());

    public void viewAllProductions(){
        tableView.setItems(observableList);
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
    }
    public void closeAddProductionWindow(){
        vAddProduction.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tID.setCellValueFactory(new PropertyValueFactory<Production,Integer>("productionID"));
        tTitel.setCellValueFactory(new PropertyValueFactory<Production,String>("productionName"));
        tReleaseDate.setCellValueFactory(new PropertyValueFactory<Production,String>("releaseDate"));

        tableView.setItems(observableList);
    }
}

