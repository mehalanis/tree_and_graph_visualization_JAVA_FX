package Graphe.graphe;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class GrapheOriente extends Graphe {

    public boolean addArc(Sommet a, Sommet b, int poids) {
        return a.addArc(new ArcOriente(b, poids));// a---poids--->b        
    }

    public void initArcBlack() {
        ArrayList<Arc> listarc;
        ArcOriente arcorente;
        for (int i = 0; i < list_sommet.size(); i++) {
            listarc = list_sommet.get(i).getList_arc();
            for (int j = 0; j < listarc.size(); j++) {
                arcorente = (ArcOriente) listarc.get(j);
                arcorente.getLine().setFill(Color.BLACK);
            }

        }
    }
}
