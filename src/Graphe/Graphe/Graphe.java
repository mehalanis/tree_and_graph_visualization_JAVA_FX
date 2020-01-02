package Graphe;

import java.util.ArrayList;

public abstract class Graphe {

    protected ArrayList<Sommet> list_sommet;

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
     public MatriceAdjacence MatriceAdjacence(){
        return new MatriceAdjacence(this);
    }
}
