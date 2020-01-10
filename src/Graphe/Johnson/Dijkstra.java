package Graphe.Johnson;

import Graphe.graphe.ArcOriente;
import Graphe.graphe.Graphe;
import Graphe.graphe.GrapheOriente;
import Graphe.graphe.Sommet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import java.util.Queue;
import javafx.scene.paint.Color;

public class Dijkstra {

    public int distances[];

    private Set<Integer> settled;

    private Set<Integer> unsettled;

    private int number_of_nodes;

    private int adjacencyMatrix[][];
    public String[][] result;
    int INFINI = 99999;
    int k = 0;
    public TableView table;
    public Graphe graphe;

    public Dijkstra(Graphe g, TableView table) {
        this.table = table;
        this.graphe = g;
        this.number_of_nodes = g.getList_sommet().size();

        adjacencyMatrix = g.MatriceAdjacence().getMatric();

        distances = new int[number_of_nodes];

        settled = new HashSet<Integer>();

        unsettled = new HashSet<Integer>();
        result = new String[number_of_nodes + 1][number_of_nodes + 1];

    }

    public void dijkstra_algorithm(int source) {

        int evaluationNode;

        /* for (int i = 0; i < number_of_nodes; i++) {
            for (int j = 0; j < number_of_nodes; j++) {
                adjacencyMatrix[i][j] = adjacency_matrix[i][j];
            }
        }*/
        int distArc[] = new int[number_of_nodes];
        for (int i = 0; i < number_of_nodes; i++) {
            result[0][i + 1] = "INFINI";
            distances[i] = this.INFINI;
            distArc[i] = this.INFINI;

        }
        result[0][0] = "Init";
        unsettled.add(source);

        result[0][source + 1] = "0";
        distances[source] = 0;
        distArc[source] = 0;

        while (!unsettled.isEmpty()) {
            this.k++;
            evaluationNode = getNodeWithMinimumDistanceFromUnsettled();

            unsettled.remove(evaluationNode);

            settled.add(evaluationNode);

            evaluateNeighbours(evaluationNode);
            this.result[k][evaluationNode + 1] = this.result[k][evaluationNode + 1] + " (*)";
        }
        if(graphe instanceof GrapheOriente){
            ((GrapheOriente)graphe).initArcBlack();
        }
        
        Queue<Sommet> q = new LinkedList<Sommet>();
        q.add(graphe.getSommet(source));
        Sommet s;
        int pos_s, pos_i;
        ArcOriente arc_o;
        while (q.size() > 0) {
            s = q.remove();
            pos_s = graphe.getList_sommet().indexOf(s);
            for (int i = 0; i < s.getList_arc().size(); i++) {
                pos_i = graphe.getList_sommet().indexOf(s.getList_arc().get(i).getSommet());
                if (distArc[pos_s] + s.getList_arc().get(i).getPoids() == distances[pos_i]) {
                    if(s.getList_arc().get(i) instanceof ArcOriente){
                        arc_o=(ArcOriente)s.getList_arc().get(i);
                        arc_o.getLine().setFill(Color.RED);
                    }
                    distArc[pos_i]=distances[pos_i];
                    q.add(s.getList_arc().get(i).getSommet());
                }
            }
        }

    }

    private int getNodeWithMinimumDistanceFromUnsettled() {

        int min;

        int node = 0;

        Iterator<Integer> iterator = unsettled.iterator();

        node = iterator.next();

        min = distances[node];

        for (int i = 0; i < distances.length; i++) {

            if (unsettled.contains(i)) {

                if (distances[i] <= min) {

                    min = distances[i];

                    node = i;

                }

            }

        }

        return node;

    }

    private void evaluateNeighbours(int evaluationNode) {

        int edgeDistance = -1;

        int newDistance = -1;

        result[this.k][0] = k + "";
        String f;
        for (int destinationNode = 0; destinationNode < number_of_nodes; destinationNode++) {

            if (!settled.contains(destinationNode)) {

                if (adjacencyMatrix[evaluationNode][destinationNode] != this.INFINI) {

                    edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];

                    newDistance = distances[evaluationNode] + edgeDistance;

                    if (newDistance < distances[destinationNode]) {

                        distances[destinationNode] = newDistance;

                    }

                    unsettled.add(destinationNode);

                }

            }
            if (distances[destinationNode] == this.INFINI) {
                f = "INFINI";

            } else {
                f = distances[destinationNode] + "";
            }
            result[this.k][destinationNode + 1] = f;

        }

    }

    public void AfficherTable(int source) {
        table.getColumns().clear();

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(result));
        // data.remove(0);//remove titles from data

        for (int i = 0; i < result[0].length; i++) {
            TableColumn tc;
            if (i > 0) {
                tc = new TableColumn(graphe.getList_sommet().get(i - 1).getNom());
            } else {
                tc = new TableColumn("sommet origine " + graphe.getList_sommet().get(source).getNom());
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
