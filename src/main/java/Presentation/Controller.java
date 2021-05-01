package Presentation;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Controller {
    public HBox hBoxSignIn;

    public void bSignIn(ActionEvent actionEvent) {
        hBoxSignIn.setVisible(true);
    }
    public void bCancel(ActionEvent actionEvent){
        hBoxSignIn.setVisible(false);
    }
}
