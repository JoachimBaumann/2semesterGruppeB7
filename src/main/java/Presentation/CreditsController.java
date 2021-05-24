package Presentation;


import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;

public class CreditsController {
    public TitledPane tAddPerson;

    public void showAddPersonWindow(){
        tAddPerson.setVisible(true);
    }

    public void hideAddPersonWindow(){
    tAddPerson.setVisible(false);
    }
    /* public ImageView addPersonImage(){
        return null;
    }

     */
}
