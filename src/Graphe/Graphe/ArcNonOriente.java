package Graphe.graphe;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;

public class ArcNonOriente extends Arc {
    private Line line;
    private Label label;
    public ArcNonOriente(Sommet sommet, int Poids,Line line) {
        super(sommet, Poids);
        this.line=line;
        
        label=new Label(Poids+"");
        
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Line getLine(double startx, double starty, double endx, double endy) {
        line.setStartX(startx);
        line.setStartY(starty);
        line.setEndX(endx);
        line.setEndY(endy);
        return line;
    }
    public void setLayoutLabel(double x,double y){
        label.setLayoutX(x);
        label.setLayoutY(y);
    }

    public Label getLabel() {
        return label;
    }

    public Line getLine() {
        return line;
    }
    
    
    
}
