package org.example;

import org.javatuples.Quartet;

import java.util.List;

public class Main {
    private static final String DEFAULT_FILEPATH=System.getProperty("user.dir")+"/OutputFiles/";
    private static PatternHandler getPatternHandler() {
        PatternHandler patternHandler=new PatternHandler(DEFAULT_FILEPATH +"Output.txt");


        Quartet<Integer, String, String, Boolean> pattern1 = Quartet.with(44, "myPattern", "src/patterns/Functional.pat", false);
        Quartet<Integer, String, String, Boolean> pattern2 = Quartet.with(45, "anotherPattern", "src/patterns/Another.pat", true);
        Quartet<Integer, String, String, Boolean> pattern3 = Quartet.with(41, "myPattern", "src/patterns/Functional.pat", false);

        patternHandler.addPattern(pattern1);
        patternHandler.addPattern(pattern2);
        patternHandler.addPattern(pattern3);
        return patternHandler;
    }


    public static void main(String[] arg){
        PatternHandler patternHandler = getPatternHandler();

        System.out.println("By id :   "+patternHandler.getPatternByIdentifier(44));
        System.out.println("By Name :   "+patternHandler.getPatternByName("myPattern"));
        System.out.println("By path :   "+patternHandler.getPatternByPath("src/patterns/Functional.pat"));
        System.out.println("By Called :   "+patternHandler.getPatternByCalledFlag(true));
        System.out.println("By Skipped :   "+patternHandler.getPatternByCalledFlag(false));


       patternHandler.storePatternsInFile("output_stored.txt");


    }


}