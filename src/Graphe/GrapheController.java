/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphe;

import Forms.Cercle;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class GrapheController extends AnchorPane {
    
    AnchorPane graphe;
    @FXML
    VBox bar_graphe;
    Cercle cercle_moved, creer_cercle;
    GrapheOriente go;
    Sommet pass;
    boolean b=false;
    int i=65;
    TableView table;
    
    public GrapheController(Cercle creer_cercle) {
        this.creer_cercle = creer_cercle;
        this.graphe=this;
                go = new GrapheOriente();

        EventHandler<MouseEvent> lineEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Cercle c = (Cercle) event.getSource();
                Sommet cs = go.getSommet(c.val);
                if (b == false) {
                    b = true;
                    
                    pass = cs;
                    pass.getCercle().setStroke(Color.YELLOW);

                } else {
                    if (pass != cs) {
                        Arrow line1;
                        TextInputDialog dialog = new TextInputDialog("0");
                        dialog.setTitle(null);
                        dialog.setHeaderText("Enter Weight of the Edge :");
                        dialog.setContentText(null);

                        Optional<String> result = dialog.showAndWait();
                        int i = 0;
                        if (result.isPresent()) {
                            i = Integer.parseInt(result.get());
                        }
                        go.addArc(pass, cs, i);
                        pass.DessinerArc(cs, graphe);
                        MatriceAdjacence ma = new MatriceAdjacence(go);
                        ma.Afficher();

                    }
                    b = false;
                    pass.getCercle().setStroke(Color.BLACK);
                }

            }
        };
        // EventHandler<MouseEvent> movedevent = ;
         creer_cercle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Sommet s = new Sommet((char)i++ + "");
                go.addSommet(s);
                cercle_moved = s.getCercle();
                //  moved.setLayout(event.getX(), event.getY());
                graphe.getChildren().add(cercle_moved);
                graphe.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (cercle_moved != null) {
                            cercle_moved.setLayout(event.getX(), event.getY());
                        }
                    }
                });
            }
        });

        graphe.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (cercle_moved != null) {
                    cercle_moved.setOnMouseClicked(lineEvent);
                    cercle_moved = null;
                    graphe.setOnMouseMoved(null);
                }
            }
        });

    }

    
}
