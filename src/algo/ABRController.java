
package algo;

import ABR_AVL.ABR;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class ABRController implements Initializable {
    ABR abr;
    @FXML Group arbre;
    @FXML TextField text;
    @FXML Label trouver_label;
    int k=3;
    @FXML
    public void insert(ActionEvent e){
       abr.insertion(text.getText()); 
       Afficher();
    }
    @FXML
    public void Rechercher(ActionEvent e){
        abr.rechercher(trouver_label,text.getText());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        abr=new ABR(arbre);
        abr.insertion(5);
        abr.insertion(4);
        abr.insertion(6);
        Afficher();
        trouver_label.setText("");
    }    
    public void Afficher(){
        arbre.getChildren().clear();
        int gap = abr.depth(abr.root);
        gap = gap * gap *8;
        abr.Afficher(abr.root, arbre ,1200/ 2, 0,1200/ 2, 0,0,gap);
    }
}
