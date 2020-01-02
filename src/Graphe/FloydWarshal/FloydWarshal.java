/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphe.FloydWarshal;

import Graphe.Graphe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    public FloydWarshal(Graphe g, TableView table) {
        this.g = g;
        this.table = table;
        floydWarshall(g.MatriceAdjacence().getMatric());
    }

    void floydWarshall(int graph[][]) {
        dist = new int[graph.length][graph.length];
        int i, j, k;

        for (i = 0; i < graph.length; i++) {
            for (j = 0; j < graph.length; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (k = 0; k < graph.length; k++) {
            for (i = 0; i < graph.length; i++) {
                for (j = 0; j < graph.length; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

    }

    public void printSolution() {
        table.getColumns().clear();
        String[][] staffArray = new String[dist.length][dist.length + 1];
        for (int i = 0; i < dist.length; ++i) {

            for (int j = 0; j < dist.length; ++j) {
                if (j == 0) 
                    staffArray[i][0] =  g.getList_sommet().get(i).getNom();
                
                    if (dist[i][j] == INFINI) {
                        System.out.print("INFINI ");
                        staffArray[i][j + 1] = "INFINI ";
                    } else {
                        System.out.print(dist[i][j] + "   ");
                        staffArray[i][j + 1] = dist[i][j] + "";
                    }
                
            }
            System.out.println();
        }

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(staffArray));
        // data.remove(0);//remove titles from data

        for (int i = 0; i < staffArray[0].length; i++) {
            TableColumn tc ;
            if(i>0){tc= new TableColumn(g.getList_sommet().get(i-1).getNom());}
            else{ tc = new TableColumn("");}

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
