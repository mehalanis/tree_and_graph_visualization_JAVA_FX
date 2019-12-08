
package algo;

import TAS.TASmin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class TAS_min_Controller implements Initializable {
    TASmin tas;
    @FXML AnchorPane arbre;
    @FXML TextField text;
    @FXML Label trouver_label;
    @FXML
    public void insert(ActionEvent e){
       tas.inserer(Integer.parseInt(text.getText())); 
       tas.Afficher();
    }
    @FXML
    public void Rechercher(ActionEvent e){
       // tas.rechercher(trouver_label,text.getText());
    }
    @FXML
    public void supprimer(ActionEvent e){
        tas.Supprimer(Integer.parseInt(text.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tas=new TASmin(15,arbre);
    }    
}
