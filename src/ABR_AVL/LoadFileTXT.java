package ABR_AVL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoadFileTXT extends Thread {

    public Arbre abr;
    public File file;
    public AnchorPane group;
    public HBox hbox_label;
    private ArrayList<Integer> list;
    private ArrayList<Label> list_label;
    
    public LoadFileTXT(Arbre abr, File file, AnchorPane group, HBox hbox_label) {
        this.abr = abr;
        this.file = file;
        this.group = group;
        this.hbox_label = hbox_label;
        list = new ArrayList<Integer>();
        list_label = new ArrayList<Label>();
        int temp;
        Label label;
        Font font= Font.font("time", FontWeight.BOLD, 18);
        Insets insets=new Insets(0,10,0,0);
        try {
            Scanner scanfile = new Scanner(file);
            while (scanfile.hasNextLine()) {
                try {
                    temp = Integer.parseInt(scanfile.nextLine());
                    list.add(temp);
                    label = new Label("" + temp);
                    
                    label.setFont(font);
                    label.setPadding(insets );
                    label.setTextFill(Color.GREY);
                    list_label.add(label);
                    hbox_label.getChildren().add(label);
                } catch (NumberFormatException ere) {
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadFileTXT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoadFileTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        Node node = null;
        int val = 0;

        insertionAnimation insert;

        insertNode t;
        Color rgb = Color.rgb(232,225,35);
        for(int i=0;i<list.size();i++){
            list_label.get(i).setTextFill(rgb);
            if(abr instanceof AVL){
                node = new NodeAVL(list.get(i));
            }else{
                node = new Node(list.get(i));
            }
            t = new insertNode(node);
            Platform.runLater(t);
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadFileTXT.class.getName()).log(Level.SEVERE, null, ex);
            }
            insert = new insertionAnimation(abr, node, group);
            insert.start();
            try {
                insert.join();
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadFileTXT.class.getName()).log(Level.SEVERE, null, ex);
            }
            list_label.get(i).setTextFill(Color.GREEN);
        }
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                hbox_label.getChildren().clear();
            }
            
        });
        
    }

    public class insertNode implements Runnable {

        Node node;

        public insertNode(Node node) {
            this.node = node;
        }

        @Override
        public void run() {
            group.getChildren().add(node.getCircle(0, 0));
        }

    }
}
