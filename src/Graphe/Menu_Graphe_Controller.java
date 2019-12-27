
package Graphe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Menu_Graphe_Controller implements Initializable {
Pane panel;
    Label titre;
    @FXML
    public void LoadGraphe(ActionEvent e){
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setPanel(Pane panel) {
        this.panel = panel;
    }

    public void setTitre(Label titre) {
        this.titre = titre;
    }
    
}
