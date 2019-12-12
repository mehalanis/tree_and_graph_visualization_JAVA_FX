package algo;

import Formes.rectangle;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class B_Arbre_Controller implements Initializable {

    @FXML
    AnchorPane arbre;
    @FXML
    TextField text;
    @FXML
    Label trouver_label;
    @FXML
    RadioButton predecesseur, successeur;
    @FXML
    HBox List_Nombre;

    @FXML
    public void insert(ActionEvent e) {
    }

    @FXML
    public void Rechercher(ActionEvent e) {
    }

    @FXML
    public void supprimer(ActionEvent e) {

    }

    @FXML
    public void FileTXT(ActionEvent e) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
