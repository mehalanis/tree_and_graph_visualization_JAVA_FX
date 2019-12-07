/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TAS;

/**
 *
 * @author meder
 */
import Formes.Cercle;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class TASmax {

    protected Node[] Tas;
    protected int taille;//La taille Du Tas
    protected int cpct;//capacité maximum du Tas
    protected AnchorPane group;

    public TASmax(int cpct, AnchorPane group) {
        this.group = group;
        this.cpct = cpct;
        this.taille = 0;
        Tas = new Node[this.cpct + 1];
        Tas[0] = new Node(Integer.MAX_VALUE);
        //l'indice 0 ne peut pas étre utilisée dans les prochaine fonctions
    }

    protected int parent(int pos) {
        return pos / 2;
    }

    protected int filsGauche(int pos) {
        return (2 * pos);
    }

    protected int filsDroit(int pos) {
        return (2 * pos) + 1;
    }

    private boolean feuille(int pos) {
        return pos >= (taille / 2) && pos <= taille;
    }

    public void Permuter(int pos1, int pos2) {
        // TODO: implement
        Node tmp;
        tmp = Tas[pos1];
        Tas[pos1] = Tas[pos2];
        Tas[pos2] = tmp;
    }

    protected void Equi(int pos) {
        if (feuille(pos)) {
            return;
        }

        if (Tas[pos].getVal() < Tas[filsGauche(pos)].getVal()
                || Tas[pos].getVal() < Tas[filsDroit(pos)].getVal()) {

            if (Tas[filsGauche(pos)].getVal() > Tas[filsDroit(pos)].getVal()) {
                Permuter(pos, filsGauche(pos));
                Equi(filsGauche(pos));
            } else {
                Permuter(pos, filsDroit(pos));
                Equi(filsDroit(pos));
            }
        }
    }

    public void inserer(int element) {
        taille++;
        Tas[taille] = new Node(element);
        Afficher();
        new insertionAnimation( group, this).start();
    }

    public int Rechercher(int valeur) {
        // TODO: implement
        int pos = 0;
        int found = 0;

        for (int i = 1; i <= taille; i++) {
            if (Tas[i].getVal() == valeur) {
                pos = i;
                found = 1;
            }
        }

        if (found == 1) {
            System.out.println("la valeur \"" + valeur + "\" éxiste à la position \"" + pos + "\" ");
            return pos;
        }
        System.out.println("la valeur \"" + valeur + "\" n\'éxiste pas.");
        return 0;
    }

    public Node Supprimer(int val) {
        int pos = 0;
        pos = Rechercher(val);
        if (pos != 0) {
            Node popped;
            popped = Tas[pos];
            Tas[pos] = Tas[taille--];
            Equi(pos);
            return popped;
        }
        return null;
    }

    public void Afficher() {
        group.getChildren().clear();
        //int gap = abr.depth(abr.root);
        int gap = 3;
        gap = gap * gap * 10;
        this.Afficher(1, group, 1200 / 2, 0, 1200 / 2, 0, 0, gap);
    }

    public void Afficher(int pos, AnchorPane g, int x, int y, int prevx, int prevy, int lev, int gap) {

        if (pos > taille) {
            return;
        }
        Tas[pos].setC(new Cercle(Math.abs(Tas[pos].getVal())));
        g.getChildren().add(Tas[pos].getC(x, y));

        if ((++lev) != 1) {
            Line line = new Line(prevx + 23, prevy + 46, x + 23, y);
            line.setStrokeWidth(2);
            g.getChildren().add(line);
            gap = (gap) / 2;
        }
        Afficher(this.filsGauche(pos), g, x - gap, y + 50, x, y, lev, gap);
        Afficher(this.filsDroit(pos), g, x + gap, y + 50, x, y, lev, gap);
    }
}
