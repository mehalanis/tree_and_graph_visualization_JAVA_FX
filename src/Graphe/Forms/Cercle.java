package Forms;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class Cercle extends StackPane {

    public String val;
    public Circle circle;
    public Label label;
    public static Font font = new Font(18);
    int x, y;

    public Cercle(String val) {
        this.val = val;
        circle = new Circle(22);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        circle.setFill(Color.WHITE);
        label = new Label(val);
        label.setFont(font);
        this.getChildren().add(circle);
        this.getChildren().add(label);
    }

    public void setVal(String val) {
        this.val=val;
        label.setText(val);
    }

    public void setStroke(Color c) {
        circle.setStroke(c);
    }

    public double getX() {
        return this.getLayoutX();
    }

    public double getY() {
        return this.getLayoutY();
    }
     public double getCenterX() {
        return this.getLayoutX()+23;
    }

    public double getCenterY() {
        return this.getLayoutY()+23;
    }
    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

}
