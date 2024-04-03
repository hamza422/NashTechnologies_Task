package org.example;

import org.javatuples.Quartet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Class to perform operations on patterns.
*/
public class PatternHandler {
    private final List<Quartet<Integer, String, String, Boolean>> patterns;
    private static final Logger LOGGER = LoggerFactory.getLogger(PatternFileHandler.class);

    /**
     * constructor with the file path to load the patterns
     * @param  filePath the path of the file
     */
    public PatternHandler(String filePath){
        this.patterns=PatternFileHandler.loadPatternsFromFile(filePath);
    }

    /**
     * write the patterns into file and store it
     * @param fileName is the given name of a file
     */
    public void storePatternsInFile(String fileName){
        PatternFileHandler.storePatternsIntoFile(fileName,this.patterns);
    }

    /**
     * returns the list of patterns
     */
    public List<Quartet<Integer, String, String, Boolean>> getListOfPatterns(){
        return this.patterns;
    }

    /**
     * add new pattern into the list. If identifier existed not add into the list.
     * @param newPattern tuple contain the data of a pattern
     */
    public void addPattern(Quartet<Integer,String,String,Boolean> newPattern){
        if(newPattern==null){
            return;
        }

        if(checkIfIdentifierExisted(newPattern.getValue0())){
            LOGGER.info("Identifier is already existed, tuple has not been added.");
            return;
        }
        patterns.add(newPattern);
    }

    private boolean checkIfIdentifierExisted(Integer patternIdentifier){
        return getPatternByIdentifier(patternIdentifier).isPresent();
    }


    /**
     * return the pattern by the specified id
     * @param  patternIdentifier the id of the pattern
     */
    public Optional<Quartet<Integer, String, String, Boolean>> getPatternByIdentifier(Integer patternIdentifier){
        if(patterns==null || patternIdentifier==null){
            return Optional.empty();
        }

        return patterns.stream().filter(p ->p.getValue0().equals(patternIdentifier)).findFirst();
    }

    /**
     * return the pattern by the specified pattern name
     * @param  patternName the name of the pattern
     */
    public List<Quartet<Integer, String, String, Boolean>> getPatternByName(String patternName){
        if(patterns==null || patternName == null){
            return null;
        }

        return patterns.stream().filter(p ->p.getValue1().equals(patternName)).collect(Collectors.toList());
    }

    /**
     * return the pattern by the specified pattern path
     * @param  patternPath the path of the pattern
     */
    public List<Quartet<Integer, String, String, Boolean>> getPatternByPath(String patternPath){
        if(patterns==null || patternPath ==null){
            return null;
        }

        return patterns.stream().filter(p ->p.getValue2().equals(patternPath)).collect(Collectors.toList());
    }

    /**
     * return the pattern by the specified pattern called flag
     * @param  calledFlag the called type of the pattern
     */
    public List<Quartet<Integer, String, String, Boolean>> getPatternByCalledFlag(boolean calledFlag){
        if(patterns==null ){
            return null;
        }

        return patterns.stream().filter(p ->p.getValue3().equals(calledFlag)).collect(Collectors.toList());
    }
}
