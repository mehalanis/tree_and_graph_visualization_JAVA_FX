package ABR_AVL;

import Formes.Cercle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;


public class OnFinishedInsertionAnimation implements EventHandler {

    public AnchorPane p;
    public Node n;
    public Arbre abr;

    public OnFinishedInsertionAnimation(AnchorPane p, Node n, Arbre abr) {
        this.p = p;
        this.n = n;
        this.abr = abr;
    }

    @Override
    public void handle(Event event) {
        p.getChildren().clear();
        abr.Afficher();
        

    }
}
