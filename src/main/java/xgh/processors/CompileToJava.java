package xgh.processors;

import java.util.List;

public class CompileToJava {

    private List<String> list;

    public CompileToJava(List<String> list) {
        this.list = list;
    }

//    [FRAG: ( command: scan )] [FRAG: ( parenthesis: ( )] [FRAG: ( type: S )] [FRAG: ( parenthesis: ) )] [FRAG: ( semicolon: ; )]

    public void compileList() {
        for (String i : list) {
            System.out.println("stuff: " + i);
        }

        switchStuff();

    }

    private void switchStuff() {

        String evaluate;

        evaluate = list.get(0);

        String[] stack = evaluate.split("[FRAG]");

        for (String i: stack) {
            System.out.println(i);
        }



    }

}
