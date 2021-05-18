package xgh.processors;

import xgh.constants.Constants;

import java.io.*;
import java.util.List;

public class CompileToPython {

    private List<String> list;

    private String line;

    public CompileToPython(List<String> list) {
        this.list = list;
    }

    public void runEverything(){
        for (String i: list) {
            this.line = i;
            this.run();
        }
    }

    public void run() {
        //[FRAG: ( command: print )] [FRAG: ( parenthesis: ( )] [FRAG: ( String: "Hello World" )] [FRAG: ( parenthesis: ) )] [FRAG: ( semicolon: ; )]
        int b = this.line.length();
        System.out.println(this.line.substring(18, 23));
        if (this.line.startsWith("print", 18)) {
            System.out.println(this.line.substring(70, b));
            int a = this.line.substring(70, b).indexOf(")");
            System.out.println(a);
            System.out.println(this.line.substring(71, 69 + a));
            try {
                File arq = new File(Constants.fileNamePython);
                arq.createNewFile();
                FileWriter fw = new FileWriter(arq, true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("print(" + this.line.substring(71, 69 + a) + ")");
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                System.err.printf("Error: %s.\n",
                        e.getMessage());
            }

        } else if (this.line.startsWith("scan", 18)) {
            try {
                File arq = new File(Constants.fileNamePython);
                arq.createNewFile();
                FileWriter fw = new FileWriter(arq, true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("str=input()");
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                System.err.printf("Error: %s.\n",
                        e.getMessage());
            }

        }

    }
}
