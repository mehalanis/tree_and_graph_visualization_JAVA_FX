package Arbre;

import TAS.LoadFileTXT;
import TAS.TASmax;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class TAS_max_Controller implements Initializable {

    TASmax tas;
    @FXML
    AnchorPane arbre;
    @FXML
    TextField text;
    @FXML
    Label trouver_label;
    @FXML
    HBox List_Nombre;

    @FXML
    public void insert(ActionEvent e) {
        try {
            tas.inserer(Integer.parseInt(text.getText()));
        } catch (NumberFormatException ere) {
        }
        text.setText("");
    }

    @FXML
    public void Rechercher(ActionEvent e) {
        try {
            tas.RechercherAnimation(Integer.parseInt(text.getText()));

        } catch (NumberFormatException ere) {
        }
        text.setText("");
    }

    @FXML
    public void supprimer(ActionEvent e) {
        try {
            tas.Supprimer(Integer.parseInt(text.getText()));

        } catch (NumberFormatException ere) {
        }
        text.setText("");
    }

    @FXML
    public void FileTXT(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier txt", "*.txt"));
        File f = fc.showOpenDialog(null);
        LoadFileTXT load = new LoadFileTXT(tas, f, arbre, List_Nombre);
        load.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tas = new TASmax(40, arbre, trouver_label);
        trouver_label.setText("");
    }
}
