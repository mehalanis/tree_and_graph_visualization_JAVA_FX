/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphe.Johnson;

import Graphe.Bellman_Ford.bell_ford;
import Graphe.graphe.ArcOriente;
import Graphe.graphe.GrapheOriente;
import Graphe.graphe.Sommet;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author meder
 */
public class Jhonson {

    ArrayList<Dijkstra> dji;
    bell_ford bell;
    Sommet q;
    GrapheOriente graphe;
    Dijkstra djikstra;

    public Jhonson(GrapheOriente g) {
        this.graphe = g;
        
        
    }

    /**
     * ********Etape 1
     *
     **********
     * @param graphe
     */
    public void etape1() {
        q = new Sommet("Q");
        for (int i = 0; i < graphe.getList_sommet().size(); i++) {
            q.addArc(new ArcOriente(graphe.getSommet(i), 0));
        }
        graphe.getList_sommet().add(0, q);
    }

    /**
     * ********Etape 2
     *
     *********
     * @param graphe
     */
    public void etape2(TableView t) {
        // bell = new bell_ford(graphe ,table); 
        bell = new bell_ford(graphe, t);
        bell.BellmanFordEvaluation(0);
        bell.Affichar();
        //bell.distances;
    }

    /**
     * *******Etape 3*********
     */
    public void etape3(TableView table) {
        graphe.getList_sommet().remove(q);
        Sommet S;
        int poid;
        ArrayList<resultEtape3> result3 = new ArrayList();
        for (int i = 0; i < graphe.getList_sommet().size(); i++) {
            S = graphe.getSommet(i);
            for (int j = 0; j < S.getList_arc().size(); j++) {
                poid = bell.distances[i + 1] + S.getList_arc().get(j).getPoids() - bell.distances[graphe.getList_sommet().indexOf(S.getList_arc().get(j).getSommet()) + 1];
                result3.add(new resultEtape3(S.getNom() + " --> " + S.getList_arc().get(j).getNomSommet(), poid));
                S.getList_arc().get(j).setPoids(poid);
            }
        }
        table.getColumns().clear();
        String result[][]=new String [2][result3.size()];
        for(int i=0;i<result[0].length;i++){
            result[0][i]=result3.get(i).getNom();
            result[1][i]=result3.get(i).getPoids()+"";
        }
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(result));
        
         data.remove(0);//remove titles from data

        for (int i = 0; i < result[0].length; i++) {
            TableColumn tc = new TableColumn(result[0][i]);
            tc.setStyle("-fx-alignment: CENTER;");
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

    /**
     * *************** Etape 4
     *
     * @param graphe
     * @param table
     */
    public void etape4(ArrayList<TableView> list_table) {
        

        for (int i = 0; i < graphe.list_sommet.size(); i++) {
            djikstra = new Dijkstra(graphe, list_table.get(i));
            djikstra.dijkstra_algorithm(i);
            djikstra.AfficherTable(i);
            
            
        }
    }

    public class resultEtape3 {

        String nom;
        int poids;

        public resultEtape3(String nom, int poids) {
            this.nom = nom;
            this.poids = poids;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public int getPoids() {
            return poids;
        }

        public void setPoids(int poids) {
            this.poids = poids;
        }

    }

}
