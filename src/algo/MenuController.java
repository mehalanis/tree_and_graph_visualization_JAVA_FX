
package algo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class MenuController implements Initializable {
    Pane panel;
    Label titre;
    @FXML
    Button btn_abr,btn_tas_min,btn_amr,btn_avl,btn_tas_max,btn_b_arbre;
    @FXML
    public void LoadArbre(ActionEvent e){
        String s=TestBtn(e);
        if(s!=null){
            titre.setText(s);
            panel.getChildren().clear();
            try {
              Parent root = FXMLLoader.load(getClass().getResource(s+".fxml"));
              panel.getChildren().add(root);
            } catch (IOException ex) {
              Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    public String TestBtn(ActionEvent e){
        String s=null;
        Button b=(Button)e.getSource();
        if(b==btn_abr) s="ABR";
        else if(b==btn_tas_min) s="TAS_min";
        else if(b==btn_amr) s="AMR";
        else if(b==btn_avl)s="AVL";
        else if(b==btn_tas_max)s="TAS_max";
        else if(b==btn_b_arbre)s="B_Arbre";
        return s;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setPanel(Pane p){
        this.panel=p;
    }

    public void setTitre(Label titre) {
        this.titre = titre;
    }
    
}
