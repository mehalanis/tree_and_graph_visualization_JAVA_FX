package Graphe;

import java.util.ArrayList;

public class GrapheOriente extends Graphe {

    public boolean addArc(Sommet a, Sommet b, int poids) {
        return a.addArc(new ArcOriente(b, poids));// a---poids--->b        
    }
   
}
