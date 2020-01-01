package Bellman_Ford;

import Graphe.ArcOriente;
import Graphe.Graphe;
import Graphe.Sommet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class bell_ford {

    private int distances[]; //distance entre les sommets, (poids) 
    private int numberofvertices; // nombre de sommet 
    public static final int MAX_VALUE = 99999; // l'infini 
    public Graphe g;
    int adjacencymatrix[][];
    public TableView table;

    public bell_ford(Graphe g, TableView table) {
        this.g = g;
        this.table = table;
        this.numberofvertices = g.getList_sommet().size();
        adjacencymatrix = g.MatriceAdjacence().getMatric();
    }

    public void BellmanFordEvaluation(int source) {
        //initialisation de la table distance pour connaitre les distance qu'on a changer...
        distances = new int[numberofvertices];
        ArrayList<String[]> listString = new ArrayList<String[]>();
        String[] list = new String[numberofvertices];
        int[][] listarc = new int[numberofvertices][2];

        for (int node = 0; node < numberofvertices; node++) {
            list[node] = "Inf";
            distances[node] = MAX_VALUE;
            listarc[node][0] = MAX_VALUE;
            listarc[node][1] = MAX_VALUE;
        }
        list[source] = "0";
        distances[source] = 0;
        listString.add(list);
        String s;
        // lancement le l'algorithme BF, maj de distance entre sommet avec enregistrement de la plus petite distance 
        for (int node = 0; node < numberofvertices - 1; node++) {
            list = new String[numberofvertices];
            int index = listString.size() - 1;
            for (int i = 0; i < listString.get(index).length; i++) {
                if ((!listString.get(index)[i].equals("Inf")) &&(listString.get(index)[i].length()>4)){
                    System.out.print("val "+ listString.get(index)[i]+ " "+( listString.get(index)[i].length() - 4 + 1));
                    s = listString.get(index)[i].substring(0, listString.get(index)[i].length() - 4 + 1);
                } else {
                    s = listString.get(index)[i];
                }
                list[i] = s;
            }
            for (int destinationnode = 0; destinationnode < numberofvertices; destinationnode++) {
                if (adjacencymatrix[node][destinationnode] != MAX_VALUE) {
                    // il y'as un meilleur chemin.. 
                    if (distances[destinationnode] > distances[node]
                            + adjacencymatrix[node][destinationnode]) {
                        if (listarc[destinationnode][0] != MAX_VALUE) {
                            Sommet sm = g.getSommet(listarc[destinationnode][0]);
                            ArcOriente arc = (ArcOriente) sm.getArc(g.getSommet(listarc[destinationnode][1]).getNom());
                            arc.getLine().setFill(Color.BLACK);
                        }
                        distances[destinationnode] = distances[node] + adjacencymatrix[node][destinationnode];
                        listarc[destinationnode][0] = node;
                        listarc[destinationnode][1] = destinationnode;
                        Sommet sm = g.getSommet(listarc[destinationnode][0]);
                        ArcOriente arc = (ArcOriente) sm.getArc(g.getSommet(listarc[destinationnode][1]).getNom());
                        arc.getLine().setFill(Color.RED);
                        list[destinationnode] = distances[destinationnode] + " (*)";

                    }
                }

            }
            listString.add(list);
        }

        liststring = new String[listString.size()][numberofvertices + 1];
        String sar = "Init";
        for (int i = 0; i < listString.size(); i++) {
            if (i > 0) {
                sar= i + "";
            }
            liststring[i][0] = sar;
            for (int j = 0; j < listString.get(i).length; j++) {
                liststring[i][j + 1] = listString.get(i)[j];
            }

        }

        // Detection de circuit absorbant!! 
        for (int sourcenode = 0; sourcenode < numberofvertices; sourcenode++) {
            for (int destinationnode = 0; destinationnode < numberofvertices; destinationnode++) {
                if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE) {
                    if (distances[destinationnode] > distances[sourcenode]
                            + adjacencymatrix[sourcenode][destinationnode]) {
                        System.out.println("The Graph contains negative edge cycle");
                    }
                }
            }
        }

        //affichage simple : 
        /* for (int vertex = 0; vertex < numberofvertices; vertex++) {
            System.out.println("distance of source : " + source + " to "
                    + vertex + " is " + distances[vertex]);
        }*/
    }
    String[][] liststring;

    public void Affichar() {
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(liststring));
        // data.remove(0);//remove titles from data

        for (int i = 0; i < liststring[0].length; i++) {
            TableColumn tc;
            if (i > 0) {
                tc = new TableColumn(g.getList_sommet().get(i - 1).getNom());
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

}
