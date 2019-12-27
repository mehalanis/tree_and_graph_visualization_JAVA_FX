package Graphe;

import java.util.ArrayList;

public class MatriceAdjacence {

    private int[][] matric;
    private ArrayList<String> list_sommet;

    public MatriceAdjacence(Graphe graphe) {
        int size_graphe = graphe.getList_sommet().size();
        list_sommet = new ArrayList<String>();
        for (int i = 0; i < size_graphe; i++) {
            this.list_sommet.add(graphe.getNomSommet(i));
        }
        matric = new int[size_graphe][size_graphe];
        Arc arc;
        Sommet sommet;
        boolean b=false;
        for (int i = 0; i < size_graphe; i++) {
            sommet = graphe.getSommet(i);
            for (int j_matric = 0; j_matric < size_graphe; j_matric++) {
                if (j_matric == i) {
                    matric[i][j_matric] = 0;
                } else {
                    b=false;
                    for (int j = 0; j < sommet.getList_arc().size(); j++) {
                        if (sommet.getList_arc().get(j).getNomSommet().equals(list_sommet.get(j_matric))) {
                            matric[i][j_matric] = sommet.getList_arc().get(j).getPoids();
                            b=true;
                        } 
                    }
                    if(b==false){
                        matric[i][j_matric] =-1;
                    }
                }
            }
        }
    }

    public void Afficher() {
        int size = list_sommet.size();
        System.out.print("  ");
        for (int i = 0; i < size; i++) {
            System.out.print(list_sommet.get(i) + "  ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(list_sommet.get(i) + "  ");
            for (int j = 0; j < size; j++) {
                System.out.print(matric[i][j] + " ");
            }
            System.out.println();
        }
    }
}
