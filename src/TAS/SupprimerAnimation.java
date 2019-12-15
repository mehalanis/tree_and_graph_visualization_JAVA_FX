package TAS;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

public class SupprimerAnimation extends Thread {

    public TASmax tas;
    public int val;

    public SupprimerAnimation(TASmax tas, int val) {
        this.tas = tas;
        this.val = val;
    }

    @Override
    public void run() {
        int pos = 0;
        //pos = tas.Rechercher(val);
        RechercherAnimation RA=new RechercherAnimation(tas,val);
        RA.start();
        try {
            RA.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SupprimerAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }
        pos=RA.getPos_val();
        tas.Tas[pos].getC().setStroke(Color.RED);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SupprimerAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }
        Permutation p1;
        if (pos != 0) {
            tas.Tas[pos].getC().setVisible(false);
            p1=new Permutation(tas, tas.taille, pos);
            p1.start();
            try {
                p1.join();
                tas.Tas[pos] = tas.Tas[tas.taille--];
                Thread.sleep(1200);
            } catch (InterruptedException ex) {
                Logger.getLogger(SupprimerAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
            Equi(pos);
        }
    }

    protected void Equi(int pos) {
        if (tas.feuille(pos)) {
            return;
        }
        
        if (tas.Tas[pos].getVal() < tas.Tas[tas.filsGauche(pos)].getVal()
                || tas.Tas[pos].getVal() < tas.Tas[tas.filsDroit(pos)].getVal()) {

            if (tas.Tas[tas.filsGauche(pos)].getVal() > tas.Tas[tas.filsDroit(pos)].getVal()) {
                tas.Permuter(pos, tas.filsGauche(pos));
                new Permutation(tas, pos, tas.filsGauche(pos)).start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SupprimerAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
                Equi(tas.filsGauche(pos));
            } else {
                tas.Permuter(pos, tas.filsDroit(pos));
                new Permutation(tas, pos, tas.filsDroit(pos)).start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SupprimerAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
                Equi(tas.filsDroit(pos));
            }
        }
    }

}
