import Model.*;
import Model.TableView.UserGameContest;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        ArrayList resultsList = new ArrayList();
        Results result = new Results(0,"Ruslan Negrei","[MATA]",0,0,5,"Math1",10,10,new SchoolClass(6,"A"));
        Results result1 = new Results(0,"Ruslan Negrei","[MATA]",0,0,5,"Math1",20,30,new SchoolClass(6,"A"));
        Results result2 = new Results(0,"Ruslan Negrei","[MATA]",0,0,3,"Math2",30,50,new SchoolClass(6,"C"));
        Results result3 = new Results(0,"Ruslan Negrei","[MATA]",0,0,3,"Math2",40,20,new SchoolClass(7,"A"));
        Results result4 = new Results(0,"Ruslan Negrei","[MATA]",0,1,1,"Math3",20,66,new SchoolClass(7,"B"));
        Results result5 = new Results(1,"Ruslan Negrei1","[MATA]",0,1,1,"Math3",30,100,new SchoolClass(7,"C"));
        Results result6 = new Results(0,"Ruslan Negrei","[MATA]",0,1,2,"Math4",10,30,new SchoolClass(8,"A"));
        Results result7 = new Results(0,"Ruslan Negrei","[MATA]",0,1,3,"Math4",40,20,new SchoolClass(9,"A"));
        resultsList.add(result);
        resultsList.add(result1);
        resultsList.add(result2);
        resultsList.add(result3);
        resultsList.add(result4);
        resultsList.add(result5);
        resultsList.add(result6);
        resultsList.add(result7);
        FileOutputStream fileOut = new FileOutputStream(new File("src/data/attributes/results.ser"));
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(resultsList);
        out.close();
        fileOut.close();

        Admin admin = new Admin(1,"Ruslan Negrei","admin","$2a$10$m3vSVN.tgV75DK3mQjFbNe08Ol/dYnnDJu0wCSvWcK.mw/1Xr8dL.");
        admin.serialize();


        /*Deserialize
        FileInputStream fileInputStream = new FileInputStream(new File("src/data/test/teachers.ser"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        deserializedList = (ArrayList) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        */


        try {
            Font.loadFont(getClass().getResourceAsStream("/assets/Western.otf"), 20);
            GridPane root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));
            Scene scene = new Scene(root,1280, 720);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/css/menu.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
