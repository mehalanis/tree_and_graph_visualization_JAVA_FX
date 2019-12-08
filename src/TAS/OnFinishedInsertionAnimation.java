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

public class OnFinishedInsertionAnimation implements EventHandler {
    public TASmax tas;

    public OnFinishedInsertionAnimation(TASmax tas) {
        this.tas = tas;
    }
    
    @Override
    public void handle(Event event) {
        tas.group.getChildren().clear();
        tas.Afficher();
    }
    
}
