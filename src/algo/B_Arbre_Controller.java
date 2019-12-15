package algo;

import BTree.*;
import BTree.Pair;
import Formes.rectangle;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class B_Arbre_Controller implements Initializable {

    BTree<Integer> bTree;
    @FXML
    Group arbre;
    @FXML
    TextField text;
    @FXML
    Label trouver_label;
    @FXML
    HBox List_Nombre;
    @FXML
    ComboBox ordre;
    @FXML
    public void insert(ActionEvent e) {
        bTree.insert(Integer.parseInt(text.getText()));
        bTree.Afficher();
    }

    @FXML
    public void Rechercher(ActionEvent e) {
        bTree.RechercheAnimation(Integer.parseInt(text.getText()),trouver_label);

    }

    @FXML
    public void supprimer(ActionEvent e) {
        bTree.delete(Integer.parseInt(text.getText()));
        bTree.Afficher();
    }

    @FXML
    public void FileTXT(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier txt", "*.txt"));
        File f = fc.showOpenDialog(null);
        LoadFileTXT load = new LoadFileTXT(bTree, f, arbre, List_Nombre);
        load.start();
    }

    @FXML
    public void ChangerOrdre(ActionEvent e) {
        bTree = new BTree<Integer>(Integer.parseInt((String) ordre.getValue()), arbre);
        bTree.Afficher();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bTree = new BTree<Integer>(3, arbre);
        trouver_label.setText("");
        for(int i=3;i<=5;i++){
            ordre.getItems().add(i+"");
        }
    }

}
