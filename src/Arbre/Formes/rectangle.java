package Formes;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class rectangle extends StackPane {
    private Rectangle rectangle;
    public Label label;
    public static Font font;
    public rectangle(int s) {
        if(font==null){
            font=new Font(20);
        }
        rectangle=new Rectangle();
        rectangle.setWidth(35);
        rectangle.setHeight(35);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        label=new Label(s+"");
        label.setFont(font);
        this.getChildren().add(rectangle);
        this.getChildren().add(label);
    }

    public void setLabel(int val) {
        this.label.setText(""+val);
    }
    
    public rectangle getRectangle() {
        return this;
    }
    public void setStroke(Color c){
        rectangle.setStroke(c);
    }
    public rectangle getRectangle(int x,int y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
        return this;
    }
    
}
