/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphe.Johnson;

import Graphe.Bellman_Ford.bell_ford;
import Graphe.graphe.Graphe;
import Graphe.graphe.Sommet;
import java.util.ArrayList;

/**
 *
 * @author meder
 */
public class Jhonson {
    
     ArrayList<Dijkstra> D;
     bell_ford B;
     Sommet q;
     Graphe graphe;
     
     public Jhonson(Graphe g){ 
         this.graphe=g;
         q = new Sommet("q");
         for (int i = 0; i < graphe.getList_sommet().size(); i++) {
             q.Arc(graphe.getSommet(i),0);
             graphe.getList_sommet().addIndex(q,0);
         }
         
         
     }
    
}
