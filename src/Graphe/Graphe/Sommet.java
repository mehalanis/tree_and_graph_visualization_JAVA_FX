package Graphe;

import Forms.Cercle;
import java.util.ArrayList;

public class Sommet {
    private char[] nom;
    private Cercle cercle;
    private ArrayList<Arc> list_arc;

    public Sommet(String nom) {
        this.nom = nom.toCharArray();
        cercle=new Cercle(nom);
        list_arc=new ArrayList<Arc>();
    }

    public Cercle getCercle() {
        return cercle;
    }
    
    public Sommet(String nom, ArrayList<Arc> list_arc) {
        this.nom = nom.toCharArray();
        this.list_arc = list_arc;
    }
    public boolean addArc(Arc arc){
        int i=0,size=list_arc.size();
        String nom_arc=arc.getNomSommet();
        while(i<size){
            if(list_arc.get(i).getNomSommet().equals(nom_arc)){
                return false;
            }
            i++;
        }
        list_arc.add(arc);
        return true;
    }
    public String getNom() {
        return String.valueOf(nom);
    }

    public void setNom(String nom) {
        this.nom = nom.toCharArray();
    }

    public ArrayList<Arc> getList_arc() {
        return list_arc;
    }
    public Arc getArc(String s){
        for(Arc e:list_arc){
            if(e.getNomSommet().equals(s)){
                return e;
            }
        }
        return null;
    }
    public void setList_arc(ArrayList<Arc> list_arc) {
        this.list_arc = list_arc;
    }
    
    
}
