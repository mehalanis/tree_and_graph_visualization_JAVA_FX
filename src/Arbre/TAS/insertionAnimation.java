
package TAS;

import Formes.Cercle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class insertionAnimation extends Thread {

    public AnchorPane group;
    public TASmax tas;

    public insertionAnimation(AnchorPane group, TASmax tas) {

        this.group = group;
        this.tas = tas;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(insertionAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }
        int current = tas.taille;
        while (tas.Tas[current].getVal() > tas.Tas[tas.parent(current)].getVal()) {
            tas.Permuter(current, tas.parent(current));
            new Permutation(tas, current, tas.parent(current)).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(insertionAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
            current = tas.parent(current);
        }
    }

}
