package xgh.processors;

public class LexicalProcessor {
    private int lineCharPos;

    private final String lineG;
    StringBuilder phraseBuilder = new StringBuilder();
    private boolean errors = false;


    public LexicalProcessor(String lineG) {
        this.lineG = lineG;
    }

    // JUNTAR SIMBOLOS E MONTAR A TABELA DE SÍMBOLO
    // ESSE CARA DISPARA ERROS SE NÃO ENCONTRAR NAS PALAVRAS RESERVADAS

    public String symbolTable() {
        System.out.println("LINE: " + lineG);

        while (lineCharPos < lineG.length()) {
            String word = interactingLine(lineG);
/*
            System.out.println("WORD: " + word);
            System.out.println("POS: " + lineCharPos);
*/

            phraseBuilder.append("[FRAG: ").append("( ").append(dictionary(word)).append(" )").append("] ");
            lineCharPos++;
        }

        System.out.println(phraseBuilder);

        if (errors) {
            return null;
        }

        return String.valueOf(phraseBuilder);
    }

    public String dictionary(String word) {

        if (word.startsWith("\""))
            return "String: " + word;

        switch (word) {
            case "(":
            case ")":
                return "parenthesis: " + word;
            case ";":
                return "semicolon: " + word;
            case "imp":
            case "while":
            case "print":
            case "if":
            case "el":
            case "ef":
            case "goto":
            case "scan":
                return "command: " + word;
            case "N":
            case "S":
            case "B":
            case ".":
                return "type: " + word;
            case "*":
            case "+":
            case "/":
            case "-":
                return "operation: " + word;
            default:
                warning("UNEXPECTED " + word);
                return "unexpected: " + word;
        }
    }

    public String interactingLine(String line) {
        StringBuilder temp = new StringBuilder();
        boolean special = false;
//        boolean insideParentheses = false;

        while (lineCharPos < lineG.length()) {
//            if (line.charAt(lineCharPos) == ' ') {
//                warning("SPACE");
//                special = true;
//            }

            if (line.charAt(lineCharPos) == '(') {
//                warning("PARENTHESIS");
//                insideParentheses = true;
                special = true;
            }

            if (line.charAt(lineCharPos) == ')') {
//                warning("PARENTHESIS");
//                insideParentheses = false;
                special = true;
            }


            if (line.charAt(lineCharPos) == ';') {
//                warning("SEMICOLON");
                special = true;
            }

            if (temp.length() > 0 && special) {
                lineCharPos--;
                return String.valueOf(temp);
            }

            temp.append(line.charAt(lineCharPos));
            lineCharPos++;
        }
        return String.valueOf(temp);
    }

    private void warning(String message) {
        System.err.println("ERROR! " + message);
        errors = true;
    }
}