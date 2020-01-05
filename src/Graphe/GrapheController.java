package Graphe;

import Graphe.Forms.Cercle;
import Graphe.graphe.Arc;
import Graphe.graphe.GrapheOriente;
import Graphe.graphe.Sommet;
import Graphe.graphe.ArcOriente;
import Graphe.graphe.Arrow;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
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
    boolean b = false;
    int i = 65;
    TableView table;
    ComboBox origine;
    Button remove;
    boolean remove_b = false;

    public GrapheController(Cercle creer_cercle, Button remove, ComboBox origine) {
        this(creer_cercle, remove);
        this.origine = origine;

    }

    public void InitComboBox() {
        origine.getItems().remove(0, go.list_sommet.size());
        for (int i = 0; i < go.list_sommet.size(); i++) {
            origine.getItems().add(go.list_sommet.get(i).getNom());
        }
        origine.getSelectionModel().selectFirst();
    }

    public GrapheController(Cercle creer_cercle, Button remove) {
        this.creer_cercle = creer_cercle;
        this.remove = remove;
        this.graphe = this;
        go = new GrapheOriente();
        remove.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                remove_b = !remove_b;
                System.out.println(remove_b);
            }
        });
        EventHandler<MouseEvent> lineEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Cercle c = (Cercle) event.getSource();
                Sommet cs = go.getSommet(c.val);
                if (event.getButton() == MouseButton.SECONDARY) {
                    TextInputDialog dialog = new TextInputDialog(cs.getNom());
                    dialog.setTitle(null);
                    dialog.setHeaderText(" ");
                    dialog.setContentText(null);

                    Optional<String> result = dialog.showAndWait();
                    String i;
                    if (result.isPresent()) {

                        cs.setNom(result.get());
                    }
                    InitComboBox();
                } else {
                    if (remove_b == true) {

                        ArrayList<Arc> listarc;
                        Arc arc;
                        ArcOriente arcOriente;
                        for (int i = 0; i < go.list_sommet.size(); i++) {
                            listarc = go.getSommet(i).getList_arc();
                           for(Arc arc_i:listarc){
                                if ((arc_i.getSommet() == cs) || (go.getSommet(i) == cs)) {
                                    if (arc_i instanceof ArcOriente) {
                                        arcOriente = (ArcOriente) arc_i;
                                        graphe.getChildren().remove(arcOriente.getLine());
                                        graphe.getChildren().remove(arcOriente.getLabel());
                                    }
                                    listarc.remove(arc_i);
                                }

                            }
                        }

                        go.list_sommet.remove(cs);
                        graphe.getChildren().remove(cs.getCercle());
                        InitComboBox();
                    } else {
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

                            }
                            b = false;
                            pass.getCercle().setStroke(Color.BLACK);
                        }
                    }

                }

            }
        };
        // EventHandler<MouseEvent> movedevent = ;
        creer_cercle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Sommet s = new Sommet((char) i++ + "");
                go.addSommet(s);
                cercle_moved = s.getCercle();

                if (origine != null) {
                    origine.getItems().add(s.getCercle().label.getText());
                    origine.getSelectionModel().selectFirst();
                }
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
