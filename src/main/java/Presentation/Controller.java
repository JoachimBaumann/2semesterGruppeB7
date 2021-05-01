package Presentation;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {
    public HBox hBoxSignIn;
    public VBox vBoxSignIn;
    public Button bDark;
    public ImageView darkBackground;

    public void bSignIn(ActionEvent actionEvent) {
        vBoxSignIn.setVisible(true);
    }
    public void bCancel(ActionEvent actionEvent){
        vBoxSignIn.setVisible(false);
    }
    public void bDark(ActionEvent actionEvent){
            if (darkBackground.isVisible() == true){
                darkBackground.setVisible(false);
            }else{
                darkBackground.setVisible(true);
            }
    }
}
