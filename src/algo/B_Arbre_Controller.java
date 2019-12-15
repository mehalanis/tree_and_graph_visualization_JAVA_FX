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
    RadioButton predecesseur, successeur;
    @FXML
    HBox List_Nombre;

    @FXML
    public void insert(ActionEvent e) {
        bTree.insert(Integer.parseInt(text.getText()));
        bTree.Afficher();
    }

    @FXML
    public void Rechercher(ActionEvent e) {
        bTree.RechercheAnimation(Integer.parseInt(text.getText()),trouver_label);
       /* BTNode<Pair<Integer>> s=bTree.getNode(Integer.parseInt(text.getText()));
        String k=s.toString();
        if (!k.equals("NullNode")) {
            trouver_label.setText("Found : " +text.getText());
        } else {
            trouver_label.setText("Not Found : " + text.getText());

        }*/

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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bTree = new BTree<Integer>(3, arbre);

    }

}
