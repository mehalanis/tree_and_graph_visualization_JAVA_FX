/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphe.Dijkstra;

import Graphe.graphe.Arc;
import Graphe.graphe.Graphe;
import Graphe.graphe.Sommet;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class Djkstra {
    public int[] distance;
    public TableView table;
    public String[][] matrice_etapes;
    public Graphe graphe;

    public Djkstra(TableView table, Graphe graphe) {
        this.table = table;
        this.graphe = graphe;
        distance=new int[graphe.getList_sommet().size()];
         matrice_etapes= new String[graphe.getList_sommet().size() + 3][graphe.getList_sommet().size() + 1];
    }
    
    
    public  String[][] pcc(Sommet sommet) {
        
        if ((graphe == null) || (!graphe.getList_sommet().contains(sommet))) {
            return null;
        }
       
        for (int i = 0; i < matrice_etapes.length; i++) {
            for (int j = 0; j < matrice_etapes[i].length; j++) {
                matrice_etapes[i][j] = "";
            }
        }
        matrice_etapes[0][0] = "etape/sommet";
        matrice_etapes[1][0] = "Init";
        for (int i = 1; i <= graphe.getList_sommet().size(); i++) {
            matrice_etapes[0][i] = graphe.getNomSommet(i - 1);
            if (graphe.getNomSommet(i - 1).equals(sommet.getNom())) {
                matrice_etapes[1][i] = "0";
            } else {
                matrice_etapes[1][i] = "INFINI";
            }
        }
        Sommet min_sommet = sommet;
        int min_poids = 99999;
        ArrayList<Sommet> marqués = new ArrayList<>();
        marqués.add(min_sommet);
        int index = 2;
        //
        for (index = 2; index < graphe.getList_sommet().size() + 2; index++) {
            matrice_etapes[index][0] = String.format("%d", index - 1);
            matrice_etapes[index][graphe.getList_sommet().indexOf(min_sommet) + 1] = matrice_etapes[index - 1][graphe.getList_sommet().indexOf(min_sommet) + 1] + "(*)";
            if (index == graphe.getList_sommet().size() + 2) {
                break;
            }
            //parcour marqués
            for (Sommet som : marqués) {
                int poids_total = 99999;
                for (int i = 2; i <= graphe.getList_sommet().size(); i++) {
                    if (!matrice_etapes[i][graphe.getList_sommet().indexOf(som) + 1].equals("") && matrice_etapes[i][graphe.getList_sommet().indexOf(som) + 1].contains("(*)")) {
                        poids_total = Integer.parseInt(matrice_etapes[i][graphe.getList_sommet().indexOf(som) + 1].substring(0, matrice_etapes[i][graphe.getList_sommet().indexOf(som) + 1].indexOf("(")));
                    }
                }
                //parcour arc de marqués

                for (Arc arc : som.getList_arc()) {
                    if (!marqués.contains(arc.getSommet())) {
                        if (matrice_etapes[index][graphe.getList_sommet().indexOf(arc.getSommet()) + 1].equals("")) {
                            matrice_etapes[index][graphe.getList_sommet().indexOf(arc.getSommet()) + 1] = String.format("%d", poids_total + arc.getPoids());
                        } else if (poids_total + arc.getPoids() < Integer.parseInt(matrice_etapes[index][graphe.getList_sommet().indexOf(arc.getSommet()) + 1])) {
                            matrice_etapes[index][graphe.getList_sommet().indexOf(arc.getSommet()) + 1] = String.format("%d", poids_total + arc.getPoids());
                        }

                    }
                }
            }

            min_poids = 99999;
            for (int i = 1; i <= graphe.getList_sommet().size(); i++) {
                if ((!matrice_etapes[index][i].equals("") && !matrice_etapes[index][i].contains("*"))) {
                    if (Integer.parseInt(matrice_etapes[index][i]) < min_poids) {
                        min_poids = Integer.parseInt(matrice_etapes[index][i]);
                        min_sommet = graphe.getSommet(matrice_etapes[0][i]);
                    }
                }
            }
            marqués.add(min_sommet);
        }
        matrice_etapes[graphe.getList_sommet().size()+2][0]="FIN";
        int in=0;
         for (Sommet som : graphe.getList_sommet()){
             for(int i=2;i<=graphe.getList_sommet().size()+1;i++){
                if(matrice_etapes[i][graphe.getList_sommet().indexOf(som)+1].contains("(*)")){
                    matrice_etapes[graphe.getList_sommet().size()+2][graphe.getList_sommet().indexOf(som)+1] =matrice_etapes[i][graphe.getList_sommet().indexOf(som)+1].substring(0, matrice_etapes[i][graphe.getList_sommet().indexOf(som)+1].indexOf("("));
                    distance[in++]=Integer.parseInt(matrice_etapes[graphe.getList_sommet().size()+2][graphe.getList_sommet().indexOf(som)+1]);
                    
                }
                }
         }

        return matrice_etapes;
    }

    public void affich(String[][] m) {

        if (m == null) {
            System.out.println("null");
        } else {
            for (int i = 0; i < m.length; i++) {

                
                for (int j = 0; j < m[i].length; j++) {
                    System.out.print(m[i][j]+" ");
                }
                System.out.println();
                System.out.println("***********");
            }
        }
    }
      public void AfficherTable(int source) {
        table.getColumns().clear();

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(matrice_etapes));
         data.remove(0);

        for (int i = 0; i < matrice_etapes[0].length; i++) {
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
