package Presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonsController {
    public TableView personsTV;
    public TitledPane tAddPerson;
    public TitledPane confirmPopUp;

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
        confirmPopUp.toFront();
        confirmPopUp.setVisible(true);
    }
}
