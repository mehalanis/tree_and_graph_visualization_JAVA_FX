
package algo;

import ABR_AVL.ABR;
import Formes.Cercle;
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


public class ABRController implements Initializable {
    ABR abr;
    @FXML AnchorPane arbre;
    @FXML TextField text;
    @FXML Label trouver_label;
    @FXML RadioButton predecesseur,successeur;
    @FXML
    public void insert(ActionEvent e){
       abr.insertionAnimation(text.getText()); 
    }
    @FXML
    public void Rechercher(ActionEvent e){
        abr.rechercher(trouver_label,text.getText());
    }
    @FXML
    public void supprimer(ActionEvent e){
        abr.suppression(text.getText(),((predecesseur.isSelected())?'P':'S'));
        Afficher();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        abr=new ABR(arbre);
        abr.insertion(5);
        abr.insertion(4);
        abr.insertion(6);
        Afficher();
        trouver_label.setText("");
        ToggleGroup tg=new ToggleGroup();
        predecesseur.setToggleGroup(tg);
        successeur.setToggleGroup(tg);
        predecesseur.setSelected(true);
    }    
    public void Afficher(){
        arbre.getChildren().clear();
        int gap = abr.depth(abr.root);
        gap = gap * gap *10;
        abr.Afficher(abr.root,null, arbre ,1200/ 2, 0,1200/ 2, 0,0,gap);
    }
   
}
