/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b_arbre;

import Formes.rectangle;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

public class AfficherB_Tree {

    public B_Arbre root;
    public AnchorPane arbre;

    public AfficherB_Tree(B_Arbre root, AnchorPane arbre) {
        this.root = root;
        this.arbre = arbre;
    }

    public void Afficherarbre() // depuis la racine  on chercherche depuis la racine
    {

        int level = 0;
        ArrayList<Pair> Queue = new ArrayList<Pair>();
        ArrayList<HBox> list_hbox = new ArrayList<HBox>();
        Pair monCouple = new Pair(root, level, 1200 / 2, 0);
        level++;
        Queue.add(monCouple);
        int x = 1200 / 2;
        int y = 0;
        int lev = 0;
        int cpt = 1;
        int gap = 3 * root.Ordre * 40;
        int m = -1;
        int pos_x = x;
        int pow = 2;
        while (!Queue.isEmpty()) {

            //Queue.get(0).AfficherPair();
            HBox hbox = new HBox();
            rectangle r;
            System.out.print("case " + cpt + " = ");
            for (int i = 0; i < Queue.get(0).getMonNoued().node.size(); i++) {
                int k = (int) Queue.get(0).getMonNoued().node.get(i);
                r = new rectangle(k);
                System.out.print(k + " ");
                hbox.getChildren().add(r);
            }
            System.out.println("level = " + Queue.get(0).getLevel() + " x = " + Queue.get(0).getX() + " y= " + Queue.get(0).getY());
            hbox.setLayoutX(Queue.get(0).getX());
            hbox.setLayoutY(Queue.get(0).getY());
            list_hbox.add(hbox);
            if (!Queue.get(0).getMonNoued().fils.isEmpty()) {
                ArrayList<B_Arbre> fils2 = Queue.get(0).getMonNoued().fils;
                int nbr = Queue.get(0).getMonNoued().node.size();
                y = (Queue.get(0).getLevel() + 1) * 50;
                //System.out.println("fils queuesize "+fils2.size());

                //if (Queue.get(0).getLevel() != m) {
                if (cpt == pow) {
                    pow = pow * pow;
                    // if(m!=-1)  gap = gap / 2;
                    pos_x = Queue.get(0).getX() - gap;
                    m = Queue.get(0).getLevel();
                    // gap = gap / nbr/* Queue.get(0).getMonNoued().Ordre*/;
                }
                for (int i = 0; i <= nbr; i++) {
                    //if(i==0)pos_x-=gap;
                    try {
                        Queue.add(new Pair(fils2.get(i), Queue.get(0).getLevel() + 1, pos_x, y));
                    } catch (Exception e) {
                    }
                    pos_x += gap;
                }


                /*    for(B_Arbre MonFils : fils2)
                {
                    Queue.add(new Pair(MonFils,level));
                }*/
            }
            cpt++;
            Queue.remove(0);

        }
        arbre.getChildren().clear();
        System.out.println(list_hbox.size());
        for (HBox i : list_hbox) {
            arbre.getChildren().add(i);
        }

    }

    public void Afficher() {
        arbre.getChildren().clear();
        int gap = 2;
        gap = gap * root.Ordre * 40;
        this.Afficher(root.getRacine(), null, 1200 / 2, 0, 1200 / 2, 0, 0, gap);

    }

    public void Afficher(B_Arbre t, B_Arbre root, int x, int y, int prevx, int prevy, int lev, int gap) {

        if (t == null) {
            return;
        }
        HBox hbox = new HBox();
        rectangle r;
        System.out.print("level " + lev + " val = ");
        for (int i = 0; i < t.node.size(); i++) {
            int k = (int) t.node.get(i);
            System.out.print(k + " ");
            r = new rectangle(k);
            hbox.getChildren().add(r);
        }
        System.out.println();
        hbox.setLayoutX(x);
        hbox.setLayoutY(y);
        arbre.getChildren().add(hbox);
        if ((++lev) != 1) {

            Line line = new Line(prevx, prevy + 36, x + (35 * t.node.size()) / 2, y);
            line.setStrokeWidth(2);
            arbre.getChildren().add(line);
            gap = (gap) / root.Ordre;
        }
        if (t.estFeuille()) {
            return;
        }
        int xpere = x;

        for (int i = 0; i < t.node.size() + 1; i++) {
            if ((t.node.size() + 1 == 2) && (i == 1)) {
                x += gap;
            }
            Afficher((B_Arbre) t.fils.get(i), t, x - gap, y + 58, xpere, y, lev, gap);
            x += gap;

            xpere += 35;
        }
    }

}
