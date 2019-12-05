
package algo;

import TAS.TASmin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;

public class TAS_min_Controller implements Initializable {
    TASmin tas;
    @FXML Group arbre;
    @FXML TextField text;
    @FXML Label trouver_label;
    @FXML
    public void insert(ActionEvent e){
       int pos=tas.inserer(Integer.parseInt(text.getText())); 
       Afficher();
    }
    @FXML
    public void Rechercher(ActionEvent e){
       // tas.rechercher(trouver_label,text.getText());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tas=new TASmin(15);
    }    
    public void Afficher(){
        arbre.getChildren().clear();
        //int gap = abr.depth(abr.root);
        int gap=3;
        gap = gap * gap *8;
        tas.Afficher(1, arbre ,1200/ 2, 0,1200/ 2, 0,0,gap);
    }
}
