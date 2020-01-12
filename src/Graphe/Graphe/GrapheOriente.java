package Graphe.graphe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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

    public void InitColorRED(int source, int distances[]) {
        this.initArcBlack();
        int distArc[] = new int[distances.length];
        for (int i = 0; i < distArc.length; i++) {
            distArc[i] = 99999;
        }
        distArc[source]=0;

        Queue<Sommet> q = new LinkedList<Sommet>();
        q.add(super.getSommet(source));
        Sommet s;
        int pos_s, pos_i;
        ArcOriente arc_o;
        while (q.size() > 0) {
            s = q.remove();
            pos_s = list_sommet.indexOf(s);
            for (int i = 0; i < s.getList_arc().size(); i++) {
                pos_i = list_sommet.indexOf(s.getList_arc().get(i).getSommet());
                if (distArc[pos_s] + s.getList_arc().get(i).getPoids() == distances[pos_i]) {
                    if (s.getList_arc().get(i) instanceof ArcOriente) {
                        arc_o = (ArcOriente) s.getList_arc().get(i);
                        arc_o.getLine().setFill(Color.RED);
                    }
                    distArc[pos_i] = distances[pos_i];
                    q.add(s.getList_arc().get(i).getSommet());
                }
            }
        }

    }
}
