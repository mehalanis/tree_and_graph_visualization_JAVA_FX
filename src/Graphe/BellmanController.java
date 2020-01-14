package Graphe;

import Graphe.graphe.Graphe;
import Graphe.graphe.GrapheOriente;
import Graphe.graphe.GrapheNonOriente;
import Graphe.Bellman.bellman;
import Graphe.Forms.Cercle;
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

public class BellmanController implements Initializable {

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
    GrapheOriente graphe;

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
        graphe_controller.i = 0;
        graphe_controller.bellman = true;
        border_Pane.setCenter(graphe_controller);
        start = new Button("Start");
        start.setFont(new Font(18));
        start.setPrefSize(80, 45);
        this.graphe = (GrapheOriente) graphe_controller.go;
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bellman bel = new bellman(graphe_controller.go);
                int source = origine.getSelectionModel().getSelectedIndex();
                ArrayList<ArrayList<String>> myResult = bel.PlusCC(bel.list_sommet.get(0)); // vers l'orgine à tous les sommets
                String[][] result = new String[myResult.size()][myResult.get(0).size() + 1];

                result[0][0] = "init";

                for (int i = 0; i < myResult.size(); i++) {
                    if (i > 0) {
                        result[i][0] = i + "";
                    }
                    for (int j = 0; j < myResult.get(i).size(); j++) {
                        result[i][j + 1] = myResult.get(i).get(j);
                    }
                }
                int[] distance = new int[result[0].length];

                for (int j = 0; j < result[0].length - 1; j++) {
                    if (result[result.length - 1][j + 1].equals("inf")) {
                        distance[j] = 99999;
                    } else {
                        distance[j] = Integer.parseInt(result[result.length - 1][j + 1]);
                    }

                }
                graphe_controller.go.InitColorRED(source, distance);
                table.getColumns().clear();

                ObservableList<String[]> data = FXCollections.observableArrayList();
                data.addAll(Arrays.asList(result));
                // data.remove(0);//remove titles from data

                for (int i = 0; i < result[0].length; i++) {
                    TableColumn tc;
                    if (i > 0) {
                        tc = new TableColumn(graphe_controller.go.getList_sommet().get(i - 1).getNom());
                    } else {
                        tc = new TableColumn("");
                    }

                    final int colNo = i;
                    tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                            return new SimpleStringProperty((p.getValue()[colNo]));
                        }
                    });
                    tc.setPrefWidth(120);
                    table.getColumns().add(tc);
                }
                table.setItems(data);

            }
        });
        Creer_circle_box.getChildren().add(start);

    }

}
