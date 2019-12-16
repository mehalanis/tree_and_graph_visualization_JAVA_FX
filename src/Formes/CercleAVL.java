package Formes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Text;


public class CercleAVL extends Cercle {
    private Label balance;
    public CercleAVL(int val,int b) {
        super(val);
        balance=new Label(b+"");
        balance.setPadding(new Insets(-30,0,0,-30));
        this.getChildren().add(balance);
    }
    
    
}
