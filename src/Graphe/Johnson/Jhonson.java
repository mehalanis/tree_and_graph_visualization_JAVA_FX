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
import javafx.scene.control.TableView;

/**
 *
 * @author meder
 */
public class Jhonson {

    ArrayList<Dijkstra> dji;
    bell_ford bell;
    Sommet q;
    GrapheOriente graphe;
    public TableView table;
    public TableView tableDji;
    Dijkstra djikstra;

    public Jhonson(GrapheOriente g ,TableView t) {
        this.graphe = g;
        
        
    }

    /**
     * ********Etape 1
     *
     **********
     * @param graphe
     */
    public void etape1() {
        q = new Sommet("q");
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
    public void etape2() {
        // bell = new bell_ford(graphe ,table); 
        bell = new bell_ford(graphe, table);
        bell.BellmanFordEvaluation(0);
        //bell.distances;
    }

    /**
     * *******Etape 3*********
     */
    public void etape3() {
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
    }

    /**
     * *************** Etape 4
     *
     * @param graphe
     * @param table
     */
    public void etape4() {
        dji = new ArrayList<>();

        for (int i = 0; i < graphe.list_sommet.size(); i++) {
            djikstra = new Dijkstra(graphe, table);
            djikstra.dijkstra_algorithm(i);
            dji.add(djikstra);
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
