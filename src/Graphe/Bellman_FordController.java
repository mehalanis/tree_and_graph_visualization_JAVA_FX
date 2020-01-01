package Graphe;

import Bellman_Ford.bell_ford;
import Forms.Cercle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Bellman_FordController implements Initializable {

       @FXML
    BorderPane border_Pane;
    GrapheController graphe_controller;
    @FXML
    TableView table;
    @FXML
    HBox Creer_circle_box;
    Button start;
    Cercle cercle;


  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         cercle = new Cercle("");
        cercle.setPadding(new Insets(0, 20, 0, 0));

        Creer_circle_box.getChildren().add(cercle);
        graphe_controller = new GrapheController(cercle);
        border_Pane.setCenter(graphe_controller);
        start = new Button("Start");
        start.setFont(new Font(18));
        start.setPrefSize(80, 45);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               bell_ford bell=new bell_ford(graphe_controller.go,table);
               bell.BellmanFordEvaluation(0);
               bell.Affichar();
            }
        });
        Creer_circle_box.getChildren().add(start);
//                border_Pane.setCenter(new GrapheController(cercle));

    /*    try {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("Graphe.fxml"));
            load.load();
            graphe_controller = load.getController();
            graphe_controller.setTable(table);
            border_Pane.setCenter(load.getRoot());
        } catch (IOException ex) {
            Logger.getLogger(Bellman_FordController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      // graphe_controller.AffichierCol(new ArrayList<String>());
    }

}
