
package algo;

import ABR_AVL.AVL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


public class AVLController implements Initializable {
    AVL avl;
    @FXML AnchorPane arbre;
    @FXML TextField text;
    @FXML Label trouver_label;
    @FXML RadioButton predecesseur,successeur;
    @FXML
    public void insert(ActionEvent e){
       avl.insertionAnimation(text.getText()); 
    }
    @FXML
    public void Rechercher(ActionEvent e){
        avl.rechercher(trouver_label,text.getText());
    }
    @FXML
    public void supprimer(ActionEvent e){
        avl.delete(text.getText(),((predecesseur.isSelected())?'P':'S'));
        avl.Afficher();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        avl=new AVL(arbre);
        trouver_label.setText("");
        ToggleGroup tg=new ToggleGroup();
        predecesseur.setToggleGroup(tg);
        successeur.setToggleGroup(tg);
        predecesseur.setSelected(true);
    }    
    
}
