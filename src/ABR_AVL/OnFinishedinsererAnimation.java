/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABR_AVL;

import Formes.Cercle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;


public class OnFinishedinsererAnimation implements EventHandler {

    public AnchorPane p;
    public Node n;
    public Arbre abr;

    public OnFinishedinsererAnimation(AnchorPane p, Node n, Arbre abr) {
        this.p = p;
        this.n = n;
        this.abr = abr;
    }

    @Override
    public void handle(Event event) {
        if (abr instanceof ABR) {
            ABR r = (ABR) abr;
            abr.root = r.insertion(abr.root, n);
        } else {
            AVL b = (AVL) abr;
        }
        n.setC(new Cercle(n.getVal()));
        p.getChildren().clear();
        int gap = abr.depth(abr.root);
        gap = gap * gap * 10;
        abr.Afficher(abr.root, null, p, 1200 / 2, 0, 1200 / 2, 0, 0, gap);

    }
}
