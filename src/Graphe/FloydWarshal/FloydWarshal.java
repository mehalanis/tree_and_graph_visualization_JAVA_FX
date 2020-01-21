package Graphe.FloydWarshal;

import Graphe.graphe.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class FloydWarshal {

    public Graphe g;
    final static int INFINI = 99999;
    public TableView table;
    int dist[][];
    public ArrayList<String[][]> listreult;
    ComboBox combo;

    public FloydWarshal(Graphe g, TableView table) {
        this.g = g;
        this.table = table;
        listreult = new ArrayList<String[][]>();
        floydWarshall(g.MatriceAdjacence().getMatric());
    }

    void floydWarshall(int graph[][]) {
        dist = new int[graph.length][graph.length];
        int i, j, k;
        String[][] result_e = new String[graph.length][graph.length + 1];
        String[] ligne;
        for (i = 0; i < graph.length; i++) {
            ligne = new String[graph.length + 1];
            ligne[0] = g.getSommet(i).getNom();
            for (j = 0; j < graph.length; j++) {
                dist[i][j] = graph[i][j];
                if (dist[i][j] == INFINI) {
                    ligne[j + 1] = "INFINI ";
                } else {
                    ligne[j + 1] = dist[i][j] + "";
                }
            }
            result_e[i] = ligne;
        }
        listreult.add(result_e);
        for (k = 0; k < graph.length; k++) {
            result_e = new String[graph.length][graph.length + 1];
            for (i = 0; i < graph.length; i++) {
                ligne = new String[graph.length + 1];
                ligne[0] = g.getSommet(i).getNom();
                for (j = 0; j < graph.length; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                    if ((dist[i][j] == INFINI) || (dist[i][j] > 90000)) {
                        ligne[j + 1] = "INFINI ";
                    } else {
                        ligne[j + 1] = dist[i][j] + "";
                    }

                }
                result_e[i] = ligne;
            }
            listreult.add(result_e);
        }
        combo = new ComboBox();
        for (int ie = 0; ie < listreult.size(); ie++) {
            combo.getItems().add("Etape " + ie);
        }
        combo.getSelectionModel().selectLast();
        combo.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                printSolution(combo.getSelectionModel().getSelectedIndex());
            }
        });

    }

    public void printSolution(int pos) {
        table.getColumns().clear();
        /* String[][] staffArray = new String[dist.length][dist.length + 1];
        for (int i = 0; i < dist.length; ++i) {

            for (int j = 0; j < dist.length; ++j) {
                if (j == 0) 
                    staffArray[i][0] =  g.getList_sommet().get(i).getNom();
                
                    if (dist[i][j] == INFINI) {
                        staffArray[i][j + 1] = "INFINI ";
                    } else {
                        staffArray[i][j + 1] = dist[i][j] + "";
                    }
                
            }
            System.out.println();
        }*/
        String[][] staffArray = listreult.get(pos);
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(staffArray));
        // data.remove(0);//remove titles from data

        combo.setMaxWidth(400);
        for (int i = 0; i < staffArray[0].length; i++) {
            TableColumn tc;
            if (i > 0) {
                tc = new TableColumn(g.getList_sommet().get(i - 1).getNom());
            } else {
                tc = new TableColumn("");
                tc.setGraphic(combo);

            }
            tc.setSortable(false);

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
}
