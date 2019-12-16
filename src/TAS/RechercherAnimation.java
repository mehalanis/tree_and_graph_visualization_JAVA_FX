package TAS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class RechercherAnimation extends Thread {

    public TASmax tas;
    private Label label;
    public int val;
    private int pos_val;

    public RechercherAnimation(TASmax tas, Label label, int val) {
        this.tas = tas;
        this.label = label;
        this.val = val;
    }

    public RechercherAnimation(TASmax tas, int val) {
        this.tas = tas;
        this.val = val;
        
    }
    
    @Override
    public void run() {
        Rechercher(val);
        if (label != null) {
            Runnable updater = new Runnable() {

                @Override
                public void run() {
                    if(tas instanceof TASmin){
                        val=-val;
                    }
                    label.setText(((pos_val != 0) ? "found : " + val : "not found : " + val));
                }
            };
            Platform.runLater(updater);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
            tas.Tas[pos_val].getC().setStroke(Color.BLACK);
        }
        //tas.Afficher();

    }

    public void Rechercher(int valeur) {
        int pos = 0;

        // Création d'une file
        Queue<Integer> q = new LinkedList<>();
        if (tas.taille != 0) {
            pos = 1;
            q.add(pos);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(RechercherAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
            tas.Tas[1].getC().setStroke(Color.YELLOW);
            while (q.peek() != null) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RechercherAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
                pos = q.remove();
                if (tas.Tas[pos].getVal() == valeur) {
                    while (q.peek() != null) {
                        tas.Tas[q.remove()].getC().setStroke(Color.BLACK);
                    }
                    tas.Tas[pos].getC().setStroke(Color.GREEN);
                    pos_val = pos;
                    break;
                } else {
                    tas.Tas[pos].getC().setStroke(Color.BLACK);
                }
                if (tas.filsGauche(pos) <= tas.taille) {
                    if (tas.Tas[tas.filsGauche(pos)].getVal() >= valeur) {
                        tas.Tas[tas.filsGauche(pos)].getC().setStroke(Color.YELLOW);
                        q.add(tas.filsGauche(pos));
                    }
                }
                if (tas.filsDroit(pos) <= tas.taille) {
                    if (tas.Tas[tas.filsDroit(pos)].getVal() >= valeur) {
                        tas.Tas[tas.filsDroit(pos)].getC().setStroke(Color.YELLOW);
                        q.add(tas.filsDroit(pos));
                    }
                }
            }
            
        }
    }

    public int getPos_val() {
        return pos_val;
    }

}
