package Graphe;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.text.Text;

public class Arrow extends Path {

    private static final double HeadSize = 12;
    private double startX, startY, endX, endY;

    public Arrow(double startX, double startY, double endX, double endY) {
        super();
        Point a = new Point(startX, startY);
        Point b = new Point(endX, endY);

        Point q1 = a.delta(b);
        Point q2 = b.delta(a);

        startX = q1.getX();
        startY = q1.getY();
        endX = q2.getX();
        endY = q2.getY();
       
        strokeProperty().bind(fillProperty());
        setFill(Color.BLACK);

        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        //Line
        
        getElements().add(new MoveTo(startX, startY));
        getElements().add(new LineTo(endX, endY));
        
        //ArrowHead
        double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        //point1
        double x1 = (-1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * this.HeadSize + endX;
        double y1 = (-1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * this.HeadSize + endY;
        //point2
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * this.HeadSize + endX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * this.HeadSize + endY;

        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(endX, endY));
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }
    

}
