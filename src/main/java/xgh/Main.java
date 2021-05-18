package xgh;

import xgh.processors.CompileToPython;
import xgh.processors.LexicalProcessor;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner fileContent;

    public static void main(String[] args) {
        openArchive();
        List<String> list = readAndProcessData();
        compile(list);
        closeArchive();
    }

    private static void openArchive() {
        try {
            fileContent = new Scanner(Paths.get("src/main/resources/test.xgh"));
        } catch (IOException errIO) {
            System.err.println("Error while opening file");
        }
    }

    public static String processData(String line) {
        LexicalProcessor lexicalProcessor = new LexicalProcessor(line);
        return lexicalProcessor.symbolTable();
//        SyntacticProcessor syntacticProcessor = new SyntacticProcessor(line);
//        syntacticProcessor.Analyzer();
    }

    private static void compile(List<String> list){
//        CompileToJava compileToJava = new CompileToJava(list);
//        compileToJava.compileList();

        CompileToPython compileToPython = new CompileToPython(list);
        compileToPython.runEverything();
    }

    private static List<String> readAndProcessData() {
        List<String> list = new ArrayList<>();
        try {
            while (fileContent.hasNext()) {
                String a = fileContent.nextLine();
                if (a == null){
                    return null;
                }
                list.add(processData(a));
            }
        } catch (Exception err) {
            System.err.println("Error " + err);
        }

        return list;
    }

    private static void closeArchive() {
        if (fileContent != null)
            fileContent.close();
    }
}