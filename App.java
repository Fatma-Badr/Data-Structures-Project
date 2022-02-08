package com.example.demo3;

import java.io.*;


import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    Button graph = new Button("Graph");

    TextField locationTextField;

String str;

    public void start(Stage primaryStage) throws IOException {
//         FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        StackPane layout = new StackPane();


        VBox vBox = new VBox();
        vBox.setSpacing(6);
        locationTextField = new TextField();
        locationTextField.setPromptText("XML path");
        locationTextField.setFocusTraversable(false);
        vBox.getChildren().add(locationTextField);

        vBox.getChildren().add(xML_consistency);
        vBox.getChildren().add(formatting_XML);
        vBox.getChildren().add(json);
        vBox.getChildren().add(compress);
        vBox.getChildren().add(s1);
        vBox.getChildren().add(s2);
        vBox.getChildren().add(graph);

        layout.getChildren().add(vBox); 
        


        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);


        xML_consistency.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                try {
                    consistency_display(consistensy(locationTextField.getText()));
                    

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
        graph.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    StringBuilder s1;
                    s1 = lisa(locationTextField.getText());
                    String s = s1.toString();

                    writeDotSourceToFile(s);
                    String command = "dot -Tpng /Users/ayahassan/Desktop/demo5/dotsource.dot -o /Users/ayahassan/Desktop/demo5/dotsource.png";
                    Process proc = Runtime.getRuntime().exec(command);
                    Thread.sleep(3000);



                    graph_display();

                } catch (Exception er1) {
                    er1.printStackTrace();

                }
            }
        });
        primaryStage.show();
    }
//     public  void consistency_display(boolean s) {
//         Stage window = new Stage();
//         window.initModality(Modality.APPLICATION_MODAL);
//         BorderPane layout = new BorderPane();
//         window.setTitle("Consistency");
//         Text t = new Text();
//         if(s == true){
//             t.setText("Consistent");
//         }
//         else{
//             t.setText("InConsistent");
//         }
//         layout.setCenter(t);

//         Scene scene = new Scene(layout, 400, 400);
//         window.setScene(scene);
//         window.showAndWait();

//     }
    public  void consistency_display(String s) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        BorderPane layout = new BorderPane();
        Scene scene;
        if(s.equals("Consistent")){
            window.setTitle("Consistency");
            Text t = new Text(s);
//            t.setText(s);
            layout.setCenter(t);
            scene = new Scene(layout, 400, 400);

        }
        else{
            window.setTitle("InConsistent");
            ScrollPane layout2 = new ScrollPane();
            Text t = new Text(s);
            layout2.setContent(t);
            scene = new Scene(layout2, 400, 400);

        }

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
         public  void graph_display() throws IOException {

         Stage window = new Stage();
         window.initModality(Modality.APPLICATION_MODAL);
         window.setTitle("Graph");
         Image image = new Image(new FileInputStream("/Users/ayahassan/Desktop/demo5/dotsource.png"));

         //Setting the image view
         ImageView imageView = new ImageView(image);

         //Setting the position of the image
         imageView.setX(50);
         imageView.setY(25);

         //setting the fit height and width of the image view
         imageView.setFitHeight(455);
         imageView.setFitWidth(500);

         //Setting the preserve ratio of the image view
         imageView.setPreserveRatio(true);



         Group root = new Group(imageView);
         Scene scene = new Scene(root, 600, 500);
         window.setScene(scene);

         window.show();

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
    
    public StringBuilder lisa(String path) {
        StringBuilder sb = new StringBuilder("");  
        sb.append("digraph G {\n");
        graph g = new graph();
        g.conv_to_mat(path);
        g.create_list(path);
        Graph_Node[] adj = g.graph_list();
         for (Graph_Node adjmat1 : adj) {
            for (int i = 0; i < adjmat1.follow.size(); i++) {
                sb.append(adjmat1.name).append("->").append(adjmat1.follow.get(i)).append(";");
            }
        }
        sb.append("\n}");
        return sb;
    }

    class Graph_Node {

        String name;
        ArrayList<String> follow = new ArrayList<String>();
    }

    class graph {

        private Graph_Node adjmat[];

        public void conv_to_mat(String path) {
            xml_tree t = new xml_tree();
            node root = t.convert_to_tree(compress(path));
            this.adjmat = new Graph_Node[root.children.size()];
            for (int i = 0; i < root.children.size(); i++) {
                for (int j = 0; j < ((root.children.get(i)).children).size(); j++) {
                    if (root.children.get(i).children.get(j).node_name.compareTo("id") == 0) {
                        adjmat[i] = new Graph_Node();
                        adjmat[i].name = root.children.get(i).children.get(j).value;

                    }

                }
            }
        }

        public void create_list(String path) {
            xml_tree t = new xml_tree();
            node root = t.convert_to_tree(compress(path));//compress
            String id = "";
            for (int i = 0; i < root.children.size(); i++) {
                for (int j = 0; j < ((root.children.get(i)).children).size(); j++) {
                    if (root.children.get(i).children.get(j).node_name.compareTo("id") == 0) {
                        id = root.children.get(i).children.get(j).value;
                    }
                    if (root.children.get(i).children.get(j).node_name.compareTo("followers") == 0) {
                        for (int k = 0; k < root.children.get(i).children.get(j).children.size(); k++) {
                            node temp = root.children.get(i).children.get(j).children.get(k);
                            for (int n = 0; n < adjmat.length; n++) {
                                if (adjmat[n].name != null) {
                                    if (((adjmat[n].name).compareTo(temp.children.get(0).value) == 0)) {
                                        adjmat[n].follow.add(id);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }

        public Graph_Node[] graph_list() {
            return adjmat;
        }

        public void print_all() {
            for (Graph_Node adjmat1 : adjmat) {
                System.out.print(adjmat1.name + ": ");
                for (int i = 0; i < adjmat1.follow.size(); i++) {
                    System.out.print(adjmat1.follow.get(i) + " ");
                }
                System.out.println();
            }
        }

    }

    public static String formating(String path) {
        if(!detection(path))
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
        static boolean detection(String path) {
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
    
        static String consistensy(String path) {
        if (detection(path)) {
            return "Consistent";
        } else {
            return "inconsistent" + "\nThe correction is\n" + correct(path);
        }
    }
    public static String compress(String path) {
        if(!detection(path))
            return "In-valid XML file";
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
     public static String correct(String p) {
        String s = xmls(p);

        Stack<String> st = new Stack<String>();
        Stack<Integer> in = new Stack<Integer>();
        ArrayList<String> q = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '<') {

                if (s.charAt(i + 1) != '/') {
                    String t = parseTag(s.substring(i + 1));
                    st.push(t);
                    q.add(t);
                    in.push(i);
                } else {
                    String t = parseTag(s.substring(i + 2));
                    // missing opening tag
                    if (st.empty() || !(st.peek().equals(t))) {
                        boolean o = false;
                        for (int y = i - 1; y > 0; y--) {
                            if (s.charAt(y) == '<') {
                                if (s.charAt(y + 1) == '/') {
                                    String g = '<' + parseTag(s.substring(y + 2)) + '>';
                                    int w = s.indexOf(g, 0);
                                    s = s.substring(0, w) + '<' + t + '>' + s.substring(w);
                                    o = true;
                                    i=i+2+t.length();

                                } else {
                                    int m = y + parseTag(s.substring(y + 1)).length() + 1;
                                    s = s.substring(0, m + 1) + '<' + t + '>' + s.substring(m + 1);
                                    o = true;
                                    i=i+2+t.length();

                                }

                            }
                            if (o) {
                                break;
                            }
                        }

                    } else {
                        st.pop();
                        in.pop();
                    }
                }
            }
        }
        if (!st.empty()) {
            while (!st.empty()) {
                String f = st.peek();
                int a = q.indexOf(f) + 1;
                String d = q.get(a);
                String e = "</" + d + '>';
                int x = s.indexOf(e, in.peek());
                int z = x + e.length();
                s = s.substring(0, z + 1) + "</" + f + '>' + s.substring(z + 1);
                st.pop();
                in.pop();
            }
        }
        return s;
    }

//     static String consistensy(String path) {
//         if (detection(path)) {
//             return "Consistent";
//         } else {
//             return "inconsistent" + "\nThe correction is\n" + correct(path);
//         }
//     }

    public static String parseTag(String s) {
        String t = s.substring(0, s.indexOf('>', 0));

        return t;
    }
    public static String covertXML_to_JSON(String p) {
        if(!detection(p))
            return "In-valid XML file";
        StringBuilder r = new StringBuilder();
        String s = xmls(p);
        xml_tree t = new xml_tree();
        node root = t.convert_to_tree(s);
        r.append("{ \n");
//        r.append(root.node_name + ": {" + "\n");
        json(root, r, isArray(root), false, root.get_children().size(), 0);
//        r.append("}" + "\n");
        r.append("}");
        return r.toString();
    }

    public static StringBuilder json(node n, StringBuilder r, boolean f, boolean more, int v, int k) {
        boolean h = isArray(n);

        if (n.get_children().size() != 0) {

            if (n.get_children().size() == 1) {
                if (!f) {
                    r.append("\"" + n.get_node_name() + "\":{\n");
                }
            }
            if (h) {
                r.append("\"" + n.get_node_name() + "\":{\n");
                r.append("\"" + n.get_children().get(0).get_node_name() + "\" : [\n");
            }
            if (!f && !h && n.get_children().size() > 1) {
                r.append("\"" + n.get_node_name() + "\":{\n");
            }
//            if(!f &&n.get_children().size()>1 && v>0)r.append("\"" + n.get_node_name() + "\":{\n");
            int j = 0;
            for (j = 0; j < n.get_children().size(); j++) {
                if (h) {
                    if (n.get_children().get(j).get_children().size() != 0) {
                        r.append("{\n");
                    }
                }

                boolean b = isArray(n);
                int m = n.get_children().size();
                if (j == n.get_children().size() - 1) {
                    more = false;
                } else {
                    more = true;
                }
//                if(h && (n.get_children().indexOf(n.get_children().get(j))<n.get_children().size()-1) &&(j==n.get_children().size()-1 ))more=true;
                json(n.get_children().get(j), r, b, more, m, j);
                if (h) {
                    if (n.get_children().get(j).get_children().size() != 0) {
                        r.append(("}"));
                        if (more) {
                            r.append(",\n");
                        } else {
                            r.append("\n");
                        }
                    }
                }
            }
//            if(!f &&n.get_children().size()>1 && v>0){
//             r.append("},\n");   
//            }
            if (!f && !h && n.get_children().size() > 1) {
                if (k <= v - 2) {
                    more = true;
                }
                if (k == 0) {
                    more = false;
                }
                r.append("}");
                if (more) {
                    r.append(",\n");
                } else {
                    r.append("\n");
                }
            }
            if (n.get_children().size() == 1) {
                if (k <= v - 2) {
                    more = true;
                }
                if (k == 0) {
                    more = false;
                }
//                if(n.get_node_name().equals("followers")) more=false;
                if (!f) {
                    r.append("}");
                    if (more) {
                        r.append(",\n");
                    } else {
                        r.append("\n");
                    }
                }
            }

            if (k <= v - 2) {
                more = true;
            }
            if (k == 0) {
                more = false;
            }
            if (h) {
                r.append("]\n");
                r.append("}");
                if (more) {
                    r.append(",\n");
                } else {
                    r.append("\n");
                }
            }
        } else {
            if (!f) {
                r.append("\"" + n.get_node_name() + "\":");
            }
            if (isNumeric(n.get_value()) == -1) {
                r.append("\"" + n.get_value() + "\" ");
            } else {
                r.append(isNumeric(n.get_value()));
            }
            if (more) {
                r.append(",\n");
            } else {
                r.append("\n");
            }
        }

        return r;
    }

    public static boolean isArray(node n) {
        boolean v = true;
        System.out.println("-----");
        if (n.get_children().size() <= 1) {
            return false;
        }
        String k = n.get_children().get(0).get_node_name();
        for (int i = 0; i < n.get_children().size(); i++) {
            String s = n.get_children().get(i).get_node_name();
            if (!s.equalsIgnoreCase(k)) {
                v = false;
                break;
            }
        }

        return v;
    }

    public static int isNumeric(String str) {
        int n = -1;
        if (str == null) {
            return -1;
        }
        try {
            n = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
        return n;
    }
     private static void  writeDotSourceToFile(String str) throws java.io.IOException
     {
         File temp;
         try {
             temp = File.createTempFile("dorrr",".dot", new File("/Users/ayahassan/Desktop/temp2"));
             FileWriter fout = new FileWriter(temp);
             fout.write(str);
             BufferedWriter br=new BufferedWriter(new FileWriter("dotsource.dot"));
             br.write(str);
             br.flush();
             br.close();
             fout.close();
         }
         catch (Exception e) {
             System.err.println("Error: I/O error while writing the dot source to temp file!");
           //  return null;
         }
         //return temp;
     }
    public static void main(String[] args) {
        launch();
    }
}

class node {

    String node_name;
    String value;
    ArrayList<node> children = new ArrayList<node>();

    public node(String n) {
        node_name = n;
    }

    public node(String n, String v) {
        node_name = n;
        value = v;
    }

    public String get_node_name() {
        return node_name;
    }

    public String get_value() {
        return value;
    }

    public ArrayList<node> get_children() {
        ArrayList<node> temp = new ArrayList<node>();

        for (int i = 0; i < children.size(); i++) {
            temp.add(children.get(i));
        }
        return temp;
    }

    public void set_node_name(String n) {
        node_name = n;
    }

    public void set_value(String v) {
        value = v;
    }

    public void add_child(node n) {
        children.add(n);
    }

}

class xml_tree {

    node root;

    public node get_root() {
        return root;
    }

    public void set_root(node n) {
        root = n;
    }

    public node convert_to_tree(String s) {
        int i;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || s.charAt(i) == '\n' || s.charAt(i) == '\r') {
                continue;
            }
            if (s.charAt(i) == '<' && s.charAt(i + 1) != '/') {
                //opening tag
                String t = parseTag(s.substring(i + 1));
                set_root(new node(t));
                i = s.indexOf('>', i) + 1;
                break;

            }
        }

        for (int j = i; j < s.length(); j++) {
            if (s.charAt(j) == ' ' || s.charAt(j) == '\n' || s.charAt(j) == '\r') {
                continue;
            }
            if (s.charAt(j) == '<' && s.charAt(j + 1) != '/') {
                //opening tag
                String t = parseTag(s.substring(j + 1));
                node p = new node(t);
                get_root().add_child(p);
                insertItschildren(p, s.substring(j + t.length() + 1));
                break;
            }
        }

        return get_root();
    }

    public void insertItschildren(node parent, String s) {

        Stack<node> st = new Stack<node>();
        st.push(get_root());
        st.push(parent);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || s.charAt(i) == '\n' || s.charAt(i) == '\r') {
                continue;
            }
            if (s.charAt(i) == '<') {
                if (s.charAt(i + 1) == '/') {

                    if (st.peek().get_node_name().equals(parseTag(s.substring(i + 2)))) {
                        st.pop();
                        continue;

                    }
                } else {
                    String t = parseTag(s.substring(i + 1));

                    int k = i + t.length() + 1 + 1;
                    String tv = "";

                    while (s.charAt(k) != '<') {
                        if (s.charAt(k) == '\n' || s.charAt(k) == '\r' || s.charAt(k) == ' ') {
                            k++;
                            continue;
                        }
                        tv += s.charAt(k++);

                    }

                    node n = new node(t, tv);
                    if (st.peek().get_value() == null || st.peek().get_value() == "") {
                        parent = st.peek();
                    }
                    parent.add_child(n);
                    st.push(n);
                    i = k - 1;
                }
            }

        }

    }

    public String parseTag(String s) {
        String t = s.substring(0, s.indexOf('>', 0));
        return t;
    }

}
