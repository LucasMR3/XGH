package xgh.service;

import java.io.*;

import static xgh.constants.Constants.fileNameJava;

public class WriteFile {

    public static void main(String[] args) {
        try {
            String content = "import java.util.Scanner;\n" +
                    "\n" +
                    "public class Test {\n" +
                    "\n" +
                    "    Scanner sc = new Scanner(System.in);\n" +
                    "\n" +
                    "    public void main(String[] args) {\n" +
                    "\n" +
                    "\n" +
                    "    }\n" +
                    "}";

            File file = new File(fileNameJava);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content);
            bw.close();

            FileReader read = new FileReader(fileNameJava);
            BufferedReader reader = new BufferedReader(read);
            String line;
            while( (line = reader.readLine()) != null ){
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
