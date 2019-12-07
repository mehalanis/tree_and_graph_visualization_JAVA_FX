/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TAS;

import Formes.Cercle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author anispitchou
 */
public class OnFinishedInsertionAnimation implements EventHandler {
    public AnchorPane p;
    public TASmax tas;
    public Node n;

    public OnFinishedInsertionAnimation(AnchorPane p, TASmax tas, Node n) {
        this.p = p;
        this.tas = tas;
        this.n = n;
    }
        
    @Override
    public void handle(Event event) {
        p.getChildren().clear();
        tas.Afficher();
    }
    
}
