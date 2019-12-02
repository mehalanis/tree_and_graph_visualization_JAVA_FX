/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABR_AVL;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author anispitchou
 */
 public class Find extends Thread{
        private Node node;
        private Label label;
        private int val;

    public Find(Node node, Label label, int val) {
        this.node = node;
        this.label = label;
        this.val = val;
    }
    
    @Override
    public void run() {
        Node n=find(node,val);
       // label.setText(((n!=null)?"trouve "+val:"pas trouve"+val));
       try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        n.setCircleColor(Color.BLACK);
        
    }
    public Node find(Node t,int find){
       if(t == null) return null;
        t.setCircleColor(Color.YELLOW);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        if(t.getVal()==find){
            t.setCircleColor(Color.GREEN);
            return t;
        }else{
            t.setCircleColor(Color.BLACK);
            if(find<t.getVal()){
                return find(t.getFG(),find);
            }else{
                return find(t.getFD(),find);
            }
        }
    }
    
}
