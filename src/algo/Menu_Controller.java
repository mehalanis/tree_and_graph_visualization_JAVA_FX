/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import Arbre.MenuController;
import Graphe.Menu_Graphe_Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author anispitchou
 */
public class Menu_Controller implements Initializable {

    Pane panel;
    Label titre;
    @FXML
    Button arbre, graphe;

    @FXML
    public void Load(ActionEvent e) {
        String s;
        if (arbre == (Button) e.getSource()) {
            s = "/Arbre/menu";
        } else {
            s = "/Graphe/Menu_Graphe";
        }

        if (s != null) {
            panel.getChildren().clear();
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource(s + ".fxml"));
                load.load();
                if (s.equals("/Arbre/menu")) {
                    MenuController i = load.getController();
                    i.setPanel(panel);
                    i.setTitre(titre);
                } else {
                    Menu_Graphe_Controller i = load.getController();
                    i.setPanel(panel);
                    i.setTitre(titre);
                }
                Parent root = load.getRoot();
                panel.getChildren().add(root);
            } catch (IOException ex) {
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setPanel(Pane panel) {
        this.panel = panel;
    }

    public void setTitre(Label titre) {
        this.titre = titre;
    }

}
