/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author anispitchou
 */
public class LoadFileTXT extends Thread {

    BTree btree;
    public File file;
    public Group group;
    public HBox hbox_label;
    private ArrayList<Integer> list;
    private ArrayList<Label> list_label;

    public LoadFileTXT(BTree btree, File file, Group group, HBox hbox_label) {
        this.btree = btree;
        this.file = file;
        this.group = group;
        this.hbox_label = hbox_label;
        list = new ArrayList<Integer>();
        list_label = new ArrayList<Label>();
        int temp;
        Label label;
        Font font = Font.font("time", FontWeight.BOLD, 18);
        Insets insets = new Insets(0, 10, 0, 0);
        try {
            Scanner scanfile = new Scanner(file);
            while (scanfile.hasNextLine()) {
                try {
                    temp = Integer.parseInt(scanfile.nextLine());
                    list.add(temp);
                    label = new Label("" + temp);

                    label.setFont(font);
                    label.setPadding(insets);
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
        BTNode node = null;
        int val = 0;

        Color rgb = Color.rgb(232, 225, 35);
        for (int i = 0; i < list.size(); i++) {
            list_label.get(i).setTextFill(rgb);
            btree.insert(list.get(i));

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    btree.Afficher();
                }

            });

            try {
                Thread.sleep(1300);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadFileTXT.class.getName()).log(Level.SEVERE, null, ex);
            }
            list_label.get(i).setTextFill(Color.GREEN);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                hbox_label.getChildren().clear();
            }

        });

    }

}
