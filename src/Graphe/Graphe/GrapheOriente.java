package Graphe.graphe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.paint.Color;

public class GrapheOriente extends Graphe {

    public boolean addArc(Sommet a, Sommet b, int poids) {
        return a.addArc(new ArcOriente(b, poids));// a---poids--->b        
    }

 

  
}
