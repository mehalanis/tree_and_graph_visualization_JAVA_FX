/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphe;

import Graphe.graphe.Graphe;
import Graphe.graphe.GrapheOriente;
import Graphe.graphe.GrapheNonOriente;
import Graphe.Forms.Cercle;
import Graphe.Johnson.Dijkstra;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class DjikstraController implements Initializable {

    @FXML
    BorderPane border_Pane;
    GrapheController graphe_controller;
    @FXML
    TableView table;
    @FXML
    HBox Creer_circle_box;
    Button start, remove;
    Cercle cercle;
    ComboBox origine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cercle = new Cercle("");
        cercle.setPadding(new Insets(0, 20, 0, 0));
        //Image iconremove = new Image(getClass().getResourceAsStream("not.png"));
        remove = new Button("Supprimer");
        //remove.setGraphic(new ImageView(iconremove));

        origine = new ComboBox();
        Creer_circle_box.getChildren().add(cercle);
        Creer_circle_box.getChildren().add(remove);
        Creer_circle_box.getChildren().add(origine);
        graphe_controller = new GrapheController(cercle, remove, origine);
        border_Pane.setCenter(graphe_controller);
        start = new Button("Start");
        start.setFont(new Font(18));
        start.setPrefSize(80, 45);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dijkstra j = new Dijkstra(graphe_controller.go, table);
                int source=origine.getSelectionModel().getSelectedIndex();
                j.dijkstra_algorithm(source);
                j.AfficherTable(source);
            }
        });
        Creer_circle_box.getChildren().add(start);
    }

}
