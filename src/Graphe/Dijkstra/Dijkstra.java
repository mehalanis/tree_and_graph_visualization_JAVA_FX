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

public class Dijkstra {

    Sommet sommet;
    int total_poids;

    public Dijkstra(Sommet sommet, int total_poids) {
        this.sommet = sommet;
        this.total_poids = total_poids;

    }

    public static ArrayList<Object> Pcc(Graphe graphe, Sommet sommet) {
        if ((graphe != null) && (graphe.getList_sommet().contains(sommet))) {
            Graphe pcc_graphe = new Graphe() {
            };
            ArrayList<Dijkstra> pcc = new ArrayList<>();
            String[][] matrix_retour = new String[graphe.list_sommet.size() + 1][graphe.list_sommet.size() + 1];
            matrix_retour[0][0] = "etape/sommet";
            for (int i = 1; i <= graphe.getList_sommet().size(); i++) {
                matrix_retour[0][i] = graphe.getNomSommet(i - 1);
            }

            pcc_graphe.addSommet(new Sommet(sommet.getNom()));
            pcc.add(new Dijkstra(sommet, 0));
            matrix_retour[1][0] = "init";
            for (int i = 1; i <= graphe.getList_sommet().size(); i++) {
                if (matrix_retour[0][i].equals(sommet.getNom())) {
                    matrix_retour[1][i] = "0 (*)";
                } else {
                    matrix_retour[1][i] = "inf";
                }
            }
            int index = 1;
            while (pcc_graphe.getList_sommet().size() < graphe.getList_sommet().size()) {
                Arc min_arc = null;
                index++;
                Dijkstra min_deb = new Dijkstra(null, Integer.MAX_VALUE);

                for (Dijkstra j : pcc) {

                    for (Arc arc : j.sommet.getList_arc()) {
                        if (arc.getPoids() + j.total_poids <= min_deb.total_poids) {
                            boolean marqué = false;
                            for (Sommet som : pcc_graphe.getList_sommet()) {
                                if (som.getNom().equals(arc.getNomSommet())) {
                                    marqué = true;
                                }
                            }
                            if (marqué == false) {
                                min_deb.sommet = new Sommet(j.sommet.getNom());
                                min_deb.total_poids = arc.getPoids() + j.total_poids;
                                min_arc = arc;
                            }
                        }
                    }

                }
                if (min_deb.total_poids != Integer.MAX_VALUE) {
                    pcc.add(new Dijkstra(min_arc.getSommet(), min_deb.total_poids));
                    Sommet new_sommet = new Sommet(min_arc.getNomSommet());
                    min_arc.setSommet(new_sommet);
                    for (Sommet som : pcc_graphe.getList_sommet()) {
                        if (som.getNom().equals(min_deb.sommet.getNom())) {
                            som.addArc(min_arc);
                        }
                    }
                    pcc_graphe.addSommet(new_sommet);
                    matrix_retour[index][0] = String.format("%d", index - 1);
                    String s;
                    for (int i = 1; i <= graphe.getList_sommet().size(); i++) {
                        if (matrix_retour[index - 1][i] != null && (!matrix_retour[index - 1][i].equals("inf"))) {
                            if ((!matrix_retour[index - 1][i].equals("inf")) && (matrix_retour[index - 1][i].length() > 4)) {
                                s = matrix_retour[index - 1][i].substring(0, matrix_retour[index - 1][i].length() - 4 + 1);
                            } else {
                                s = matrix_retour[index - 1][i];
                            }
                            matrix_retour[index][i] = s;
                        } else if (matrix_retour[0][i].equals(new_sommet.getNom())) {
                            matrix_retour[index][i] = String.format("%d", min_deb.total_poids) + " (*)";
                        } else {
                            matrix_retour[index][i] = "inf";
                        }
                    }

                } else {
                    for (Sommet som : graphe.getList_sommet()) {
                        if (!pcc_graphe.getList_sommet().contains(som)) {
                            pcc_graphe.addSommet(som);
                        }
                    }
                }
            }
            ArrayList<Object> retour = new ArrayList<>();
            retour.add(pcc_graphe);
            retour.add(matrix_retour);

            return retour;
        }
        return null;
    }

}
