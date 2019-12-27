package Arbre;

import AMR.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class AMR_Controller implements Initializable {

    amr amr;
    @FXML
    Group arbre;
    @FXML
    TextField text;
    @FXML
    Label trouver_label;
    @FXML
    RadioButton predecesseur, successeur;
    @FXML
    HBox List_Nombre;
    @FXML
    ComboBox ordre;

    @FXML
    public void insert(ActionEvent e) {
        try {
            amr.insertion(Integer.parseInt(text.getText()));
            amr.Afficher();
        } catch (NumberFormatException ere) {
        }
        text.setText("");

    }

    @FXML
    public void Rechercher(ActionEvent e) {
        try {
            amr.RechercherAnimation(Integer.parseInt(text.getText()), trouver_label);
        } catch (NumberFormatException ere) {
        }
        text.setText("");

    }

    @FXML
    public void supprimer(ActionEvent e) {
        try {
            amr.SwitchSucPred(((predecesseur.isSelected()) ? 'P' : 'S'));
            amr.suppression(Integer.parseInt(text.getText()));
            amr.Afficher();
        } catch (NumberFormatException ere) {
        }
        text.setText("");

    }

    @FXML
    public void FileTXT(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier txt", "*.txt"));
        File f = fc.showOpenDialog(null);
        LoadFileTXT load = new LoadFileTXT(amr, f, arbre, List_Nombre);
        load.start();
    }

    @FXML
    public void ChangerOrdre(ActionEvent e) {
        amr = new amr(Integer.parseInt((String) ordre.getValue()), arbre);
        amr.Afficher();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        amr = new amr(3, arbre);
        for (int i = 3; i <= 5; i++) {
            ordre.getItems().add(i + "");
        }
        trouver_label.setText("");
        ToggleGroup tg = new ToggleGroup();
        predecesseur.setToggleGroup(tg);
        successeur.setToggleGroup(tg);
        predecesseur.setSelected(true);
    }

}
