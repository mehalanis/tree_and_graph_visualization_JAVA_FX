/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphe;

import Graphe.Forms.Cercle;
import Graphe.Johnson.Jhonson;
import Graphe.graphe.GrapheOriente;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Johnson_Controller implements Initializable {

   /* @FXML
    VBox border_Pane;*/
    @FXML
     GridPane dijkstra_grid_pane;
    
    GrapheController graphe_controller;
    @FXML
    TableView table_bell,table_etape2;
    @FXML
    HBox Creer_circle_box;
    Button start, remove;
    Cercle cercle;
    @FXML
    Tab tabpane_graphe;
    ArrayList<TableView> list_table_dijkstra;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cercle = new Cercle("");
        cercle.setPadding(new Insets(0, 20, 0, 0));
        // Image iconremove = new Image(getClass().getResourceAsStream("not.png"));
        remove = new Button("Supprimer");
        // remove.setGraphic(new ImageView(iconremove));
        remove.setPadding(new Insets(0, 20, 0, 0));
        Creer_circle_box.getChildren().add(cercle);
        Creer_circle_box.getChildren().add(remove);
        graphe_controller = new GrapheController(cercle, remove);
        //graphe_controller.setPrefHeight(400);
       tabpane_graphe.setContent(graphe_controller);
        start = new Button("Start");
        start.setFont(new Font(18));
        start.setPrefSize(80, 45);
        list_table_dijkstra=new ArrayList<TableView> ();
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Jhonson john=new Jhonson((GrapheOriente) graphe_controller.go);
                john.etape1();
                john.etape2(table_bell);
                john.etape3(table_etape2);
                //list_table_dijkstra.removeAll(list_table_dijkstra);
                int len=graphe_controller.go.getList_sommet().size();
                for(int i=0;i<len;i++){
                    list_table_dijkstra.add(new TableView());
                }
                john.etape4(list_table_dijkstra);
                int k=0;
                for(int i=0;(i<len)&&(k<len);i++){
                    for(int j=0;(j<2)&&(k<len);j++){
                        dijkstra_grid_pane.add(list_table_dijkstra.get(k), j, i);
                        k++;
                    }
                }
                
            }
        });
        Creer_circle_box.getChildren().add(start);
    }

}
