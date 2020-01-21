package algo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class indexController implements Initializable {

    GridPane menu;
    Parent root;
    @FXML
    private StackPane panel;

    @FXML
    private Label titre;

    @FXML
    public void home() {
        panel.getChildren().clear();
        if (root != null) {
            panel.getChildren().add(menu);
            titre.setText("Projet ALGO");
        } else {
            
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("menu.fxml"));

            load.load();
            Menu_Controller i = load.getController();
            i.setPanel(panel);
            i.setTitre(titre);
            menu = load.getRoot();

            panel.getChildren().add(menu);
            titre.setText("Projet 1 ALGO");

        } catch (IOException ex) {
            Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
