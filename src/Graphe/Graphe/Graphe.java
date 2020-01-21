package Graphe.graphe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.paint.Color;

public abstract class Graphe {

    public ArrayList<Sommet> list_sommet;

    public Graphe() {
        this.list_sommet = new ArrayList<Sommet>();
    }

    public boolean addSommet(Sommet s) {
        boolean b = true;
        for (Sommet e : list_sommet) {
            if (e.getNom().equals(s.getNom())) {
                b = false;
            }
        }
        if (b == true) {
            list_sommet.add(s);
        }
        return b;
    }

    public String getNomSommet(int i) {
        return list_sommet.get(i).getNom();
    }

    public Sommet getSommet(String s) {
        for (Sommet e : list_sommet) {
            if (e.getNom().equals(s)) {
                return e;
            }
        }
        return null;
    }

    public Sommet getSommet(int i) {
        return list_sommet.get(i);
    }

    public ArrayList<Sommet> getList_sommet() {
        return list_sommet;
    }

    public MatriceAdjacence MatriceAdjacence() {
        return new MatriceAdjacence(this);
    }

    public void initArcBlack() {
        ArrayList<Arc> listarc;
        ArcOriente arcorente;
        ArcNonOriente ano;
        for (int i = 0; i < list_sommet.size(); i++) {
            listarc = list_sommet.get(i).getList_arc();
            for (int j = 0; j < listarc.size(); j++) {
                if (listarc.get(j) instanceof ArcOriente) {
                    arcorente = (ArcOriente) listarc.get(j);
                    arcorente.getLine().setFill(Color.BLACK);
                } else {
                    ano = (ArcNonOriente) listarc.get(j);
                    ano.getLine().setStroke(Color.BLACK);
                }

            }

        }
    }

    public void InitColorRED(int source, int distances[]) {
        this.initArcBlack();
        int distArc[] = new int[distances.length];
        boolean tabMarque[]=new boolean[distances.length];
        for (int i = 0; i < distArc.length; i++) {
            distArc[i] = 99999;
            tabMarque[i]=false;
        }
        distArc[source] = 0;
        tabMarque[source]=true;
        Queue<Sommet> q = new LinkedList<Sommet>();
        q.add(this.getSommet(source));
        Sommet s;
        int pos_s, pos_i;
        ArcOriente arc_o;
        ArcNonOriente ano;

        while (q.size() > 0) {
            s = q.remove();
            pos_s = list_sommet.indexOf(s);
            for (int i = 0; i < s.getList_arc().size(); i++) {
                pos_i = list_sommet.indexOf(s.getList_arc().get(i).getSommet());
                if ((distArc[pos_s] + s.getList_arc().get(i).getPoids() == distances[pos_i])&&(tabMarque[pos_i]==false)) {
                    if (s.getList_arc().get(i) instanceof ArcOriente) {
                        arc_o = (ArcOriente) s.getList_arc().get(i);
                        arc_o.getLine().setFill(Color.RED);
                    } else {
                        ano=(ArcNonOriente)s.getList_arc().get(i);
                        ano.getLine().setStroke(Color.RED);
                    }
                    distArc[pos_i] = distances[pos_i];
                    tabMarque[pos_i]=true;
                    q.add(s.getList_arc().get(i).getSommet());
                }
            }
        }

    }
}
