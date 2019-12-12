package algo;

import TAS.LoadFileTXT;
import TAS.TASmin;
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

public class TAS_min_Controller implements Initializable {

    TASmin tas;
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
        tas.inserer(Integer.parseInt(text.getText()));
        tas.Afficher();
    }

    @FXML
    public void Rechercher(ActionEvent e) {
        tas.RechercherAnimation(Integer.parseInt(text.getText()));
    }

    @FXML
    public void supprimer(ActionEvent e) {
        tas.Supprimer(Integer.parseInt(text.getText()));
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
        tas = new TASmin(40, arbre, trouver_label);
    }
}
