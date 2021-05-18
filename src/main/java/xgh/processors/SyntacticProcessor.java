package xgh.processors;

public class SyntacticProcessor {

    public String line;
    public String[] stack;
    public int tm;

    public SyntacticProcessor(String line) {
        this.line = line;
        this.stack = this.line.split(" ");
        this.tm = stack.length;
    }

    public void Analyzer() {
        switch (this.stack[0].substring(0, 1)) {
            case "S":
            case ".":
            case "N":
            case "B":
                declarations();
                break;
        }
        switch (this.stack[0]) {
            case "imp":
                imports();
                break;
            case "print":
                prints();
                break;
        }
        System.out.println("\n\n\n");
    }

    private void prints() {
        int i = 1, c = 0;
        if (this.stack[i].equals("(")) {
            i++;

            if (this.stack[i].equals("\"")) {
                System.out.println("localizado aspas");
                for (;i < tm; i++) {
                    //   System.out.println("localizado aspas");
                    if (this.stack[i].charAt(0) == '"') {

                        c = i;
                    }

                }
                System.out.println("fecha aspas na posicao : " + c);
                for (int a = 3; a < c; a++) {
                    System.out.println(this.stack[a]);
                }
                i = c + 1;
            } else {
                atribu(i);
            }
            if (this.stack[tm - 1].equals(")")) {
                System.out.println("fechou o: ) ");
            } else {

                System.out.println("esperado: ) na posisao " + i);
            }
        } else {
            System.out.println("esperado: (");
        }


    }

    private void declarations() {

        System.out.println("tipo da variavel : " + this.stack[0].charAt(0));
        if (this.stack[0].length() <= 1) {
            System.out.println("falta 2 pontos");
        } else {
            if (this.stack[0].charAt(1) == ':') {
                System.out.println("nome da variavel: " + this.stack[1]);
                if (this.stack.length <= 2) {
                } else {
                    if (this.stack[2].equals("=")) {
                        System.out.println("atribuicao de valor");
                        atribu(3);
                    }
                }
            } else {
                System.out.println("falta 2 pontos");
            }
        }
    }

    private void atribu(int i) {
        System.out.println("vamos analizar a variavel: " + this.stack[i]);
        verifres(this.stack[i]);
        if (this.stack.length > (i + 1)) {
            System.out.println("vericando se: " + this.stack[i + 1] + " e operador ");
            verifop(i + 1);
        } else {
            System.out.println("acabou ");
        }
    }

    private void verifop(int s) {

        switch (this.stack[s]) {
            case "*":
            case "/":
            case "+":
            case "-":
                System.out.println("operador: " + this.stack[s] + "");
                atribu(s + 1);
                break;
            default:
                System.out.println("nao e operador");
                break;
        }
    }

    private void verifres(String s) {

        switch (s) {
            case "imp":
            case "N":
            case "S":
            case "B":
            case ".":
            case "while":
            case "print":
            case "if":
            case "el":
            case "ef":
            case "goto":
            case "*":
            case "+":
            case "/":
            case "-":
                System.out.println("palavra reservada:" + s + " nao pode ser nome de varievel");
                break;
        }


    }

    public void imports() {
        System.out.println(stack[0] + "<<<<<importando");
        System.out.println("verificando posisao 0 " + "((" + stack[1].charAt(0) + "))");
        if (stack[1].charAt(0) == '[') {
            System.out.println("abriu colcheites");
            System.out.println("verificando posisao  " + stack[1].length() + "((" + stack[1].substring(stack[1].length() - 1) + "))");
            if (stack[1].endsWith("]")) {
                System.out.println("fechou colcheites");
                System.out.println("link\n" + stack[1].substring(1, stack[1].length() - 1));

            } else {
                System.out.println("nao fechou colcheites");
            }
        } else {
            System.out.println("nao abriu colcheites");
        }

    }
}