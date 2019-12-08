package TAS;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Permutation extends Thread {

    public TASmax tas;
    public int pos1, pos2;

    public Permutation(TASmax tas, int pos1, int pos2) {
        this.tas = tas;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    @Override
    public void run() {
        TranslateTransition t1, t2;
        t1 = new TranslateTransition(Duration.millis(500), tas.Tas[pos2].getC());
        t1.setToX(tas.Tas[pos1].getC().getLayoutX() - tas.Tas[pos2].getC().getLayoutX());
        t1.setToY(tas.Tas[pos1].getC().getLayoutY() - tas.Tas[pos2].getC().getLayoutY());
        t1.setOnFinished(new OnFinishedInsertionAnimation(tas));

        t2 = new TranslateTransition(Duration.millis(500), tas.Tas[pos1].getC());
        t2.setToX(tas.Tas[pos2].getC().getLayoutX() - tas.Tas[pos1].getC().getLayoutX());
        t2.setToY(tas.Tas[pos2].getC().getLayoutY() - tas.Tas[pos1].getC().getLayoutY());
        t2.setOnFinished(new OnFinishedInsertionAnimation(tas));
        t1.play();
        t2.play();
    }

}
