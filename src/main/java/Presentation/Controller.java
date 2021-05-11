package Presentation;

import Domain.Catalog.Catalog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public HBox hBoxSignIn;
    public VBox vBoxSignIn;
    public Button bDark;
    public ImageView darkBackground;
    public ImageView creditDark;
    public ImageView lightCredits;
    public AnchorPane newMember;
    public TextField userName;
    public PasswordField userPassword;
    public Label label;
    Domain.Catalog.Catalog catalog = new Catalog();


    public void signIn(ActionEvent actionEvent) throws IOException {
        if(userName.getText().equals("producer") && userPassword.getText().equals("1234")) {
            Stage primaryPro = new Stage();
            Parent rootPro = FXMLLoader.load(getClass().getResource("producer.fxml"));
            Scene scenePro = new Scene(rootPro, 838, 540);
            primaryPro.setScene(scenePro);
            primaryPro.show();
        }
        if(userName.getText().equals("systemadmin") && userPassword.getText().equals("5678")) {
            Stage primaryAdmin = new Stage();
            Parent rootAdmin = FXMLLoader.load(getClass().getResource("systemadmin.fxml"));
            Scene sceneAdmin = new Scene(rootAdmin, 838, 540);
            primaryAdmin.setScene(sceneAdmin);
            primaryAdmin.show();
        }
        if(userName.getText().equals("bruger") && userPassword.getText().equals("91011")) {
            Stage primaryUser = new Stage();
            Parent rootUser = FXMLLoader.load(getClass().getResource("user.fxml"));
            Scene sceneUser = new Scene(rootUser, 838,540);
            primaryUser.setScene(sceneUser);
            primaryUser.show();
        } else {
          label.setText("Forkert brugernavn eller adgangskode");
        }
    }

    public void signOutP(ActionEvent actionEvent) throws IOException {
        Parent rootP = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage primaryP = new Stage();
        primaryP.setTitle("TV2 Credits");
        primaryP.setScene(new Scene(rootP, 838, 540));
        primaryP.show();
    }

    public void signOutA(ActionEvent actionEvent) throws IOException {
        Parent rootA = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage primaryA = new Stage();
        primaryA.setTitle("TV2 Credits");
        primaryA.setScene(new Scene(rootA, 838, 540));
        primaryA.show();
    }

    public void signOutU(ActionEvent actionEvent) throws IOException {
        Parent rootU = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage primaryU = new Stage();
        primaryU.setTitle("TV2 Credits");
        primaryU.setScene(new Scene(rootU,838,540));
        primaryU.show();
    }

    public void logoClick(ActionEvent actionEvent){
        vBoxSignIn.setVisible(false);
        newMember.setVisible(false);
    }

    public void actionOne(ActionEvent actionEvent){
        vBoxSignIn.setVisible(true);
        newMember.setVisible(false);
    }

    public void actionTwo(ActionEvent actionEvent){
        vBoxSignIn.setVisible(true);
        newMember.setVisible(true);
    }

    public void rCancel(ActionEvent actionEvent){
        newMember.setVisible(false);
    }

    public void forgotCode(ActionEvent actionEvent) {
        System.out.println("Forgot the code");
    }

    public void bCancel(ActionEvent actionEvent){
        vBoxSignIn.setVisible(false);
    }
    public void bDark(ActionEvent actionEvent){
            if (darkBackground.isVisible() == true){
                darkBackground.setVisible(false);
                creditDark.setVisible(false);
                lightCredits.setVisible(true);
            }else{
                darkBackground.setVisible(true);
                creditDark.setVisible(true);
                lightCredits.setVisible(false);
            }
    }
}
