package Graphe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Menu_Graphe_Controller implements Initializable {

    Pane panel;
    Label titre;
    @FXML Button bellman_ford,floyd,Dijkstra,Bellman;
    @FXML
    public void LoadGraphe(ActionEvent e) {
        try {
            panel.getChildren().clear();
            String s=testBtn(e);
            titre.setText(Nom(s));
            Parent root = FXMLLoader.load(getClass().getResource(s+".fxml"));

            panel.getChildren().add(root);
        } catch (IOException ex) {
        }
    }
    private String testBtn(ActionEvent e){
        Button btn=(Button) e.getSource();
        String s="";
        if(btn==bellman_ford) s="Bellman_Ford";
        else if(btn==floyd)s="FloydWarshal";
        else if(btn==Dijkstra)s="Djikstra";
        else if(btn==Bellman)s="Bellman";
        return s;
    }
    private String Nom(String nom){
        switch(nom){
            case "Bellman_Ford":{return "Bellman Ford"; }
            case "FloydWarshal" :{ return "Floyd Warshal";}
            case "Djikstra" :{ return "Dijkstra";}
            case "Bellman" :{ return "Bellman";}
        }
        return "";
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setPanel(Pane panel) {
        this.panel = panel;
    }

    public void setTitre(Label titre) {
        this.titre = titre;
    }

}
