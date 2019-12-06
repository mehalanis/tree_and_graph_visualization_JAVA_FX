/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TAS;

import Formes.Cercle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author anispitchou
 */
public class insertionAnimation extends Thread {

    public AnchorPane group;
    public TASmax tas;

    public insertionAnimation( AnchorPane group, TASmax tas) {
       
        this.group = group;
        this.tas = tas;
    }

    @Override
    public void run() {
       /* Runnable updater = new Runnable() {
            @Override
            public void run() {
                tas.Afficher();
            }
        };
        
        Platform.runLater(updater);*/
        int current = tas.taille;
        TranslateTransition t1, t2;
        while (tas.Tas[current].getVal() > tas.Tas[tas.parent(current)].getVal()) {
            tas.Permuter(current, tas.parent(current));
            t1 = new TranslateTransition(Duration.millis(500), tas.Tas[tas.parent(current)].getC());
            t1.setToX(tas.Tas[current].getC().getLayoutX() - tas.Tas[tas.parent(current)].getC().getLayoutX());
            t1.setToY(tas.Tas[current].getC().getLayoutY() - tas.Tas[tas.parent(current)].getC().getLayoutY());
            t1.setOnFinished(new OnFinishedInsertionAnimation(group, tas, tas.Tas[tas.parent(current)]));

            t2 = new TranslateTransition(Duration.millis(500), tas.Tas[current].getC());
            t2.setToX(tas.Tas[tas.parent(current)].getC().getLayoutX() - tas.Tas[current].getC().getLayoutX());
            t2.setToY(tas.Tas[tas.parent(current)].getC().getLayoutY() - tas.Tas[current].getC().getLayoutY());
            t2.setOnFinished(new OnFinishedInsertionAnimation(group, tas, tas.Tas[current]));
            t1.play();
            t2.play();
            try {
                Thread.sleep(600);
            } catch (InterruptedException ex) {
                Logger.getLogger(insertionAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }            
            current = tas.parent(current);
        }
    }

}
