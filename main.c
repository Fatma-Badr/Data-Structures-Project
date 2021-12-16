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
