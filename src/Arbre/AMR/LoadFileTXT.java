package AMR;

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

public class LoadFileTXT extends Thread {

    public amr amr;
    public File file;
    public Group group;
    public HBox hbox_label;
    private ArrayList<Integer> list;
    private ArrayList<Label> list_label;

    public LoadFileTXT(amr amr, File file, Group group, HBox hbox_label) {
        this.amr = amr;
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
            Logger.getLogger(TAS.LoadFileTXT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {

            Logger.getLogger(LoadFileTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        int val = 0;

        Color rgb = Color.rgb(232, 225, 35);
        for (int i = 0; i < list.size(); i++) {
            list_label.get(i).setTextFill(rgb);
            amr.insertion(list.get(i));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    amr.Afficher();
                }

            });
            try {
                Thread.sleep(1000);
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
