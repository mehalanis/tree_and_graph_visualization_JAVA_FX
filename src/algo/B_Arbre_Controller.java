package algo;

import Formes.rectangle;
import b_arbre.AfficherB_Tree;
import b_arbre.B_Arbre;
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

    B_Arbre racine4;
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
        racine4 = racine4.Insertion(Integer.parseInt(text.getText()));
        racine4 = racine4.getRacine();
        AfficherB_Tree aff = new AfficherB_Tree(racine4, arbre);
        aff.Afficher();
     //   racine4.AfficherT();
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

    @FXML
    public void ChangerOrdre(ActionEvent e) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        racine4 = new B_Arbre(25);
        racine4.setOrdre(3); // pour les ordre pair on prends tjr ordre 3 
        racine4 = racine4.Insertion(60);
        racine4 = racine4.Insertion(35);
        racine4 = racine4.Insertion(10);
        racine4 = racine4.Insertion(5);
        racine4 = racine4.Insertion(18);
        racine4 = racine4.Insertion(22);
        racine4 = racine4.Insertion(44);
        racine4 = racine4.Insertion(13);
        racine4 = racine4.getRacine();
        AfficherB_Tree aff = new AfficherB_Tree(racine4, arbre);
        aff.Afficher();
        //racine4.Afficherarbre();
        // racine4.AfficherT();
        // MyCanvas my=new MyCanvas(400,400,racine4,arbre);
        //   my.DrawBTree();
    }

}
