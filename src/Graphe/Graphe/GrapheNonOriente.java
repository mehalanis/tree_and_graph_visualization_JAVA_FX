package Graphe;

public class GrapheNonOriente extends Graphe {
    public boolean addArc(Sommet a,Sommet b,int poids){ 
       return (a.addArc(new ArcNonOriente(b,poids))&&b.addArc(new ArcNonOriente(a,poids)));// a---poids---b        
    }
    public boolean addArc(String a,String b,int poids){
        Sommet sommet_a=this.getSommet(a);
        Sommet sommet_b=this.getSommet(b);
        if((sommet_a!=null)&&(sommet_b!=null)){
            return this.addArc(sommet_a, sommet_b, poids);
        }
        return false;
    }
}
