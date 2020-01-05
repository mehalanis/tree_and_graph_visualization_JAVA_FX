package Graphe.graphe;

import Graphe.Forms.Cercle;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Sommet {

    private char[] nom;
    private Cercle cercle;
    private ArrayList<Arc> list_arc;

    public Sommet(String nom) {
        this.nom = nom.toCharArray();
        cercle = new Cercle(nom);
        list_arc = new ArrayList<Arc>();
    }

    public Cercle getCercle() {
        return cercle;
    }

    public Sommet(String nom, ArrayList<Arc> list_arc) {
        this.nom = nom.toCharArray();
        this.list_arc = list_arc;
    }

    public boolean addArc(Arc arc) {
        int i = 0, size = list_arc.size();
        String nom_arc = arc.getNomSommet();
        while (i < size) {
            if (list_arc.get(i).getNomSommet().equals(nom_arc)) {
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
        cercle.setVal(nom);
    }

    public ArrayList<Arc> getList_arc() {
        return list_arc;
    }

    public Arc getArc(String s) {
        for (Arc e : list_arc) {
            if (e.getNomSommet().equals(s)) {
                return e;
            }
        }
        return null;
    }

    public void DessinerArc(Sommet s, AnchorPane pane) {
        Arc arc_a = this.getArc(s.getNom());
        Arc arc_b = s.getArc(this.getNom());
       
        Point a = new Point(this.getCercle().getCenterX(), this.getCercle().getCenterY());
        Point b = new Point(s.getCercle().getCenterX(), s.getCercle().getCenterY());
        ArcOriente arc_a_o = (ArcOriente) arc_a;
        if ((arc_a == null || arc_b == null) || arc_a instanceof ArcNonOriente) {

            Point q1 = a.delta(b);
            Point q2 = b.delta(a);
            Arrow ss = arc_a_o.getLine(q1.getX(), q1.getY(), q2.getX(), q2.getY());
            pane.getChildren().add(ss);
            int y=0;
            if(Math.abs(q1.getX()-q2.getX())<50){
                y=15;
            }
            arc_a_o.setLayoutLabel((arc_a_o.getLine().getStartX() + arc_a_o.getLine().getEndX() - 15-y) / 2, (arc_a_o.getLine().getStartY() + arc_a_o.getLine().getEndY() + 10) / 2);
            pane.getChildren().add(arc_a_o.getLabel());
        } else {
             pane.getChildren().removeAll(arc_a_o.getLine(),arc_a_o.getLabel());
            Point q1 = a.delta(b);
            Point q2 = b.delta(a);
            q1 = (new Point(this.getCercle().getCenterX(), this.getCercle().getCenterY())).Rotation(q1, 70);
            q2 = (new Point(s.getCercle().getCenterX(), s.getCercle().getCenterY())).Rotation(q2, -70);
            Arrow ss = arc_a_o.getLine(q1.getX(), q1.getY(), q2.getX(), q2.getY());
            pane.getChildren().add(ss);
            arc_a_o.setLayoutLabel((arc_a_o.getLine().getStartX() + arc_a_o.getLine().getEndX() - 15) / 2, (arc_a_o.getLine().getStartY() + arc_a_o.getLine().getEndY() + 10) / 2);
            pane.getChildren().add(arc_a_o.getLabel());
            arc_a_o = (ArcOriente) arc_b;
            q1 = b.delta(a);
            q2 = a.delta(b);
            
             pane.getChildren().removeAll(arc_a_o.getLine(),arc_a_o.getLabel());
            q1 = (new Point(s.getCercle().getCenterX(), s.getCercle().getCenterY())).Rotation(q1, 70);
            q2 = (new Point(this.getCercle().getCenterX(), this.getCercle().getCenterY())).Rotation(q2, -70);
            ss = arc_a_o.getLine(q1.getX(), q1.getY(), q2.getX(), q2.getY());
            pane.getChildren().add(ss);
            arc_a_o.setLayoutLabel((arc_a_o.getLine().getStartX() + arc_a_o.getLine().getEndX() - 15) / 2, (arc_a_o.getLine().getStartY() + arc_a_o.getLine().getEndY() + 10) / 2);
            pane.getChildren().add(arc_a_o.getLabel());

        }

    }

    public void setList_arc(ArrayList<Arc> list_arc) {
        this.list_arc = list_arc;
    }

}
