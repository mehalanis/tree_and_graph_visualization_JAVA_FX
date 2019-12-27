package Graphe;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ArcOriente extends Arc {

    private Arrow line;
    private Label label;

    public ArcOriente(Sommet sommet, int Poids) {
        super(sommet, Poids);
    }

    public Arrow getLine(double startx, double starty, double endx, double endy) {
        line = new Arrow(startx, starty, endx, endy);
        label = new Label(super.getPoids() + "");
        return line;
    }
    
    public void setLine(Arrow line) {
        this.line = line;
    }

    public Arrow getLine() {
        return line;
    }
    
    public Label getLabel() {
        return label;
    }
    public void setLayoutLabel(double x,double y){
        label.setLayoutX(x);
        label.setLayoutY(y);
    }
}
