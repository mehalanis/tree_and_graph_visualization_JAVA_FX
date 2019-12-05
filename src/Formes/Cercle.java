/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formes;

import ABR_AVL.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 *
 * @author Mehalanis
 */
public class Cercle extends StackPane {
   public int val;
   public Circle circle; 
   public Label label;
   public static Font font=new Font(18);
   public Cercle(int val){
       this.val=val;
       circle=new Circle(22);
       circle.setStroke(Color.BLACK);
       circle.setStrokeWidth(2);
       circle.setFill(Color.WHITE);
       label=new Label(val+"");
       label.setFont(font);
       this.getChildren().add(circle);
       this.getChildren().add(label);
   }
   public void setVal(int val){
       label.setText(val+"");
   }
   public void setStroke(Color c){
       circle.setStroke(c);
   }
   public void setLayout(int x,int y){
        this.setLayoutX(x);
        this.setLayoutY(y);
   }
}
