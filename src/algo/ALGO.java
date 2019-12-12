
package algo;

import ABR_AVL.ABR;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ALGO extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader load=new FXMLLoader();
        load.setLocation(getClass().getResource("index.fxml"));
        load.load();
        indexController i= load.getController();
        // i.set("anis");
        Parent root = load.getRoot();
        
        Scene scene = new Scene(root);

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
