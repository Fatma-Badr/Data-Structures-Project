package com.example.demo3;

import java.io.*;

import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class App extends Application  {

    Button xML_consistency = new Button("Consistency");
    Button s1 = new Button("Save");
    Button s2 = new Button("Save as");

    Button formatting_XML = new Button("Formatting");
    Button json = new Button("Json");
    Button compress = new Button("Compress");

    TextField locationTextField = new TextField();

String str;

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        StackPane layout = new StackPane();


        VBox vBox = new VBox();
        vBox.setSpacing(6);
        locationTextField = new TextField();
        vBox.getChildren().add(locationTextField);

        vBox.getChildren().add(xML_consistency);
        vBox.getChildren().add(formatting_XML);
        vBox.getChildren().add(json);
        vBox.getChildren().add(compress);
        vBox.getChildren().add(s1);
        vBox.getChildren().add(s2);
        layout.getChildren().add(vBox);


        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);


        xML_consistency.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                try {
                    consistency_display(consistinsy(locationTextField.getText()));

                    } catch (Exception er1) {
                    er1.printStackTrace();

                }
                }

            }
        ));

        compress.setOnMouseClicked(e ->{
            try{
                str = compress(locationTextField.getText());
                conmpress_display(compress(locationTextField.getText()));}
            catch(Exception er1){
                er1.printStackTrace();

            }
        } );
        formatting_XML.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                    try {
                        str = formating(locationTextField.getText());
                        formatting_display(formating(locationTextField.getText()));
//                        json_display(covertXML_to_JSON(locationTextField.getText()));

                    } catch (Exception er1) {
                        er1.printStackTrace();

                    }
            }
        }));
        json.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    str = covertXML_to_JSON((locationTextField.getText()));
                    json_display(covertXML_to_JSON(locationTextField.getText()));

                } catch (Exception er1) {
                    er1.printStackTrace();

                }
            }
        }));


        s1.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                try {
                    save(locationTextField.getText(),str);

                } catch (Exception er1) {
                    er1.printStackTrace();

                }
            }

        }
        ));
        s2.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                try {
                    String[]temp = save_display();
                    SaveAs(str,temp[1],temp[0]);

                } catch (Exception er1) {
                    er1.printStackTrace();

                }
            }

        }
        ));
        primaryStage.show();
    }
    public  void consistency_display(boolean s) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        BorderPane layout = new BorderPane();
        window.setTitle("Consistency");
        Text t = new Text();
        if(s == true){
            t.setText("Consistent");
        }
        else{
            t.setText("InConsistent");
        }
        layout.setCenter(t);

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

    }
    public  void formatting_display(String s) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        ScrollPane layout = new ScrollPane();
        window.setTitle("format");
        Text t = new Text(s);
        layout.setContent(t);

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

    }
    public  void json_display(String s) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        ScrollPane layout = new ScrollPane();
        window.setTitle("json");
        Text t = new Text(s);
        layout.setContent(t);

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

    }

    public  void conmpress_display(String s) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        ScrollPane layout = new ScrollPane();
        window.setTitle("compress");
        Text t = new Text(s);
        layout.setContent(t);
//        layout.fitToWidthProperty();
        t.wrappingWidthProperty().bind(layout.widthProperty());
        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

    }
    public  String[] save_display(){
        String [] arr = new String[2];
        Stage window = new Stage();
        Button sv = new Button("Save");
        sv.setOnMouseClicked(e ->{
            try{
                window.close();
            }
            catch(Exception er1){

            }
        } );
        TextField t = new TextField();
        TextField t1 = new TextField();

        t.setPromptText("Location");
        t1.setPromptText("Name");
        window.initModality(Modality.APPLICATION_MODAL);
        VBox layout = new VBox();
        window.setTitle("Save as");
        layout.getChildren().addAll(t1,t,sv);
        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();
        arr[0] = t1.getText();
        arr[1] = t.getText();


        return arr;
    }  
public static StringBuilder format(node r, String space, StringBuilder s) {
//        s.append(r.key);
        space = space + '\t';
        for (int i = 0; i < r.children.size(); i++) {
            s.append("\r\n").append(space).append('<').append(r.children.get(i).node_name).append('>').append((r.children.get(i).value != null) ? ("\r\n" + space + '\t' + r.children.get(i).value) : "");
            //System.out.println(r.children.get(i).node_name + " "+ r.children.get(i).children.size());
            if (!r.children.get(i).children.isEmpty()) {//!r.children.isEmpty() //r.children.get(i).children.size()
                format(r.children.get(i), space, s); //(Character.toString(s.charAt(s.length()-1))).
            }
            s.append("\r\n").append(space).append('<').append('/').append(r.children.get(i).node_name).append('>');//s.substring(s.length() - 1)).compareTo("\r\n")

        }
        return s;
    }

    public static String formating(String path) {
        if(!consistinsy(path))
            return "In-valid XML file";
        xml_tree t = new xml_tree();
        //System.out.println(compress(path));
        node root = t.convert_to_tree(compress(path));
        //LevelOrderTraversal(root);
        return format(root, "", new StringBuilder('<' + root.node_name + '>')).append("\r\n").append('<').append('/').append(root.node_name).append('>').toString();

    }
    
        public static void save(String path, String text) {
        int i;
        for (i = path.length() - 1; i >= 0; i--) {
            if ((path.charAt(i) == '\\') || (path.charAt(i) == '/')) {
                break;
            }
        }
//        System.out.println(path.charAt(i + 1));
//        System.out.println(path.substring(0, i + 1));
        String newName = path.substring(0, i + 1) + "New File.XML";
//        System.out.println(newName);
//        try {
//            File myObj = new File(newName);
//            myObj.createNewFile();
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
        try {
            FileWriter myWriter = new FileWriter(newName);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SaveAs(String text, String to, String name) {
        char del = 0;
        for (int i = to.length() - 1; i >= 0; i--) {
            if ((to.charAt(i) == '\\') || (to.charAt(i) == '/')) {
                del = to.charAt(i);
                break;
            }
        }
        try {
            FileWriter myWriter = new FileWriter(to + del + name);
            System.out.println(to + del + name);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        static boolean consistinsy(String path) {
        Stack<String> stk = new Stack<>();
        String txt = "";
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                for (int i = 0; i < data.length(); i++) {
                    txt = "";
                    if (data.charAt(i) == '<') {
                        i++;
                        if (data.charAt(i) == '/') {
                            if (!stk.empty()) {
                                i++;
                                while ((i < data.length())&&(data.charAt(i) != '>')) {
                                    txt = txt + data.charAt(i);
                                    i++;
                                }
                                if(i >= data.length())
                                    return false;
                                System.out.println("to pop " + txt);
                                if (txt.compareTo(stk.peek()) == 0) {
                                    stk.pop();
                                    System.out.println("here");
                                } else {
                                    System.out.println("here1");
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            while ((i < data.length())&&(data.charAt(i) != '>')) {
                                txt = txt + data.charAt(i);
                                i++;
                            }
                            if(i >= data.length())
                                    return false;
                            System.out.println("add to stack " + txt);
                            stk.push(txt);

                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (!stk.empty()) {
            return false;
        }
        return true;
    }
    
    public static String compress(String path) {
        String s = "";
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == ' ' || data.charAt(i) == '\n' || data.charAt(i) == '\r') {

                    } else {
                        s = s + data.charAt(i);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return s;
    }
    public static String xmls(String path) {
        String xmls = "";
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                xmls = xmls + data + "\r\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return xmls;

    }
    public static void main(String[] args) {
        launch();
    }
}
}
