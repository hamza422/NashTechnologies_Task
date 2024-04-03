package org.example;

import org.javatuples.Quartet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Class to perform I/0 file operations for patterns.
 */
public class PatternFileHandler {
    private static final String DEFAULT_FILEPATH=System.getProperty("user.dir")+"/OutputFiles/";
    private static final Logger LOGGER = LoggerFactory.getLogger(PatternFileHandler.class);

    /**
     * load the file and return the list of patterns
     * @param filePath is the given path of a file
     */
    public static List<Quartet<Integer, String, String, Boolean>> loadPatternsFromFile(String filePath){
             if(ifFileIsNotPresent(filePath)){
                 return new ArrayList<>();
             }

             try(Stream<String> lines= Files.lines(Paths.get(filePath))){
                 return lines
                         .map(line -> line.split(","))
                         .filter(parts->parts.length==4)
                         .map(parts-> Objects.requireNonNull(createPatternTuple(parts)))
                         .collect(Collectors.toList());
             }
             catch (IOException ex){
                 LOGGER.error("An error occurred during loading file", ex.getCause());
             }

        return new ArrayList<>();
    }

    private static Quartet<Integer, String, String, Boolean> createPatternTuple(String[] pattern) {
        try{
            int value0= Integer.parseInt(pattern[0]);
            String value1= pattern[1].trim();
            String value2= pattern[2].trim();
            boolean value3= Boolean.parseBoolean(pattern[3]);
            return Quartet.with(value0,value1,value2,value3);
        }catch(NumberFormatException ex){
            LOGGER.error("An error occurred during creating Tuple");
            throw new DataNotAssignedToTuple(ex.getMessage());
        }
    }

    /**
     * write the patterns into file and store it
     * @param fileName is the given name of a file
     * @param patterns is the list of patterns need to write into file.
     */
    public static void storePatternsIntoFile(String fileName, List<Quartet<Integer, String, String, Boolean>> patterns){
        try {
            List<String> lines=patterns.stream()
                    .map(PatternFileHandler::formatQuartetAsString)
                    .collect(Collectors.toList());

            Files.write(Paths.get(DEFAULT_FILEPATH+fileName),lines);

            LOGGER.info("Pattens have been stored to " + DEFAULT_FILEPATH+fileName);
        }
        catch (IOException ex){
            LOGGER.error("An error occurred during storing file", ex.getCause());
        }
    }

    private static String formatQuartetAsString(Quartet<Integer, String, String, Boolean> pattern){
        return pattern.getValue0()+","+pattern.getValue1()+","+pattern.getValue2()+","+pattern.getValue3();
    }


    private static boolean ifFileIsNotPresent(String filePath){
        if(filePath==null ){
            LOGGER.info("File path is not defined");
            return true;
        }
        Path path= Paths.get(filePath);

        if(!Files.exists(path)){
            LOGGER.info("File is not present");
            return true;
        }

        return false;
    }

    private static class DataNotAssignedToTuple extends NumberFormatException{
        public DataNotAssignedToTuple(String message){
            super(message);
        }
    }
}
