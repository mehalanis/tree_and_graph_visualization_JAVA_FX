package algo;

import ABR_AVL.ABR;
import ABR_AVL.LoadFileTXT;
import Formes.Cercle;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class ABRController implements Initializable {

    ABR abr;
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
        abr.insertionAnimation(text.getText());
    }

    @FXML
    public void Rechercher(ActionEvent e) {
        abr.rechercher(trouver_label, text.getText());
    }

    @FXML
    public void supprimer(ActionEvent e) {
        abr.suppression(text.getText(), ((predecesseur.isSelected()) ? 'P' : 'S'));
        abr.Afficher();
    }

    @FXML
    public void FileTXT(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier txt", "*.txt"));
        File f=fc.showOpenDialog(null);
        LoadFileTXT load = new LoadFileTXT(abr, f, arbre,List_Nombre);
        load.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        abr = new ABR(arbre);
        trouver_label.setText("");
        ToggleGroup tg = new ToggleGroup();
        predecesseur.setToggleGroup(tg);
        successeur.setToggleGroup(tg);
        predecesseur.setSelected(true);
    }
}
