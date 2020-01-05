package Graphe.graphe;

public abstract class Arc {
    private Sommet sommet;
    private int Poids;

    public Arc(Sommet sommet, int Poids) {
        this.sommet = sommet;
        this.Poids = Poids;
    }

    public Sommet getSommet() {
        return sommet;
    }
    public String getNomSommet(){
        return sommet.getNom();
    }
    public void setSommet(Sommet sommet) {
        this.sommet = sommet;
    }

    public int getPoids() {
        return Poids;
    }

    public void setPoids(int Poids) {
        this.Poids = Poids;
    }
    
}
